package net.xstopho.resource_backpacks.client.slot;

import net.minecraft.world.inventory.Slot;

public interface BackpackSlotExtension {
    Slot getTarget();
    void setPosition(int x, int y);
}
