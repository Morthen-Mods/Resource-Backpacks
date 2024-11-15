package net.xstopho.resource_backpacks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.xstopho.resource_backpacks.network.OpenBackpackPayload;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

public class ResourceBackpacksClient implements ClientModInitializer {

    private final KeyMapping openBackpack = KeyBindingHelper.registerKeyBinding(KeyMappingRegistry.OPEN_BACKPACK);

    @Override
    public void onInitializeClient() {
        initKeyMapping();

        BackpackConstants.clientInit();
    }

    private void initKeyMapping() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            if (openBackpack.consumeClick()) {
                ClientPlayNetworking.send(new OpenBackpackPayload(1));
            }
        });
    }
}
