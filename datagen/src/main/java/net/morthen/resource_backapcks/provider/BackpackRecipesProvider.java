package net.morthen.resource_backapcks.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.morthen.resource_backpacks.BackpackConstants;
import net.morthen.resource_backpacks.registries.BlockRegistry;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class BackpackRecipesProvider extends RecipeProvider {
    public BackpackRecipesProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        this.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_LEATHER.get())
                .pattern("LLL").pattern("LCL").pattern("LLL")
                .define('L', Tags.Items.LEATHERS)
                .define('C', Tags.Items.CHESTS_WOODEN)
                .unlockedBy(getHasName(Items.LEATHER), has(Tags.Items.LEATHERS))
                .save(this.output, getRecipeKey(BlockRegistry.BACKPACK_LEATHER.get()));

        this.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_COPPER.get())
                .pattern("CCC").pattern("CBC").pattern("CCC")
                .define('C', Tags.Items.INGOTS_COPPER)
                .define('B', BlockRegistry.BACKPACK_LEATHER.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_LEATHER.get()), has(BlockRegistry.BACKPACK_LEATHER.get()))
                .save(this.output, getRecipeKey(BlockRegistry.BACKPACK_COPPER.get()));

        this.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_IRON.get())
                .pattern("III").pattern("IBI").pattern("III")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('B', BlockRegistry.BACKPACK_COPPER.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_COPPER.get()), has(BlockRegistry.BACKPACK_COPPER.get()))
                .save(this.output, getRecipeKey(BlockRegistry.BACKPACK_IRON.get()));

        this.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_GOLD.get())
                .pattern("GSG").pattern("GBG").pattern("GSG")
                .define('G', Tags.Items.INGOTS_GOLD)
                .define('B', BlockRegistry.BACKPACK_IRON.get())
                .define('S', Items.SHULKER_SHELL)
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_IRON.get()), has(BlockRegistry.BACKPACK_IRON.get()))
                .save(this.output, getRecipeKey(BlockRegistry.BACKPACK_GOLD.get()));

        this.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_DIAMOND.get())
                .pattern("DSD").pattern("SBS").pattern("DSD")
                .define('D', Tags.Items.GEMS_DIAMOND)
                .define('B', BlockRegistry.BACKPACK_GOLD.get())
                .define('S', Items.SHULKER_SHELL)
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_GOLD.get()), has(BlockRegistry.BACKPACK_GOLD.get()))
                .save(this.output, getRecipeKey(BlockRegistry.BACKPACK_DIAMOND.get()));

        this.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_NETHERITE.get())
                .pattern("NDN").pattern("SBS").pattern("NDN")
                .define('N', Tags.Items.INGOTS_NETHERITE)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .define('B', BlockRegistry.BACKPACK_DIAMOND.get())
                .define('S', Tags.Items.SHULKER_BOXES)
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_DIAMOND.get()), has(BlockRegistry.BACKPACK_DIAMOND.get()))
                .save(this.output, getRecipeKey(BlockRegistry.BACKPACK_NETHERITE.get()));

        this.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_END.get())
                .pattern("DCD").pattern("EBE").pattern("DCD")
                .define('C', Tags.Items.CHESTS_ENDER)
                .define('E', Items.ENDER_EYE)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .define('B', BlockRegistry.BACKPACK_GOLD.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_GOLD.get()), has(BlockRegistry.BACKPACK_GOLD.get()))
                .save(this.output, getRecipeKey(BlockRegistry.BACKPACK_END.get()));
    }

    private ResourceKey<Recipe<?>> getRecipeKey(ItemLike item) {
        return ResourceKey.create(Registries.RECIPE, BackpackConstants.of(getSimpleRecipeName(item)));
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new BackpackRecipesProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Backpack Recipe Provider";
        }
    }
}
