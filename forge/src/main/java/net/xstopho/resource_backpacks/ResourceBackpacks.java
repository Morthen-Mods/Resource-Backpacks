package net.xstopho.resource_backpacks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.SimpleChannel;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.registries.CreativeTabRegistry;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resourceconfigapi.api.ConfigRegistry;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public static SimpleChannel NETWORK;

    public ResourceBackpacks(FMLJavaModLoadingContext context) {
        ConfigRegistry.register(BackpackConstants.MOD_ID, BackpackConfig.BUILDER, true);

        context.getModEventBus().addListener(this::doCommonStuff);

        DataComponentsRegistry.init();

        ItemRegistry.init();
        MenuTypeRegistry.init();

        CreativeTabRegistry.init();
    }

    private void doCommonStuff(FMLCommonSetupEvent event) {
        event.enqueueWork(BackpackNetwork::setupPackets);
    }
}
