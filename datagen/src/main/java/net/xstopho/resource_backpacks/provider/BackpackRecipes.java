package net.xstopho.resource_backpacks.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.BlockRegistry;

import java.util.concurrent.CompletableFuture;

public class BackpackRecipes extends RecipeProvider {
    public BackpackRecipes(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    @Override
    public void buildRecipes() {
        backpackRecipe(Items.CHEST, BackpackItemTags.BACKPACK_LEATHER_INGREDIENT, BlockRegistry.BACKPACK_LEATHER.get());
        backpackRecipe(BlockRegistry.BACKPACK_LEATHER.get(), BackpackItemTags.COPPER_INGOTS, BlockRegistry.BACKPACK_COPPER.get());
        backpackRecipe(BlockRegistry.BACKPACK_COPPER.get(), BackpackItemTags.GOLD_INGOTS, BlockRegistry.BACKPACK_GOLD.get());
        backpackRecipe(BlockRegistry.BACKPACK_GOLD.get(), BackpackItemTags.IRON_INGOTS, BlockRegistry.BACKPACK_IRON.get());
        backpackRecipe(BlockRegistry.BACKPACK_IRON.get(),BackpackItemTags.DIAMONDS, BlockRegistry.BACKPACK_DIAMOND.get());

        this.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_NETHERITE.get())
                .pattern("NDN").pattern("DBD").pattern("NDN")
                .define('N', BackpackItemTags.NETHERITE_INGOTS)
                .define('D', BackpackItemTags.DIAMONDS)
                .define('B', BlockRegistry.BACKPACK_DIAMOND.get())
                .unlockedBy("has_diamond_backpack", has(BlockRegistry.BACKPACK_DIAMOND.get()))
                .save(this.output, key(BlockRegistry.BACKPACK_NETHERITE.get()));

        this.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_END.get())
                .pattern("DCD").pattern("EBE").pattern("DCD")
                .define('D', Items.DIAMOND)
                .define('C', Items.ENDER_CHEST)
                .define('E', Items.ENDER_EYE)
                .define('B', BlockRegistry.BACKPACK_IRON.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_IRON.get()), has(BlockRegistry.BACKPACK_IRON.get()))
                .save(output, key(BlockRegistry.BACKPACK_END.get()));
    }

    private void backpackRecipe(ItemLike input, TagKey<Item> upgradeMaterial, ItemLike output) {
        this.shaped(RecipeCategory.MISC, output)
                .pattern("UUU").pattern("UBU").pattern("UUU")
                .define('U', upgradeMaterial)
                .define('B', input)
                .unlockedBy(getHasName(input), has(input))
                .save(this.output, key(output));
    }

    private ResourceKey<Recipe<?>> key(ItemLike output) {
        return ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, getSimpleRecipeName(output)));
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(packOutput, completableFuture);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new BackpackRecipes(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Backpack Recipe Provider";
        }
    }
}
