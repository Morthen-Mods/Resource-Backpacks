package net.morthen.resource_backpacks.client.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.morthen.resource_backpacks.mixin.accessor.KeyMappingAccessor;
import net.morthen.resource_backpacks.network.payloads.SyncEntityBackpackPayload;
import net.morthen.resource_backpacks.registries.KeyMappingRegistry;

public class BackpackClientUtils {

    public static boolean enableCompactPreview() {
        return hasKeyDown(KeyMappingRegistry.SHOW_COMPACT_PREVIEW);
    }

    public static boolean enableInventoryPreview() {
        return hasKeyDown(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW);
    }

    /**
     * Check if the given KeyMapping is pressed.
     * @param keyMapping Configurable {@link KeyMapping}
     * @return {@link Boolean}
     */
    public static boolean hasKeyDown(KeyMapping keyMapping) {
        if (keyMapping.isUnbound()) return false;

        if (keyMapping instanceof KeyMappingAccessor accessor) {
            int keyCode = accessor.rb$getKey().getValue();
            return InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), keyCode);
        }

        return false;
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
