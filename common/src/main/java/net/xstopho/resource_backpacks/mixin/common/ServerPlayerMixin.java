package net.xstopho.resource_backpacks.mixin.common;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.network.payloads.SyncEntityBackpackPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {
    public ServerPlayerMixin(Level level, BlockPos pos, float yRot, GameProfile gameProfile) {
        super(level, pos, yRot, gameProfile);
    }

    @Inject(method = "die", at = @At("TAIL"))
    public void resource_backpacks$die(DamageSource source, CallbackInfo info) {
        ((BackpackHolder) this).dropBackpack(this);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void resource_backpacks$readData(CompoundTag tag, CallbackInfo info) {
        ItemStack backpack = ((BackpackHolder) this).getBackpack();
        BackpackNetwork.INSTANCE.sendToAllClients((ServerPlayer) (Object) this, new SyncEntityBackpackPayload(this.getId(), backpack));
    }
}
