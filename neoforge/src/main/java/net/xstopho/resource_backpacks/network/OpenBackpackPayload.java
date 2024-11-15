package net.xstopho.resource_backpacks.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.items.BackpackItem;

public record OpenBackpackPayload(int id) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<OpenBackpackPayload> PACKET_TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, "open_backpack_payload"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenBackpackPayload> PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.INT,  OpenBackpackPayload::id, OpenBackpackPayload::new);

    public static void apply(OpenBackpackPayload payload, IPayloadContext context) {
        context.player().getServer().execute(() -> {
            Player player = context.player();

            if (player instanceof ServerPlayer serverPlayer) {
                ItemStack itemStack = serverPlayer.getInventory().getArmor(EquipmentSlot.CHEST.getIndex());

                if (itemStack.getItem() instanceof BackpackItem backpackItem) {
                    serverPlayer.openMenu(backpackItem.getMenuProvider(itemStack));
                }
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return PACKET_TYPE;
    }
}
