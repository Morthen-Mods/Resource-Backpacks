package net.morthen.resource_backpacks.network.payloads;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.morthen.resource_backpacks.BackpackConstants;
import net.morthen.resource_backpacks.backpack.BackpackItem;
import net.morthen.resource_backpacks.backpack.api.BackpackHolder;

public record OpenBackpackPayload() implements CustomPacketPayload {
    public static final Type<OpenBackpackPayload> TYPE = BackpackConstants.type("open_backpack_payload");
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenBackpackPayload> CODEC = StreamCodec.unit(new OpenBackpackPayload());

    public static void handle(OpenBackpackPayload payload, ServerPlayer player) {
        player.level().getServer().execute(() -> {
            ItemStack itemStack = ((BackpackHolder) player).getBackpack();

            if (itemStack.getItem() instanceof BackpackItem backpackItem) {
                player.openMenu(backpackItem.getMenuProvider(itemStack));
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
