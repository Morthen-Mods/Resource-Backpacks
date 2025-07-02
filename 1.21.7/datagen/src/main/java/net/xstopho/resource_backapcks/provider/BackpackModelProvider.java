package net.xstopho.resource_backapcks.provider;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.xstopho.resource_backapcks.helper.ModelHelper;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.BlockRegistry;

public class BackpackModelProvider extends ModelProvider {
    public BackpackModelProvider(PackOutput output) {
        super(output, BackpackConstants.MOD_ID);
    }

    private void registerBlockModels(BlockModelGenerators block) {
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_LEATHER.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_COPPER.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_IRON.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_GOLD.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_DIAMOND.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_NETHERITE.get());
        ModelHelper.createBackpackModel(block, BlockRegistry.BACKPACK_END.get());
    }

    private void registerItemModels(ItemModelGenerators item) {

    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        this.registerBlockModels(blockModels);
        this.registerItemModels(itemModels);
    }
}
