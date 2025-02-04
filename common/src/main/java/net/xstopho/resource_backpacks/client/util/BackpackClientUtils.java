package net.xstopho.resource_backpacks.client.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.PeriodicNotificationManager;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.logging.Level;

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

    public static Entity getEntityById(int entityId) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return null;

        Entity entity = level.getEntity(entityId);

        if (entity instanceof LivingEntity) {
            return entity;
        }
        return null;
    }
}
