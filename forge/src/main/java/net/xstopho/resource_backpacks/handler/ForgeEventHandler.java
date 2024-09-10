package net.xstopho.resource_backpacks.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.config.BackpackConfig;

@Mod.EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {

    @SubscribeEvent
    public static void registerLoginEvents(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        if (!BackpackConfig.DISABLE_INFO_MESSAGE.get()) {
            player.sendSystemMessage(Component.literal("[Resource Backpacks]").withStyle(ChatFormatting.GOLD), false);
            player.sendSystemMessage(Component.literal("The current Beta version will be ported to further Minecraft Versions, but will not receive new features."), false);
            player.sendSystemMessage(Component.literal("The Next Version will be the 0.9-BETA or 1.0 version, this will break all current Backpacks, " +
                    "so make sure to empty all your Backpacks before updating!"), false);
            player.sendSystemMessage(Component.literal("This Message can be disabled in the Config File."), false);
        }
    }
}
