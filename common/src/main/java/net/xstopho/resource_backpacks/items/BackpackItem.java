package net.xstopho.resource_backpacks.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.xstopho.resource_backpacks.util.BackpackInventory;
import net.xstopho.resource_backpacks.util.BackpackLevel;

public class BackpackItem extends BlockItem {

    private final BackpackLevel backpackLevel;

    public BackpackItem(Block block, BackpackLevel backpackLevel, Properties properties) {
        super(block, properties);
        this.backpackLevel = backpackLevel;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        BackpackInventory backpackInventory = new BackpackInventory(player.getItemInHand(hand), backpackLevel.getSize());

        if (!level.isClientSide) {
            player.openMenu(new SimpleMenuProvider((i, inventory, player1) -> new ShulkerBoxMenu(i, inventory, backpackInventory), Component.literal("Test Backpack")));
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
