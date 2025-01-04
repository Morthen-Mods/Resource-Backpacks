package net.xstopho.resource_backpacks.client.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.xstopho.resource_backpacks.BackpackConstants;

import java.util.ServiceLoader;

public class BackpackClientUtils {

    /**
     * Used to send a Loader specific request payload to get the EnderChest Inventory
     */
    public interface NetworkHook {
        void sendEnderChestRequest();
    }

    /**
     * Used to get the current set Key, because {@link KeyMapping}
     * only allows to get the default key.
     */
    public interface KeyMappingAccess {
        InputConstants.Key getKey();
    }

    /**
     * Used to send a request package to the Server, this syncs the Ender Chest Inventory with the Client
     */
    public static void syncEnderChestInventory() {

        load(NetworkHook.class).sendEnderChestRequest();
    }

    public static Player getLocalPlayer() {
        return Minecraft.getInstance().player;
    }

    /**
     * Check if the given KeyMapping is pressed.
     * @param keyMapping
     * @return
     */
    public static boolean hasKeyDown(KeyMapping keyMapping) {
        if (keyMapping.isUnbound()) return false;

        int keyCode = ((KeyMappingAccess) keyMapping).getKey().getValue();
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), keyCode);
    }

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        BackpackConstants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
