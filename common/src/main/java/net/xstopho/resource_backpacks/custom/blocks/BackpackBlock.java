package net.xstopho.resource_backpacks.custom.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.xstopho.resource_backpacks.custom.entities.BackpackBlockEntity;
import net.xstopho.resource_backpacks.custom.util.BackpackLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BackpackBlock extends BaseEntityBlock {

    private final BackpackLevel backpackLevel;

    public BackpackBlock(Properties properties, BackpackLevel backpackLevel) {
        super(properties);
        this.backpackLevel = backpackLevel;
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof BackpackBlockEntity backpackBlockEntity) {
            player.openMenu(backpackBlockEntity);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (!level.isClientSide) {
            ItemStack backpack = new ItemStack(this.asItem());
            NonNullList<ItemStack> items = ((BackpackBlockEntity) blockEntity).getItems();
            backpack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(items));

            ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), backpack);

            level.addFreshEntity(itemEntity);
        }

        super.playerDestroy(level, player, pos, state, blockEntity, tool);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BackpackBlockEntity(blockPos, blockState);
    }

    public BackpackLevel getBackpackLevel() {
        return backpackLevel;
    }

    public static BackpackLevel getLevelFromBlock(Block block) {
        return block instanceof BackpackBlock ? ((BackpackBlock) block).getBackpackLevel() : null;
    }
}