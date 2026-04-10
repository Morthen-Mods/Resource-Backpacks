package net.xstopho.resource_backpacks.network.payloads;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.ItemStackWithSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.client.util.BackpackClientUtils;
import net.xstopho.resource_backpacks.network.BackpackCodecs;
import org.jetbrains.annotations.Nullable;

public record EnderChestResponsePayload(@Nullable ListTag inventory) implements CustomPacketPayload {
    public static final Type<EnderChestResponsePayload> TYPE = BackpackConstants.type("ender_chest_response_payload");
    public static final StreamCodec<RegistryFriendlyByteBuf, EnderChestResponsePayload> CODEC =
            StreamCodec.composite(BackpackCodecs.ENDER_CHEST, EnderChestResponsePayload::inventory, EnderChestResponsePayload::new);

    public static EnderChestResponsePayload create(PlayerEnderChestContainer container, HolderLookup.Provider registry) {
        TagValueOutput valueOutput = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, registry);
        container.storeAsSlots(valueOutput.list("ender_chest", ItemStackWithSlot.CODEC));
        return new EnderChestResponsePayload(valueOutput.buildResult().getListOrEmpty("ender_chest"));
    }

    public static void handle(EnderChestResponsePayload payload) {
        if (payload.inventory() == null) return;

        Player player = BackpackClientUtils.getPlayer();

        if (player != null) {
            CompoundTag tag = new CompoundTag();
            tag.put("ender_chest", payload.inventory());

            ValueInput valueInput = TagValueInput.create(ProblemReporter.DISCARDING, player.registryAccess(), tag);
            player.getEnderChestInventory().fromSlots(valueInput.listOrEmpty("ender_chest", ItemStackWithSlot.CODEC));
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
