package net.xstopho.resource_backpacks.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.BlockRegistry;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.util.TagUtil;

import java.util.concurrent.CompletableFuture;

public class BackpackBlockTags extends TagsProvider<Block> {

    public static final TagKey<Block> BACKPACKS = TagUtil.createBlockTag("backpack");

    public BackpackBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.BLOCK, lookupProvider, BackpackConstants.MOD_ID);
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
    private void tagBuilder(TagKey<Block> outputTag, RegistryObject<Block>... blocks) {
        TagAppender<Block> appender = this.tag(outputTag);

        for (RegistryObject<Block> block : blocks) {
            appender.add(block.getResourceKey());
        }
    }
}
