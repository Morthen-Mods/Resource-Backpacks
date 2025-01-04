package net.xstopho.resource_backpacks.util;

import net.neoforged.neoforge.network.PacketDistributor;
import net.xstopho.resource_backpacks.client.util.BackpackClientUtils;
import net.xstopho.resource_backpacks.network.EnderChestRequestPayload;

public class NeoForgeNetworkHook implements BackpackClientUtils.NetworkHook {
    @Override
    public void sendEnderChestRequest() {

        PacketDistributor.sendToServer(new EnderChestRequestPayload());
    }
}
