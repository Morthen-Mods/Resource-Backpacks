package net.morthen.resource_backpacks.registries;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.morthen.resource_backpacks.BackpackConstants;

public class KeyMappingRegistry {

    private static final KeyMapping.Category CATEGORY = new KeyMapping.Category(BackpackConstants.of("controls"));

    public static final KeyMapping OPEN_BACKPACK = new KeyMapping("key.resource_backpacks.open_backpack", InputConstants.KEY_B, CATEGORY);
    public static final KeyMapping SHOW_COMPACT_PREVIEW = new KeyMapping("key.resource_backpacks.show_compact_preview", InputConstants.KEY_LSHIFT, CATEGORY);
    public static final KeyMapping SHOW_INVENTORY_PREVIEW = new KeyMapping("key.resource_backpacks.show_inventory_preview", InputConstants.KEY_LALT, CATEGORY);
}
