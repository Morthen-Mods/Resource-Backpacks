package net.xstopho.resource_backpacks.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import net.xstopho.resource_backpacks.client.slot.BackpackSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {
    @Inject(at = @At("TAIL"), method = "renderSlot")
    public void renderSlot(GuiGraphics guiGraphics, Slot slot, CallbackInfo info) {
        if (slot instanceof BackpackSlot backpackSlot) {
            backpackSlot.renderSurvival(guiGraphics);
        }
    }
}
