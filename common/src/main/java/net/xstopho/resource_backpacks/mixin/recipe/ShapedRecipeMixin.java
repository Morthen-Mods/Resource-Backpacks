package net.xstopho.resource_backpacks.mixin.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapedRecipe.class)
public abstract class ShapedRecipeMixin {

    @Inject(method = "assemble", at = @At("HEAD"), cancellable = true)
    private void onAssemble(CraftingInput input, HolderLookup.Provider registries, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack result = ((ShapedRecipeAccessor) this).cracker_getResult().copy();

        if (result.getItem() instanceof BackpackItem) {
            ItemStack backpack = input.getItem(4);

            result.set(DataComponentsRegistry.BACKPACK_CONTAINER.get(), backpack.get(DataComponentsRegistry.BACKPACK_CONTAINER.get()));
            cir.setReturnValue(result);
        }
    }
}
