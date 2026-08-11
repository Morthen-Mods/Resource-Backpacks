package net.morthen.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.morthen.resource_backpacks.BackpackConstants;
import net.morthen.resource_backpacks.backpack.BackpackBlock;
import net.morthen.resource_backpacks.backpack.BackpackItem;
import net.morthen.resource_backpacks.backpack.util.BackpackLevel;
import net.morthen.resourcelibrary.registration.RegistryObject;
import net.morthen.resourcelibrary.registration.RegistryProvider;

import java.util.function.Function;

public class BlockRegistry {

    private static final RegistryProvider<Block> BLOCKS = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.BLOCK);
    private static final RegistryProvider<Item> ITEMS = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.ITEM);

    public static final RegistryObject<Block> BACKPACK_LEATHER = registerBlock("backpack_leather", BackpackLevel.LEATHER);
    public static final RegistryObject<Block> BACKPACK_COPPER = registerBlock("backpack_copper", BackpackLevel.COPPER);
    public static final RegistryObject<Block> BACKPACK_GOLD = registerBlock("backpack_gold", BackpackLevel.GOLD);
    public static final RegistryObject<Block> BACKPACK_IRON = registerBlock("backpack_iron", BackpackLevel.IRON);
    public static final RegistryObject<Block> BACKPACK_DIAMOND = registerBlock("backpack_diamond", BackpackLevel.DIAMOND);
    public static final RegistryObject<Block> BACKPACK_NETHERITE = registerBlock("backpack_netherite", BackpackLevel.NETHERITE);
    public static final RegistryObject<Block> BACKPACK_END = registerBlock("backpack_end", BackpackLevel.END);


    private static RegistryObject<Block> registerBlock(String id, BackpackLevel level) {
        BlockBehaviour.Properties blockBehaviour = BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_ORANGE).strength(0.5f).sound(SoundType.WOOL);

        blockBehaviour = level.equals(BackpackLevel.NETHERITE) ? blockBehaviour.explosionResistance(1200f) : blockBehaviour;

        return registerBlock(id, properties -> new BackpackBlock(properties, level), level, blockBehaviour);
    }


    private static RegistryObject<Block> registerBlock(String id, Function<BlockBehaviour.Properties, Block> function, BackpackLevel level, BlockBehaviour.Properties blockBehavior) {
        ResourceKey<Block> blockId = createBlockId(id);

        RegistryObject<Block> block = BLOCKS.register(id, () -> function.apply(blockBehavior.setId(blockId)));

        Item.Properties itemProperties = level == BackpackLevel.NETHERITE ? new Item.Properties().fireResistant() : new Item.Properties();
        registerItem(id, properties -> new BackpackItem(block.get(), level, properties), itemProperties.useBlockDescriptionPrefix());

        return block;
    }

    private static void registerItem(String id, Function<Item.Properties, Item> function, Item.Properties properties) {
        ResourceKey<Item> itemId = createItemId(id);
        ITEMS.register(id, () -> function.apply(properties.setId(itemId).stacksTo(1)));
    }

    private static ResourceKey<Block> createBlockId(String id) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(BackpackConstants.MOD_ID, id));
    }

    private static ResourceKey<Item> createItemId(String id) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BackpackConstants.MOD_ID, id));
    }

    public static void init() {}
}
