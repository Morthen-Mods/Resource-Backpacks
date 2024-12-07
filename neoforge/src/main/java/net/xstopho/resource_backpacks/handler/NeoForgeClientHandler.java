package net.xstopho.resource_backpacks.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.network.OpenBackpackPayload;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

@EventBusSubscriber(modid = BackpackConstants.MOD_ID, value = Dist.CLIENT)
public class NeoForgeClientHandler {

    @SubscribeEvent
    public static void registerClientTickEvents(ClientTickEvent.Post event) {
        Player player = Minecraft.getInstance().player;
        if (KeyMappingRegistry.OPEN_BACKPACK.consumeClick()) {
            PacketDistributor.sendToServer(new OpenBackpackPayload());
        }
    }
}
