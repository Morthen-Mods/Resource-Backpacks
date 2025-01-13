package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Inventory.class)
public class InventoryMixin {

    @ModifyArg(method = "<init>", index = 0, at = @At(value = "INVOKE", ordinal = 2, target = "Lnet/minecraft/core/NonNullList;withSize(ILjava/lang/Object;)Lnet/minecraft/core/NonNullList;"))
    private int modifyOffhandList(int size) {
        return 2;
    }
}
