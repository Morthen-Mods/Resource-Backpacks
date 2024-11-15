package net.xstopho.resource_backpacks.compat;

import com.misterpemodder.shulkerboxtooltip.api.PreviewContext;
import com.misterpemodder.shulkerboxtooltip.api.color.ColorKey;
import com.misterpemodder.shulkerboxtooltip.api.provider.PreviewProvider;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.xstopho.resource_backpacks.items.BackpackItem;
import net.xstopho.resource_backpacks.util.BackpackLevel;

import java.util.List;

public class BackpackPreviewProvider implements PreviewProvider {
    @Override
    public boolean shouldDisplay(PreviewContext previewContext) {
        return !getInventory(previewContext).isEmpty();
    }

    @Override
    public List<ItemStack> getInventory(PreviewContext previewContext) {
        ItemStack stack = previewContext.stack();
        Player player = previewContext.owner();

        if (stack.getItem() instanceof BackpackItem backpackItem) {

            if (backpackItem.getBackpackLevel().equals(BackpackLevel.END) && player != null) {
                PlayerEnderChestContainer enderChest = player.getEnderChestInventory();

                return enderChest.getItems();
            }

            ItemContainerContents containerContent = stack.get(DataComponents.CONTAINER);
            if (containerContent != null) {

                return containerContent.stream().toList();
            }
        }

        return List.of();
    }

    @Override
    public int getInventoryMaxSize(PreviewContext previewContext) {
        ItemStack stack = previewContext.stack();

        if (stack.getItem() instanceof BackpackItem backpackItem) {
            return backpackItem.getBackpackLevel().getSize();
        }

        return 0;
    }

    @Override
    public int getCompactMaxRowSize(PreviewContext context) {
        return ((BackpackItem) context.stack().getItem()).getBackpackLevel().getColumns();
    }

    @Override
    public ColorKey getWindowColorKey(PreviewContext context) {
        ItemStack stack = context.stack();

        if (stack.getItem() instanceof BackpackItem backpackItem) {
            if (backpackItem.getBackpackLevel().equals(BackpackLevel.END)) {
                return ColorKey.ENDER_CHEST;
            }
        }

        return ColorKey.DEFAULT;
    }
}
