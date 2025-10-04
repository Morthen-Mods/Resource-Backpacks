package net.xstopho.resource_backpacks.mixin.common;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player implements BackpackHolder {
    public ServerPlayerMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @Inject(method = "die(Lnet/minecraft/world/damagesource/DamageSource;)V", at = @At("TAIL"))
    public void resource_backpacks$die(DamageSource source, CallbackInfo info) {
        boolean keepInventory = this.level().getServer().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY);
        if (!keepInventory && !this.isCreative() && !this.isSpectator()) {
            this.dropBackpack(this.level(), this.getOnPos());
        }
    }

    @Inject(method = "restoreFrom(Lnet/minecraft/server/level/ServerPlayer;Z)V", at = @At("TAIL"))
    public void resource_backpacks$restoreFrom(ServerPlayer oldPlayer, boolean keepEverything, CallbackInfo info) {
        ServerPlayer newPlayer = (ServerPlayer) (Object) this;
        boolean keepInventory = this.level().getServer().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY);
        if (keepInventory || this.isCreative() || this.isSpectator() || keepEverything) {
            BackpackHolder.restorePlayerBackpack(oldPlayer, newPlayer);
        }
    }
}
