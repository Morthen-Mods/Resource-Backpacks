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
import net.xstopho.resource_backpacks.registries.ItemRegistry;

import java.util.concurrent.CompletableFuture;

public class  RecipeProv extends RecipeProvider {

    public RecipeProv(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    @Override
    public void buildRecipes() {
        createBackpackCraftingRecipe(Items.CHEST, BackpackItemTags.BACKPACK_LEATHER_INGREDIENT, ItemRegistry.BACKPACK_LEATHER.get());
        createBackpackCraftingRecipe(ItemRegistry.BACKPACK_LEATHER.get(), Ingredient.of(Items.COPPER_INGOT), ItemRegistry.BACKPACK_COPPER.get());
        createBackpackCraftingRecipe(ItemRegistry.BACKPACK_COPPER.get(), Ingredient.of(Items.GOLD_INGOT), ItemRegistry.BACKPACK_GOLD.get());
        createBackpackCraftingRecipe(ItemRegistry.BACKPACK_GOLD.get(), Ingredient.of(Items.IRON_INGOT), ItemRegistry.BACKPACK_IRON.get());
        createBackpackCraftingRecipe(ItemRegistry.BACKPACK_IRON.get(), Ingredient.of(Items.DIAMOND), ItemRegistry.BACKPACK_DIAMOND.get());

        createBackpackSmithingRecipe(ItemRegistry.BACKPACK_DIAMOND.get(), Items.NETHERITE_INGOT, ItemRegistry.BACKPACK_NETHERITE.get());

        this.shaped(RecipeCategory.MISC, ItemRegistry.BACKPACK_ENDER.get(), 1)
                .pattern("DCD").pattern("EBE").pattern("DCD")
                .define('D', Items.DIAMOND)
                .define('C', Items.ENDER_CHEST)
                .define('E', Items.ENDER_EYE)
                .define('B', ItemRegistry.BACKPACK_IRON.get())
                .unlockedBy(getHasName(ItemRegistry.BACKPACK_IRON.get()), has(ItemRegistry.BACKPACK_IRON.get()))
                .save(output, location("crafting/" + getSimpleRecipeName(ItemRegistry.BACKPACK_ENDER.get())));
    }

    private void createBackpackCraftingRecipe(ItemLike input, Ingredient upgradeMaterial, ItemLike output) {
        this.shaped(RecipeCategory.MISC, output, 1)
                .pattern("UUU").pattern("UIU").pattern("UUU")
                .define('U', upgradeMaterial).define('I', input)
                .unlockedBy(getHasName(input), has(input))
                .save(this.output, location("crafting/" + getSimpleRecipeName(output)));
    }

    private void createBackpackCraftingRecipe(ItemLike input, TagKey<Item> upgradeMaterial, ItemLike output) {
        this.shaped(RecipeCategory.MISC, output, 1)
                .pattern("UUU").pattern("UIU").pattern("UUU")
                .define('U', upgradeMaterial).define('I', input)
                .unlockedBy(getHasName(input), has(input))
                .save(this.output, location("crafting/" + getSimpleRecipeName(output)));
    }

    private void createBackpackSmithingRecipe(ItemLike input, ItemLike upgradeMaterial, ItemLike output) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(input), Ingredient.of(upgradeMaterial), RecipeCategory.MISC, output.asItem())
                .unlocks(getHasName(input), has(input))
                .save(this.output, location("smithing/" + getSimpleRecipeName(output)));
    }

    private ResourceKey<Recipe<?>> location(String id) {
        return ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, id));
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new RecipeProv(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Resource Backpacks Recipes";
        }
    }
}
