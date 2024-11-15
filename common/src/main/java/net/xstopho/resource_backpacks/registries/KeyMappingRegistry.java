package net.xstopho.resource_backpacks.registries;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class KeyMappingRegistry {

    private static final String CATEGORY = "category.resource_backpacks.controls";

    public static final KeyMapping OPEN_BACKPACK = new KeyMapping("key.resource_backpacks.open_backpack", GLFW.GLFW_KEY_B, CATEGORY);
}
