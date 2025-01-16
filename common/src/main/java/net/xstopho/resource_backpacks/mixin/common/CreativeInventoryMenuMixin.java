package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen.ItemPickerMenu;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen.SlotWrapper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.CreativeModeTab;
import net.xstopho.resource_backpacks.client.slot.SurvivalBackpackSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeInventoryMenuMixin extends AbstractContainerScreen<ItemPickerMenu> {

    @Shadow private Slot destroyItemSlot;
    @Shadow static SimpleContainer CONTAINER;

    public CreativeInventoryMenuMixin(ItemPickerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Inject(at = @At("TAIL"), method = "selectTab")
    private void resource_backpack$addCreativeBackpackSlot(CreativeModeTab tab, CallbackInfo info) {
        if (tab.getType() == CreativeModeTab.Type.INVENTORY) {
            this.menu.slots.clear();
            AbstractContainerMenu invMenu = this.minecraft.player.inventoryMenu;

            for (int index = 0; index < invMenu.slots.size(); ++index) {
                int x = -2000, y = -2000;
                if (index >= 5 && index < 9) {
                    x = 54 + ((index - 5) / 2) * 54;
                    y = 6 + ((index - 5) % 2) * 27;
                } else if (index == 45) {
                    x = 35;
                    y = 20;
                } else if (index >= 9) {
                    x = 9 + ((index - 9) % 9) * 18;
                    y = (index >= 36) ? 112 : 54 + ((index - 9) / 9) * 18;
                }

                Slot slot = invMenu.getSlot(index);
                if (slot instanceof SurvivalBackpackSlot) continue;
                this.menu.slots.add(new SlotWrapper(slot, index, x, y));
            }

            this.destroyItemSlot = new Slot(CONTAINER, 0, 173, 112);
            this.menu.slots.add(this.destroyItemSlot);
        }
    }

//    @Inject(at = @At("TAIL"), method = "selectTab")
//    private void resource_backpack$addCreativeBackpackSlot(CreativeModeTab tab, CallbackInfo info) {
//        if (tab.getType() == CreativeModeTab.Type.INVENTORY) {
//            for (Slot slot : this.minecraft.player.inventoryMenu.slots) {
//                if (slot instanceof SurvivalBackpackSlot original) {
//                    this.menu.slots.remove(this.menu.slots.size() - 2);
//                    this.menu.slots.add(new CreativeBackpackSlot(original, 127, 19));
//                }
//            }
//        }
//    }

//    @Inject(at = @At(value = "INVOKE", target = "net/minecraft/world/inventory/Slot.<init>(Lnet/minecraft/world/Container;III)V"), method = "selectTab")
//    private void resource_backpacks$addBackpackSlot(CreativeModeTab tab, CallbackInfo info) {
//        Slot slot = this.minecraft.player.inventoryMenu.slots.getLast();
//        this.menu.slots.remove(slot);
//        this.menu.slots.add(new CreativeBackpackSlot(slot, 127, 19));
//    }
}
