package net.xstopho.resource_backpacks.util;

import net.minecraftforge.network.PacketDistributor;
import net.xstopho.resource_backpacks.ResourceBackpacks;
import net.xstopho.resource_backpacks.network.EnderChestRequestPayload;
import net.xstopho.resource_backpacks.network.EnderChestResponsePayload;

public class ForgeNetworkHook implements BackpackUtils.NetworkHook {
    @Override
    public void sendEnderChestRequest() {

        ResourceBackpacks.NETWORK.send(new EnderChestRequestPayload(), PacketDistributor.SERVER.noArg());
    }
}
