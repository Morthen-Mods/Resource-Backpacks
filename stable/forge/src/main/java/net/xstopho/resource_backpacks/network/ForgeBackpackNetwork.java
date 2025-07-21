package net.xstopho.resource_backpacks.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.PacketDistributor;
import net.xstopho.resource_backpacks.ResourceBackpacks;

public class ForgeBackpackNetwork implements BackpackNetwork {

    @Override
    public void sendToClient(ServerPlayer player, CustomPacketPayload payload) {
        ResourceBackpacks.NETWORK.send(payload, PacketDistributor.PLAYER.with(player));
    }

    @Override
    public void sendToClientsTrackingEntity(LivingEntity livingEntity, CustomPacketPayload payload) {
        ResourceBackpacks.NETWORK.send(payload, PacketDistributor.TRACKING_ENTITY.with(livingEntity));
    }

    @Override
    public void sendToServer(CustomPacketPayload payload) {
        ResourceBackpacks.NETWORK.send(payload, PacketDistributor.SERVER.noArg());
    }
}
