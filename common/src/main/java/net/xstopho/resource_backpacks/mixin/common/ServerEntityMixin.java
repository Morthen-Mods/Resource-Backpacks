package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.network.payloads.SyncEntityBackpackPayload;
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

    @Inject(method = "addPairing", at = @At("RETURN"))
    private void resource_backpacks$addPairing(ServerPlayer player, CallbackInfo info) {
        ItemStack backpack = ((BackpackHolder) player).getBackpack();
        BackpackNetwork.INSTANCE.sendToClientsTrackingEntity(player, new SyncEntityBackpackPayload(player.getId(), backpack));
    }
}
