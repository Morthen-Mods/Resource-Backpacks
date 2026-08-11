package net.morthen.resource_backpacks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.morthen.resource_backpacks.backpack.tooltip.CompactClientTooltipComponent;
import net.morthen.resource_backpacks.backpack.tooltip.InventoryClientTooltipComponent;
import net.morthen.resource_backpacks.client.model.BackpackModel;
import net.morthen.resource_backpacks.client.renderer.ArmorStandBackpackLayer;
import net.morthen.resource_backpacks.client.renderer.CreeperBackpackLayer;
import net.morthen.resource_backpacks.client.renderer.PlayerBackpackLayer;
import net.morthen.resource_backpacks.client.renderer.ZombieBackpackLayer;
import net.morthen.resource_backpacks.network.BackpackNetwork;
import net.morthen.resource_backpacks.network.payloads.EnderChestResponsePayload;
import net.morthen.resource_backpacks.network.payloads.OpenBackpackPayload;
import net.morthen.resource_backpacks.network.payloads.SyncEntityBackpackPayload;
import net.morthen.resource_backpacks.registries.KeyMappingRegistry;

public class ResourceBackpacksClient implements ClientModInitializer {

    private final KeyMapping openBackpack = KeyMappingHelper.registerKeyMapping(KeyMappingRegistry.OPEN_BACKPACK);

    @Override
    public void onInitializeClient() {
        BackpackConstants.clientInit();
        BackpackConstants.packInit();
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
        ModelLayerRegistry.registerModelLayer(BackpackModel.BACKPACK_LAYER, BackpackModel::createLayer);

        LivingEntityRenderLayerRegistrationCallback.EVENT.register((_, renderer, helper, context) -> {
            if (renderer instanceof AvatarRenderer<?> player) helper.register(new PlayerBackpackLayer(player, context.getModelSet()));
            if (renderer instanceof ArmorStandRenderer armorStand) helper.register(new ArmorStandBackpackLayer(armorStand, context.getModelSet()));
            if (renderer instanceof ZombieRenderer zombie) helper.register(new ZombieBackpackLayer(zombie, context.getModelSet()));
            if (renderer instanceof CreeperRenderer creeper) helper.register(new CreeperBackpackLayer(creeper, context.getModelSet()));
        });
    }

    private void registerTooltipComponents() {
        ClientTooltipComponentCallback.EVENT.register(component -> {
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
        KeyMappingHelper.registerKeyMapping(KeyMappingRegistry.SHOW_COMPACT_PREVIEW);
        KeyMappingHelper.registerKeyMapping(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW);

        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            if (openBackpack.consumeClick()) {
                BackpackNetwork.INSTANCE.sendToServer(new OpenBackpackPayload());
            }
        });
    }
}
