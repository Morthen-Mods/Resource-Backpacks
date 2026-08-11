package net.morthen.resource_backapcks.provider;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class BackpackTagsProvider {

    /**
     * Item Tags
     */
    public static final class ItemTags {

        public static final TagKey<Item> BACKPACKS = key("backpacks");

        public static final TagKey<Item> LEATHER = key("leathers");
        public static final TagKey<Item> ENDER_CHESTS = key("chests/ender");
        public static final TagKey<Item> DIAMONDS = key("gems/diamond");
        public static final TagKey<Item> COPPER_INGOTS = key("ingots/copper");
        public static final TagKey<Item> GOLD_INGOTS = key("ingots/gold");
        public static final TagKey<Item> IRON_INGOTS = key("ingots/iron");
        public static final TagKey<Item> NETHERITE_INGOTS = key("ingots/netherite");

        public static final TagKey<Item> BACKPACK_LEATHER = key("backpacks/leather");
        public static final TagKey<Item> BACKPACK_COPPER = key("backpacks/copper");
        public static final TagKey<Item> BACKPACK_GOLD = key("backpacks/gold");
        public static final TagKey<Item> BACKPACK_IRON = key("backpacks/iron");
        public static final TagKey<Item> BACKPACK_DIAMOND = key("backpacks/diamond");
        public static final TagKey<Item> BACKPACK_NETHERITE = key("backpacks/netherite");
        public static final TagKey<Item> BACKPACK_END = key("backpacks/end");

        private static TagKey<Item> key(String path) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", path));
        }
        

//        public ItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> unused) {
//            super(packOutput, lookupProvider, BackpackConstants.MOD_ID);
//        }
//
//
//        @Override
//        protected void addTags(HolderLookup.Provider provider) {
//            tagBuilder(BACKPACKS,
//                    BACKPACK_LEATHER,
//                    BACKPACK_COPPER,
//                    BACKPACK_IRON,
//                    BACKPACK_GOLD,
//                    BACKPACK_DIAMOND,
//                    BACKPACK_NETHERITE,
//                    BACKPACK_END);
//
//            tagBuilder(LEATHER, Items.LEATHER, Items.RABBIT_HIDE);
//            tagBuilder(ENDER_CHESTS, Items.ENDER_CHEST);
//
//            tagBuilder(BACKPACK_LEATHER, BlockRegistry.BACKPACK_LEATHER);
//            tagBuilder(BACKPACK_COPPER, BlockRegistry.BACKPACK_COPPER);
//            tagBuilder(BACKPACK_GOLD, BlockRegistry.BACKPACK_GOLD);
//            tagBuilder(BACKPACK_IRON, BlockRegistry.BACKPACK_IRON);
//            tagBuilder(BACKPACK_DIAMOND, BlockRegistry.BACKPACK_DIAMOND);
//            tagBuilder(BACKPACK_NETHERITE, BlockRegistry.BACKPACK_NETHERITE);
//            tagBuilder(BACKPACK_END, BlockRegistry.BACKPACK_END);
//
//            tagBuilder(COPPER_INGOTS, Items.COPPER_INGOT);
//            tagBuilder(GOLD_INGOTS, Items.GOLD_INGOT);
//            tagBuilder(IRON_INGOTS, Items.IRON_INGOT);
//            tagBuilder(NETHERITE_INGOTS, Items.NETHERITE_INGOT);
//            tagBuilder(DIAMONDS, Items.DIAMOND);
//        }
//
//        @SafeVarargs
//        private void tagBuilder(TagKey<Item> outputTag, RegistryObject<Block>... blocks) {
//            TagAppender<Item, Item> appender = this.tag(outputTag);
//
//            for (RegistryObject<Block> block : blocks) {
//                appender.add(block.get().asItem());
//            }
//        }
//
//        private void tagBuilder(TagKey<Item> outputTag, Item... items) {
//            TagAppender<Item, Item> appender = this.tag(outputTag);
//
//            for (Item item : items) {
//                appender.add(item);
//            }
//        }
//
//        @SafeVarargs
//        private void tagBuilder(TagKey<Item> outputTag, TagKey<Item>... tagKeys) {
//            TagAppender<Item, Item> appender = this.tag(outputTag);
//
//            for (TagKey<Item> tag : tagKeys) {
//                appender.addTag(tag);
//            }
//        }
    }
//
//    /**
//     * Block Tags
//     */
//    public static final class BlockTags extends BlockTagsProvider {
//
//        public static final TagKey<Block> BACKPACKS = TagUtil.createBlockTag("backpacks");
//
//        public BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
//            super(output, lookupProvider, BackpackConstants.MOD_ID);
//        }
//
//        @Override
//        protected void addTags(HolderLookup.Provider provider) {
//            tagBuilder(BACKPACKS, BlockRegistry.BACKPACK_LEATHER,
//                    BlockRegistry.BACKPACK_COPPER,
//                    BlockRegistry.BACKPACK_GOLD,
//                    BlockRegistry.BACKPACK_IRON,
//                    BlockRegistry.BACKPACK_DIAMOND,
//                    BlockRegistry.BACKPACK_NETHERITE,
//                    BlockRegistry.BACKPACK_END);
//        }
//
//        @SafeVarargs
//        private void tagBuilder(TagKey<Block> tag, RegistryObject<Block>... blocks) {
//            TagAppender<Block, Block> appender = this.tag(tag);
//
//            for (RegistryObject<Block> block : blocks) {
//                appender.add(block.get());
//            }
//        }
//    }
}
