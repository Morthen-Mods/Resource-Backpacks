package net.xstopho.resource_backpacks;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.xstopho.resource_backpacks.backpack.tooltip.CompactClientTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.CompactTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.InventoryClientTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.InventoryTooltipComponent;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus eventBus) {
        eventBus.addListener(this::registerTooltip);
        BackpackConstants.commonInit();
    }

    private void registerTooltip(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(CompactTooltipComponent.class, CompactClientTooltipComponent::new);
        event.register(InventoryTooltipComponent.class, InventoryClientTooltipComponent::new);
    }
}
