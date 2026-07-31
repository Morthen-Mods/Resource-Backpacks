package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.component.BackpackContainerContents;
import net.morthen.resourcelibrary.registration.RegistryObject;
import net.morthen.resourcelibrary.registration.RegistryProvider;

public class DataComponentRegistry {

    private static final RegistryProvider<DataComponentType<?>> COMPONENTS = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.DATA_COMPONENT_TYPE);

    public static final RegistryObject<DataComponentType<BackpackContainerContents>> BACKPACK_CONTAINER = COMPONENTS.register("backpack_container",
            () -> DataComponentType.<BackpackContainerContents>builder().persistent(BackpackContainerContents.CODEC).networkSynchronized(BackpackContainerContents.STREAM_CODEC).build());

    public static void init() {}
}
