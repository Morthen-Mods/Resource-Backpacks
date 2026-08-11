package net.morthen.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.morthen.resource_backpacks.modifier.EntityModifier;
import net.morthen.resource_backpacks.network.payloads.*;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        BackpackConstants.commonInit();
        registerServerPayloads();

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> EntityModifier.modifyEntities(entity));
    }

    private void registerServerPayloads() {
        PayloadTypeRegistry.serverboundPlay().register(OpenBackpackPayload.TYPE, OpenBackpackPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(OpenBackpackPayload.TYPE, (payload, context) -> OpenBackpackPayload.handle(payload, context.player()));

        PayloadTypeRegistry.serverboundPlay().register(EnderChestRequestPayload.TYPE, EnderChestRequestPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(EnderChestRequestPayload.TYPE, (payload, context) -> EnderChestRequestPayload.handle(payload, context.player()));

        PayloadTypeRegistry.serverboundPlay().register(SyncCreativeSlotPayload.TYPE, SyncCreativeSlotPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SyncCreativeSlotPayload.TYPE, (payload, context) -> SyncCreativeSlotPayload.handle(payload, context.player()));

        PayloadTypeRegistry.clientboundPlay().register(EnderChestResponsePayload.TYPE, EnderChestResponsePayload.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(SyncEntityBackpackPayload.TYPE, SyncEntityBackpackPayload.CODEC);
    }
}
