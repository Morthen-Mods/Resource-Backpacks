package net.xstopho.resource_backpacks.mixin.smithing;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.xstopho.resource_backpacks.components.BackpackContainerContent;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SmithingTransformRecipe.class)
public abstract class SmithingTransformRecipeMixin {

    @Inject(method = "assemble", at = @At("HEAD"), cancellable = true)
    private void onAssemble(SmithingRecipeInput input, HolderLookup.Provider registries, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack oldBackpack = input.base();

        if (oldBackpack.getItem() instanceof BackpackItem) {
            ItemStack newBackpack = ((SmithingTransformRecipeAccessor) this).getResult().copy();

            if (newBackpack.getItem() instanceof BackpackItem) {
                BackpackContainerContent container = oldBackpack.get(DataComponentsRegistry.BACKPACK_CONTAINER.get());
                newBackpack.set(DataComponentsRegistry.BACKPACK_CONTAINER.get(), container);
                cir.setReturnValue(newBackpack);
            }
        }
    }
}
