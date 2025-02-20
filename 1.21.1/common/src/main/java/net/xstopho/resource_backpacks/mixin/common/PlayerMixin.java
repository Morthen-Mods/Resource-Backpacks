package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.client.slot.BackpackSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements BackpackHolder {

    @Shadow @Final
    public InventoryMenu inventoryMenu;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void resource_backpacks$readData(CompoundTag tag, CallbackInfo info) {
        for (Slot slot : this.inventoryMenu.slots) {
            if (slot instanceof BackpackSlot) {
                slot.set(this.getBackpack());
            }
        }
    }
}
