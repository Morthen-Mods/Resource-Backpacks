package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.CreativeModeTab;
import net.xstopho.resource_backpacks.client.slot.CreativeBackpackSlot;
import net.xstopho.resource_backpacks.client.slot.SurvivalBackpackSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeInventoryMenuMixin extends EffectRenderingInventoryScreen<CreativeModeInventoryScreen.ItemPickerMenu> {
    public CreativeInventoryMenuMixin(CreativeModeInventoryScreen.ItemPickerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Inject(at = @At(value = "INVOKE", target = "net/minecraft/world/inventory/Slot.<init>(Lnet/minecraft/world/Container;III)V"), method = "selectTab")
    private void resource_backpack$addCreativeBackpackSlot(CreativeModeTab tab, CallbackInfo info) {
        if (tab.getType() == CreativeModeTab.Type.INVENTORY) {
            for (Slot slot : this.minecraft.player.inventoryMenu.slots) {
                if (slot instanceof SurvivalBackpackSlot original) {
                    this.menu.slots.add(new CreativeBackpackSlot(original, 127, 19));
                }
            }
        }
    }
}
