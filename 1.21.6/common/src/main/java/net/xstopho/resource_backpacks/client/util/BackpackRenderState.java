package net.xstopho.resource_backpacks.client.util;

import net.minecraft.world.item.ItemStack;

public interface BackpackRenderState {
    ItemStack getBackpack();
    void setBackpack(ItemStack backpack);
}
