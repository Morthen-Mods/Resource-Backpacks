package net.xstopho.resource_backpacks;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.xstopho.resource_backpacks.client.screen.BackpackMenuScreen;
import net.xstopho.resource_backpacks.config.client.ClientConfig;
import net.xstopho.resource_backpacks.config.common.BackpackConfig;
import net.xstopho.resource_backpacks.config.common.ChestLootConfig;
import net.xstopho.resource_backpacks.config.common.EntityConfig;
import net.xstopho.resource_backpacks.modifier.ChestLootModifier;
import net.xstopho.resource_backpacks.registries.*;
import net.xstopho.resourceconfigapi.api.ConfigRegistry;
import net.xstopho.resourcelibrary.modifier.LootTableModifier;
import net.xstopho.resourcelibrary.registration.ResourcePackRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackpackConstants {
    public static final String MOD_ID = "resource_backpacks";
    public static final String MOD_NAME = "Resource Backpacks";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    private static final LootTableModifier modifier = LootTableModifier.getInstance();

    public static void commonInit() {
        ConfigRegistry.register(ClientConfig.class, MOD_ID);

        ConfigRegistry.register(BackpackConfig.class, MOD_ID);
        ConfigRegistry.register(ChestLootConfig.class, MOD_ID);
        ConfigRegistry.register(EntityConfig.class, MOD_ID);

        ChestLootModifier.initLootModifier(modifier);

        DataComponentRegistry.init();

        BlockRegistry.init();
        BlockEntityRegistry.init();

        MenuTypeRegistry.init();

        CreativeTabRegistry.init();
    }

    public static void clientInit() {
        MenuScreens.register(MenuTypeRegistry.LEATHER_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.COPPER_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.GOLD_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.IRON_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.DIAMOND_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.NETHERITE_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.END_MENU.get(), BackpackMenuScreen::new);
    }

    public static void packInit() {
        ResourcePackRegistry packRegistry = ResourcePackRegistry.getInstance(MOD_ID);
        packRegistry.register("resource_backpacks_ore_ui", "Ore UI");
        packRegistry.register("resource_backpacks_ore_ui_dark", "Ore UI Dark");
        packRegistry.register("resource_backpacks_vanilla_dark", "Vanilla Dark");
    }

    public static Identifier of(String id) {

        return Identifier.fromNamespaceAndPath(MOD_ID, id);
    }

    public static <T extends CustomPacketPayload> CustomPacketPayload.Type<T> type(String id) {
        return new CustomPacketPayload.Type<>(of(id));
    }
}
