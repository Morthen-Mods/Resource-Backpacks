package net.xstopho.resource_backpacks.handler;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.datagen.BlockTagProv;
import net.xstopho.resource_backpacks.datagen.ItemTagProv;
import net.xstopho.resource_backpacks.datagen.RecipeProv;
import net.xstopho.resource_backpacks.network.BackpackNetwork;

import java.util.concurrent.CompletableFuture;


@EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BackpackEventHandler {

    @SubscribeEvent
    public static void registerPacketHandler(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar payload = event.registrar(BackpackConstants.MOD_ID);

        BackpackNetwork.registerServer(payload);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new RecipeProv.Runner(output, provider));

        BlockTagProv blockTags = generator.addProvider(event.includeServer(), new BlockTagProv(output, provider, fileHelper));
        generator.addProvider(event.includeServer(), new ItemTagProv(output, provider, blockTags.contentsGetter(), fileHelper));
    }
}
