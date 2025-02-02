package net.xstopho.resource_backpacks.handler;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.client.BackpackModel;
import net.xstopho.resource_backpacks.client.PlayerBackpackRenderLayer;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

@EventBusSubscriber(modid = BackpackConstants.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModClientHandler {

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        BackpackConstants.clientInit();
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KeyMappingRegistry.OPEN_BACKPACK);
        event.register(KeyMappingRegistry.SHOW_COMPACT_PREVIEW);
        event.register(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW);
    }

    @SubscribeEvent
    public static void registerRenderLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BackpackModel.BACKPACK_LAYER, BackpackModel::createLayer);
    }

    @SubscribeEvent
    public static void addRenderLayer(EntityRenderersEvent.AddLayers event) {
        LivingEntityRenderer<Player, PlayerRenderState, PlayerModel> playerRenderer = event.getSkin(PlayerSkin.Model.WIDE);
        if (playerRenderer != null){
            playerRenderer.addLayer(new PlayerBackpackRenderLayer(playerRenderer, event.getEntityModels()));
        }

        LivingEntityRenderer<Player, PlayerRenderState, PlayerModel> playerSlimRenderer = event.getSkin(PlayerSkin.Model.SLIM);
        if (playerSlimRenderer != null) {
            playerSlimRenderer.addLayer(new PlayerBackpackRenderLayer(playerSlimRenderer, event.getEntityModels()));
        }
    }
}
