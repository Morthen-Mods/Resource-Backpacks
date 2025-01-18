package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.xstopho.resource_backpacks.network.payloads.EnderChestRequestPayload;
import net.xstopho.resource_backpacks.network.payloads.EnderChestResponsePayload;
import net.xstopho.resource_backpacks.network.payloads.OpenBackpackPayload;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        BackpackConstants.commonInit();
        registerServerPayloads();
    }

    private void registerServerPayloads() {
        PayloadTypeRegistry.playC2S().register(OpenBackpackPayload.TYPE, OpenBackpackPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(OpenBackpackPayload.TYPE, (payload, context) -> OpenBackpackPayload.handle(payload, context.player()));

        PayloadTypeRegistry.playC2S().register(EnderChestRequestPayload.TYPE, EnderChestRequestPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(EnderChestRequestPayload.TYPE, (payload, context) -> EnderChestRequestPayload.handle(payload, context.player()));

        PayloadTypeRegistry.playS2C().register(EnderChestResponsePayload.TYPE, EnderChestResponsePayload.CODEC);
    }
}
