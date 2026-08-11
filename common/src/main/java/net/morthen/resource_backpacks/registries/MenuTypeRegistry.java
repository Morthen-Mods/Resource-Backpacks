package net.morthen.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.morthen.resource_backpacks.BackpackConstants;
import net.morthen.resource_backpacks.client.screen.BackpackMenu;
import net.morthen.resourcelibrary.registration.RegistryObject;
import net.morthen.resourcelibrary.registration.RegistryProvider;

public class MenuTypeRegistry {

    private static final RegistryProvider<MenuType<?>> MENU_TYPES = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.MENU);

    public static final RegistryObject<MenuType<BackpackMenu>> LEATHER_MENU = MENU_TYPES.register("leather_menu", () -> new MenuType<>(BackpackMenu::leatherMenu, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<BackpackMenu>> COPPER_MENU = MENU_TYPES.register("copper_menu", () -> new MenuType<>(BackpackMenu::copperMenu, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<BackpackMenu>> GOLD_MENU = MENU_TYPES.register("gold_menu", () -> new MenuType<>(BackpackMenu::goldMenu, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<BackpackMenu>> IRON_MENU = MENU_TYPES.register("iron_menu", () -> new MenuType<>(BackpackMenu::ironMenu, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<BackpackMenu>> DIAMOND_MENU = MENU_TYPES.register("diamond_menu", () -> new MenuType<>(BackpackMenu::diamondMenu, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<BackpackMenu>> NETHERITE_MENU = MENU_TYPES.register("netherite_menu", () -> new MenuType<>(BackpackMenu::netheriteMenu, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<BackpackMenu>> END_MENU = MENU_TYPES.register("end_menu", () -> new MenuType<>(BackpackMenu::endMenu, FeatureFlags.DEFAULT_FLAGS));

    public static void init() {}
}
