package net.xstopho.resource_backpacks.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.xstopho.resource_backpacks.BackpackConstants;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public record EnderChestResponsePayload(@Nullable ListTag inventoryTag) implements CustomPacketPayload {

    public static EnderChestResponsePayload create(PlayerEnderChestContainer container, HolderLookup.Provider registries) {
        return new EnderChestResponsePayload(container.createTag(registries));
    }

    public static final Type<EnderChestResponsePayload> TYPE =
            new Type<>(BackpackConstants.of("ender_chest_response_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EnderChestResponsePayload> CODEC =
            new StreamCodec<>() {
                @Override
                public EnderChestResponsePayload decode(RegistryFriendlyByteBuf byteBuf) {
                    CompoundTag compound = byteBuf.readNbt();

                    if (compound == null || !compound.contains("inv", ListTag.TAG_LIST))
                        return new EnderChestResponsePayload(null);
                    return new EnderChestResponsePayload(compound.getList("inv", ListTag.TAG_COMPOUND));
                }

                @Override
                public void encode(RegistryFriendlyByteBuf byteBuf, EnderChestResponsePayload payload) {
                    CompoundTag compound = new CompoundTag();

                    compound.put("inv", Objects.requireNonNull(payload.inventoryTag()));
                    byteBuf.writeNbt(compound);
                }
            };

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
