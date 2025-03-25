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
import java.util.stream.Stream;

//TODO: rework this to make it more simple
public final class BackpackContainerComponent implements TooltipProvider {
    public static final Codec<BackpackContainerComponent> CODEC = BackpackSlot.CODEC.sizeLimitedListOf(256)
            .xmap(BackpackContainerComponent::fromSlots, BackpackContainerComponent::asSlots);

    public static final StreamCodec<RegistryFriendlyByteBuf, BackpackContainerComponent> STREAM_CODEC =
            ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list(256)).map(BackpackContainerComponent::new, container -> container.items);

    public static final BackpackContainerComponent EMPTY = new BackpackContainerComponent(NonNullList.create());

    private final NonNullList<ItemStack> items;

    public BackpackContainerComponent(ItemContainerContents container) {
        this(container.stream().toList());
    }

    private BackpackContainerComponent(NonNullList<ItemStack> items) {
        if (items.size() > 256) throw new IllegalArgumentException("Too many items");

        this.items = items;
    }

    private BackpackContainerComponent(int size) {
        this(NonNullList.withSize(size, ItemStack.EMPTY));
    }

    private BackpackContainerComponent(List<ItemStack> items) {
        this(items.size());

        for (int index = 0; index < items.size(); index++) {
            this.items.set(index, items.get(index));
        }
    }

    public static BackpackContainerComponent fromItems(List<ItemStack> items) {
        if (items.isEmpty()) return EMPTY;
        else {
            return new BackpackContainerComponent(items);
        }
    }

    private static BackpackContainerComponent fromSlots(List<BackpackSlot> slots) {
        OptionalInt optional = slots.stream().mapToInt(BackpackSlot::index).max();
        BackpackContainerComponent component = EMPTY;
        if (optional.isPresent()) {
            component = new BackpackContainerComponent(NonNullList.withSize(optional.getAsInt() + 1, ItemStack.EMPTY));
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

    public Stream<ItemStack> stream() {
        return this.items.stream().map(ItemStack::copy);
    }

    public void copyInto(NonNullList<ItemStack> list) {
        for(int i = 0; i < list.size(); ++i) {
            ItemStack itemstack = i < this.items.size() ? this.items.get(i) : ItemStack.EMPTY;
            list.set(i, itemstack.copy());
        }
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
