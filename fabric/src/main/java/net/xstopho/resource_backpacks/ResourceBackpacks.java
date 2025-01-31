package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.client.slot.BackpackHolderDeprecated;
import net.xstopho.resource_backpacks.network.payloads.EnderChestRequestPayload;
import net.xstopho.resource_backpacks.network.payloads.EnderChestResponsePayload;
import net.xstopho.resource_backpacks.network.payloads.OpenBackpackPayload;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        BackpackConstants.commonInit();
        registerServerPayloads();

        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            if (oldPlayer.getServer().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
                BackpackHolder.restorePlayerBackpack(oldPlayer, newPlayer);
            }
        });

        //TODO: remove with next update
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.player;
            ItemStack backpack = ((BackpackHolderDeprecated) player.getInventory()).resource_backpack$getBackpack();

            if (!backpack.isEmpty()) {
                player.displayClientMessage(Component.literal("Don't run away, you dropped your Backpack by accident!"), false);
                ItemEntity backpackEntity = new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), backpack);
                backpackEntity.setPickUpDelay(100);

                player.level().addFreshEntity(backpackEntity);
                ((BackpackHolderDeprecated) player.getInventory()).resource_backpack$setBackpack(ItemStack.EMPTY);
            }
        });
    }

    private void registerServerPayloads() {
        PayloadTypeRegistry.playC2S().register(OpenBackpackPayload.TYPE, OpenBackpackPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(OpenBackpackPayload.TYPE, (payload, context) -> OpenBackpackPayload.handle(payload, context.player()));

        PayloadTypeRegistry.playC2S().register(EnderChestRequestPayload.TYPE, EnderChestRequestPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(EnderChestRequestPayload.TYPE, (payload, context) -> EnderChestRequestPayload.handle(payload, context.player()));

        PayloadTypeRegistry.playS2C().register(EnderChestResponsePayload.TYPE, EnderChestResponsePayload.CODEC);
    }
}
