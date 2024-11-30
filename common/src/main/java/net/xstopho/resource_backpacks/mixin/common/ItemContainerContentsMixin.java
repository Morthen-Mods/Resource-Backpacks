package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.xstopho.resource_backpacks.util.ItemContainerInterface;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemContainerContents.class)
public abstract class ItemContainerContentsMixin implements ItemContainerInterface {

    @Override
    public NonNullList<ItemStack> getItems() {
        return ((ItemContainerContentsAccessor) this).backpack$getItems();
    }
}
