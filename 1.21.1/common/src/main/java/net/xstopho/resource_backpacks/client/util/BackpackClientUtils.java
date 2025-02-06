package net.xstopho.resource_backpacks.client.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.xstopho.resource_backpacks.network.payloads.SyncEntityBackpackPayload;

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
     * @param keyMapping Configurable {@link KeyMapping}
     * @return {@link Boolean}
     */
    public static boolean hasKeyDown(KeyMapping keyMapping) {
        if (keyMapping.isUnbound()) return false;

        int keyCode = ((KeyMappingAccess) keyMapping).getKey().getValue();
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), keyCode);
    }

    /**
     * Get the LocalPlayer
     * @return player
     */
    public static Player getPlayer() {
        return Minecraft.getInstance().player;
    }

    /**
     * get the Entity by its ID, used to hide Client code in {@link SyncEntityBackpackPayload}
     * @param entityId {@link Integer}
     * @return {@link Entity}
     */
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
