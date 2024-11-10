package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

public class CreativeTabRegistry {

    private static final RegistryProvider<CreativeModeTab> CREATIVE_TAB = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.CREATIVE_MODE_TAB);
}
