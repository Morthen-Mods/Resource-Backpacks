package net.xstopho.resource_backpacks.mixin.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
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
public abstract class InventoryScreenMixin extends AbstractRecipeBookScreen<InventoryMenu> {
    public InventoryScreenMixin(InventoryMenu menu, RecipeBookComponent<?> recipeBookComponent, Inventory playerInventory, Component title) {
        super(menu, recipeBookComponent, playerInventory, title);
    }

    @Inject(method = "extractBackground(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V", at = @At("TAIL"))
    private void resource_backpacks$renderBg(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
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
