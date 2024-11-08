package net.xstopho.resource_backpacks;

import com.misterpemodder.shulkerboxtooltip.api.neoforge.ShulkerBoxTooltipPlugin;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.xstopho.resource_backpacks.compat.shulkerboxtooltip.BackpackTooltipPlugin;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.registries.CreativeTabRegistry;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resourceconfigapi.api.ConfigRegistry;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus eventBus) {
        ConfigRegistry.register(BackpackConstants.MOD_ID, BackpackConfig.BUILDER, true);

        DataComponentsRegistry.init();

        ItemRegistry.init();
        MenuTypeRegistry.init();

        CreativeTabRegistry.init();
    }
}
