package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.entities.BackpackBlockEntity;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

import java.util.Set;

import static net.xstopho.resource_backpacks.registries.BlockRegistry.*;

public class BlockEntityRegistry {

    private static final RegistryProvider<BlockEntityType<?>> BLOCK_ENTITIES = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.BLOCK_ENTITY_TYPE);

    public static final RegistryObject<BlockEntityType<BackpackBlockEntity>> BACKPACK_ENTITY = BLOCK_ENTITIES.register("backpack_entity",
            () -> new BlockEntityType<>(BackpackBlockEntity::new, Set.of(BACKPACK_TEST.get(), BACKPACK_LEATHER.get(), BACKPACK_COPPER.get(),
                    BACKPACK_GOLD.get(), BACKPACK_IRON.get(), BACKPACK_DIAMOND.get(), BACKPACK_NETHERITE.get())));

    public static void init() {}
}
