package net.xstopho.resource_backpacks.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resourceconfigapi.ResourceConfigConstants;

import java.util.ServiceLoader;

public class BackpackUtils {

    /**
     * Used to send a Loader specific request payload to get the EnderChest Inventory
     */
    public interface NetworkHook {
        void sendEnderChestRequest();
    }

    /**
     * Used to get the Items of the {@link net.minecraft.world.item.component.ItemContainerContents} since there
     * isn't a native method to get the NonNullList as it is.
     */
    public interface ItemContainerAccess {
        NonNullList<ItemStack> backpack$getItemsForPreview();
    }

    /**
     * Used to get the current set Key, because {@link net.minecraft.client.KeyMapping}
     * only allows to get the default key.
     */
    public interface KeyMappingAccess {
        InputConstants.Key getKey();
    }

    /**
     * Check if the given KeyMapping is pressed.
     * @param keyMapping
     * @return
     */
    public static boolean hasKeyDown(KeyMapping keyMapping) {
        if (keyMapping.isUnbound()) return false;

        int keyCode = ((BackpackUtils.KeyMappingAccess) keyMapping).getKey().getValue();
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), keyCode);
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
}
