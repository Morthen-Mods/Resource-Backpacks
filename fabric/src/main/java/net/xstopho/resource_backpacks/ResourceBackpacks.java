package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resourceconfigapi.api.ConfigRegistry;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigRegistry.register(BackpackConstants.MOD_ID, BackpackConfig.BUILDER, false);

        BackpackNetwork.initPayloads();

        BackpackConstants.commonInit();
    }
}
