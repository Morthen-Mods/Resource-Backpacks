package net.xstopho.resource_backpacks.handler;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.datagen.BackpackItemTags;
import net.xstopho.resource_backpacks.datagen.BackpackRecipeProvider;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModHandler {

    @SubscribeEvent
    public static void generateData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new BackpackRecipeProvider(packOutput, provider));
        generator.addProvider(event.includeServer(), new BackpackItemTags(packOutput, provider));
    }
}
