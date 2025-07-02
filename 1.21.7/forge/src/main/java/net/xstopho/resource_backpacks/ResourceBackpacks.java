package net.xstopho.resource_backpacks;

import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.SimpleChannel;
import net.xstopho.resource_backpacks.backpack.tooltip.CompactClientTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.InventoryClientTooltipComponent;
import net.xstopho.resource_backpacks.network.BackpackNetworkRegistry;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public static SimpleChannel NETWORK;

    public ResourceBackpacks(FMLJavaModLoadingContext context) {
        var modBus = context.getModBusGroup();
        FMLCommonSetupEvent.getBus(modBus).addListener(this::initCommon);
        RegisterClientTooltipComponentFactoriesEvent.getBus(modBus).addListener(this::registerTooltip);

        BackpackConstants.commonInit();
    }

    private void initCommon(FMLCommonSetupEvent event) {

        event.enqueueWork(BackpackNetworkRegistry::registerPayloads);
    }

    private void registerTooltip(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(CompactClientTooltipComponent.CompactTooltipComponent.class, CompactClientTooltipComponent::new);
        event.register(InventoryClientTooltipComponent.InventoryTooltipComponent.class, InventoryClientTooltipComponent::new);
    }
}
