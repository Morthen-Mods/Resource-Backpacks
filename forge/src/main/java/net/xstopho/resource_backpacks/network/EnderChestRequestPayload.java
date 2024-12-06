package net.xstopho.resource_backpacks.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.PacketDistributor;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.ResourceBackpacks;

public record EnderChestRequestPayload() {

    public static EnderChestRequestPayload decode(FriendlyByteBuf byteBuf) {
        return new EnderChestRequestPayload();
    }
    public static void encode(EnderChestRequestPayload payload, FriendlyByteBuf byteBuf) {}

    public static void apply(EnderChestRequestPayload payload, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            ResourceBackpacks.NETWORK.send(EnderChestResponsePayload.create(player.getEnderChestInventory(), player.registryAccess()), PacketDistributor.PLAYER.with(player));
        });
        context.setPacketHandled(true);
    }
}
