package net.xstopho.resource_backpacks.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.BlockRegistry;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.util.TagHelper;

import java.util.concurrent.CompletableFuture;

public class BackpackItemTags extends TagsProvider<Item> {

    public static final TagKey<Item> BACKPACKS = TagHelper.createItemTag("backpacks");

    public static final TagKey<Item> BACKPACK_LEATHER_INGREDIENT = TagHelper.createItemTag("backpack/leather_ingredient");

    public static final TagKey<Item> BACKPACK_LEATHER = TagHelper.createItemTag("backpack/leather");
    public static final TagKey<Item> BACKPACK_COPPER = TagHelper.createItemTag("backpack/copper");
    public static final TagKey<Item> BACKPACK_GOLD = TagHelper.createItemTag("backpack/gold");
    public static final TagKey<Item> BACKPACK_IRON = TagHelper.createItemTag("backpack/iron");
    public static final TagKey<Item> BACKPACK_DIAMOND = TagHelper.createItemTag("backpack/diamond");
    public static final TagKey<Item> BACKPACK_NETHERITE = TagHelper.createItemTag("backpack/netherite");
    public static final TagKey<Item> BACKPACK_END = TagHelper.createItemTag("backpack/end");

    public static final TagKey<Item> COPPER_INGOTS = TagHelper.createItemTag("copper_ingots");
    public static final TagKey<Item> GOLD_INGOTS = TagHelper.createItemTag("gold_ingots");
    public static final TagKey<Item> IRON_INGOTS = TagHelper.createItemTag("iron_ingots");
    public static final TagKey<Item> NETHERITE_INGOTS = TagHelper.createItemTag("netherite_ingots");
    public static final TagKey<Item> DIAMONDS = TagHelper.createItemTag("diamonds");
    public static final TagKey<Item> LEATHER = TagHelper.createItemTag("leather");

    public BackpackItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, Registries.ITEM, lookupProvider, BackpackConstants.MOD_ID, existingFileHelper);
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

        tagBuilder(LEATHER, Items.LEATHER, Items.RABBIT_HIDE);

        tagBuilder(BACKPACK_LEATHER_INGREDIENT, LEATHER);

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
        TagAppender<Item> appender = this.tag(outputTag);

        for (RegistryObject<Block> block : blocks) {
            appender.add(convert(block));
        }
    }

    private void tagBuilder(TagKey<Item> outputTag, Item... items) {
        TagAppender<Item> appender = this.tag(outputTag);

        for (Item item : items) {
            appender.add(BuiltInRegistries.ITEM.getResourceKey(item).get());
        }
    }

    @SafeVarargs
    private void tagBuilder(TagKey<Item> outputTag, TagKey<Item>... tagKeys) {
        TagAppender<Item> appender = this.tag(outputTag);

        for (TagKey<Item> tag : tagKeys) {
            appender.addTag(tag);
        }
    }

    private ResourceKey<Item> convert(RegistryObject<Block> block) {
        ResourceKey<Block> blockKey = block.getResourceKey();
        return ResourceKey.create(Registries.ITEM, blockKey.location());
    }
}