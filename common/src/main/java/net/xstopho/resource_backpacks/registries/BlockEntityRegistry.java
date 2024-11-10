package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.custom.entities.BackpackBlockEntity;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

import java.util.Set;

public class BlockEntityRegistry {

    private static final RegistryProvider<BlockEntityType<?>> BLOCK_ENTITIES = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.BLOCK_ENTITY_TYPE);

    public static final RegistryObject<BlockEntityType<?>> BACKPACK_ENTITY = register("backpack_entity", BackpackBlockEntity::new, BlockRegistry.TEST_BACKPACK.get());

    private static RegistryObject<BlockEntityType<?>> register(String id, BlockEntityType.BlockEntitySupplier<?> blockEntitySupplier, Block... blocks) {
        return BLOCK_ENTITIES.register(id, () -> new BlockEntityType<>(blockEntitySupplier, Set.of(blocks)));
    }

    public static void init() {}
}
