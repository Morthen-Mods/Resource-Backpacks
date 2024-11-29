package net.xstopho.resource_backpacks.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class BackpackNetwork {

    public static void initServerPayloads() {
        PayloadTypeRegistry.playC2S().register(OpenBackpackPayload.TYPE, OpenBackpackPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(OpenBackpackPayload.TYPE, OpenBackpackPayload::apply);

        PayloadTypeRegistry.playC2S().register(EnderChestRequestPayload.TYPE, EnderChestRequestPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(EnderChestRequestPayload.TYPE, EnderChestRequestPayload::apply);

        PayloadTypeRegistry.playS2C().register(EnderChestResponsePayload.TYPE, EnderChestResponsePayload.CODEC);
    }

    public static void initClientPayloads() {
        ClientPlayNetworking.registerGlobalReceiver(EnderChestResponsePayload.TYPE, EnderChestResponsePayload::apply);
    }
}
