package net.xstopho.resource_backpacks.network.payloads;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.network.BackpackNetwork;

public record EnderChestRequestPayload() implements CustomPacketPayload {
    public static final Type<EnderChestRequestPayload> TYPE = BackpackConstants.type("ender_chest_request_payload");
    public static final StreamCodec<RegistryFriendlyByteBuf, EnderChestRequestPayload> CODEC = StreamCodec.unit(new EnderChestRequestPayload());

    public static void handle(EnderChestRequestPayload payload, ServerPlayer player) {
        player.level().getServer().execute(() -> {
            BackpackNetwork.INSTANCE.sendToClient(player, EnderChestResponsePayload.create(player.getEnderChestInventory(), player.registryAccess()));
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
