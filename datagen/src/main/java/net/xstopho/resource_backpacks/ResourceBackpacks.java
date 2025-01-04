package net.xstopho.resource_backpacks;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.xstopho.resource_backpacks.provider.BackpackBlockTags;
import net.xstopho.resource_backpacks.provider.BackpackItemTags;
import net.xstopho.resource_backpacks.provider.BackpackRecipes;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus bus) {
        BackpackConstants.commonInit();
    }

    @EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class Datagen {

        @SubscribeEvent
        public static void generateData(GatherDataEvent.Client event) {
            event.createProvider(BackpackRecipes.Runner::new);
            event.createBlockAndItemTags(BackpackBlockTags::new, BackpackItemTags::new);
        }
    }
}
