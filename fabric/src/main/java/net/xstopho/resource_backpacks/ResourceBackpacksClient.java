package net.xstopho.resource_backpacks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.xstopho.resource_backpacks.network.packets.OpenBackpackPacket;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_backpacks.rendering.container.BackpackContainerScreen;

public class ResourceBackpacksClient implements ClientModInitializer {

    private final KeyMapping openBackpack = KeyBindingHelper.registerKeyBinding(KeyMappingRegistry.OPEN_BACKPACK);

    @Override
    public void onInitializeClient() {

        registerKeyMappings();

        MenuScreens.register(MenuTypeRegistry.LEATHER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.COPPER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.GOLD_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.IRON_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.DIAMOND_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.NETHERITE_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.ENDER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
    }

    private void registerKeyMappings() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openBackpack.consumeClick()) ClientPlayNetworking.send(new OpenBackpackPacket(1));
        });
    }
}
