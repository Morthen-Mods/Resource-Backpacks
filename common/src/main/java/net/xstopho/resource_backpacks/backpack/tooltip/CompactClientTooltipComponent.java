package net.xstopho.resource_backpacks.backpack.tooltip;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.util.BackpackLevel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CompactClientTooltipComponent extends BaseClientTooltipComponent {

    private List<StackHolder> items;

    public CompactClientTooltipComponent(CompactTooltipComponent component) {
        List<ItemStack> contentItemList = component.content().stream().toList();
        this.items = getCompactItemList(contentItemList);

        if (component.level().equals(BackpackLevel.END)) {
            Player player = Minecraft.getInstance().player;

            List<ItemStack> enderChestItems = this.getEnderChestItems(player);
            this.items = getCompactItemList(enderChestItems);
        }
    }

    @Override
    public void renderPreview(Font font, int x, int y, GuiGraphics guiGraphics) {
        int xOffset = 0;
        int yOffset = 0;

        for (StackHolder holder : items) {
            renderDecoratedItem(font, holder.getStack(), holder.getCount(), x + xOffset, y + yOffset, guiGraphics);
            xOffset += 18;
            if (xOffset == getWidth(font)) {
                xOffset = 0;
                yOffset += 18;
            }
        }
    }

    @Override
    public int getHeight(Font font) {
        if (!items.isEmpty()) {
            return (int) Math.ceil(((double) items.size() / 10)) * 18;
        }
        return 0;
    }

    @Override
    public int getWidth(Font font) {
        return items.size() < 10 ? items.size() * 18 : 180;
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
}
