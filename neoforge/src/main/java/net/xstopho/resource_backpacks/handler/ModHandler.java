package net.xstopho.resource_backpacks.handler;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.datagen.BackpackItemTags;
import net.xstopho.resource_backpacks.datagen.BackpackRecipeProvider;
import net.xstopho.resource_backpacks.network.OpenBackpackPayload;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModHandler {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar payload = event.registrar(BackpackConstants.MOD_ID);

        payload.playToServer(OpenBackpackPayload.PACKET_TYPE, OpenBackpackPayload.PACKET_CODEC, OpenBackpackPayload::apply);
    }

    @SubscribeEvent
    public static void generateData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new BackpackRecipeProvider.Runner(packOutput, provider));
        generator.addProvider(event.includeServer(), new BackpackItemTags(packOutput, provider));
    }
}
