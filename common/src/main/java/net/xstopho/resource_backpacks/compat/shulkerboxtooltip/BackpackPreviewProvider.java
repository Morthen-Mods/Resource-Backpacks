package net.xstopho.resource_backpacks.compat.shulkerboxtooltip;

import com.misterpemodder.shulkerboxtooltip.api.PreviewContext;
import com.misterpemodder.shulkerboxtooltip.api.provider.PreviewProvider;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.components.BackpackContainerContent;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;

import java.util.List;

public class BackpackPreviewProvider implements PreviewProvider {
    @Override
    public boolean shouldDisplay(PreviewContext context) {
        return !getInventory(context).isEmpty();
    }

    @Override
    public List<ItemStack> getInventory(PreviewContext context) {
        ItemStack stack = context.stack();
        
        BackpackContainerContent containerContent = stack.get(DataComponentsRegistry.BACKPACK_CONTAINER.get());
        if (containerContent != null) {
            return containerContent.stream().toList();
        }
        return List.of();
    }

    @Override
    public int getInventoryMaxSize(PreviewContext context) {
        ItemStack stack = context.stack();
        if (stack.getItem() instanceof BackpackItem backpack) {
            return backpack.getLevel().getRows() * backpack.getLevel().getColumns();
        }
        return 27;
    }

    @Override
    public int getMaxRowSize(PreviewContext context) {
        return ((BackpackItem) context.stack().getItem()).getLevel().getColumns();
    }
}
