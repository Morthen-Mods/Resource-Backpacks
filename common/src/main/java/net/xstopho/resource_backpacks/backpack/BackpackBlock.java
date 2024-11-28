package net.xstopho.resource_backpacks.backpack;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.xstopho.resource_backpacks.backpack.util.BackpackBlockShapes;
import net.xstopho.resource_backpacks.util.BackpackLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BackpackBlock extends BaseEntityBlock implements SimpleWaterloggedBlock{

    public static final MapCodec<BackpackBlock> CODEC = simpleCodec(BackpackBlock::new);

    private final BackpackLevel backpackLevel;

    public BackpackBlock(Properties properties, BackpackLevel backpackLevel) {
        super(properties);
        this.backpackLevel = backpackLevel;
    }

    private BackpackBlock(Properties properties) {
        this(properties, BackpackLevel.DEFAULT);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level instanceof ServerLevel) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BackpackBlockEntity backpackBlockEntity) {
                player.openMenu(backpackBlockEntity);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (level instanceof ServerLevel) {
            if (blockEntity instanceof BackpackBlockEntity backpackBlockEntity) {
                ItemStack backpack = new ItemStack(this.asItem());
                backpack.applyComponents(backpackBlockEntity.collectComponents());

                ItemEntity entity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, backpack);
                entity.setDefaultPickUpDelay();

                level.addFreshEntity(entity);
            }
        }

        super.playerDestroy(level, player, pos, state, blockEntity, tool);
    }

    @Override
    protected BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess scheduledTickAccess, BlockPos blockPos,
                                     Direction direction, BlockPos blockPos1, BlockState blockState1, RandomSource randomSource) {

        if (blockState.getValue(BlockStateProperties.WATERLOGGED)) {
            scheduledTickAccess.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelReader));
        }

        return super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos1, blockState1, randomSource);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        return BackpackBlockShapes.getVoxelShape(direction);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());

        return defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite())
                .setValue(BlockStateProperties.WATERLOGGED, fluidState.is(Fluids.WATER));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(BlockStateProperties.HORIZONTAL_FACING, rotation.rotate(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING)
                .add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BackpackBlockEntity(blockPos, blockState, this.backpackLevel);
    }

    public BackpackLevel getBackpackLevel() {
        return backpackLevel;
    }

    public static BackpackLevel getLevelFromBlock(Block block) {
        return block instanceof BackpackBlock ? ((BackpackBlock) block).getBackpackLevel() : null;
    }
}