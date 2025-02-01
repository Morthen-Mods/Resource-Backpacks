package net.xstopho.resource_backpacks.handler;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.BackpackForRemoval;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.modifier.EntityModifier;

@Mod.EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeHandler {

    @SubscribeEvent
    public static void registerEntityLoad(EntityJoinLevelEvent event) {
        EntityModifier.modifyEntities(event.getEntity());
    }

    @SubscribeEvent
    public static void registerPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        BackpackForRemoval.sendPlayerMessage(event.getEntity());
    }

    @SubscribeEvent
    public static void registerPlayerClone(PlayerEvent.Clone event) {
        Player newPlayer = event.getEntity();
        if (newPlayer.getServer().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) || newPlayer.isCreative() || newPlayer.isSpectator()) {
            BackpackHolder.restorePlayerBackpack(event.getOriginal(), newPlayer);
        }
    }
}
