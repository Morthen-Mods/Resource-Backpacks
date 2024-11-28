package net.xstopho.resource_backpacks.registries;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class KeyMappingRegistry {

    private static final String CATEGORY = "category.resource_backpacks.controls";

    public static final KeyMapping OPEN_BACKPACK = new KeyMapping("key.resource_backpacks.open_backpack", GLFW.GLFW_KEY_B, CATEGORY);
    public static final KeyMapping SHOW_COMPACT_PREVIEW = new KeyMapping("key.resource_backpacks.show_compact_preview", GLFW.GLFW_KEY_LEFT_SHIFT, CATEGORY);
    public static final KeyMapping SHOW_INVENTORY_PREVIEW = new KeyMapping("key.resource_backpacks.show_INVENTORY_preview", GLFW.GLFW_KEY_LEFT_ALT, CATEGORY);
}
