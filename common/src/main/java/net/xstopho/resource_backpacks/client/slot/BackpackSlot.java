package net.xstopho.resource_backpacks.client.slot;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.BackpackItem;

public class BackpackSlot extends Slot {
    private static final ResourceLocation BACKPACK_SPRITE = BackpackConstants.of("textures/gui/sprites/container/backpack.png");
    private static final ResourceLocation SLOT = ResourceLocation.withDefaultNamespace("textures/gui/sprites/container/slot.png");

    public BackpackSlot(Container container, int x, int y) {
        super(container, container.getContainerSize() - 1, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() instanceof BackpackItem;
    }

    public void renderSurvival(GuiGraphics guiGraphics) {
        renderSlot(guiGraphics, false);
    }

    public void renderCreative(GuiGraphics guiGraphics) {
        renderSlot(guiGraphics, true);
    }

    private void renderSlot(GuiGraphics guiGraphics, boolean creative) {
        if (creative) {
            guiGraphics.blit(SLOT, this.x - 1, this.y - 1, 0, 0, 18, 18, 18, 18);
        }
        if (!hasItem()) {
            guiGraphics.blit(BACKPACK_SPRITE, this.x - 1, this.y - 1, 0, 0, 18, 18, 18, 18);
        }
    }
}
