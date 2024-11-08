package net.xstopho.resource_backpacks.network.packets;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.item.BackpackItem;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public record OpenBackpackPacket(int id) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<OpenBackpackPacket> PACKET_TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, "open_backpack_packet"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenBackpackPacket> PACKET_CODEC;

    public static void apply(OpenBackpackPacket packet, ServerPlayNetworking.Context context) {
        context.player().getServer().execute(() -> {
            Player player = context.player();
            if (player instanceof ServerPlayer serverPlayer) {
                List<ItemStack> itemStacks = new LinkedList<>() {{
                    //add(AccessoriesHelper.getEquippedBackpack(serverPlayer));
                    //add(TrinketHelper.getEquippedBackpack(serverPlayer));
                    add(serverPlayer.getInventory().getArmor(EquipmentSlot.CHEST.getIndex()));
                }};

                for (ItemStack stack : itemStacks) {
                    if (stack.getItem() instanceof BackpackItem backpackItem) {
                        serverPlayer.openMenu(backpackItem.getMenuProvider(stack));
                        return;
                    }
                }

                for (ItemStack stack : serverPlayer.getInventory().items) {
                    if (stack.getItem() instanceof BackpackItem backpackItem) {
                        serverPlayer.openMenu(backpackItem.getMenuProvider(stack));
                    }
                }
            }
        });
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return PACKET_TYPE;
    }

    static {
        PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.INT, OpenBackpackPacket::id, OpenBackpackPacket::new);
    }
}
