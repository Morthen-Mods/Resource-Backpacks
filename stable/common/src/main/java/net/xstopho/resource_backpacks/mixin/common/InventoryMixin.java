package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.core.NonNullList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Inventory.class)
public class InventoryMixin {

    @Shadow NonNullList<ItemStack> items;
    @Shadow Player player;

    //TODO: - rework code to make it more robust
    //      - add translation
    @Inject(method = "tick", at = @At("TAIL"))
    public void resource_backpacks$tick(CallbackInfo ci) {
//        if (BackpackConfig.slownessPenalty) {
//            int count = 0;
//
//            for (ItemStack item : items) {
//                if (item.getItem() instanceof BackpackItem backpack) {
//                    ItemContainerContents container = item.get(DataComponents.CONTAINER);
//                    if (container != null && !container.stream().toList().isEmpty()) {
//                        count++;
//                    }
//                }
//            }
//
//            if (player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) return;
//
//            if (count > BackpackConfig.slownessOne) addPenalty(0);
//            if (count > BackpackConfig.slownessTwo) addPenalty(1);
//            if (count > BackpackConfig.slownessThree) addPenalty(2);
//            if (count <= 2) removePenalty();
//        }
    }

    @Unique
    private void addPenalty(int amplifier) {
        this.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, amplifier));
    }

    @Unique
    private void removePenalty() {
        this.player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
    }
}
