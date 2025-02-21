package net.xstopho.resource_backapcks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.xstopho.resource_backapcks.provider.BackpackRecipesProvider;
import net.xstopho.resource_backapcks.provider.BackpackTagsProvider;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.BlockRegistry;

import java.util.concurrent.CompletableFuture;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus eventBus) {
        BlockRegistry.init();
    }


    @EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class Datagen {
        @SubscribeEvent
        public static void generateData(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            PackOutput packOutput = generator.getPackOutput();
            CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

            generator.addProvider(event.includeServer(), new BackpackRecipesProvider.Runner(packOutput, provider));
            generator.addProvider(event.includeServer(), new BackpackTagsProvider.ItemTags(packOutput, provider, existingFileHelper));
            generator.addProvider(event.includeServer(), new BackpackTagsProvider.BlockTags(packOutput, provider, existingFileHelper));
        }
    }
}
