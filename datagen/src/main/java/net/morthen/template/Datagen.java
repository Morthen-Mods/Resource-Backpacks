package net.morthen.template;

import net.morthen.resourcelibrary.registration.RegistryObject;
import net.morthen.template.provider.TemplateProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(CommonConstants.MOD_ID)
public class Datagen {
    public Datagen(IEventBus eventBus) {
        eventBus.addListener(Datagen::gatherData);
    }

    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(TemplateProvider.CustomRecipes.Runner::new);
        event.createProvider(TemplateProvider.CustomItemTags::new);
    }
}
