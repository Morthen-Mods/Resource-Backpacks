package net.xstopho.resource_backpacks.util;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.xstopho.resource_backpacks.client.util.BackpackClientUtils;
import net.xstopho.resource_backpacks.network.EnderChestRequestPayload;

public class FabricNetworkHook implements BackpackClientUtils.NetworkHook {
    @Override
    public void sendEnderChestRequest() {
        ClientPlayNetworking.send(new EnderChestRequestPayload());
    }
}
