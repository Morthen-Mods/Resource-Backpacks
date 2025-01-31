package net.xstopho.resource_backpacks.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.LivingEntity;

public class FabricBackpackNetwork implements BackpackNetwork {
    @Override
    public void sendToClient(ServerPlayer player, CustomPacketPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }

    @Override
    public void sendToServer(CustomPacketPayload payload) {
        ClientPlayNetworking.send(payload);
    }

    @Override
    public void sendToAllClients(ServerPlayer except, CustomPacketPayload payload) {
        PlayerList playerList = except.getServer().getPlayerList();
        for (ServerPlayer player : playerList.getPlayers()) {
            ServerPlayNetworking.send(player, payload);
        }
    }

    @Override
    public void sendToClientsTrackingEntity(LivingEntity livingEntity, CustomPacketPayload payload) {
        for (ServerPlayer player : PlayerLookup.tracking(livingEntity)) {
            ServerPlayNetworking.send(player, payload);
        }
    }
}
