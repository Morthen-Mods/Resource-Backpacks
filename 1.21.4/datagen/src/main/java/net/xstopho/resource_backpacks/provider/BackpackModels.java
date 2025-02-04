package net.xstopho.resource_backpacks.provider;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.helper.ModelHelper;
import net.xstopho.resource_backpacks.registries.BlockRegistry;

public class BackpackModels extends ModelProvider {

    public BackpackModels(PackOutput output) {
        super(output, BackpackConstants.MOD_ID);
    }

    private void createBlockModels(BlockModelGenerators block) {
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_LEATHER.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_COPPER.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_IRON.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_GOLD.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_DIAMOND.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_NETHERITE.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_END.get());
    }

    private void createItemModels(ItemModelGenerators item) {

    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        this.createBlockModels(blockModels);
        this.createItemModels(itemModels);
    }
}
