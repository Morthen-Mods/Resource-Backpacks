package net.morthen.template.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.morthen.template.CommonConstants;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class TemplateProvider {
    public static class CustomRecipes extends RecipeProvider {
        protected CustomRecipes(HolderLookup.Provider registries, RecipeOutput output) {
            super(registries, output);
        }

        @Override
        protected void buildRecipes() {
            this.shapeless(RecipeCategory.MISC, Items.DIAMOND, 2)
                    .requires(Items.DIAMOND)
                    .unlockedBy("has_diamond", has(Items.DIAMOND))
                    .save(this.output, path(getItemName(Items.DIAMOND)));
        }

        protected ResourceKey<Recipe<?>> path(String path) {
            return ResourceKey.create(Registries.RECIPE, CommonConstants.of(path));
        }

        public static class Runner extends RecipeProvider.Runner {
            public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
                super(packOutput, registries);
            }

            @Override
            protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
                return new CustomRecipes(provider, recipeOutput);
            }

            @Override
            public @NonNull String getName() {
                return "Template Recipe Provider";
            }
        }
    }

    public static class CustomItemTags extends ItemTagsProvider {
        public CustomItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider, CommonConstants.MOD_ID);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            this.tag(ItemTags.LEAVES)
                    .add(Items.BONE);
        }
    }
}
