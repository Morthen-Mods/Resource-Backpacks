package net.xstopho.resource_backpacks.backpack.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.client.util.BackpackClientUtils;

import java.util.List;

public abstract class BaseClientTooltipComponent implements ClientTooltipComponent {

    public abstract void renderPreview(Font font, int x, int y, GuiGraphics guiGraphics);

    @Override
    public void renderImage(Font font, int x, int y, int width, int height, GuiGraphics guiGraphics) {
        renderPreview(font, x, y, guiGraphics);
    }

    protected List<ItemStack> getEnderChestItems(Player player) {
        BackpackClientUtils.syncEnderChestInventory();

        if (player != null) {
            PlayerEnderChestContainer container = player.getEnderChestInventory();

            return container.getItems();
        }

        return List.of();
    }

    public void renderDecoratedItem(Font font, ItemStack stack, int count, int x, int y, GuiGraphics guiGraphics) {
        if (!stack.isEmpty()) {
            guiGraphics.pose().pushPose();
            guiGraphics.renderItem(stack, x, y);
            renderItemBar(stack, x, y, guiGraphics);
            renderItemCount(font, count, x, y, guiGraphics);
            guiGraphics.pose().popPose();
        }
    }

    private void renderItemBar(ItemStack stack, int x, int y, GuiGraphics guiGraphics) {
        if (stack.isBarVisible()) {
            int xPos = x + 2;
            int yPos = y + 13;
            guiGraphics.fill(RenderType.gui(), xPos, yPos, xPos + 13, yPos + 2, 200, -16777216);
            guiGraphics.fill(RenderType.gui(), xPos, yPos, xPos + stack.getBarWidth(), yPos + 1, 200, ARGB.opaque(stack.getBarColor()));
        }
    }

    private void renderItemCount(Font font, int count, int x, int y, GuiGraphics guiGraphics) {
        if (count != 1) {
            Component component = getReadableNumber(count);
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0f, 0f, 200f);
            guiGraphics.drawString(font, component, x + 17 - font.width(component), y + 9, -1, true);
            guiGraphics.pose().popPose();
        }
    }

    private Component getReadableNumber(int count) {
        return count > 1000
                ? Component.literal(String.format("%.1fk", count / 1000.0))
                : Component.literal(String.valueOf(count));
    }

    public static class StackHolder {

        private final ItemStack stack;
        private int count;

        public StackHolder(ItemStack stack, int count) {
            this.stack = stack;
            this.count = count;
        }

        public ItemStack getStack() {
            return stack;
        }

        public int getCount() {
            return count;
        }

        public boolean combine(int stackCount) {
            this.count += stackCount;
            return true;
        }
    }
}
