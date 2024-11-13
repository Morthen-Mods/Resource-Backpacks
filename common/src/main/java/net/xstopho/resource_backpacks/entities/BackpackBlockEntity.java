package net.xstopho.resource_backpacks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.xstopho.resource_backpacks.blocks.BackpackBlock;
import net.xstopho.resource_backpacks.registries.BlockEntityRegistry;
import net.xstopho.resource_backpacks.screen.BackpackMenu;
import net.xstopho.resource_backpacks.util.BackpackLevel;
import net.xstopho.resource_backpacks.util.ImplementedInventory;
import org.jetbrains.annotations.NotNull;

public class BackpackBlockEntity extends BaseContainerBlockEntity implements ImplementedInventory {

    private NonNullList<ItemStack> items;
    private BackpackLevel backpackLevel;

    public BackpackBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistry.BACKPACK_ENTITY.get(), pos, blockState);
        this.backpackLevel = BackpackBlock.getLevelFromBlock(blockState.getBlock());

        items = NonNullList.withSize(backpackLevel.getSize(), ItemStack.EMPTY);
    }

    public BackpackBlockEntity(BlockPos pos, BlockState state, BackpackLevel backpackLevel) {
        this(pos, state);
        this.backpackLevel = backpackLevel;
        this.items = NonNullList.withSize(backpackLevel.getSize(), ItemStack.EMPTY);
    }

    @Override
    public @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("Test Backpack");
    }

    @Override
    public void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return switch(backpackLevel) {
            case LEATHER -> BackpackMenu.leatherMenu(i, inventory, this);
            case COPPER -> BackpackMenu.copperMenu(i, inventory, this);
            case GOLD -> BackpackMenu.goldMenu(i, inventory, this);
            case IRON -> BackpackMenu.ironMenu(i, inventory, this);
            case DIAMOND -> BackpackMenu.diamondMenu(i, inventory, this);
            case NETHERITE -> BackpackMenu.netheriteMenu(i, inventory, this);
            case END -> BackpackMenu.endMenu(i, inventory, inventory.player.getEnderChestInventory());
            default -> null;
        };
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.loadFromTag(tag, registries);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, true, registries);
    }

    public void loadFromTag(CompoundTag tag, HolderLookup.Provider levelRegistry) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, levelRegistry);
    }
}
