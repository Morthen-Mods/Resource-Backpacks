package net.xstopho.resource_backapcks.helper;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.BackpackBlock;

import java.util.Optional;

public class ModelHelper {
    public static void createBackpackModel(BlockModelGenerators generator, Block block) {
        ResourceLocation blockKey = getKey(block).withPrefix("block/");

        TextureMapping map = new TextureMapping();
        map.put(TextureSlot.TEXTURE, blockKey);

        ResourceLocation model = new ModelTemplate(Optional.of(BackpackConstants.of("block/base/backpack")),
                Optional.empty(), TextureSlot.TEXTURE).create(block, map, generator.modelOutput);

        ResourceLocation wallModel = new ModelTemplate(Optional.of(BackpackConstants.of("block/base/backpack_on_wall")),
                Optional.of("_on_wall"), TextureSlot.TEXTURE).create(block, map, generator.modelOutput);

        generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(createProperties(model, wallModel)));

        new ModelTemplate(Optional.of(model), Optional.empty()).create(block.asItem(), new TextureMapping(), generator.modelOutput);
    }

    private static PropertyDispatch createProperties(ResourceLocation model, ResourceLocation wallModel) {
        return PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, BackpackBlock.PLACED_ON_WALL)
                .select(Direction.NORTH, false, Variant.variant().with(VariantProperties.MODEL, model))
                .select(Direction.NORTH, true, Variant.variant().with(VariantProperties.MODEL, wallModel))

                .select(Direction.EAST, false, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .select(Direction.EAST, true, Variant.variant().with(VariantProperties.MODEL, wallModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))

                .select(Direction.SOUTH, false, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                .select(Direction.SOUTH, true, Variant.variant().with(VariantProperties.MODEL, wallModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))

                .select(Direction.WEST, false, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                .select(Direction.WEST, true, Variant.variant().with(VariantProperties.MODEL, wallModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270));

    }

    private static ResourceLocation getKey(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
}
