package net.xstopho.resource_backpacks.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.xstopho.resource_backpacks.BackpackConstants;

public record EnderChestRequestPayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<EnderChestRequestPayload> TYPE =
            new CustomPacketPayload.Type<>(BackpackConstants.of("ender_chest_request_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EnderChestRequestPayload> CODEC =
            new StreamCodec<>() {
                @Override
                public EnderChestRequestPayload decode(RegistryFriendlyByteBuf object) {
                    return  new EnderChestRequestPayload();
                }

                @Override
                public void encode(RegistryFriendlyByteBuf object, EnderChestRequestPayload object2) {}
            };

    public static void apply(EnderChestRequestPayload payload, ServerPlayNetworking.Context context) {
        context.player().getServer().execute(() -> {
            ServerPlayer player = context.player();

            context.responseSender().sendPacket(EnderChestResponsePayload.create(player.getEnderChestInventory(), player.registryAccess()));
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
