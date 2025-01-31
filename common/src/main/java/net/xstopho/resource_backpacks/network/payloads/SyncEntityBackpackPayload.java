package net.xstopho.resource_backpacks.network.payloads;

import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;

public record SyncEntityBackpackPayload(int entityId, ItemStack backpack) implements CustomPacketPayload {
    public static final Type<SyncEntityBackpackPayload> TYPE = new Type<>(BackpackConstants.of("sync_entity_backpack_payload"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncEntityBackpackPayload> CODEC =
            StreamCodec.composite(ByteBufCodecs.INT, SyncEntityBackpackPayload::entityId,
                    ItemStack.OPTIONAL_STREAM_CODEC, SyncEntityBackpackPayload::backpack,
                    SyncEntityBackpackPayload::new);

    public static void handle(SyncEntityBackpackPayload payload) {
        Level level = Minecraft.getInstance().level;
        Entity entity = level.getEntity(payload.entityId());

        if (entity instanceof LivingEntity livingEntity) {
            ((BackpackHolder) livingEntity).setBackpack(payload.backpack());
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
