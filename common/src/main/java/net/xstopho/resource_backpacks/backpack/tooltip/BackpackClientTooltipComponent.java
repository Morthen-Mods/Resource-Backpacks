package net.xstopho.resource_backpacks.backpack.tooltip;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;
import net.xstopho.resource_backpacks.util.BackpackLevel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BackpackClientTooltipComponent implements ClientTooltipComponent {

    private List<StackHolder> compactedItems;
    private List<ItemStack> items;
    private final BackpackLevel level;

    public BackpackClientTooltipComponent(BackpackTooltipComponent component) {
        this.items = component.content().stream().toList();
        this.level = component.level();
        this.compactedItems = getCompactItemList(this.items);

        if (level.equals(BackpackLevel.END)) {
            BackpackConstants.requestEnderChestContainer();

            Player player = Minecraft.getInstance().player;

            if (player != null) {
                PlayerEnderChestContainer enderItems = player.getEnderChestInventory();

                this.items = enderItems.getItems();
                this.compactedItems = getCompactItemList(items);
            }
        }

    }

    @Override
    public int getHeight(Font font) {
        int height;
        if (this.hasKeyDown() && !this.items.isEmpty()) {
            height = level.getRows() * 18;
        } else {
            height = (int) Math.ceil((double) compactedItems.size() / 10) * 18;
        }

        return height + 3;
    }

    @Override
    public int getWidth(Font font) {
        if (this.hasKeyDown() && !this.items.isEmpty()) {
            return level.getColumns() * 18;
        }
        return compactedItems.size() < 10 ? compactedItems.size() * 18 : 180;
    }

    @Override
    public void renderImage(Font font, int x, int y, int width, int height, GuiGraphics guiGraphics) {
        renderPreview(font, x, y, guiGraphics);
    }

    private void renderPreview(Font font, int x, int y, GuiGraphics guiGraphics) {
        int xOffset = 0;
        int yOffset = 0;

        if (this.hasKeyDown()) {
            for (ItemStack stack : items) {
                renderDecoratedItem(font, stack, stack.getCount(), x + xOffset + 1, y + yOffset, guiGraphics);
                xOffset += 18;
                if (xOffset == getWidth(font)) {
                    xOffset = 0;
                    yOffset += 18;
                }
            }
        } else {
            for (StackHolder holder : compactedItems) {
                renderDecoratedItem(font, holder.getStack(), holder.getCount(), x + xOffset + 1, y + yOffset, guiGraphics);
                xOffset += 18;
                if (xOffset == getWidth(font)) {
                    xOffset = 0;
                    yOffset += 18;
                }
            }
        }
    }

    private List<StackHolder> getCompactItemList(List<ItemStack> items) {
        List<StackHolder> holderList = new ArrayList<>();

        for (ItemStack stack : items) {
            boolean combined = false;
            if (stack != ItemStack.EMPTY) {
                for (StackHolder holder : holderList) {
                    if (stack.getItem() == holder.getStack().getItem() && !stack.isDamageableItem()) {
                        combined = holder.combine(stack.getCount());
                    }
                }
                if (!combined) holderList.add(new StackHolder(stack, stack.getCount()));
            }
        }

        holderList.sort(Comparator.comparingInt(StackHolder::getCount));

        return holderList.reversed();
    }

    private void renderDecoratedItem(Font font, ItemStack stack, int count, int x, int y, GuiGraphics guiGraphics) {
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

    private boolean hasKeyDown() {
        return BackpackConstants.hasKeyDown(KeyMappingRegistry.SHOW_INVENTORY_PREVIEW);
    }

    private Component getReadableNumber(int count) {
        return count > 1000
            ? Component.literal(String.format("%.1fk", count / 1000.0))
            : Component.literal(String.valueOf(count));
    }

    private static class StackHolder {

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
