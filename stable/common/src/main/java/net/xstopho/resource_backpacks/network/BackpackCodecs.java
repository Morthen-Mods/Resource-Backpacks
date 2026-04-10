package net.xstopho.resource_backpacks.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.Objects;

public class BackpackCodecs {

    public static final StreamCodec<RegistryFriendlyByteBuf, ListTag> ENDER_CHEST = new StreamCodec<>() {
        @Override
        public ListTag decode(RegistryFriendlyByteBuf byteBuf) {
            CompoundTag tag = byteBuf.readNbt();

            if (tag == null || !tag.contains("ender_chest")) {
                return null;
            }

            return tag.getList("ender_chest").orElse(null);
        }

        @Override
        public void encode(RegistryFriendlyByteBuf byteBuf, ListTag listTag) {
            CompoundTag tag = new CompoundTag();

            tag.put("ender_chest", Objects.requireNonNull(listTag));
            byteBuf.writeNbt(tag);
        }
    };
}
