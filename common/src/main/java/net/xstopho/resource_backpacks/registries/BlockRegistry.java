package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.custom.blocks.BackpackBlock;
import net.xstopho.resource_backpacks.custom.util.BackpackLevel;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

import java.util.function.Function;

public class BlockRegistry {

    private static final RegistryProvider<Block> BLOCKS = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.BLOCK);
    private static final RegistryProvider<Item> ITEMS = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.ITEM);

    public static final RegistryObject<Block> TEST_BACKPACK = registerBlock("test_backpack", properties -> new BackpackBlock(properties, BackpackLevel.SHULKER), BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT));
    public static final RegistryObject<Block> TEST_BACKPACK_2 = registerBlock("test_backpack_2", properties -> new BackpackBlock(properties, BackpackLevel.BIG_CHEST), BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT));

    private static RegistryObject<Block> registerBlock(String id, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties blockBehavior) {
        ResourceKey<Block> blockId = createBlockId(id);

        RegistryObject<Block> block = BLOCKS.register(id, () -> function.apply(blockBehavior.setId(blockId)));
        registerItem(id, properties -> new BlockItem(block.get(), properties), new Item.Properties().useBlockDescriptionPrefix());

        return block;
    }

    private static void registerItem(String id, Function<Item.Properties, Item> function, Item.Properties properties) {
        ResourceKey<Item> itemId = createItemId(id);
        ITEMS.register(id, () -> function.apply(properties.setId(itemId).stacksTo(1)));
    }

    private static ResourceKey<Block> createBlockId(String id) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, id));
    }

    private static ResourceKey<Item> createItemId(String id) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, id));
    }

    public static void init() {}
}
