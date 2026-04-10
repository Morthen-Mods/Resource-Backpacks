package net.xstopho.resource_backpacks.backpack;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.util.datafix.fixes.BlockEntityCustomNameToComponentFix;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.xstopho.resource_backpacks.backpack.api.ImplementedInventory;
import net.xstopho.resource_backpacks.backpack.component.BackpackContainerContents;
import net.xstopho.resource_backpacks.backpack.util.BackpackLevel;
import net.xstopho.resource_backpacks.client.screen.BackpackMenu;
import net.xstopho.resource_backpacks.registries.BlockEntityRegistry;
import net.xstopho.resource_backpacks.registries.DataComponentRegistry;

public class BackpackBlockEntity extends BlockEntity implements Nameable, ImplementedInventory, MenuProvider {
    private final BackpackLevel level;

    private NonNullList<ItemStack> items;
    private Component displayName;

    public BackpackBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistry.BACKPACK_ENTITY.get(), pos, blockState);
        this.level = BackpackBlock.getLevelFromBlock(blockState.getBlock());
        this.items = NonNullList.withSize(level.getSize(), ItemStack.EMPTY);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public Component getName() {
        return this.displayName != null ? this.displayName : level.getDefaultName();
    }

    @Override
    public Component getDisplayName() {
        return this.getName();
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        valueOutput.storeNullable("CustomName", ComponentSerialization.CODEC, this.displayName);
        ContainerHelper.saveAllItems(valueOutput, this.items, true);
    }

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        super.loadAdditional(valueInput);
        this.displayName = parseCustomNameSafe(valueInput, "CustomName");
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(valueInput, this.items);
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return switch(level) {
            case LEATHER -> BackpackMenu.leatherMenu(i, inventory, this, true);
            case COPPER -> BackpackMenu.copperMenu(i, inventory, this, true);
            case GOLD -> BackpackMenu.goldMenu(i, inventory, this, true);
            case IRON -> BackpackMenu.ironMenu(i, inventory, this, true);
            case DIAMOND -> BackpackMenu.diamondMenu(i, inventory, this, true);
            case NETHERITE -> BackpackMenu.netheriteMenu(i, inventory, this, true);
            case END -> BackpackMenu.endMenu(i, inventory, inventory.player.getEnderChestInventory(), true);
        };
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter getter) {
        this.displayName = getter.get(DataComponents.CUSTOM_NAME);
        getter.getOrDefault(DataComponentRegistry.BACKPACK_CONTAINER.get(), BackpackContainerContents.EMPTY).copyInto(this.getItems());
        super.applyImplicitComponents(getter);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder builder) {
        builder.set(DataComponents.CUSTOM_NAME, this.displayName);
        builder.set(DataComponentRegistry.BACKPACK_CONTAINER.get(), new BackpackContainerContents(this.getItems()));
        super.collectImplicitComponents(builder);
    }

    public void spawnFreshItemEntity(Level level, BlockPos pos, Item item) {
        if (!level.isClientSide()) {
            ItemStack itemStack = new ItemStack(item);
            itemStack.applyComponents(this.collectComponents());

            ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
            itemEntity.setDefaultPickUpDelay();

            level.addFreshEntity(itemEntity);
            this.clearContent();
        }
    }
}
