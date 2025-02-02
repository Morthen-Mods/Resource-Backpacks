package net.xstopho.resource_backpacks.network;

import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.SimpleChannel;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.ResourceBackpacks;
import net.xstopho.resource_backpacks.network.payloads.EnderChestRequestPayload;
import net.xstopho.resource_backpacks.network.payloads.EnderChestResponsePayload;
import net.xstopho.resource_backpacks.network.payloads.OpenBackpackPayload;
import net.xstopho.resource_backpacks.network.payloads.SyncEntityBackpackPayload;

public class BackpackNetworkRegistry {

    public static SimpleChannel registerPayloads() {
        SimpleChannel channel = ChannelBuilder.named(BackpackConstants.of("backpack_network"))
                .acceptedVersions(Channel.VersionTest.exact(1))
                .networkProtocolVersion(1)
                .simpleChannel();

        ResourceBackpacks.NETWORK = channel;

        channel.messageBuilder(OpenBackpackPayload.class, 0, NetworkDirection.PLAY_TO_SERVER)
                .decoder(OpenBackpackPayload.CODEC::decode)
                .encoder((payload, byteBuf) -> OpenBackpackPayload.CODEC.encode(byteBuf, payload))
                .consumerNetworkThread((payload, context) -> {
                    context.enqueueWork(() -> OpenBackpackPayload.handle(payload, context.getSender()));
                }).add();

        channel.messageBuilder(EnderChestRequestPayload.class, 1, NetworkDirection.PLAY_TO_SERVER)
                .decoder(EnderChestRequestPayload.CODEC::decode)
                .encoder((payload, byteBuf) -> EnderChestRequestPayload.CODEC.encode(byteBuf, payload))
                .consumerNetworkThread((payload, context) -> {
                    context.enqueueWork(() -> EnderChestRequestPayload.handle(payload, context.getSender()));
                }).add();

        channel.messageBuilder(EnderChestResponsePayload.class, 2, NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnderChestResponsePayload.CODEC::decode)
                .encoder((payload, byteBuf) -> EnderChestResponsePayload.CODEC.encode(byteBuf, payload))
                .consumerNetworkThread((payload, context) -> {
                    context.enqueueWork(() -> EnderChestResponsePayload.handle(payload));
                }).add();

        channel.messageBuilder(SyncEntityBackpackPayload.class, 3, NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncEntityBackpackPayload.CODEC::decode)
                .encoder((payload, byteBuf) -> SyncEntityBackpackPayload.CODEC.encode(byteBuf, payload))
                .consumerNetworkThread((payload, context) -> {
                    context.enqueueWork(() -> SyncEntityBackpackPayload.handle(payload));
                }).add();

        return channel;
    }
}
