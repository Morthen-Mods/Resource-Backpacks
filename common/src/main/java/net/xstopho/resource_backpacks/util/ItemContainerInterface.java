package net.xstopho.resource_backpacks.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public interface ItemContainerInterface {

    NonNullList<ItemStack> getItems();
}
