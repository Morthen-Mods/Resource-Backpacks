package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.screen.BackpackMenu;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

public class MenuTypeRegistry {

    private static final RegistryProvider<MenuType<?>> MENU_TYPES = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.MENU);

    public static final RegistryObject<MenuType<BackpackMenu>> TEST_MENU = MENU_TYPES.register("test_menu", () -> new MenuType<>(BackpackMenu::testMenu, FeatureFlags.DEFAULT_FLAGS));

    public static void init() {}
}
