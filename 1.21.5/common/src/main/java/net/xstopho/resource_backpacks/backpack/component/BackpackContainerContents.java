package net.xstopho.resource_backpacks.backpack.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.component.TooltipProvider;
import net.xstopho.resource_backpacks.client.util.BackpackClientUtils;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Consumer;

//TODO: make it more readable and eventually shrink the code a bit
public final class BackpackContainerContents implements TooltipProvider {
    public static final Codec<BackpackContainerContents> CODEC = BackpackSlot.CODEC.sizeLimitedListOf(256)
            .xmap(BackpackContainerContents::fromSlots, BackpackContainerContents::asSlots);

    public static final StreamCodec<RegistryFriendlyByteBuf, BackpackContainerContents> STREAM_CODEC =
            ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list(256)).map(BackpackContainerContents::new, BackpackContainerContents::toList);


    public static final BackpackContainerContents EMPTY = new BackpackContainerContents(NonNullList.create());
    private final NonNullList<ItemStack> items;
    private final int hashCode;

    //TODO: remove with later update
    @Deprecated(forRemoval = true)
    public BackpackContainerContents(ItemContainerContents container) {
        this(container.stream().toList());
    }

    public BackpackContainerContents(List<ItemStack> items) {
        if (items.size() > 256) throw new IllegalArgumentException("Too many items");

        this.items = NonNullList.withSize(items.size(), ItemStack.EMPTY);
        this.hashCode = ItemStack.hashStackList(items);

        for (int index = 0; index < items.size(); index++) {
            this.items.set(index, items.get(index));
        }
    }

    private static BackpackContainerContents fromSlots(List<BackpackSlot> slots) {
        OptionalInt optional = slots.stream().mapToInt(BackpackSlot::index).max();
        BackpackContainerContents component = EMPTY;
        if (optional.isPresent()) {
            component = new BackpackContainerContents(NonNullList.withSize(optional.getAsInt() + 1, ItemStack.EMPTY));
            for (BackpackSlot slot : slots) {
                component.items.set(slot.index(), slot.stack());
            }
        }

        return component;
    }

    private List<BackpackSlot> asSlots() {
        List<BackpackSlot> slots = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).isEmpty()) {
                slots.add(new BackpackSlot(i, items.get(i)));
            }
        }

        return slots;
    }

    public List<ItemStack> toList() {
        return this.items.stream().map(ItemStack::copy).toList();
    }

    public void copyInto(NonNullList<ItemStack> list) {
        for(int i = 0; i < list.size(); ++i) {
            ItemStack stack = ItemStack.EMPTY;
            if (i < this.items.size()) {
                stack = this.items.get(i);
            }
            list.set(i, stack.copy());
        }
    }

    public boolean equals(Object other) {
        if (this == other) return true;

        if (other instanceof BackpackContainerContents container) {
            return ItemStack.listMatches(this.items, container.items);
        }

        return false;
    }

    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public void addToTooltip(Item.TooltipContext tooltipContext, Consumer<Component> consumer, TooltipFlag tooltipFlag, DataComponentGetter dataComponentGetter) {
        if (!BackpackClientUtils.enableCompactPreview() && !BackpackClientUtils.enableInventoryPreview()) {
            if (!KeyMappingRegistry.SHOW_COMPACT_PREVIEW.isUnbound()) {
                consumer.accept(KeyMappingRegistry.SHOW_COMPACT_PREVIEW.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)
                        .append(Component.literal(": "))
                        .append(Component.translatable("tooltip.resource_backpacks.info.compact_preview").withStyle(ChatFormatting.WHITE)));
            }

            if (!KeyMappingRegistry.SHOW_INVENTORY_PREVIEW.isUnbound()) {
                consumer.accept(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)
                        .append(Component.literal(": "))
                        .append(Component.translatable("tooltip.resource_backpacks.info.inventory_preview").withStyle(ChatFormatting.WHITE)));
            }
        }
    }

    record BackpackSlot(int index, ItemStack stack) {
        public static final Codec<BackpackSlot> CODEC = RecordCodecBuilder.create(slot ->
                slot.group(Codec.intRange(0, 256).fieldOf("slot").forGetter(BackpackSlot::index),
                        ItemStack.CODEC.fieldOf("item").forGetter(BackpackSlot::stack))
                        .apply(slot, BackpackSlot::new));
    }
}
