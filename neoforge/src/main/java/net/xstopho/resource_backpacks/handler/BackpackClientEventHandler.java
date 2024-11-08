package net.xstopho.resource_backpacks.handler;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_backpacks.rendering.container.BackpackContainerScreen;


@EventBusSubscriber(modid = BackpackConstants.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class BackpackClientEventHandler {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(MenuTypeRegistry.LEATHER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        event.register(MenuTypeRegistry.COPPER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        event.register(MenuTypeRegistry.GOLD_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        event.register(MenuTypeRegistry.IRON_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        event.register(MenuTypeRegistry.DIAMOND_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        event.register(MenuTypeRegistry.NETHERITE_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        event.register(MenuTypeRegistry.ENDER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KeyMappingRegistry.OPEN_BACKPACK);
    }
}
