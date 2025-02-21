package net.xstopho.resource_backapcks;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.xstopho.resource_backapcks.provider.BackpackModelProvider;
import net.xstopho.resource_backapcks.provider.BackpackRecipesProvider;
import net.xstopho.resource_backapcks.provider.BackpackTagsProvider;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.BlockRegistry;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus eventBus) {
        BlockRegistry.init();
    }


    @EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class Datagen {
        @SubscribeEvent
        public static void generateData(GatherDataEvent.Client event) {
            event.createProvider(BackpackRecipesProvider.Runner::new);
            event.createBlockAndItemTags(BackpackTagsProvider.BlockTags::new, BackpackTagsProvider.ItemTags::new);
            event.createProvider(BackpackModelProvider::new);
        }
    }
}
