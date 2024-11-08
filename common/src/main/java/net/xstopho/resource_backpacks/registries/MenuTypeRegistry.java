package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.rendering.container.BackpackContainer;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

public class MenuTypeRegistry {

    private static final RegistryProvider<MenuType<?>> MENU_TYPES = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.MENU);

    public static final RegistryObject<MenuType<BackpackContainer>> LEATHER_BACKPACK_MENU = MENU_TYPES.register("leather_backpack_menu", () -> create(BackpackContainer::leatherContainer));
    public static final RegistryObject<MenuType<BackpackContainer>> COPPER_BACKPACK_MENU = MENU_TYPES.register("copper_backpack_menu", () -> create(BackpackContainer::copperContainer));
    public static final RegistryObject<MenuType<BackpackContainer>> GOLD_BACKPACK_MENU = MENU_TYPES.register("gold_backpack_menu", () -> create(BackpackContainer::goldContainer));
    public static final RegistryObject<MenuType<BackpackContainer>> IRON_BACKPACK_MENU = MENU_TYPES.register("iron_backpack_menu", () -> create(BackpackContainer::ironContainer));
    public static final RegistryObject<MenuType<BackpackContainer>> DIAMOND_BACKPACK_MENU = MENU_TYPES.register("diamond_backpack_menu", () -> create(BackpackContainer::diamondContainer));
    public static final RegistryObject<MenuType<BackpackContainer>> NETHERITE_BACKPACK_MENU = MENU_TYPES.register("netherite_backpack_menu", () -> create(BackpackContainer::netheriteContainer));
    public static final RegistryObject<MenuType<BackpackContainer>> ENDER_BACKPACK_MENU = MENU_TYPES.register("ender_backpack_menu", () -> create(BackpackContainer::enderContainer));


    private static <T extends AbstractContainerMenu> MenuType<T> create(MenuType.MenuSupplier<T> supplier) {
        return new MenuType<>(supplier, FeatureFlags.DEFAULT_FLAGS);
    }

    public static void init() {};
}
