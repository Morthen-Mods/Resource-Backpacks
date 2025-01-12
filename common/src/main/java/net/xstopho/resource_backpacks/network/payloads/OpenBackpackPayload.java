package net.xstopho.resource_backpacks.network.payloads;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.BackpackItem;

public record OpenBackpackPayload() implements CustomPacketPayload {
    public static final Type<OpenBackpackPayload> TYPE =
            new Type<>(BackpackConstants.of("open_backpack_payload"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenBackpackPayload> CODEC =
            StreamCodec.unit(new OpenBackpackPayload());

    public static void handle(OpenBackpackPayload payload, ServerPlayer player) {
        player.getServer().execute(() -> {
            ItemStack backpack = player.getInventory().getArmor(EquipmentSlot.CHEST.getIndex());

            if (backpack.getItem() instanceof BackpackItem item) {
                player.openMenu(item.getMenuProvider(backpack));
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
