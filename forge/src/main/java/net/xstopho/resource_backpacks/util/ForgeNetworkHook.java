package net.xstopho.resource_backpacks.util;

import net.minecraftforge.network.PacketDistributor;
import net.xstopho.resource_backpacks.ResourceBackpacks;
import net.xstopho.resource_backpacks.network.EnderChestRequestPayload;

public class ForgeNetworkHook implements CommonNetworkHook {
    @Override
    public void sendEnderChestRequest() {
        ResourceBackpacks.NETWORK.send(new EnderChestRequestPayload(1), PacketDistributor.SERVER.noArg());
    }
}
