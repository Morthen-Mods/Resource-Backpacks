package net.xstopho.resource_backpacks.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.xstopho.resource_backpacks.backpack.BackpackItem;

public class OpenBackpackPayload {

    private final int id;

    public OpenBackpackPayload(int id) {
        this.id = id;
    }

    public static OpenBackpackPayload decode(FriendlyByteBuf byteBuf) {
        return new OpenBackpackPayload(byteBuf.readInt());
    }

    public static void encode(OpenBackpackPayload payload, FriendlyByteBuf byteBuf) {
        byteBuf.writeInt(payload.id);
    }

    public static void apply(OpenBackpackPayload payload, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            Player player = context.getSender();

            if (player instanceof ServerPlayer serverPlayer) {
                ItemStack itemStack = serverPlayer.getInventory().getArmor(EquipmentSlot.CHEST.getIndex());

                if (itemStack.getItem() instanceof BackpackItem backpackItem) {
                    serverPlayer.openMenu(backpackItem.getMenuProvider(itemStack));
                }
            }
        });
        context.setPacketHandled(true);
    }
}
