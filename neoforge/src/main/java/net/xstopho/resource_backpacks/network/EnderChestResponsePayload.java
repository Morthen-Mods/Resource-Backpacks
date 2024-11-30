package net.xstopho.resource_backpacks.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.xstopho.resource_backpacks.BackpackConstants;

import java.util.List;
import java.util.UUID;

public record EnderChestResponsePayload(List<ItemStack> items) implements CustomPacketPayload {
    public static final Type<EnderChestResponsePayload> TYPE =
            new Type<>(BackpackConstants.of("ender_chest_response_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EnderChestResponsePayload> CODEC =
            StreamCodec.composite(ItemStack.OPTIONAL_LIST_STREAM_CODEC, EnderChestResponsePayload::items, EnderChestResponsePayload::new);

    public static void apply(EnderChestResponsePayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            UUID uuid = context.player().getUUID();
            BackpackConstants.ENDER_CHESTS.put(uuid, payload.items());
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
