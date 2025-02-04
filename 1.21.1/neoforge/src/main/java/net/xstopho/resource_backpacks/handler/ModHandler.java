package net.xstopho.resource_backpacks.handler;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.network.payloads.*;

@EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModHandler {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registry = event.registrar(BackpackConstants.MOD_ID);

        registry.playToServer(OpenBackpackPayload.TYPE, OpenBackpackPayload.CODEC, (payload, context) -> OpenBackpackPayload.handle(payload, (ServerPlayer) context.player()));
        registry.playToServer(EnderChestRequestPayload.TYPE, EnderChestRequestPayload.CODEC, (payload, context) -> EnderChestRequestPayload.handle(payload, (ServerPlayer) context.player()));
        registry.playToServer(SyncCreativeSlotPayload.TYPE, SyncCreativeSlotPayload.CODEC, (payload, context) -> SyncCreativeSlotPayload.handle(payload, (ServerPlayer) context.player()));

        registry.playToClient(EnderChestResponsePayload.TYPE, EnderChestResponsePayload.CODEC, (payload, context) -> EnderChestResponsePayload.handle(payload));
        registry.playToClient(SyncEntityBackpackPayload.TYPE, SyncEntityBackpackPayload.CODEC, (payload, context) -> SyncEntityBackpackPayload.handle(payload));
    }
}
