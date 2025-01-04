package net.xstopho.resource_backpacks.handler;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.ArmorStandRenderState;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.client.BackpackModel;
import net.xstopho.resource_backpacks.client.BackpackRenderLayer;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

@Mod.EventBusSubscriber(modid = BackpackConstants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientHandler {

    @SubscribeEvent
    public static void registerKeyMapping(RegisterKeyMappingsEvent event) {
        event.register(KeyMappingRegistry.OPEN_BACKPACK);
        event.register(KeyMappingRegistry.SHOW_COMPACT_PREVIEW);
        event.register(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW);
    }

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        BackpackConstants.clientInit();
    }

    @SubscribeEvent
    public static void registerRenderLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BackpackModel.BACKPACK_LAYER, BackpackModel::createLayer);
    }

    @SubscribeEvent
    public static void addRenderLayer(EntityRenderersEvent.AddLayers event) {
        LivingEntityRenderer<Player, PlayerRenderState, HumanoidModel<PlayerRenderState>> playerRenderer = event.getPlayerSkin(PlayerSkin.Model.WIDE);
        if (playerRenderer != null){
            playerRenderer.addLayer(new BackpackRenderLayer<>(playerRenderer, event.getEntityModels()));
        }

        LivingEntityRenderer<Player, PlayerRenderState, HumanoidModel<PlayerRenderState>> playerSlimRenderer = event.getPlayerSkin(PlayerSkin.Model.SLIM);
        if (playerSlimRenderer != null) {
            playerSlimRenderer.addLayer(new BackpackRenderLayer<>(playerSlimRenderer, event.getEntityModels()));
        }

        LivingEntityRenderer<ArmorStand, ArmorStandRenderState, HumanoidModel<ArmorStandRenderState>> armorStandRenderer = event.getEntityRenderer(EntityType.ARMOR_STAND);
        if (armorStandRenderer != null) {
            armorStandRenderer.addLayer(new BackpackRenderLayer<>(armorStandRenderer, event.getEntityModels()));
        }
    }
}
