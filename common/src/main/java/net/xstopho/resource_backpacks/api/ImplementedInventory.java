package net.xstopho.resource_backpacks.api;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ImplementedInventory extends WorldlyContainer {

    NonNullList<ItemStack> getItems();

    static ImplementedInventory of(NonNullList<ItemStack> items) {
        return () -> items;
    }

    default int @NotNull [] getSlotsForFace(@NotNull Direction side) {
        int size = this.getItems().size();
        int[] result = new int[size];

        for(int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    default boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction side) {
        return false;
    }

    default boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
        return false;
    }

    default int getContainerSize() {
        return this.getItems().size();
    }

    default boolean isEmpty() {
        for(int i = 0; i < this.getContainerSize(); ++i) {
            ItemStack stack = this.getItem(i);
            if (!stack.isEmpty()) return false;
        }

        return true;
    }

    default @NotNull ItemStack getItem(int slot) {
        return this.getItems().get(slot);
    }

    default @NotNull ItemStack removeItem(int slot, int count) {
        ItemStack result = ContainerHelper.removeItem(this.getItems(), slot, count);
        if (!result.isEmpty()) this.setChanged();

        return result;
    }

    default @NotNull ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.getItems(), slot);
    }

    default void setItem(int slot, @NotNull ItemStack stack) {
        this.getItems().set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

    }

    default void clearContent() {
        this.getItems().clear();
    }

    default void setChanged() {}

    default boolean stillValid(@NotNull Player player) {
        return true;
    }
}
