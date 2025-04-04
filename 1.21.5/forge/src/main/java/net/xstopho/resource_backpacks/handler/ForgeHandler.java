package net.xstopho.resource_backpacks.handler;

import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.modifier.EntityModifier;

@Mod.EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeHandler {

    @SubscribeEvent
    public static void registerEntityLoad(EntityJoinLevelEvent event) {
        EntityModifier.modifyEntities(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        BackpackConstants.showMessage(event.getEntity());
    }
}
