package net.xstopho.resource_backpacks.client.slot;

import net.minecraft.world.item.ItemStack;

//@Deprecated(forRemoval = true, since = "0.13.0-BETA")
public interface BackpackHolder {
    ItemStack resource_backpack$getBackpack();
    void resource_backpack$setBackpack(ItemStack backpack);
}
