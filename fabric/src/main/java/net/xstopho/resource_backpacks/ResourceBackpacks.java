package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.xstopho.resource_backpacks.network.BackpackNetwork;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        BackpackNetwork.initServerPayloads();

        BackpackConstants.commonInit();
    }
}
