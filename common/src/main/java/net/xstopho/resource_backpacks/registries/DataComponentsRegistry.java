package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.components.BackpackContainerContent;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

import java.util.function.UnaryOperator;

public class DataComponentsRegistry {

    private static final RegistryProvider<DataComponentType<?>> DATA_COMPONENTS = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.DATA_COMPONENT_TYPE);

    public static final RegistryObject<DataComponentType<BackpackContainerContent>> BACKPACK_CONTAINER = register("backpack_container",
            builder -> builder.persistent(BackpackContainerContent.CODEC).networkSynchronized(BackpackContainerContent.STREAM_CODEC).cacheEncoding());

    private static <T> RegistryObject<DataComponentType<T>> register(String id, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return DATA_COMPONENTS.register(id, () -> builder.apply(DataComponentType.builder()).build());
    }

    public static void init() {}
}
