package net.xstopho.resource_backpacks.handler;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.modifier.EntityModifier;

@EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class NeoforgeHandler {

    @SubscribeEvent
    public static void registerEntityLoad(EntityJoinLevelEvent event) {
        EntityModifier.modifyEntities(event.getEntity());
    }
}
