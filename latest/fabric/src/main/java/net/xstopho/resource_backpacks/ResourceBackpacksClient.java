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
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.xstopho.resource_backpacks.backpack.tooltip.CompactClientTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.InventoryClientTooltipComponent;
import net.xstopho.resource_backpacks.client.model.BackpackModel;
import net.xstopho.resource_backpacks.client.renderer.ArmorStandBackpackLayer;
import net.xstopho.resource_backpacks.client.renderer.CreeperBackpackLayer;
import net.xstopho.resource_backpacks.client.renderer.PlayerBackpackLayer;
import net.xstopho.resource_backpacks.client.renderer.ZombieBackpackLayer;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.network.payloads.EnderChestResponsePayload;
import net.xstopho.resource_backpacks.network.payloads.OpenBackpackPayload;
import net.xstopho.resource_backpacks.network.payloads.SyncEntityBackpackPayload;
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
        ClientPlayNetworking.registerGlobalReceiver(SyncEntityBackpackPayload.TYPE, (payload, context) -> SyncEntityBackpackPayload.handle(payload));
    }

    private void registerRendering() {
        EntityModelLayerRegistry.registerModelLayer(BackpackModel.BACKPACK_LAYER, BackpackModel::createLayer);

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, renderer, helper, context) -> {
            if (renderer instanceof AvatarRenderer<?> player) helper.register(new PlayerBackpackLayer(player, context.getModelSet()));
            if (renderer instanceof ArmorStandRenderer armorStand) helper.register(new ArmorStandBackpackLayer(armorStand, context.getModelSet()));
            if (renderer instanceof ZombieRenderer zombie) helper.register(new ZombieBackpackLayer(zombie, context.getModelSet()));
            if (renderer instanceof CreeperRenderer creeper) helper.register(new CreeperBackpackLayer(creeper, context.getModelSet()));
        });
    }

    private void registerTooltipComponents() {
        TooltipComponentCallback.EVENT.register(component -> {
            if (component instanceof CompactClientTooltipComponent.CompactTooltipComponent data) {
                return new CompactClientTooltipComponent(data);
            }

            if (component instanceof InventoryClientTooltipComponent.InventoryTooltipComponent data) {
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
