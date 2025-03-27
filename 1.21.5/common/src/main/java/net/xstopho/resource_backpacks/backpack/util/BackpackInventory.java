package net.xstopho.resource_backpacks.backpack.util;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.xstopho.resource_backpacks.backpack.api.ImplementedInventory;
import net.xstopho.resource_backpacks.backpack.component.BackpackContainerContents;
import net.xstopho.resource_backpacks.registries.DataComponentRegistry;

public class BackpackInventory implements ImplementedInventory {

    private final NonNullList<ItemStack> items;
    private final BackpackLevel backpackLevel;
    private final ItemStack stack;

    @SuppressWarnings("removal")
    public BackpackInventory(ItemStack stack, BackpackLevel backpackLevel) {
        this.items = NonNullList.withSize(backpackLevel.getSize(), ItemStack.EMPTY);
        this.backpackLevel = backpackLevel;
        this.stack = stack;
        //TODO: remove in a later update
        ItemContainerContents oldContainer = stack.get(DataComponents.CONTAINER);
        if (oldContainer != null) {
            stack.set(DataComponentRegistry.BACKPACK_CONTAINER.get(), new BackpackContainerContents(oldContainer));
            stack.remove(DataComponents.CONTAINER);
        }

        BackpackContainerContents container = stack.get(DataComponentRegistry.BACKPACK_CONTAINER.get());
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
        this.stack.set(DataComponentRegistry.BACKPACK_CONTAINER.get(), new BackpackContainerContents(items));
    }

}
