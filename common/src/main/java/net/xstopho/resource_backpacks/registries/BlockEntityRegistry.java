package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.entities.BackpackBlockEntity;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

import java.util.Set;

public class BlockEntityRegistry {

    private static final RegistryProvider<BlockEntityType<?>> BLOCK_ENTITIES = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.BLOCK_ENTITY_TYPE);

    public static final RegistryObject<BlockEntityType<BackpackBlockEntity>> BACKPACK_ENTITY = BLOCK_ENTITIES.register("backpack_entity",
            () -> new BlockEntityType<>(BackpackBlockEntity::new, Set.of(BlockRegistry.TEST_BACKPACK.get())));

    public static void init() {}
}
