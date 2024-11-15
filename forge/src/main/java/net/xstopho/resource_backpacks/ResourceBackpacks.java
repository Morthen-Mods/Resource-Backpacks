package net.xstopho.resource_backpacks;

import com.misterpemodder.shulkerboxtooltip.api.forge.ShulkerBoxTooltipPlugin;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.network.SimpleChannel;
import net.xstopho.resource_backpacks.compat.BackpackTooltipPlugin;
import net.xstopho.resource_backpacks.network.BackpackNetwork;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public static SimpleChannel NETWORK;

    public ResourceBackpacks(FMLJavaModLoadingContext context) {
        context.getModEventBus().addListener(this::initCommon);

        BackpackConstants.commonInit();

        if (FMLLoader.getDist().equals(Dist.CLIENT)) {
            if (ModList.get().isLoaded("shulkerboxtooltip")) {
                context.registerExtensionPoint(ShulkerBoxTooltipPlugin.class,
                        () -> new ShulkerBoxTooltipPlugin(BackpackTooltipPlugin::new));
            }
        }
    }

    private void initCommon(FMLCommonSetupEvent event) {
        event.enqueueWork(BackpackNetwork::initPayloads);
    }
}
