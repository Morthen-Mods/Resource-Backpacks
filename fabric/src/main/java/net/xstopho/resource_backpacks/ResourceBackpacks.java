package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.registries.CreativeTabRegistry;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resourceconfigapi.api.ConfigRegistry;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigRegistry.register(BackpackConstants.MOD_ID, BackpackConfig.BUILDER, true);

        BackpackNetwork.initServer();

        DataComponentsRegistry.init();

        ItemRegistry.init();
        MenuTypeRegistry.init();

        CreativeTabRegistry.init();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.getPlayer();

            if (!BackpackConfig.DISABLE_INFO_MESSAGE.get()) {
                player.sendSystemMessage(Component.literal("[Resource Backpacks]").withStyle(ChatFormatting.GOLD), false);
                player.sendSystemMessage(Component.literal("The current Beta version will be ported to further Minecraft Versions, but will not receive new features."), false);
                player.sendSystemMessage(Component.literal("The Next Version will be the 0.9-BETA or 1.0 version, this will break all current Backpacks, " +
                        "so make sure to empty all your Backpacks before updating!"), false);
                player.sendSystemMessage(Component.literal("This Message can be disabled in the Config File."), false);
            }
        });
    }
}
