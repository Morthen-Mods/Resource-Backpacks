package net.xstopho.resource_backpacks.custom.util;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.xstopho.resource_backpacks.api.ImplementedInventory;

public class BackpackInventory implements ImplementedInventory {
    private final ItemStack stack;
    private NonNullList<ItemStack> items;

    public BackpackInventory(ItemStack stack, NonNullList<ItemStack> items) {
        this.items = items;
        this.stack = stack;
        ItemContainerContents container = stack.get(DataComponents.CONTAINER);
        if (container != null) {
            container.copyInto(items);
        }
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
