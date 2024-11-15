package net.xstopho.resource_backpacks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.xstopho.resource_backpacks.registries.BlockRegistry;

import java.util.concurrent.CompletableFuture;

public class BackpackRecipeProvider extends RecipeProvider {

    private RecipeOutput recipeOutput;

    public BackpackRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {
        this.recipeOutput = recipeOutput;

        backpackRecipe(Items.CHEST, BackpackItemTags.BACKPACK_LEATHER_INGREDIENT, BlockRegistry.BACKPACK_LEATHER.get());
        backpackRecipe(BlockRegistry.BACKPACK_LEATHER.get(), BackpackItemTags.COPPER_INGOTS, BlockRegistry.BACKPACK_COPPER.get());
        backpackRecipe(BlockRegistry.BACKPACK_COPPER.get(), BackpackItemTags.GOLD_INGOTS, BlockRegistry.BACKPACK_GOLD.get());
        backpackRecipe(BlockRegistry.BACKPACK_GOLD.get(), BackpackItemTags.IRON_INGOTS, BlockRegistry.BACKPACK_IRON.get());
        backpackRecipe(BlockRegistry.BACKPACK_IRON.get(), BackpackItemTags.DIAMONDS, BlockRegistry.BACKPACK_DIAMOND.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_NETHERITE.get())
                .pattern("NDN").pattern("DBD").pattern("NDN")
                .define('N', BackpackItemTags.NETHERITE_INGOTS)
                .define('D', BackpackItemTags.DIAMONDS)
                .define('B', BlockRegistry.BACKPACK_DIAMOND.get())
                .unlockedBy("has_diamond_backpack", has(BlockRegistry.BACKPACK_DIAMOND.get()))
                .save(recipeOutput, getSimpleRecipeName(BlockRegistry.BACKPACK_NETHERITE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_END.get())
                .pattern("DCD").pattern("EBE").pattern("DCD")
                .define('D', Items.DIAMOND)
                .define('C', Items.ENDER_CHEST)
                .define('E', Items.ENDER_EYE)
                .define('B', BlockRegistry.BACKPACK_IRON.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_IRON.get()), has(BlockRegistry.BACKPACK_IRON.get()))
                .save(recipeOutput, getSimpleRecipeName(BlockRegistry.BACKPACK_END.get()));
    }

    private void backpackRecipe(ItemLike input, TagKey<Item> upgradeMaterial, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .pattern("UUU").pattern("UBU").pattern("UUU")
                .define('U', upgradeMaterial)
                .define('B', input)
                .unlockedBy(getHasName(input), has(input))
                .save(this.recipeOutput, getSimpleRecipeName(output));
    }
}
