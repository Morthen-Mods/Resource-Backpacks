package net.xstopho.resource_backpacks.mixin.client;

import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen.SlotWrapper;
import net.minecraft.world.inventory.Slot;
import net.xstopho.resource_backpacks.client.slot.BackpackSlotExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SlotWrapper.class)
public abstract class SlotWrapperMixin implements BackpackSlotExtension {

    @Shadow
    Slot target;

    @Override
    public Slot getTarget() {
        return target;
    }
}
