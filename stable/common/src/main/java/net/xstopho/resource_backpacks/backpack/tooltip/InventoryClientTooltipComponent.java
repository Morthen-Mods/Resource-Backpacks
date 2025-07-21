package net.xstopho.resource_backpacks.backpack.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.xstopho.resource_backpacks.backpack.util.BackpackLevel;
import net.xstopho.resource_backpacks.client.util.BackpackClientUtils;

import java.util.List;

public class InventoryClientTooltipComponent extends BaseClientTooltipComponent {
    public record InventoryTooltipComponent(ItemContainerContents content, BackpackLevel level) implements TooltipComponent {}

    private final BackpackLevel level;
    private List<ItemStack> items;

    public InventoryClientTooltipComponent(InventoryTooltipComponent component) {
        this.level = component.level();
        this.items = component.content().stream().toList();
        if (level.equals(BackpackLevel.END)) {
            Player player = BackpackClientUtils.getPlayer();
            this.items = getEnderChestItems(player);
        }
    }

    @Override
    public void renderPreview(Font font, int x, int y, GuiGraphics guiGraphics) {
        int xOffset = 0;
        int yOffset = 0;

        for (ItemStack stack : items) {
            renderDecoratedItem(font, stack, stack.getCount(), x + xOffset, y + yOffset, guiGraphics);
            xOffset += 18;
            if (xOffset == getWidth(font)) {
                xOffset = 0;
                yOffset += 18;
            }
        }
    }

    @Override
    public int getHeight() {
        return this.level.getRows() * 18;
    }

    @Override
    public int getWidth(Font font) {
        return this.level.getColumns() * 18;
    }
}
