package net.xstopho.resource_backpacks.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xstopho.resource_backpacks.BackpackConstants;
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
}
