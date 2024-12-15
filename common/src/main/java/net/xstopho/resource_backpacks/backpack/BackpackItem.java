package net.xstopho.resource_backpacks.backpack;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.xstopho.resource_backpacks.BackpackConfig;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.tooltip.BackpackTooltipComponent;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;
import net.xstopho.resource_backpacks.screen.BackpackMenu;
import net.xstopho.resource_backpacks.util.BackpackInventory;
import net.xstopho.resource_backpacks.util.BackpackLevel;

import java.util.List;
import java.util.Optional;

public class BackpackItem extends BlockItem {

    private final BackpackLevel backpackLevel;

    public BackpackItem(Block block, BackpackLevel backpackLevel, Properties properties) {
        super(block, properties.useBlockDescriptionPrefix()
                .component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)
                .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST).build()));
        this.backpackLevel = backpackLevel;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide && BackpackConfig.OPEN_FROM_INVENTORY.get()) {
            player.openMenu(getMenuProvider(player.getItemInHand(hand)));
        }

        return InteractionResult.CONSUME;
    }

    public MenuProvider getMenuProvider(ItemStack stack) {
        BackpackInventory backpackInventory = new BackpackInventory(stack, backpackLevel.getSize());

        return switch(backpackLevel) {
            case LEATHER -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.leatherMenu(i, inventory, backpackInventory), Component.translatable("block.resource_backpacks.backpack_leather"));
            case COPPER -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.copperMenu(i, inventory, backpackInventory), Component.translatable("block.resource_backpacks.backpack_copper"));
            case GOLD -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.goldMenu(i, inventory, backpackInventory), Component.translatable("block.resource_backpacks.backpack_gold"));
            case IRON -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.ironMenu(i, inventory, backpackInventory), Component.translatable("block.resource_backpacks.backpack_iron"));
            case DIAMOND -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.diamondMenu(i, inventory, backpackInventory), Component.translatable("block.resource_backpacks.backpack_diamond"));
            case NETHERITE -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.netheriteMenu(i, inventory, backpackInventory), Component.translatable("block.resource_backpacks.backpack_netherite"));
            case END -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.endMenu(i, inventory, player.getEnderChestInventory()), Component.translatable("block.resource_backpacks.backpack_end"));
            default -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.defaultMenu(i, inventory, backpackInventory), Component.literal("Default Backpack"));
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
        return isShiftDown() ? Optional.of(new BackpackTooltipComponent(content, backpackLevel)) : Optional.empty();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        if (!isShiftDown()) {
            tooltip.add(KeyMappingRegistry.SHOW_COMPACT_PREVIEW.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)
                    .append(Component.literal(": "))
                    .append(Component.translatable("tooltip.resource_backpacks.info.compact_preview").withStyle(ChatFormatting.WHITE)));

        } else if (isShiftDown() && !isAltDown()){
            tooltip.add(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)
                    .append(Component.literal(": "))
                    .append(Component.translatable("tooltip.resource_backpacks.info.inventory_preview").withStyle(ChatFormatting.WHITE)));
        }

        super.appendHoverText(stack, context, tooltip, tooltipFlag);
    }

    private boolean isShiftDown() {
        return BackpackConstants.hasKeyDown(KeyMappingRegistry.SHOW_COMPACT_PREVIEW);
    }

    private boolean isAltDown() {
        return BackpackConstants.hasKeyDown(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW);
    }
}
