package net.xstopho.resource_backpacks.handler;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.network.EnderChestRequestPayload;
import net.xstopho.resource_backpacks.network.EnderChestResponsePayload;
import net.xstopho.resource_backpacks.network.OpenBackpackPayload;

@EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModHandler {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar payload = event.registrar(BackpackConstants.MOD_ID);

        payload.playToServer(OpenBackpackPayload.TYPE, OpenBackpackPayload.CODEC, OpenBackpackPayload::apply);
        payload.playToServer(EnderChestRequestPayload.TYPE, EnderChestRequestPayload.CODEC, EnderChestRequestPayload::apply);

        payload.playToClient(EnderChestResponsePayload.TYPE, EnderChestResponsePayload.CODEC, EnderChestResponsePayload::apply);
    }
}
