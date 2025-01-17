package net.xstopho.resource_backpacks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.xstopho.resource_backpacks.backpack.tooltip.CompactClientTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.CompactTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.InventoryClientTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.InventoryTooltipComponent;
import net.xstopho.resource_backpacks.client.BackpackModel;
import net.xstopho.resource_backpacks.client.BackpackRenderLayer;
import net.xstopho.resource_backpacks.client.PlayerBackpackRenderLayer;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.network.payloads.EnderChestResponsePayload;
import net.xstopho.resource_backpacks.network.payloads.OpenBackpackPayload;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

public class ResourceBackpacksClient implements ClientModInitializer {

    private final KeyMapping openBackpack = KeyBindingHelper.registerKeyBinding(KeyMappingRegistry.OPEN_BACKPACK);

    @Override
    public void onInitializeClient() {
        BackpackConstants.clientInit();
        registerTooltipComponents();
        registerClientPayloads();
        registerKeyMapping();
        registerRendering();
    }

    private void registerClientPayloads() {
        ClientPlayNetworking.registerGlobalReceiver(EnderChestResponsePayload.TYPE, (payload, context) -> EnderChestResponsePayload.handle(payload));
    }

    private void registerRendering() {
        EntityModelLayerRegistry.registerModelLayer(BackpackModel.BACKPACK_LAYER, BackpackModel::createLayer);

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityRenderer instanceof PlayerRenderer renderer) {
                registrationHelper.register(new PlayerBackpackRenderLayer(renderer, context.getModelSet()));
            }
        });
    }

    private void registerTooltipComponents() {
        TooltipComponentCallback.EVENT.register(component -> {
            if (component instanceof CompactTooltipComponent data) {
                return new CompactClientTooltipComponent(data);
            }

            if (component instanceof InventoryTooltipComponent data) {
                return new InventoryClientTooltipComponent(data);
            }
            return null;
        });
    }

    private void registerKeyMapping() {
        KeyBindingHelper.registerKeyBinding(KeyMappingRegistry.SHOW_COMPACT_PREVIEW);
        KeyBindingHelper.registerKeyBinding(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW);

        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            if (openBackpack.consumeClick()) {
                BackpackNetwork.INSTANCE.sendToServer(new OpenBackpackPayload());
            }
        });
    }
}
