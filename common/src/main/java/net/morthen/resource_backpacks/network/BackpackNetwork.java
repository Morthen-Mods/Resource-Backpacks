package net.morthen.resource_backpacks.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.morthen.resourcelibrary.service.LibServices;

public interface BackpackNetwork {

    BackpackNetwork INSTANCE = LibServices.load(BackpackNetwork.class);

    void sendToClient(ServerPlayer player, CustomPacketPayload payload);
    void sendToClientsTrackingEntity(LivingEntity livingEntity, CustomPacketPayload payload);

    void sendToServer(CustomPacketPayload payload);
}
