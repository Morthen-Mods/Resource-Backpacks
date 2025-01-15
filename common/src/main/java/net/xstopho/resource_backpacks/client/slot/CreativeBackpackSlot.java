package net.xstopho.resource_backpacks.client.slot;

import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen.SlotWrapper;
import net.minecraft.world.inventory.Slot;

public class CreativeBackpackSlot extends SlotWrapper {
    public CreativeBackpackSlot(Slot original, int x, int y) {
        super(original, original.getContainerSlot(), x, y);
    }
}
