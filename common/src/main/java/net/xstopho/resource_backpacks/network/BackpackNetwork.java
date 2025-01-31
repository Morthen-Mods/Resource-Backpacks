package net.xstopho.resource_backpacks.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.xstopho.resourceconfigapi.platform.CoreServices;

public interface BackpackNetwork {

    BackpackNetwork INSTANCE = CoreServices.load(BackpackNetwork.class);

    void sendToClient(ServerPlayer player, CustomPacketPayload payload);
    void sendToAllClients(ServerPlayer except, CustomPacketPayload payload);
    void sendToClientsTrackingEntity(LivingEntity livingEntity, CustomPacketPayload payload);

    void sendToServer(CustomPacketPayload payload);
}
