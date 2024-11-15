package net.xstopho.resource_backpacks;

import com.misterpemodder.shulkerboxtooltip.api.neoforge.ShulkerBoxTooltipPlugin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.xstopho.resource_backpacks.compat.BackpackTooltipPlugin;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus eventBus) {
        BackpackConstants.commonInit();

        if (FMLLoader.getDist().equals(Dist.CLIENT)) {
            if (ModList.get().isLoaded("shulkerboxtooltip")) {
                ModLoadingContext.get().registerExtensionPoint(ShulkerBoxTooltipPlugin.class,
                        () -> new ShulkerBoxTooltipPlugin(BackpackTooltipPlugin::new));
            }
        }
    }
}
