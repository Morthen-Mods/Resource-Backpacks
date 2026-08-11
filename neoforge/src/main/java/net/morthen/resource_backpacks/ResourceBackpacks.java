package net.morthen.resource_backpacks;

import net.morthen.resource_backpacks.backpack.tooltip.CompactClientTooltipComponent;
import net.morthen.resource_backpacks.backpack.tooltip.InventoryClientTooltipComponent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus eventBus) {
        eventBus.addListener(this::registerTooltip);
        BackpackConstants.commonInit();
        BackpackConstants.packInit();
    }

    private void registerTooltip(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(CompactClientTooltipComponent.CompactTooltipComponent.class, CompactClientTooltipComponent::new);
        event.register(InventoryClientTooltipComponent.InventoryTooltipComponent.class, InventoryClientTooltipComponent::new);
    }
}
