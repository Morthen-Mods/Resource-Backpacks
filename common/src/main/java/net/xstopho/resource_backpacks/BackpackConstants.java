package net.xstopho.resource_backpacks;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.registries.BlockEntityRegistry;
import net.xstopho.resource_backpacks.registries.BlockRegistry;
import net.xstopho.resource_backpacks.registries.CreativeTabRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_backpacks.screen.BackpackMenuScreen;
import net.xstopho.resourceconfigapi.api.ConfigRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackpackConstants {
    public static final String MOD_ID = "resource_backpacks";
    public static final String MOD_NAME = "Resource Backpacks";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void commonInit() {
        ConfigRegistry.register(BackpackConfig.class, MOD_ID);

        BlockRegistry.init();
        BlockEntityRegistry.init();

        MenuTypeRegistry.init();

        CreativeTabRegistry.init();
    }

    public static void clientInit() {
        MenuScreens.register(MenuTypeRegistry.DEFAULT_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.LEATHER_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.COPPER_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.GOLD_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.IRON_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.DIAMOND_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.NETHERITE_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.END_MENU.get(), BackpackMenuScreen::new);
    }

    public static ResourceLocation of(String id) {

        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }
}
