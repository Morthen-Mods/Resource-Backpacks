package net.xstopho.resource_backpacks;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.xstopho.resource_backpacks.datagen.ItemTagProv;
import net.xstopho.resource_backpacks.datagen.RecipeProv;

import java.util.concurrent.CompletableFuture;

public class ResourceBackpacksDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ReciProv::new);
        pack.addProvider(ItemTagProv::new);
    }

    private static class ReciProv extends FabricRecipeProvider {

        public ReciProv(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        public RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new RecipeProv(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Recipe Provider";
        }
    }
}
