package net.xstopho.resource_backapcks.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.BlockRegistry;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.util.TagUtil;

import java.util.concurrent.CompletableFuture;

public final class BackpackTagsProvider {

    /**
     * Item Tags
     */
    public static final class ItemTags extends ItemTagsProvider {

        public static final TagKey<Item> BACKPACKS = TagUtil.createItemTag("backpacks");

        public static final TagKey<Item> LEATHER = TagUtil.createItemTag("leathers");
        public static final TagKey<Item> ENDER_CHESTS = TagUtil.createItemTag("chests/ender");
        public static final TagKey<Item> DIAMONDS = TagUtil.createItemTag("gems/diamond");
        public static final TagKey<Item> COPPER_INGOTS = TagUtil.createItemTag("ingots/copper");
        public static final TagKey<Item> GOLD_INGOTS = TagUtil.createItemTag("ingots/gold");
        public static final TagKey<Item> IRON_INGOTS = TagUtil.createItemTag("ingots/iron");
        public static final TagKey<Item> NETHERITE_INGOTS = TagUtil.createItemTag("ingots/netherite");

        public static final TagKey<Item> BACKPACK_LEATHER = TagUtil.createItemTag("backpacks/leather");
        public static final TagKey<Item> BACKPACK_COPPER = TagUtil.createItemTag("backpacks/copper");
        public static final TagKey<Item> BACKPACK_GOLD = TagUtil.createItemTag("backpacks/gold");
        public static final TagKey<Item> BACKPACK_IRON = TagUtil.createItemTag("backpacks/iron");
        public static final TagKey<Item> BACKPACK_DIAMOND = TagUtil.createItemTag("backpacks/diamond");
        public static final TagKey<Item> BACKPACK_NETHERITE = TagUtil.createItemTag("backpacks/netherite");
        public static final TagKey<Item> BACKPACK_END = TagUtil.createItemTag("backpacks/end");


        public ItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> unused) {
            super(packOutput, lookupProvider, BackpackConstants.MOD_ID);
        }


        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tagBuilder(BACKPACKS,
                    BACKPACK_LEATHER,
                    BACKPACK_COPPER,
                    BACKPACK_IRON,
                    BACKPACK_GOLD,
                    BACKPACK_DIAMOND,
                    BACKPACK_NETHERITE,
                    BACKPACK_END);

            tagBuilder(LEATHER, Items.LEATHER, Items.RABBIT_HIDE);
            tagBuilder(ENDER_CHESTS, Items.ENDER_CHEST);

            tagBuilder(BACKPACK_LEATHER, BlockRegistry.BACKPACK_LEATHER);
            tagBuilder(BACKPACK_COPPER, BlockRegistry.BACKPACK_COPPER);
            tagBuilder(BACKPACK_GOLD, BlockRegistry.BACKPACK_GOLD);
            tagBuilder(BACKPACK_IRON, BlockRegistry.BACKPACK_IRON);
            tagBuilder(BACKPACK_DIAMOND, BlockRegistry.BACKPACK_DIAMOND);
            tagBuilder(BACKPACK_NETHERITE, BlockRegistry.BACKPACK_NETHERITE);
            tagBuilder(BACKPACK_END, BlockRegistry.BACKPACK_END);

            tagBuilder(COPPER_INGOTS, Items.COPPER_INGOT);
            tagBuilder(GOLD_INGOTS, Items.GOLD_INGOT);
            tagBuilder(IRON_INGOTS, Items.IRON_INGOT);
            tagBuilder(NETHERITE_INGOTS, Items.NETHERITE_INGOT);
            tagBuilder(DIAMONDS, Items.DIAMOND);
        }

        @SafeVarargs
        private void tagBuilder(TagKey<Item> outputTag, RegistryObject<Block>... blocks) {
            TagAppender<Item, Item> appender = this.tag(outputTag);

            for (RegistryObject<Block> block : blocks) {
                appender.add(block.get().asItem());
            }
        }

        private void tagBuilder(TagKey<Item> outputTag, Item... items) {
            TagAppender<Item, Item> appender = this.tag(outputTag);

            for (Item item : items) {
                appender.add(item);
            }
        }

        @SafeVarargs
        private void tagBuilder(TagKey<Item> outputTag, TagKey<Item>... tagKeys) {
            TagAppender<Item, Item> appender = this.tag(outputTag);

            for (TagKey<Item> tag : tagKeys) {
                appender.addTag(tag);
            }
        }
    }

    /**
     * Block Tags
     */
    public static final class BlockTags extends BlockTagsProvider {

        public static final TagKey<Block> BACKPACKS = TagUtil.createBlockTag("backpacks");

        public BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider, BackpackConstants.MOD_ID);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tagBuilder(BACKPACKS, BlockRegistry.BACKPACK_LEATHER,
                    BlockRegistry.BACKPACK_COPPER,
                    BlockRegistry.BACKPACK_GOLD,
                    BlockRegistry.BACKPACK_IRON,
                    BlockRegistry.BACKPACK_DIAMOND,
                    BlockRegistry.BACKPACK_NETHERITE,
                    BlockRegistry.BACKPACK_END);
        }

        @SafeVarargs
        private void tagBuilder(TagKey<Block> tag, RegistryObject<Block>... blocks) {
            TagAppender<Block, Block> appender = this.tag(tag);

            for (RegistryObject<Block> block : blocks) {
                appender.add(block.get());
            }
        }
    }
}
