package net.xstopho.resource_backpacks;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus eventBus) {
        BackpackConstants.commonInit();
    }
}
