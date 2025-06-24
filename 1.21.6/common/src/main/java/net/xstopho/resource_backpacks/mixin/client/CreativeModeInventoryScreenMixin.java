package net.xstopho.resource_backpacks.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen.ItemPickerMenu;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.CreativeModeTab;
import net.xstopho.resource_backpacks.client.slot.BackpackSlot;
import net.xstopho.resource_backpacks.client.slot.SlotExtension;
import net.xstopho.resource_backpacks.client.slot.SlotWrapperExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin extends AbstractContainerScreen<ItemPickerMenu> {

    @Unique
    private final ResourceLocation SLOT = ResourceLocation.withDefaultNamespace("textures/gui/sprites/container/slot.png");

    @Shadow
    private static CreativeModeTab selectedTab;

    public CreativeModeInventoryScreenMixin(ItemPickerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Inject(at = @At(value = "INVOKE", target = "net/minecraft/world/inventory/Slot.<init>(Lnet/minecraft/world/Container;III)V"), method = "selectTab")
    private void resource_backpacks$addCreativeBackpackSlot(CreativeModeTab tab, CallbackInfo info) {
        for (Slot slot : this.menu.slots) {
            if (((SlotWrapperExtension) slot).getTarget() instanceof BackpackSlot) {
                ((SlotExtension) slot).setPosition(127, 20);
            }
        }
    }

    @Inject(method = "renderBg(Lnet/minecraft/client/gui/GuiGraphics;FII)V", at = @At("TAIL"))
    private void resource_backpacks$renderSlot(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY, CallbackInfo info) {
        if (selectedTab.getType() == CreativeModeTab.Type.INVENTORY) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SLOT, this.leftPos + 126, this.topPos + 19,
                    0, 0, 18, 18, 18, 18);
        }
    }
}
