package net.xstopho.resource_backpacks.screen;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConfig;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_backpacks.util.BackpackLevel;

public class BackpackMenu extends AbstractContainerMenu {

    private final Container backpackInventory;
    private final BackpackLevel backpackLevel;

    private BackpackMenu(MenuType<?> menuType, int containerId, Inventory playerInventory, BackpackLevel backpackLevel) {
        this(menuType, containerId, playerInventory, new SimpleContainer(backpackLevel.getSize()), backpackLevel);
    }

    public static BackpackMenu defaultMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.DEFAULT_MENU.get(), containerId, playerInventory, BackpackLevel.DEFAULT);
    }

    public static BackpackMenu defaultMenu(int containerId, Inventory playerInventory, Container backpackInventory) {
        return new BackpackMenu(MenuTypeRegistry.DEFAULT_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.DEFAULT);
    }

    public static BackpackMenu leatherMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.LEATHER_MENU.get(), containerId, playerInventory, BackpackLevel.LEATHER);
    }

    public static BackpackMenu leatherMenu(int containerId, Inventory playerInventory, Container backpackInventory) {
        return new BackpackMenu(MenuTypeRegistry.LEATHER_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.LEATHER);
    }

    public static BackpackMenu copperMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.COPPER_MENU.get(), containerId, playerInventory, BackpackLevel.COPPER);
    }

    public static BackpackMenu copperMenu(int containerId, Inventory playerInventory, Container backpackInventory) {
        return new BackpackMenu(MenuTypeRegistry.COPPER_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.COPPER);
    }

    public static BackpackMenu goldMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.GOLD_MENU.get(), containerId, playerInventory, BackpackLevel.GOLD);
    }

    public static BackpackMenu goldMenu(int containerId, Inventory playerInventory, Container backpackInventory) {
        return new BackpackMenu(MenuTypeRegistry.GOLD_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.GOLD);
    }

    public static BackpackMenu ironMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.IRON_MENU.get(), containerId, playerInventory, BackpackLevel.IRON);
    }

    public static BackpackMenu ironMenu(int containerId, Inventory playerInventory, Container backpackInventory) {
        return new BackpackMenu(MenuTypeRegistry.IRON_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.IRON);
    }

    public static BackpackMenu diamondMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.DIAMOND_MENU.get(), containerId, playerInventory, BackpackLevel.DIAMOND);
    }

    public static BackpackMenu diamondMenu(int containerId, Inventory playerInventory, Container backpackInventory) {
        return new BackpackMenu(MenuTypeRegistry.DIAMOND_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.DIAMOND);
    }

    public static BackpackMenu netheriteMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.NETHERITE_MENU.get(), containerId, playerInventory, BackpackLevel.NETHERITE);
    }

    public static BackpackMenu netheriteMenu(int containerId, Inventory playerInventory, Container backpackInventory) {
        return new BackpackMenu(MenuTypeRegistry.NETHERITE_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.NETHERITE);
    }

    public static BackpackMenu endMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.END_MENU.get(), containerId, playerInventory, BackpackLevel.END);
    }

    public static BackpackMenu endMenu(int containerId, Inventory playerInventory, Container backpackInventory) {
        return new BackpackMenu(MenuTypeRegistry.END_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.END);
    }

    public BackpackMenu(MenuType<?> menuType, int containerId, Inventory playerInventory, Container backpackInventory, BackpackLevel backpackLevel) {
        super(menuType, containerId);
        checkContainerSize(backpackInventory, backpackLevel.getSize());

        this.backpackInventory = backpackInventory;
        this.backpackLevel = backpackLevel;

        backpackInventory.startOpen(playerInventory.player);

        addBackpackSlots();

        int xPos = ((backpackLevel.getColumns() - 9) * 18) / 2;
        this.addStandardInventorySlots(playerInventory, xPos + 8, (backpackLevel.getRows() * 18) + 31);
    }

    private void addBackpackSlots() {
        for (int row = 0; row < backpackLevel.getRows(); row++) {
            for (int column = 0; column < backpackLevel.getColumns(); column++) {
                this.addSlot(new BackpackSlot(backpackInventory, column + row * 9, 8 + column * 18, 18 + row * 18));
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            returnStack = stack.copy();
            if (index < this.backpackInventory.getContainerSize()) {
                if (!this.moveItemStackTo(stack, this.backpackInventory.getContainerSize(), this.slots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 0, this.backpackInventory.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) slot.setByPlayer(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return returnStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.backpackInventory.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.backpackInventory.stopOpen(player);
    }

    public BackpackLevel getBackpackLevel() {
        return backpackLevel;
    }

    public static class BackpackSlot extends Slot {
        public BackpackSlot(Container inventory, int index, int x, int y) {
            super(inventory, index, x, y);

        }

        @Override
        public boolean mayPickup(Player player) {
            return this.canMoveStack(this.getItem());

        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return this.canMoveStack(stack);

        }

        public boolean canMoveStack(ItemStack stack) {
            return BackpackConfig.TAKE_CONTAINER_ITEMS_FROM_END_BACKPACK.get()
                    || stack.getItem().canFitInsideContainerItems();

        }
    }
}
