package net.xstopho.resource_backpacks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.BlockRegistry;
import net.xstopho.resourcelibrary.util.TagUtil;

import java.util.concurrent.CompletableFuture;

public class BackpackRecipeProvider extends RecipeProvider {
    public BackpackRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    @Override
    public void buildRecipes() {
        backpackRecipe(Items.CHEST, BackpackItemTags.BACKPACK_LEATHER_INGREDIENT, BlockRegistry.BACKPACK_LEATHER.get());
        backpackRecipe(BlockRegistry.BACKPACK_LEATHER.get(), TagUtil.createItemTag("copper_ingots"), BlockRegistry.BACKPACK_COPPER.get());
        backpackRecipe(BlockRegistry.BACKPACK_COPPER.get(), TagUtil.createItemTag("gold_ingots"), BlockRegistry.BACKPACK_GOLD.get());
        backpackRecipe(BlockRegistry.BACKPACK_GOLD.get(), TagUtil.createItemTag("iron_ingots"), BlockRegistry.BACKPACK_IRON.get());
        backpackRecipe(BlockRegistry.BACKPACK_IRON.get(), TagUtil.createItemTag("diamonds"), BlockRegistry.BACKPACK_DIAMOND.get());

        this.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_END.get(), 1)
                .pattern("DCD").pattern("EBE").pattern("DCD")
                .define('D', Items.DIAMOND)
                .define('C', Items.ENDER_CHEST)
                .define('E', Items.ENDER_EYE)
                .define('B', BlockRegistry.BACKPACK_IRON.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_IRON.get()), has(BlockRegistry.BACKPACK_IRON.get()))
                .save(output, key(BlockRegistry.BACKPACK_END.get()));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(BlockRegistry.BACKPACK_IRON.get()), Ingredient.of(Items.NETHERITE_INGOT),
                RecipeCategory.MISC, BlockRegistry.BACKPACK_NETHERITE.get().asItem())
                .unlocks(getHasName(BlockRegistry.BACKPACK_DIAMOND.get()), has(BlockRegistry.BACKPACK_DIAMOND.get()))
                .save(this.output, key(BlockRegistry.BACKPACK_NETHERITE.get()));
    }

    private void backpackRecipe(TagKey<Item> input, TagKey<Item> upgradeMaterial, ItemLike output) {
        this.shaped(RecipeCategory.MISC, output)
                .pattern("UUU").pattern("UBU").pattern("UUU")
                .define('U', upgradeMaterial)
                .define('B', input)
                .unlockedBy("has_chest", has(input))
                .save(this.output, key(output));
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
            return new BackpackRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Backpack Recipe Provider";
        }
    }
}
