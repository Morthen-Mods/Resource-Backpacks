package net.xstopho.resource_backpacks.client.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class BackpackClientUtils {

    /**
     * Used to get the current set Key, because {@link KeyMapping}
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

        int keyCode = ((KeyMappingAccess) keyMapping).getKey().getValue();
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), keyCode);
    }
}
