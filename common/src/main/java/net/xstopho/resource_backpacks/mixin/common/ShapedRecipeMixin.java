package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.xstopho.resource_backpacks.backpack.BackpackItem;
import net.xstopho.resource_backpacks.util.BackpackLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapedRecipe.class)
public abstract class ShapedRecipeMixin {

    @Shadow
    public abstract ItemStack getResultItem(HolderLookup.Provider registries);

    @Inject(method = "assemble", at = @At("HEAD"), cancellable = true)
    private void onAssemble(CraftingInput input, HolderLookup.Provider registries, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack result = getResultItem(registries);

        if (result.getItem() instanceof BackpackItem backpackItem) {
            ItemStack backpack = input.getItem(4);

            if (backpack.getItem() instanceof BackpackItem) {
                ItemContainerContents container = backpack.get(DataComponents.CONTAINER);

                if (container != null) {
                    if (backpackItem.getBackpackLevel().equals(BackpackLevel.END) && !emptyContainer(container)) {
                        cir.setReturnValue(new ItemStack(Items.AIR));
                    } else {
                        result.set(DataComponents.CONTAINER, container);
                        cir.setReturnValue(result);
                    }
                }
            }
        }
    }

    private boolean emptyContainer(ItemContainerContents container) {
        for (ItemStack stack : container.stream().toList()) {
            if (stack.getItem() != Items.AIR) {
                return false;
            }
        }
        return true;
    }
}
