package net.xstopho.resource_backpacks.compat;

import com.misterpemodder.shulkerboxtooltip.api.ShulkerBoxTooltipApi;
import com.misterpemodder.shulkerboxtooltip.api.provider.PreviewProviderRegistry;
import net.minecraft.resources.ResourceLocation;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.BlockRegistry;

public class BackpackTooltipPlugin implements ShulkerBoxTooltipApi {
    @Override
    public void registerProviders(PreviewProviderRegistry registry) {
        registry.register(location("backpack_tooltip_plugin"), new BackpackPreviewProvider(),
                BlockRegistry.BACKPACK_LEATHER.get().asItem(), BlockRegistry.BACKPACK_COPPER.get().asItem(),
                BlockRegistry.BACKPACK_GOLD.get().asItem(), BlockRegistry.BACKPACK_IRON.get().asItem(),
                BlockRegistry.BACKPACK_DIAMOND.get().asItem(), BlockRegistry.BACKPACK_NETHERITE.get().asItem(),
                BlockRegistry.BACKPACK_END.get().asItem());
    }

    private ResourceLocation location(String id) {
        return ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, id);
    }
}
