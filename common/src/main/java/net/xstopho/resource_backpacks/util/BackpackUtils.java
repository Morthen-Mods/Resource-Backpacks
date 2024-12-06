package net.xstopho.resource_backpacks.util;

import com.mojang.blaze3d.platform.InputConstants;

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
}
