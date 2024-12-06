package net.xstopho.resource_backpacks.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.xstopho.resource_backpacks.BackpackConstants;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record EnderChestResponsePayload(@Nullable ListTag inventoryTag) {

    public static EnderChestResponsePayload create(PlayerEnderChestContainer container, HolderLookup.Provider registries) {
        return new EnderChestResponsePayload(container.createTag(registries));
    }

    public static EnderChestResponsePayload decode(RegistryFriendlyByteBuf byteBuf) {
        CompoundTag compound = byteBuf.readNbt();

        if (compound == null || !compound.contains("inv", ListTag.TAG_LIST))
            return new EnderChestResponsePayload(null);
        return new EnderChestResponsePayload(compound.getList("inv", ListTag.TAG_COMPOUND));
    }

    public static void encode(EnderChestResponsePayload payload, RegistryFriendlyByteBuf byteBuf) {
        CompoundTag compound = new CompoundTag();

        compound.put("inv", Objects.requireNonNull(payload.inventoryTag()));
        byteBuf.writeNbt(compound);
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