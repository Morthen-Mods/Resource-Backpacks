package net.xstopho.resource_backpacks.network.payloads;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.BackpackItem;

public record OpenBackpackPayload() implements CustomPacketPayload {
    public static final Type<OpenBackpackPayload> TYPE = new Type<>(BackpackConstants.of("open_backpack_payload"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenBackpackPayload> CODEC = StreamCodec.unit(new OpenBackpackPayload());

    public static void handle(OpenBackpackPayload payload, ServerPlayer player) {
        player.getServer().execute(() -> {
            Inventory inventory = player.getInventory();
            ItemStack stack = inventory.getItem(inventory.getContainerSize() - 1);

            if (stack.getItem() instanceof BackpackItem backpack) {
                player.openMenu(backpack.getMenuProvider(stack));
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
