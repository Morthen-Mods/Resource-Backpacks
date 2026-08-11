package net.morthen.resource_backapcks;

import net.morthen.resource_backapcks.provider.BackpackModelProvider;
import net.morthen.resource_backapcks.provider.BackpackRecipesProvider;
import net.morthen.resource_backpacks.BackpackConstants;
import net.morthen.resource_backpacks.registries.BlockRegistry;
import net.morthen.resource_backpacks.registries.DataComponentRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus eventBus) {
        DataComponentRegistry.init();
        BlockRegistry.init();
    }


    @EventBusSubscriber(modid = BackpackConstants.MOD_ID)
    public static class Datagen {
        @SubscribeEvent
        public static void generateData(GatherDataEvent.Client event) {
            event.createProvider(BackpackRecipesProvider.Runner::new);
//            event.createBlockAndItemTags(BackpackTagsProvider.BlockTags::new, BackpackTagsProvider.ItemTags::new);
            event.createProvider(BackpackModelProvider::new);
        }
    }
}
