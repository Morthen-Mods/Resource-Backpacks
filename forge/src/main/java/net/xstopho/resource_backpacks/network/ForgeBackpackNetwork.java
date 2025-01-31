package net.xstopho.resource_backpacks.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraftforge.network.PacketDistributor;
import net.xstopho.resource_backpacks.ResourceBackpacks;

public class ForgeBackpackNetwork implements BackpackNetwork {
    @Override
    public void sendToClient(ServerPlayer player, CustomPacketPayload payload) {
        ResourceBackpacks.NETWORK.send(payload, PacketDistributor.PLAYER.with(player));
    }

    @Override
    public void sendToAllClients(ServerPlayer except, CustomPacketPayload payload) {
        PlayerList playerList = except.getServer().getPlayerList();
        for (ServerPlayer player : playerList.getPlayers()) {
            if (player != except) {
                ResourceBackpacks.NETWORK.send(payload, PacketDistributor.PLAYER.with(player));
            }
        }
    }

    @Override
    public void sendToServer(CustomPacketPayload payload) {
        ResourceBackpacks.NETWORK.send(payload, PacketDistributor.SERVER.noArg());
    }
}
