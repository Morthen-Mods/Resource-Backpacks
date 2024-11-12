package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.blocks.BackpackBlock;
import net.xstopho.resource_backpacks.items.BackpackItem;
import net.xstopho.resource_backpacks.util.BackpackLevel;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

import java.util.function.Function;

public class BlockRegistry {

    private static final RegistryProvider<Block> BLOCKS = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.BLOCK);
    private static final RegistryProvider<Item> ITEMS = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.ITEM);

    public static final RegistryObject<Block> TEST_BACKPACK = registerBlock("test_backpack", BackpackLevel.SHULKER);


    private static RegistryObject<Block> registerBlock(String id, BackpackLevel level) {
        BlockBehaviour.Properties blockBehaviour = BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT);

        return registerBlock(id, properties -> new BackpackBlock(properties, level), level, blockBehaviour);
    }


    private static RegistryObject<Block> registerBlock(String id, Function<BlockBehaviour.Properties, Block> function, BackpackLevel level, BlockBehaviour.Properties blockBehavior) {
        ResourceKey<Block> blockId = createBlockId(id);

        RegistryObject<Block> block = BLOCKS.register(id, () -> function.apply(blockBehavior.setId(blockId)));
        registerItem(id, properties -> new BackpackItem(block.get(), level, properties), new Item.Properties());

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
