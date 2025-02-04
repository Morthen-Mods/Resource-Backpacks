package net.xstopho.resource_backpacks;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.xstopho.resource_backpacks.backpack.tooltip.CompactClientTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.InventoryClientTooltipComponent;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus eventBus) {
        eventBus.addListener(this::registerTooltip);
        BackpackConstants.commonInit();
    }

    private void registerTooltip(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(CompactClientTooltipComponent.CompactTooltipComponent.class, CompactClientTooltipComponent::new);
        event.register(InventoryClientTooltipComponent.InventoryTooltipComponent.class, InventoryClientTooltipComponent::new);
    }
}
