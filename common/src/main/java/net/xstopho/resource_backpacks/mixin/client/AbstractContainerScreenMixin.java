package net.xstopho.resource_backpacks.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.client.slot.BackpackSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {

    private static final ResourceLocation BACKPACK_SPRITE = BackpackConstants.of("textures/gui/sprites/container/backpack.png");
    private static final ResourceLocation SLOT = ResourceLocation.withDefaultNamespace("textures/gui/sprites/container/slot.png");

    @Inject(at = @At("TAIL"), method = "renderSlot")
    public void renderSlot(GuiGraphics guiGraphics, Slot slot, CallbackInfo info) {
        if (slot instanceof BackpackSlot) {
            if ((Object) this instanceof InventoryScreen) {
                if (!slot.hasItem()) {
                    guiGraphics.blit(BACKPACK_SPRITE, slot.x - 1, slot.y - 1, 0, 0, 18, 18, 18, 18);
                }
            }
        }
    }
}
