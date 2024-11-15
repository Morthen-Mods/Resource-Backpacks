package net.xstopho.resource_backpacks.handler;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.network.OpenBackpackPayload;

@EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModHandler {

    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar payload = event.registrar(BackpackConstants.MOD_ID);

        payload.playToServer(OpenBackpackPayload.PACKET_TYPE, OpenBackpackPayload.PACKET_CODEC, OpenBackpackPayload::apply);
    }
}
