package net.xstopho.resource_backpacks.backpack;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.xstopho.resource_backpacks.backpack.tooltip.CompactTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.InventoryTooltipComponent;
import net.xstopho.resource_backpacks.backpack.util.BackpackInventory;
import net.xstopho.resource_backpacks.backpack.util.BackpackLevel;
import net.xstopho.resource_backpacks.client.screen.BackpackMenu;
import net.xstopho.resource_backpacks.client.util.BackpackClientUtils;
import net.xstopho.resource_backpacks.config.common.BackpackConfig;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

import java.util.List;
import java.util.Optional;

public class BackpackItem extends BlockItem {

    private final BackpackLevel backpackLevel;

    public BackpackItem(Block block, BackpackLevel backpackLevel, Properties properties) {
        super(block, properties.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
        this.backpackLevel = backpackLevel;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (!level.isClientSide && BackpackConfig.openFromInventory) {
            player.openMenu(getMenuProvider(stack));
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();

        if (player != null && (player.isCrouching() || !BackpackConfig.openFromInventory)) {
            super.useOn(context);
        }

        return InteractionResult.PASS;
    }

    public MenuProvider getMenuProvider(ItemStack stack) {
        BackpackInventory backpackInventory = new BackpackInventory(stack, backpackLevel);

        return switch(backpackLevel) {
            case LEATHER -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.leatherMenu(i, inventory, backpackInventory, false), Component.translatable("block.resource_backpacks.backpack_leather"));
            case COPPER -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.copperMenu(i, inventory, backpackInventory, false), Component.translatable("block.resource_backpacks.backpack_copper"));
            case GOLD -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.goldMenu(i, inventory, backpackInventory, false), Component.translatable("block.resource_backpacks.backpack_gold"));
            case IRON -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.ironMenu(i, inventory, backpackInventory, false), Component.translatable("block.resource_backpacks.backpack_iron"));
            case DIAMOND -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.diamondMenu(i, inventory, backpackInventory, false), Component.translatable("block.resource_backpacks.backpack_diamond"));
            case NETHERITE -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.netheriteMenu(i, inventory, backpackInventory, false), Component.translatable("block.resource_backpacks.backpack_netherite"));
            case END -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.endMenu(i, inventory, player.getEnderChestInventory(), false), Component.translatable("block.resource_backpacks.backpack_end"));
        };
    }

    public BackpackLevel getBackpackLevel() {
        return backpackLevel;
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;

    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        ItemContainerContents content = stack.get(DataComponents.CONTAINER);
        Optional<TooltipComponent> tooltipComponent = Optional.empty();

        if (enableCompactPreview()) {
            tooltipComponent = Optional.of(new CompactTooltipComponent(content, backpackLevel));

        } else if (enableInventoryPreview()) {
            tooltipComponent = Optional.of(new InventoryTooltipComponent(content, backpackLevel));
        }

        return tooltipComponent;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {

        if (!enableCompactPreview() && !enableInventoryPreview()) {
            if (!KeyMappingRegistry.SHOW_COMPACT_PREVIEW.isUnbound()) {
                tooltip.add(KeyMappingRegistry.SHOW_COMPACT_PREVIEW.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)
                        .append(Component.literal(": "))
                        .append(Component.translatable("tooltip.resource_backpacks.info.compact_preview").withStyle(ChatFormatting.WHITE)));
            }

            if (!KeyMappingRegistry.SHOW_INVENTORY_PREVIEW.isUnbound()) {
                tooltip.add(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)
                        .append(Component.literal(": "))
                        .append(Component.translatable("tooltip.resource_backpacks.info.inventory_preview").withStyle(ChatFormatting.WHITE)));
            }
        }

        super.appendHoverText(stack, context, tooltip, tooltipFlag);
    }

    private boolean enableCompactPreview() {
        return BackpackClientUtils.hasKeyDown(KeyMappingRegistry.SHOW_COMPACT_PREVIEW);
    }

    private boolean enableInventoryPreview() {
        return BackpackClientUtils.hasKeyDown(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW);
    }
}
