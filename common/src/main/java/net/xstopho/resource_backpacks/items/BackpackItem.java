package net.xstopho.resource_backpacks.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.xstopho.resource_backpacks.BackpackConfig;
import net.xstopho.resource_backpacks.screen.BackpackMenu;
import net.xstopho.resource_backpacks.util.BackpackInventory;
import net.xstopho.resource_backpacks.util.BackpackLevel;

public class BackpackItem extends BlockItem implements Equipable {

    private final BackpackLevel backpackLevel;

    public BackpackItem(Block block, BackpackLevel backpackLevel, Properties properties) {
        super(block, properties.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
        this.backpackLevel = backpackLevel;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (!level.isClientSide && BackpackConfig.OPEN_FROM_INVENTORY.get()) {
            player.openMenu(getMenuProvider(stack));
        }

        return InteractionResultHolder.pass(stack);
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
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }
}
