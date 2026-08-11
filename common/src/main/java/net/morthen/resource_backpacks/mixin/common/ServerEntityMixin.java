package net.morthen.resource_backpacks.mixin.common;

import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.morthen.resource_backpacks.backpack.api.BackpackHolder;
import net.morthen.resource_backpacks.network.BackpackNetwork;
import net.morthen.resource_backpacks.network.payloads.SyncEntityBackpackPayload;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerEntity.class)
public abstract class ServerEntityMixin {

    @Shadow @Final
    private Entity entity;

    // Syncs the Entity Data every 2-3 seconds
    @Inject(method = "addPairing(Lnet/minecraft/server/level/ServerPlayer;)V", at = @At("RETURN"))
    private void resource_backpacks$addPairing(ServerPlayer player, CallbackInfo info) {
        if (entity instanceof LivingEntity livingEntity) {
            ItemStack itemStack = ((BackpackHolder) livingEntity).getBackpack();
            BackpackNetwork.INSTANCE.sendToClient(player, new SyncEntityBackpackPayload(livingEntity.getId(), itemStack));
        }
    }
}
