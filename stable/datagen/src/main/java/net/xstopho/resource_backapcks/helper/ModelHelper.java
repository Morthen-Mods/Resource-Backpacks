package net.xstopho.resource_backapcks.helper;

import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.BackpackBlock;

import java.util.Optional;

public class ModelHelper {
    public static void createBackpackModel(BlockModelGenerators generator, Block block) {
        Identifier blockKey = getKey(block).withPrefix("block/");

        TextureMapping map = new TextureMapping();
        map.put(TextureSlot.TEXTURE, new Material(blockKey));

        Identifier model = new ModelTemplate(Optional.of(BackpackConstants.of("block/base/backpack")),
                Optional.empty(), TextureSlot.TEXTURE).create(block, map, generator.modelOutput);

        Identifier wallModel = new ModelTemplate(Optional.of(BackpackConstants.of("block/base/backpack_on_wall")),
                Optional.of("_on_wall"), TextureSlot.TEXTURE).create(block, map, generator.modelOutput);

        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(createProperties(model, wallModel)));

        new ModelTemplate(Optional.of(model), Optional.empty()).create(block.asItem(), new TextureMapping(), generator.modelOutput);
    }

    private static PropertyDispatch<MultiVariant> createProperties(Identifier normal, Identifier wall) {
        MultiVariant model = BlockModelGenerators.plainVariant(normal);
        MultiVariant wallModel = BlockModelGenerators.plainVariant(wall);
        return PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING, BackpackBlock.PLACED_ON_WALL)
                .select(Direction.NORTH, false, model)
                .select(Direction.NORTH, true, wallModel)

                .select(Direction.EAST, false, model.with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
                .select(Direction.EAST, true, wallModel.with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))

                .select(Direction.SOUTH, false, model.with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
                .select(Direction.SOUTH, true, wallModel.with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))

                .select(Direction.WEST, false, model.with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
                .select(Direction.WEST, true, wallModel.with(VariantMutator.Y_ROT.withValue(Quadrant.R270)));

    }

    private static Identifier getKey(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
}
