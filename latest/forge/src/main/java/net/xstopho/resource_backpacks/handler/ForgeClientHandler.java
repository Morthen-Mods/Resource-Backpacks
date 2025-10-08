package net.xstopho.resource_backpacks.handler;

import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.client.model.BackpackModel;
import net.xstopho.resource_backpacks.client.renderer.ArmorStandBackpackLayer;
import net.xstopho.resource_backpacks.client.renderer.CreeperBackpackLayer;
import net.xstopho.resource_backpacks.client.renderer.PlayerBackpackLayer;
import net.xstopho.resource_backpacks.client.renderer.ZombieBackpackLayer;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.network.payloads.OpenBackpackPayload;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

@Mod.EventBusSubscriber(modid = BackpackConstants.MOD_ID, value = Dist.CLIENT)
public class ForgeClientHandler {

    @SubscribeEvent
    public static void clientTickEvent(TickEvent.ClientTickEvent.Post event) {
        if (KeyMappingRegistry.OPEN_BACKPACK.consumeClick()) {
            BackpackNetwork.INSTANCE.sendToServer(new OpenBackpackPayload());
        }
    }

    @SubscribeEvent
    public static void registerRenderLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BackpackModel.BACKPACK_LAYER, BackpackModel::createLayer);
    }

    @SubscribeEvent
    public static void registerKeyMapping(RegisterKeyMappingsEvent event) {
        event.register(KeyMappingRegistry.OPEN_BACKPACK);
        event.register(KeyMappingRegistry.SHOW_COMPACT_PREVIEW);
        event.register(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW);
    }

    @SubscribeEvent
    public static void addRenderLayer(EntityRenderersEvent.AddLayers event) {
        event.getModelTypes().forEach(model -> {
            AvatarRenderer<? extends Player> renderer = event.getPlayerRenderer(model);
            if (renderer != null) renderer.addLayer(new PlayerBackpackLayer(renderer, event.getContext().getModelSet()));
        });

        BuiltInRegistries.ENTITY_TYPE.forEach(entityType -> {
            EntityRenderer<?, ?> entityRenderer = event.getEntityRenderer((EntityType<? extends LivingEntity>) entityType);
            if (entityRenderer instanceof CreeperRenderer creeper) creeper.addLayer(new CreeperBackpackLayer(creeper, event.getContext().getModelSet()));
            if (entityRenderer instanceof ZombieRenderer zombie) zombie.addLayer(new ZombieBackpackLayer(zombie, event.getContext().getModelSet()));
            if (entityRenderer instanceof ArmorStandRenderer armorStand) armorStand.addLayer(new ArmorStandBackpackLayer(armorStand, event.getContext().getModelSet()));
        });
    }
}
