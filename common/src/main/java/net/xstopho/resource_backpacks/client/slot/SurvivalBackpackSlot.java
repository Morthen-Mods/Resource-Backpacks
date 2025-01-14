package net.xstopho.resource_backpacks.client.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.backpack.BackpackItem;

public class SurvivalBackpackSlot extends Slot {

    public SurvivalBackpackSlot(Container container, int x, int y) {
        super(container, container.getContainerSize() - 1, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() instanceof BackpackItem;
    }
}
