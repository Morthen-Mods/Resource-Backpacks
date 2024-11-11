package net.xstopho.resource_backpacks.custom.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.xstopho.resource_backpacks.api.ImplementedInventory;
import net.xstopho.resource_backpacks.custom.blocks.BackpackBlock;
import net.xstopho.resource_backpacks.custom.util.BackpackLevel;
import net.xstopho.resource_backpacks.registries.BlockEntityRegistry;
import org.jetbrains.annotations.NotNull;

public class BackpackBlockEntity extends BaseContainerBlockEntity implements ImplementedInventory {

    private NonNullList<ItemStack> items;
    private BackpackLevel level;

    public BackpackBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistry.BACKPACK_ENTITY.get(), pos, blockState);
        this.level = BackpackBlock.getLevelFromBlock(blockState.getBlock());

        items = NonNullList.withSize(level.getSize(), ItemStack.EMPTY);
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
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        if (this.items.size() == 27) {
            return new ShulkerBoxMenu(i, inventory, this);
        }
        if (this.items.size() == 54) {
            return ChestMenu.sixRows(i, inventory, this);
        }
        return null;
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
        ContainerHelper.saveAllItems(tag, this.items, false, registries);
    }

    public void loadFromTag(CompoundTag tag, HolderLookup.Provider levelRegistry) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, levelRegistry);
    }
}
