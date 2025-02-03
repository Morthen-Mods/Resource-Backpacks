package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.level.GameRules;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.modifier.EntityModifier;
import net.xstopho.resource_backpacks.network.payloads.*;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        BackpackConstants.commonInit();
        registerServerPayloads();

        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            if (newPlayer.getServer().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) || newPlayer.isCreative() || newPlayer.isSpectator()) {
                BackpackHolder.restorePlayerBackpack(oldPlayer, newPlayer);
            }
        });

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> EntityModifier.modifyEntities(entity));

        //TODO: remove with next update
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            BackpackForRemoval.sendPlayerMessage(handler.getPlayer());
        });
    }

    private void registerServerPayloads() {
        PayloadTypeRegistry.playC2S().register(OpenBackpackPayload.TYPE, OpenBackpackPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(OpenBackpackPayload.TYPE, (payload, context) -> OpenBackpackPayload.handle(payload, context.player()));

        PayloadTypeRegistry.playC2S().register(EnderChestRequestPayload.TYPE, EnderChestRequestPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(EnderChestRequestPayload.TYPE, (payload, context) -> EnderChestRequestPayload.handle(payload, context.player()));

        PayloadTypeRegistry.playC2S().register(SyncCreativeSlotPayload.TYPE, SyncCreativeSlotPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SyncCreativeSlotPayload.TYPE, (payload, context) -> SyncCreativeSlotPayload.handle(payload, context.player()));

        PayloadTypeRegistry.playS2C().register(EnderChestResponsePayload.TYPE, EnderChestResponsePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SyncEntityBackpackPayload.TYPE, SyncEntityBackpackPayload.CODEC);
    }
}
