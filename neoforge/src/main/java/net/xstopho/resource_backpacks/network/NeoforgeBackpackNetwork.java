package net.xstopho.resource_backpacks.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoforgeBackpackNetwork implements BackpackNetwork {
    @Override
    public void sendToClient(ServerPlayer player, CustomPacketPayload payload) {
        PacketDistributor.sendToPlayer(player, payload);
    }

    @Override
    public void sendToAllClients(ServerPlayer except, CustomPacketPayload payload) {
        PlayerList playerList = except.getServer().getPlayerList();
        for (ServerPlayer player : playerList.getPlayers()) {
            if (player != except) {
                PacketDistributor.sendToPlayer(player, payload);
            }
        }
    }

    @Override
    public void sendToClientsTrackingEntity(LivingEntity livingEntity, CustomPacketPayload payload) {
        PacketDistributor.sendToPlayersTrackingEntity(livingEntity, payload);
    }

    @Override
    public void sendToServer(CustomPacketPayload payload) {
        PacketDistributor.sendToServer(payload);
    }
}
