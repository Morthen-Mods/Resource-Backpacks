package net.xstopho.resource_backpacks.network;

import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.xstopho.resource_backpacks.BackpackConstants;

public record EnderChestRequestPayload(int id) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<EnderChestRequestPayload> TYPE =
            new CustomPacketPayload.Type<>(BackpackConstants.of("ender_chest_request_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EnderChestRequestPayload> CODEC =
            StreamCodec.composite(ByteBufCodecs.INT, EnderChestRequestPayload::id, EnderChestRequestPayload::new);

    public static void apply(EnderChestRequestPayload payload, IPayloadContext context) {
        context.player().getServer().execute(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

            NonNullList<ItemStack> itemList = player.getEnderChestInventory().getItems();
            PacketDistributor.sendToPlayer(player, new EnderChestResponsePayload(itemList));
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}