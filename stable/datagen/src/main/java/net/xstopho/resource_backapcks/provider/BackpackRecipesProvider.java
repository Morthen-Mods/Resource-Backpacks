package net.xstopho.resource_backapcks.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.BlockRegistry;

import java.util.concurrent.CompletableFuture;

public class BackpackRecipesProvider extends RecipeProvider {
    public BackpackRecipesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_LEATHER.get())
                .pattern("LLL").pattern("LCL").pattern("LLL")
                .define('L', BackpackTagsProvider.ItemTags.LEATHER)
                .define('C', Tags.Items.CHESTS_WOODEN)
                .unlockedBy(getHasName(Items.LEATHER), has(BackpackTagsProvider.ItemTags.LEATHER))
                .save(recipeOutput, getRecipeLocation(BlockRegistry.BACKPACK_LEATHER.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_COPPER.get())
                .pattern("CCC").pattern("CBC").pattern("CCC")
                .define('C', BackpackTagsProvider.ItemTags.COPPER_INGOTS)
                .define('B', BlockRegistry.BACKPACK_LEATHER.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_LEATHER.get()), has(BlockRegistry.BACKPACK_LEATHER.get()))
                .save(recipeOutput, getRecipeLocation(BlockRegistry.BACKPACK_COPPER.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_GOLD.get())
                .pattern("GGG").pattern("GBG").pattern("GGG")
                .define('G', BackpackTagsProvider.ItemTags.GOLD_INGOTS)
                .define('B', BlockRegistry.BACKPACK_COPPER.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_COPPER.get()), has(BlockRegistry.BACKPACK_COPPER.get()))
                .save(recipeOutput, getRecipeLocation(BlockRegistry.BACKPACK_GOLD.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_IRON.get())
                .pattern("III").pattern("IBI").pattern("III")
                .define('I', BackpackTagsProvider.ItemTags.IRON_INGOTS)
                .define('B', BlockRegistry.BACKPACK_GOLD.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_GOLD.get()), has(BlockRegistry.BACKPACK_GOLD.get()))
                .save(recipeOutput, getRecipeLocation(BlockRegistry.BACKPACK_IRON.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_DIAMOND.get())
                .pattern("DDD").pattern("DBD").pattern("DDD")
                .define('D', BackpackTagsProvider.ItemTags.DIAMONDS)
                .define('B', BlockRegistry.BACKPACK_IRON.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_IRON.get()), has(BlockRegistry.BACKPACK_IRON.get()))
                .save(recipeOutput, getRecipeLocation(BlockRegistry.BACKPACK_DIAMOND.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_NETHERITE.get())
                .pattern("NDN").pattern("DBD").pattern("NDN")
                .define('N', BackpackTagsProvider.ItemTags.NETHERITE_INGOTS)
                .define('D', BackpackTagsProvider.ItemTags.DIAMONDS)
                .define('B', BlockRegistry.BACKPACK_DIAMOND.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_DIAMOND.get()), has(BlockRegistry.BACKPACK_DIAMOND.get()))
                .save(recipeOutput, getRecipeLocation(BlockRegistry.BACKPACK_NETHERITE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.BACKPACK_END.get())
                .pattern("DCD").pattern("EBE").pattern("DCD")
                .define('C', BackpackTagsProvider.ItemTags.ENDER_CHESTS)
                .define('E', Items.ENDER_EYE)
                .define('D', BackpackTagsProvider.ItemTags.DIAMONDS)
                .define('B', BlockRegistry.BACKPACK_IRON.get())
                .unlockedBy(getHasName(BlockRegistry.BACKPACK_IRON.get()), has(BlockRegistry.BACKPACK_IRON.get()))
                .save(recipeOutput, getRecipeLocation(BlockRegistry.BACKPACK_END.get()));
    }

    private ResourceLocation getRecipeLocation(ItemLike item) {
        return BackpackConstants.of(getSimpleRecipeName(item));
    }
}
