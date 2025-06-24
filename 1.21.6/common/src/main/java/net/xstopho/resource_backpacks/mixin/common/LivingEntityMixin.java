package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements BackpackHolder {

    @Unique
    private ItemStack backpack = ItemStack.EMPTY;

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public ItemStack getBackpack() {
        return backpack;
    }

    @Override
    public void setBackpack(ItemStack backpack) {
        this.backpack = backpack;
    }

    @Inject(method = "die(Lnet/minecraft/world/damagesource/DamageSource;)V", at = @At("TAIL"))
    public void resource_backpacks$die(DamageSource source, CallbackInfo info) {
        if (source.getEntity() instanceof Player) {
            this.dropBackpack(this.level(), this.getOnPos());
        }
    }

    @Inject(method = "addAdditionalSaveData(Lnet/minecraft/world/level/storage/ValueOutput;)V", at = @At("TAIL"))
    public void resource_backpacks$saveData(ValueOutput valueOutput, CallbackInfo ci) {
        this.saveBackpackToValueOutput(valueOutput);
    }

    @Inject(method = "readAdditionalSaveData(Lnet/minecraft/world/level/storage/ValueInput;)V", at = @At("TAIL"))
    public void resource_backpacks$readData(ValueInput valueInput, CallbackInfo ci) {
        this.readBackpackFromValueInput(valueInput);
    }
}
