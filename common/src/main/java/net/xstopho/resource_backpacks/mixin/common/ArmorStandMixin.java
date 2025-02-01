package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.xstopho.resource_backpacks.backpack.BackpackItem;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.network.payloads.SyncEntityBackpackPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStand.class)
public abstract class ArmorStandMixin extends LivingEntity {

    @Shadow
    public abstract EquipmentSlot getClickedSlot(Vec3 vector);

    protected ArmorStandMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "brokenByAnything", at = @At("TAIL"))
    public void resource_backpacks$brokenByAnything(ServerLevel level, DamageSource source, CallbackInfo info) {
        ((BackpackHolder) this).dropBackpack(this);
    }

    @Inject(method = "interactAt", at = @At("HEAD"), cancellable = true)
    public void resource_backpacks$interactAt(Player player, Vec3 vec, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack handStack = player.getItemInHand(hand).copy();
        if (handStack.getItem() instanceof BackpackItem) {
            cir.setReturnValue(setOrSwapBackpack(player, hand, handStack));

        } else if (handStack.isEmpty()) {
            if (getClickedSlot(vec) == EquipmentSlot.MAINHAND) {
                ItemStack armorStandStack = ((BackpackHolder) this).getBackpack();
                if (!armorStandStack.isEmpty()) {
                    if (player.getItemInHand(hand).isEmpty()) {
                        player.setItemSlot(EquipmentSlot.MAINHAND, armorStandStack);
                    } else {
                        player.getInventory().add(armorStandStack);
                    }
                    ((BackpackHolder) this).setBackpack(ItemStack.EMPTY);
                    if (!this.level().isClientSide()) {
                        BackpackNetwork.INSTANCE.sendToClientsTrackingEntity(this, new SyncEntityBackpackPayload(this.getId(), ItemStack.EMPTY));
                    }
                }
            }
        }
    }

    private InteractionResult setOrSwapBackpack(Player player, InteractionHand hand, ItemStack handStack) {
        if (handStack.getItem() instanceof BackpackItem) {
            ItemStack armorStandBackpack = ((BackpackHolder) this).getBackpack();
            if (armorStandBackpack.isEmpty()) {
                ((BackpackHolder) this).setBackpack(handStack);
                player.getItemInHand(hand).shrink(1);
            } else {
                ItemStack backpackCopy = armorStandBackpack.copy();
                ((BackpackHolder) this).setBackpack(handStack);

                player.getItemInHand(hand).shrink(1);
                player.getInventory().add(backpackCopy);
            }
        }

        if (!this.level().isClientSide()) {
            BackpackNetwork.INSTANCE.sendToClientsTrackingEntity(this, new SyncEntityBackpackPayload(this.getId(), handStack));
        }

        return InteractionResult.SUCCESS;
    }
}
