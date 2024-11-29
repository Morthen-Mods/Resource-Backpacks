package net.xstopho.resource_backpacks.util;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.xstopho.resource_backpacks.network.EnderChestRequestPayload;

public class FabricNetworkHook implements CommonNetworkHook {
    @Override
    public void sendEnderChestRequest() {
        ClientPlayNetworking.send(new EnderChestRequestPayload(1));
    }
}
