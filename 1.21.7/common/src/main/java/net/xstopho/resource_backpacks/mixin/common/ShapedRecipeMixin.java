package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.xstopho.resource_backpacks.backpack.BackpackItem;
import net.xstopho.resource_backpacks.backpack.component.BackpackContainerContents;
import net.xstopho.resource_backpacks.backpack.util.BackpackLevel;
import net.xstopho.resource_backpacks.mixin.accessor.ShapedRecipeAccessor;
import net.xstopho.resource_backpacks.registries.DataComponentRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapedRecipe.class)
public abstract class ShapedRecipeMixin {

    @Inject(at = @At("HEAD"), cancellable = true,
            method = "assemble(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;")
    private void onAssemble(CraftingInput input, HolderLookup.Provider registries, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack result = ((ShapedRecipeAccessor) this).getResult().copy();

        if (result.getItem() instanceof BackpackItem backpackItem) {
            ItemStack backpack = input.getItem(4);

            if (backpack.getItem() instanceof BackpackItem) {
                BackpackContainerContents container = backpack.get(DataComponentRegistry.BACKPACK_CONTAINER.get());

                if (container != null) {
                    if (backpackItem.getBackpackLevel().equals(BackpackLevel.END) && !emptyContainer(container)) {
                        cir.setReturnValue(new ItemStack(Items.AIR));
                    } else {
                        result.set(DataComponentRegistry.BACKPACK_CONTAINER.get(), container);
                        cir.setReturnValue(result);
                    }
                }
            }
        }
    }

    @Unique
    private boolean emptyContainer(BackpackContainerContents container) {
        for (ItemStack stack : container.toList()) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }
}
