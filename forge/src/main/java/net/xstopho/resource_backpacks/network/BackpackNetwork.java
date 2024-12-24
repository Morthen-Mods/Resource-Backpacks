package net.xstopho.resource_backpacks.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.SimpleChannel;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.ResourceBackpacks;

public class BackpackNetwork {

    public static SimpleChannel initPayloads() {
        SimpleChannel channel = ChannelBuilder.named(of("backpack_network"))
                .acceptedVersions(Channel.VersionTest.exact(1))
                .networkProtocolVersion(1)
                .simpleChannel();

        ResourceBackpacks.NETWORK = channel;

        channel.messageBuilder(OpenBackpackPayload.class, 0, NetworkDirection.PLAY_TO_SERVER)
                .decoder(OpenBackpackPayload::decode)
                .encoder(OpenBackpackPayload::encode)
                .consumerNetworkThread(OpenBackpackPayload::apply)
                .add();

        channel.messageBuilder(EnderChestRequestPayload.class, 1, NetworkDirection.PLAY_TO_SERVER)
                .decoder(EnderChestRequestPayload::decode)
                .encoder(EnderChestRequestPayload::encode)
                .consumerNetworkThread(EnderChestRequestPayload::apply)
                .add();

        channel.messageBuilder(EnderChestResponsePayload.class, 2, NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnderChestResponsePayload::decode)
                .encoder(EnderChestResponsePayload::encode)
                .consumerNetworkThread(EnderChestResponsePayload::apply)
                .add();

        return channel;
    }

    public static ResourceLocation of(String id) {
        return ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, id);
    }
}
