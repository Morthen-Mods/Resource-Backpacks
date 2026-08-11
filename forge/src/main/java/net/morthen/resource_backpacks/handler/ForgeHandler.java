package net.morthen.resource_backpacks.handler;

import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.morthen.resource_backpacks.BackpackConstants;
import net.morthen.resource_backpacks.modifier.EntityModifier;

@Mod.EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeHandler {

    @SubscribeEvent
    public static void registerEntityLoad(EntityJoinLevelEvent event) {
        EntityModifier.modifyEntities(event.getEntity());
    }
}
