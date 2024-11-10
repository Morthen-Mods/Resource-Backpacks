package net.xstopho.resource_backpacks;

import net.fabricmc.api.ClientModInitializer;

public class ResourceBackpacksClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BackpackConstants.clientInit();
    }
}
