package net.xstopho.resource_backpacks.network;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.PacketDistributor;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.ResourceBackpacks;

public class EnderChestRequestPayload {

    private final int id;

    public EnderChestRequestPayload(int id) {
        this.id = id;
    }

    public static EnderChestRequestPayload decode(FriendlyByteBuf byteBuf) {
        return new EnderChestRequestPayload(byteBuf.readInt());
    }
    public static void encode(EnderChestRequestPayload payload, FriendlyByteBuf byteBuf) {
        byteBuf.writeInt(payload.id);

    }

    public static void apply(EnderChestRequestPayload payload, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            NonNullList<ItemStack> itemList = player.getEnderChestInventory().getItems();

            BackpackConstants.LOG.error("Request UUID: {}", player.getUUID());

            ResourceBackpacks.NETWORK.send(new EnderChestResponsePayload(itemList), PacketDistributor.PLAYER.with(player));
        });
        context.setPacketHandled(true);
    }
}
