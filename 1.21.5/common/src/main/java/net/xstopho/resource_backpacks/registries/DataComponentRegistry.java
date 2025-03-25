package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.component.BackpackContainerComponent;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

public class DataComponentRegistry {

    private static final RegistryProvider<DataComponentType<?>> COMPONENTS = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.DATA_COMPONENT_TYPE);

    public static final RegistryObject<DataComponentType<BackpackContainerComponent>> BACKPACK_CONTAINER = COMPONENTS.register("backpack_container",
            () -> DataComponentType.<BackpackContainerComponent>builder().persistent(BackpackContainerComponent.CODEC).networkSynchronized(BackpackContainerComponent.STREAM_CODEC).build());

    public static void init() {}
}
