package net.xstopho.resource_backpacks.handler;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.datagen.BlockTagProv;
import net.xstopho.resource_backpacks.datagen.ItemTagProv;
import net.xstopho.resource_backpacks.datagen.RecipeProv;

import java.util.concurrent.CompletableFuture;


@Mod.EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BackpackEventHandler {

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
