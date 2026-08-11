package net.morthen.resource_backpacks.registries;

import net.minecraft.client.KeyMapping;
import net.morthen.resource_backpacks.BackpackConstants;
import org.lwjgl.glfw.GLFW;

public class KeyMappingRegistry {

    private static final KeyMapping.Category CATEGORY = new KeyMapping.Category(BackpackConstants.of("controls"));

    public static final KeyMapping OPEN_BACKPACK = new KeyMapping("key.resource_backpacks.open_backpack", GLFW.GLFW_KEY_B, CATEGORY);
    public static final KeyMapping SHOW_COMPACT_PREVIEW = new KeyMapping("key.resource_backpacks.show_compact_preview", GLFW.GLFW_KEY_LEFT_SHIFT, CATEGORY);
    public static final KeyMapping SHOW_INVENTORY_PREVIEW = new KeyMapping("key.resource_backpacks.show_inventory_preview", GLFW.GLFW_KEY_LEFT_ALT, CATEGORY);
}
