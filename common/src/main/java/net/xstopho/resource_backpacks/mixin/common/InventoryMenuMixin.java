package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractCraftingMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.xstopho.resource_backpacks.client.slot.BackpackSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryMenu.class)
public abstract class InventoryMenuMixin extends AbstractCraftingMenu {

    public InventoryMenuMixin(MenuType<?> menuType, int containerId, int width, int height) {
        super(menuType, containerId, width, height);
    }

    @Inject(at = @At("TAIL"), method = "<init>")
    public void init(Inventory playerInventory, boolean active, final Player player, CallbackInfo info) {
        this.addSlot(new BackpackSlot(new SimpleContainer(1), player, 26, 62));
    }
}