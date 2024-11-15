package net.xstopho.resource_backpacks;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.xstopho.resource_backpacks.datagen.BackpackItemTags;
import net.xstopho.resource_backpacks.datagen.BackpackRecipeProvider;

public class ResourceBackpacksDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(BackpackRecipeProvider.Runner::new);
        pack.addProvider(BackpackItemTags::new);
    }
}
