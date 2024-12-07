package net.xstopho.resource_backpacks.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.util.BackpackUtils;
import org.jetbrains.annotations.Nullable;

public record EnderChestResponsePayload(@Nullable ListTag inventoryTag) implements CustomPacketPayload {

    public static EnderChestResponsePayload create(PlayerEnderChestContainer container, HolderLookup.Provider registries) {
        return new EnderChestResponsePayload(container.createTag(registries));
    }

    public static final CustomPacketPayload.Type<EnderChestResponsePayload> TYPE =
            new CustomPacketPayload.Type<>(BackpackConstants.of("ender_chest_response_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EnderChestResponsePayload> CODEC =
            StreamCodec.composite(BackpackUtils.ENDER_CHEST, EnderChestResponsePayload::inventoryTag, EnderChestResponsePayload::new);

    public static void apply(EnderChestResponsePayload payload, ClientPlayNetworking.Context context) {
        if (payload.inventoryTag() == null) return;

        context.client().execute(() -> {
            if (context.player() != null) {
                var player = context.player();
                player.getEnderChestInventory().fromTag(payload.inventoryTag(), player.registryAccess());
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
