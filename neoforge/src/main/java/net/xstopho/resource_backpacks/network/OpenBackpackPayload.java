package net.xstopho.resource_backpacks.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.BackpackItem;

public record OpenBackpackPayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<OpenBackpackPayload> TYPE =
            new CustomPacketPayload.Type<>(BackpackConstants.of("open_backpack_payload"));

    public static final StreamCodec<RegistryFriendlyByteBuf, OpenBackpackPayload> CODEC =
            StreamCodec.unit(new OpenBackpackPayload());

    public static void apply(OpenBackpackPayload payload, IPayloadContext context) {
        context.player().getServer().execute(() -> {
            Player player = context.player();

            if (player instanceof ServerPlayer serverPlayer) {
                ItemStack stack = serverPlayer.getInventory().getArmor(EquipmentSlot.CHEST.getIndex());

                if (stack.getItem() instanceof BackpackItem backpack) {
                    serverPlayer.openMenu(backpack.getMenuProvider(stack));
                }
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
