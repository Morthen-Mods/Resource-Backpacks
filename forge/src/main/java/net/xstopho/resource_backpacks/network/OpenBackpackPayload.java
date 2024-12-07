package net.xstopho.resource_backpacks.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.xstopho.resource_backpacks.backpack.BackpackItem;

public record OpenBackpackPayload() {

    public static OpenBackpackPayload decode(FriendlyByteBuf byteBuf) {
        return new OpenBackpackPayload();
    }

    public static void encode(OpenBackpackPayload payload, FriendlyByteBuf byteBuf) {}

    public static void apply(OpenBackpackPayload payload, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            Player player = context.getSender();

            if (player instanceof ServerPlayer serverPlayer) {
                ItemStack stack = serverPlayer.getInventory().getArmor(EquipmentSlot.CHEST.getIndex());

                if (stack.getItem() instanceof BackpackItem backpack) {
                    serverPlayer.openMenu(backpack.getMenuProvider(stack));
                }
            }
        });
        context.setPacketHandled(true);
    }
}
