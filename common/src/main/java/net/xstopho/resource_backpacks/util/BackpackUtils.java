package net.xstopho.resource_backpacks.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.xstopho.resourceconfigapi.ResourceConfigConstants;

import java.util.Objects;
import java.util.ServiceLoader;

public class BackpackUtils {

    /**
     * Used to send a Loader specific request payload to get the EnderChest Inventory
     */
    public interface NetworkHook {
        void sendEnderChestRequest();
    }

    /**
     * Used to get the current set Key, because {@link net.minecraft.client.KeyMapping}
     * only allows to get the default key.
     */
    public interface KeyMappingAccess {
        InputConstants.Key getKey();
    }

    /**
     * Used to send a request package to the Server, this syncs the Ender Chest Inventory with the Client
     */
    public static void syncEnderChestInventory() {

        load(BackpackUtils.NetworkHook.class).sendEnderChestRequest();
    }

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        ResourceConfigConstants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, ListTag> ENDER_CHEST = new StreamCodec<>() {
        @Override
        public ListTag decode(RegistryFriendlyByteBuf byteBuf) {
            CompoundTag tag = byteBuf.readNbt();

            if (tag == null || !tag.contains("ender_chest", ListTag.TAG_LIST)) {
                return null;
            }

            return tag.getList("ender_chest", ListTag.TAG_COMPOUND);
        }

        @Override
        public void encode(RegistryFriendlyByteBuf byteBuf, ListTag listTag) {
            CompoundTag tag = new CompoundTag();

            tag.put("ender_chest", Objects.requireNonNull(listTag));
            byteBuf.writeNbt(tag);
        }
    };
}
