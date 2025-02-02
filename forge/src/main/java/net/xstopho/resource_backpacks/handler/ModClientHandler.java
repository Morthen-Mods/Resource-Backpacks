package net.xstopho.resource_backpacks.handler;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.client.BackpackModel;
import net.xstopho.resource_backpacks.client.PlayerBackpackRenderLayer;
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
}
