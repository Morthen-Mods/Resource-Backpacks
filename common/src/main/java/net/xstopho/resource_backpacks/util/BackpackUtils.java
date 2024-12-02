package net.xstopho.resource_backpacks.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class BackpackUtils {

    /**
     * Used to send a Loader specific request payload to get the EnderChest Inventory
     */
    public interface NetworkHook {
        void sendEnderChestRequest();
    }

    /**
     * Used to get the Items of the {@link net.minecraft.world.item.component.ItemContainerContents} since there
     * isn't a native method to get the NonNullList as it is.
     */
    public interface ItemContainerAccess {
        NonNullList<ItemStack> backpack$getItemsForPreview();
    }

    /**
     * Used to get the current set Key, because {@link net.minecraft.client.KeyMapping}
     * only allows to get the default key.
     */
    public interface KeyMappingAccess {
        InputConstants.Key getKey();
    }
}
