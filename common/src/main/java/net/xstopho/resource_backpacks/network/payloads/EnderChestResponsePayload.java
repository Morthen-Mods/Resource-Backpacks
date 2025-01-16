package net.xstopho.resource_backpacks.network.payloads;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.network.BackpackCodecs;
import org.jetbrains.annotations.Nullable;

public record EnderChestResponsePayload(@Nullable ListTag inventory) implements CustomPacketPayload {
    public static final Type<EnderChestResponsePayload> TYPE = new Type<>(BackpackConstants.of("ender_chest_response_payload"));
    public static final StreamCodec<RegistryFriendlyByteBuf, EnderChestResponsePayload> CODEC =
            StreamCodec.composite(BackpackCodecs.ENDER_CHEST, EnderChestResponsePayload::inventory, EnderChestResponsePayload::new);

    public static EnderChestResponsePayload create(PlayerEnderChestContainer container, HolderLookup.Provider registry) {
        return new EnderChestResponsePayload(container.createTag(registry));
    }

    public static void handle(EnderChestResponsePayload payload) {
        if (payload.inventory() == null) return;

        LocalPlayer player = Minecraft.getInstance().player;

        if (player != null) {
            player.getEnderChestInventory().fromTag(payload.inventory(), player.registryAccess());
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
