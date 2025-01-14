package net.xstopho.resource_backpacks.client.slot;

import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen.SlotWrapper;

public class CreativeBackpackSlot extends SlotWrapper {
    public CreativeBackpackSlot(SurvivalBackpackSlot original, int x, int y) {
        super(original, original.getContainerSlot(), x, y);
    }
}
