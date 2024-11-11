package net.xstopho.resource_backpacks.custom.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.xstopho.resource_backpacks.custom.entities.BackpackBlockEntity;
import org.jetbrains.annotations.Nullable;

public class BackpackBlock extends BaseEntityBlock {

    public BackpackBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof BackpackBlockEntity backpackBlockEntity) {

            player.openMenu(backpackBlockEntity);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BackpackBlockEntity(blockPos, blockState);
    }
}