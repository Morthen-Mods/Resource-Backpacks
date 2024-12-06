package net.xstopho.resource_backpacks.util;

import net.neoforged.neoforge.network.PacketDistributor;
import net.xstopho.resource_backpacks.network.EnderChestRequestPayload;

public class NeoForgeNetworkHook implements CommonNetworkHook {
    @Override
    public void sendEnderChestRequest() {

        PacketDistributor.sendToServer(new EnderChestRequestPayload());
    }
}
