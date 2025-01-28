package net.xstopho.resource_backpacks.network;

import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.SimpleChannel;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.ResourceBackpacks;
import net.xstopho.resource_backpacks.network.payloads.EnderChestRequestPayload;
import net.xstopho.resource_backpacks.network.payloads.EnderChestResponsePayload;
import net.xstopho.resource_backpacks.network.payloads.OpenBackpackPayload;

import java.util.function.BiConsumer;

public class BackpackNetworkRegistry {

    public static SimpleChannel registerPayloads() {
        SimpleChannel channel = ChannelBuilder.named(BackpackConstants.of("backpack_network"))
                .acceptedVersions(Channel.VersionTest.exact(1))
                .networkProtocolVersion(1)
                .simpleChannel();

        ResourceBackpacks.NETWORK = channel;
        int index = 0;

        channel.messageBuilder(OpenBackpackPayload.class, index++, NetworkDirection.PLAY_TO_SERVER)
                .decoder(OpenBackpackPayload.CODEC::decode)
                .encoder((payload, byteBuf) -> OpenBackpackPayload.CODEC.encode(byteBuf, payload))
                .consumerNetworkThread((BiConsumer<OpenBackpackPayload, CustomPayloadEvent.Context>) (payload, context) -> OpenBackpackPayload.handle(payload, context.getSender()))
                .add();

        channel.messageBuilder(EnderChestRequestPayload.class, index++, NetworkDirection.PLAY_TO_SERVER)
                .decoder(EnderChestRequestPayload.CODEC::decode)
                .encoder((payload, byteBuf) -> EnderChestRequestPayload.CODEC.encode(byteBuf, payload))
                .consumerNetworkThread((BiConsumer<EnderChestRequestPayload, CustomPayloadEvent.Context>) (payload, context) -> EnderChestRequestPayload.handle(payload, context.getSender()))
                .add();

        channel.messageBuilder(EnderChestResponsePayload.class, index++, NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnderChestResponsePayload.CODEC::decode)
                .encoder((payload, byteBuf) -> EnderChestResponsePayload.CODEC.encode(byteBuf, payload))
                .consumerNetworkThread((BiConsumer<EnderChestResponsePayload, CustomPayloadEvent.Context>) (payload, context) -> EnderChestResponsePayload.handle(payload))
                .add();

        return channel;
    }
}
