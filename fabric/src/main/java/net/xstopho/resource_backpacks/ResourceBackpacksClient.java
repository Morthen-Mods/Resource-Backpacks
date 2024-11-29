package net.xstopho.resource_backpacks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.KeyMapping;
import net.xstopho.resource_backpacks.backpack.tooltip.BackpackClientTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.BackpackTooltipComponent;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.network.OpenBackpackPayload;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

public class ResourceBackpacksClient implements ClientModInitializer {

    private final KeyMapping openBackpack = KeyBindingHelper.registerKeyBinding(KeyMappingRegistry.OPEN_BACKPACK);

    @Override
    public void onInitializeClient() {
        BackpackNetwork.initClientPayloads();
        initKeyMapping();

        KeyBindingHelper.registerKeyBinding(KeyMappingRegistry.SHOW_COMPACT_PREVIEW);
        KeyBindingHelper.registerKeyBinding(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW);

        BackpackConstants.clientInit();

        TooltipComponentCallback.EVENT.register(component -> {
            if (component instanceof BackpackTooltipComponent data) {
                return new BackpackClientTooltipComponent(data);
            }
            return null;
        });
    }

    private void initKeyMapping() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            if (openBackpack.consumeClick()) {
                ClientPlayNetworking.send(new OpenBackpackPayload(1));
            }
        });
    }
}
