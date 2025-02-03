package net.xstopho.resource_backpacks.mixin.client;

import net.minecraft.world.inventory.Slot;
import net.xstopho.resource_backpacks.client.slot.BackpackSlotExtension;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Slot.class)
public abstract class SlotMixin implements BackpackSlotExtension {

    @Shadow @Final @Mutable
    public int x;

    @Shadow @Final @Mutable
    public int y;

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
