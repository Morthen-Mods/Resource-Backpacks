package net.xstopho.resource_backpacks.backpack.util;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.xstopho.resource_backpacks.backpack.api.ImplementedInventory;

public class BackpackInventory implements ImplementedInventory {

    private final NonNullList<ItemStack> items;
    private final BackpackLevel backpackLevel;
    private final ItemStack stack;

    public BackpackInventory(ItemStack stack, BackpackLevel backpackLevel) {
        this.items = NonNullList.withSize(backpackLevel.getSize(), ItemStack.EMPTY);
        this.backpackLevel = backpackLevel;
        this.stack = stack;
        ItemContainerContents container = stack.get(DataComponents.CONTAINER);
        if (container != null) {
            container.copyInto(items);
        }
    }

    @Override
    public int getContainerSize() {
        return backpackLevel.getSize();
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void setChanged() {
        this.stack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.items));
    }

}
