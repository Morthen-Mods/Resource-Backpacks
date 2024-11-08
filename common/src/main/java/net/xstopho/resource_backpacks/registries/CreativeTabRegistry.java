package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

public class CreativeTabRegistry {

    private static final RegistryProvider<CreativeModeTab> CREATIVE_TABS = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.CREATIVE_MODE_TAB);

    public static final RegistryObject<CreativeModeTab> RESOURCE_BACKPACKS = CREATIVE_TABS.register("item_group",
            () -> CreativeModeTab.builder(null, -1).title(Component.translatable("item_group.resource_backpacks"))
                    .icon(() -> new ItemStack(ItemRegistry.BACKPACK_NETHERITE.get())).displayItems((itemDisplayParameters, output) -> {

                        output.accept(ItemRegistry.BACKPACK_LEATHER.get());
                        output.accept(ItemRegistry.BACKPACK_COPPER.get());
                        output.accept(ItemRegistry.BACKPACK_GOLD.get());
                        output.accept(ItemRegistry.BACKPACK_IRON.get());
                        output.accept(ItemRegistry.BACKPACK_DIAMOND.get());
                        output.accept(ItemRegistry.BACKPACK_NETHERITE.get());
                        output.accept(ItemRegistry.BACKPACK_ENDER.get());

                    }).build());

    public static void init() {}
}
