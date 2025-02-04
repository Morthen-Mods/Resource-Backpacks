package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

public class CreativeTabRegistry {

    private static final RegistryProvider<CreativeModeTab> CREATIVE_TAB = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.CREATIVE_MODE_TAB);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_TAB.register("backpack_tab",
            () -> CreativeModeTab.builder(null, -1).title(Component.translatable("tab.resource_backpacks"))
                    .icon(() -> new ItemStack(BlockRegistry.BACKPACK_LEATHER.get()))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(BlockRegistry.BACKPACK_LEATHER.get());
                        output.accept(BlockRegistry.BACKPACK_COPPER.get());
                        output.accept(BlockRegistry.BACKPACK_GOLD.get());
                        output.accept(BlockRegistry.BACKPACK_IRON.get());
                        output.accept(BlockRegistry.BACKPACK_DIAMOND.get());
                        output.accept(BlockRegistry.BACKPACK_NETHERITE.get());
                        output.accept(BlockRegistry.BACKPACK_END.get());

                    }).build());

    public static void init() {}
}
