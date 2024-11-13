package net.xstopho.resource_backpacks.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.xstopho.resource_backpacks.screen.BackpackMenu;
import net.xstopho.resource_backpacks.util.BackpackInventory;
import net.xstopho.resource_backpacks.util.BackpackLevel;

public class BackpackItem extends BlockItem {

    private final BackpackLevel backpackLevel;

    public BackpackItem(Block block, BackpackLevel backpackLevel, Properties properties) {
        super(block, properties.useBlockDescriptionPrefix());
        this.backpackLevel = backpackLevel;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        BackpackInventory backpackInventory = new BackpackInventory(player.getItemInHand(hand), backpackLevel.getSize());

        if (!level.isClientSide) {
            player.openMenu(getMenuProvider(backpackInventory));
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private MenuProvider getMenuProvider(BackpackInventory backpackInventory) {
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
}
