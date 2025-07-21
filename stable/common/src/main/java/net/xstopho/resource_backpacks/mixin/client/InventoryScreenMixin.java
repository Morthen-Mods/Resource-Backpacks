package net.xstopho.resource_backpacks.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.xstopho.resource_backpacks.client.slot.BackpackSlot;
import net.xstopho.resource_backpacks.client.slot.SlotExtension;
import net.xstopho.resource_backpacks.client.util.SlotPointer;
import net.xstopho.resource_backpacks.config.client.ClientConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends EffectRenderingInventoryScreen<InventoryMenu> implements RecipeUpdateListener {
    public InventoryScreenMixin(InventoryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Inject(method = "renderBg(Lnet/minecraft/client/gui/GuiGraphics;FII)V", at = @At("TAIL"))
    private void resource_backpacks$renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY, CallbackInfo ci) {
        SlotPointer pointer = this.resource_backpacks$getSlotPOinter();

        for (Slot slot : this.menu.slots) {
            if (slot instanceof BackpackSlot) {
                ((SlotExtension) slot).setPosition(pointer.x(), pointer.y());
            }
        }
    }

    @Unique
    private SlotPointer resource_backpacks$getSlotPOinter() {
        return switch (ClientConfig.position) {
            case BOTTOM_LEFT -> new SlotPointer(26, 62);
            case BOTTOM_RIGHT -> new SlotPointer(59, 62);
            case TOP_LEFT -> new SlotPointer(26, 8);
            case TOP_RIGHT -> new SlotPointer(59, 8);
        };
    }
}
