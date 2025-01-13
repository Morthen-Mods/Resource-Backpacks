package net.xstopho.resource_backpacks.client.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class BackpackClientUtils {

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

        int keyCode = ((BackpackClientUtils.KeyMappingAccess) keyMapping).getKey().getValue();
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), keyCode);
    }
}
