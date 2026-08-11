package net.morthen.resource_backpacks.mixin.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen.ItemPickerMenu;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.CreativeModeTab;
import net.morthen.resource_backpacks.client.slot.BackpackSlot;
import net.morthen.resource_backpacks.client.slot.SlotExtension;
import net.morthen.resource_backpacks.client.slot.SlotWrapperExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin extends AbstractContainerScreen<ItemPickerMenu> {

    @Unique
    private final Identifier SLOT = Identifier.withDefaultNamespace("textures/gui/sprites/container/slot.png");

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


    @Inject(method = "extractBackground(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V", at = @At("TAIL"))
    private void resource_backpacks$renderSlot(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        if (selectedTab.getType() == CreativeModeTab.Type.INVENTORY) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, SLOT, this.leftPos + 126, this.topPos + 19,
                    0, 0, 18, 18, 18, 18);
        }
    }
}
