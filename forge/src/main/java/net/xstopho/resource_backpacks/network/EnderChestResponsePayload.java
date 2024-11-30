package net.xstopho.resource_backpacks.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.xstopho.resource_backpacks.BackpackConstants;

import java.util.List;
import java.util.UUID;

public class EnderChestResponsePayload {

    private final List<ItemStack> items;

    public EnderChestResponsePayload(List<ItemStack> items) {
        this.items = items;
    }

    public static EnderChestResponsePayload decode(RegistryFriendlyByteBuf byteBuf) {
        return new EnderChestResponsePayload(ItemStack.OPTIONAL_LIST_STREAM_CODEC.decode(byteBuf));
    }

    public static void encode(EnderChestResponsePayload payload, RegistryFriendlyByteBuf byteBuf) {
        ItemStack.OPTIONAL_LIST_STREAM_CODEC.encode(byteBuf, payload.items);
    }

    public static void apply(EnderChestResponsePayload payload, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            Player player = context.getSender();
            BackpackConstants.LOG.error("Response uuid: {}", player.getUUID());
            saveData(player.getUUID(), payload.items);
        });
        context.setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    public static void saveData(UUID uuid, List<ItemStack> items) {
        BackpackConstants.ENDER_CHESTS.put(uuid, items);
    }
}