package net.xstopho.resource_backpacks.network.payloads;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;

public record SyncCreativeSlotPayload(int index, ItemStack itemStack) implements CustomPacketPayload {
    public static final Type<SyncCreativeSlotPayload> TYPE = BackpackConstants.type("sync_creative_slot_payload");
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncCreativeSlotPayload> CODEC =
            StreamCodec.composite(ByteBufCodecs.INT, SyncCreativeSlotPayload::index,
                    ItemStack.OPTIONAL_STREAM_CODEC, SyncCreativeSlotPayload::itemStack,
                    SyncCreativeSlotPayload::new);

    public static void handle(SyncCreativeSlotPayload payload, ServerPlayer player) {
        player.getServer().execute(() -> {
            player.inventoryMenu.getSlot(payload.index()).set(payload.itemStack());
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
