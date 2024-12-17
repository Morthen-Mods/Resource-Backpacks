package net.xstopho.resource_backpacks.backpack.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.component.ItemContainerContents;
import net.xstopho.resource_backpacks.util.BackpackLevel;

public record InventoryTooltipComponent(ItemContainerContents content, BackpackLevel level) implements TooltipComponent {
}
