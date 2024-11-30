package net.xstopho.resource_backpacks.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class BackpackNetwork {

    public static void initPayloads() {
        PayloadTypeRegistry.playC2S().register(OpenBackpackPayload.PACKET_TYPE, OpenBackpackPayload.PACKET_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(OpenBackpackPayload.PACKET_TYPE, OpenBackpackPayload::apply);

        PayloadTypeRegistry.playC2S().register(EnderChestRequestPayload.TYPE, EnderChestRequestPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(EnderChestRequestPayload.TYPE, EnderChestRequestPayload::apply);

        PayloadTypeRegistry.playS2C().register(EnderChestResponsePayload.TYPE, EnderChestResponsePayload.CODEC);
    }

    public static void initClientPayloads() {
        ClientPlayNetworking.registerGlobalReceiver(EnderChestResponsePayload.TYPE, EnderChestResponsePayload::apply);
    }
}
