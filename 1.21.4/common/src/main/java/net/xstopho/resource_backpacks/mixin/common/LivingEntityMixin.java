package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements BackpackHolder {

    @Unique
    private Optional<ItemStack> backpack = Optional.empty();

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Optional<ItemStack> getBackpack() {
        return backpack;
    }

    @Override
    public void setBackpack(ItemStack backpack) {
        this.backpack = Optional.of(backpack);
    }

    @Inject(method = "die", at = @At("TAIL"))
    public void resource_backpacks$die(DamageSource source, CallbackInfo info) {
        if (source.getEntity() instanceof Player) {
            this.dropBackpack(this.level(), this.getOnPos());
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void resource_backpacks$saveData(CompoundTag tag, CallbackInfo info) {
        this.saveBackpackOnCompound(tag, this.registryAccess());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void resource_backpacks$readData(CompoundTag tag, CallbackInfo info) {
        this.readBackpackFromCompound(tag, this.registryAccess());
    }
}
