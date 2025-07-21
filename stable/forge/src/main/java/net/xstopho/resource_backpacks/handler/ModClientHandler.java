package net.xstopho.resource_backpacks.handler;

import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.client.model.BackpackModel;
import net.xstopho.resource_backpacks.client.renderer.ArmorStandBackpackLayer;
import net.xstopho.resource_backpacks.client.renderer.CreeperBackpackLayer;
import net.xstopho.resource_backpacks.client.renderer.PlayerBackpackLayer;
import net.xstopho.resource_backpacks.client.renderer.ZombieBackpackLayer;
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
        event.getSkins().forEach(model -> {
            PlayerRenderer renderer = event.getPlayerSkin(model);
            if (renderer != null) renderer.addLayer(new PlayerBackpackLayer(renderer, event.getContext().getModelSet()));
        });

        BuiltInRegistries.ENTITY_TYPE.forEach(entityType -> {
            EntityRenderer<?> entityRenderer = event.getEntityRenderer((EntityType<? extends LivingEntity>) entityType);
            if (entityRenderer instanceof CreeperRenderer creeper) creeper.addLayer(new CreeperBackpackLayer(creeper, event.getContext().getModelSet()));
            if (entityRenderer instanceof ZombieRenderer zombie) zombie.addLayer(new ZombieBackpackLayer(zombie, event.getContext().getModelSet()));
            if (entityRenderer instanceof ArmorStandRenderer armorStand) armorStand.addLayer(new ArmorStandBackpackLayer(armorStand, event.getContext().getModelSet()));
        });
    }
}
