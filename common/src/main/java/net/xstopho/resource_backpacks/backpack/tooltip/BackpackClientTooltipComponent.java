package net.xstopho.resource_backpacks.backpack.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.util.BackpackLevel;
import net.xstopho.resource_backpacks.util.ItemContainerInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BackpackClientTooltipComponent implements ClientTooltipComponent {

    //TODO: Add small outline to slots
    // - eventually colorized slot texture for every backpack level
    // - add proper translation
    // - add customizable KeyBinds?
    // -

    private final ResourceLocation SLOT = ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, "textures/gui/container/slot.png");
    private final List<StackHolder> compactedItems;
    private final NonNullList<ItemStack> items;
    private final BackpackLevel level;

    public BackpackClientTooltipComponent(BackpackTooltipComponent component) {
        this.items = ((ItemContainerInterface) component.content()).getItems();
        this.compactedItems = getCompactItemList();
        this.level = component.level();
    }

    @Override
    public int getHeight(Font font) {
        if (Screen.hasAltDown()) {
            return level.getRows() * 18;
        }
        return (int) Math.ceil((double) compactedItems.size() / 10) * 18;
    }

    @Override
    public int getWidth(Font font) {
        if (Screen.hasAltDown()) {
            return level.getColumns() * 18;
        }
        return compactedItems.size() < 10 ? compactedItems.size() * 18 : 180;
    }

    @Override
    public void renderImage(Font font, int x, int y, int width, int height, GuiGraphics guiGraphics) {
        if (Screen.hasAltDown()) renderInventory(font, x, y, guiGraphics);
        else renderCompactInventory(font, x, y, guiGraphics);
    }

    private void renderCompactInventory(Font font, int x, int y, GuiGraphics guiGraphics) {
        guiGraphics.blit(RenderType::guiTextured, SLOT, x, y, 0f, 0f, getWidth(font), getHeight(font), 18, 18);
        int xOffset = 0;
        int yOffset = 0;
        for (StackHolder holder : compactedItems) {
            renderDecoratedItem(font, holder.getStack(), holder.getCount(), x + xOffset + 1, y + yOffset + 1, guiGraphics);
            xOffset += 18;
            if (xOffset == getWidth(font)) {
                xOffset = 0;
                yOffset += 18;
            }
        }
    }

    private void renderInventory(Font font, int x, int y, GuiGraphics guiGraphics) {
        guiGraphics.blit(RenderType::guiTextured, SLOT, x, y, 0f, 0f, getWidth(font), getHeight(font), 18, 18);
        int xOffset = 0;
        int yOffset = 0;
        for (ItemStack stack : items) {
            renderDecoratedItem(font, stack, stack.getCount(), x + xOffset + 1, y + yOffset + 1, guiGraphics);
            xOffset += 18;
            if (xOffset == getWidth(font)) {
                xOffset = 0;
                yOffset += 18;
            }
        }
    }

    private List<StackHolder> getCompactItemList() {
        List<StackHolder> holderList = new ArrayList<>();

        for (ItemStack stack : this.items) {
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
            String countText = String.valueOf(count);
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0f, 0f, 200f);
            guiGraphics.drawString(font, countText, x + 17 - font.width(countText), y + 9, -1, true);
            guiGraphics.pose().popPose();
        }
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
