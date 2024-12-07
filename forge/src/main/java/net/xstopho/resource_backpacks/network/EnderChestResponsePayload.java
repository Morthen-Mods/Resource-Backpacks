package net.xstopho.resource_backpacks.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.xstopho.resource_backpacks.util.BackpackUtils;
import org.jetbrains.annotations.Nullable;

public record EnderChestResponsePayload(@Nullable ListTag inventoryTag) {

    public static EnderChestResponsePayload create(PlayerEnderChestContainer container, HolderLookup.Provider registries) {
        return new EnderChestResponsePayload(container.createTag(registries));
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, EnderChestResponsePayload> CODEC =
            StreamCodec.composite(BackpackUtils.ENDER_CHEST, EnderChestResponsePayload::inventoryTag, EnderChestResponsePayload::new);

    public static EnderChestResponsePayload decode(RegistryFriendlyByteBuf byteBuf) {
        return CODEC.decode(byteBuf);
    }

    public static void encode(EnderChestResponsePayload payload, RegistryFriendlyByteBuf byteBuf) {
        CODEC.encode(byteBuf, payload);
    }

    public static void apply(EnderChestResponsePayload payload, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;

            if (player != null) {
                player.getEnderChestInventory().fromTag(payload.inventoryTag(), player.registryAccess());
            }
        });
        context.setPacketHandled(true);
    }
}