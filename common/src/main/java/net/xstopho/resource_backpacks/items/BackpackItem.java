package net.xstopho.resource_backpacks.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.xstopho.resource_backpacks.entities.BackpackBlockEntity;
import net.xstopho.resource_backpacks.util.BackpackInventory;
import net.xstopho.resource_backpacks.util.BackpackLevel;

public class BackpackItem extends BlockItem {

    private final BackpackLevel backpackLevel;
    private final NonNullList<ItemStack> items;

    public BackpackItem(Block block, BackpackLevel level, Properties properties) {
        super(block, properties.useBlockDescriptionPrefix());
        this.backpackLevel = level;
        this.items = NonNullList.withSize(level.getSize(), ItemStack.EMPTY);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            if (this.backpackLevel == BackpackLevel.SHULKER) {
                player.openMenu(new SimpleMenuProvider((i, inventory, player1) -> new ShulkerBoxMenu(i, inventory, new BackpackInventory(player.getItemInHand(hand), this.items)), Component.literal("Test")));
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        InteractionResult result = super.place(context);

        if (result != InteractionResult.FAIL) {
            BlockPos pos = context.getClickedPos();
            BlockState blockState = context.getLevel().getBlockState(pos);

            BackpackBlockEntity backpackBlockEntity = new BackpackBlockEntity(pos, blockState);

            backpackBlockEntity.setItems(this.items);

            context.getLevel().setBlockEntity(backpackBlockEntity);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }
}
