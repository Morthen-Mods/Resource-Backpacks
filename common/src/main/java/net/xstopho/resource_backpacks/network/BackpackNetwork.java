package net.xstopho.resource_backpacks.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.xstopho.resourceconfigapi.platform.CoreServices;

public interface BackpackNetwork {

    static BackpackNetwork INSTANCE = CoreServices.load(BackpackNetwork.class);

    void sendToClient(ServerPlayer player, CustomPacketPayload payload);
    void sendToServer(CustomPacketPayload payload);
}
