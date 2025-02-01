package net.xstopho.resource_backpacks;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.client.slot.BackpackHolderDeprecated;

public class BackpackForRemoval {

    public static void sendPlayerMessage(Player entity) {
        ServerPlayer player = (ServerPlayer) entity;
        ItemStack backpack = ((BackpackHolderDeprecated) player.getInventory()).resource_backpack$getBackpack();

        if (!backpack.isEmpty()) {
            player.displayClientMessage(Component.literal("Don't run away, you dropped your Backpack by accident!"), false);
            ItemEntity backpackEntity = new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), backpack);
            backpackEntity.setPickUpDelay(100);

            player.level().addFreshEntity(backpackEntity);
            ((BackpackHolderDeprecated) player.getInventory()).resource_backpack$setBackpack(ItemStack.EMPTY);
        }
    }
}
