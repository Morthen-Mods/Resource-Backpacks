package net.xstopho.resource_backpacks.screen;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_backpacks.util.BackpackLevel;

public class BackpackMenu extends AbstractContainerMenu {

    private final Container backpackInventory;
    private final BackpackLevel backpackLevel;

    private BackpackMenu(MenuType<?> menuType, int containerId, Inventory playerInventory, BackpackLevel backpackLevel) {
        this(menuType, containerId, playerInventory, new SimpleContainer(backpackLevel.getSize()), backpackLevel);
    }

    public static BackpackMenu testMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.TEST_MENU.get(), containerId, playerInventory, BackpackLevel.TEST);
    }

    public static BackpackMenu testMenu(int containerId, Inventory playerInventory, Container backpackInventory) {
        return new BackpackMenu(MenuTypeRegistry.TEST_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.TEST);
    }

    public BackpackMenu(MenuType<?> menuType, int containerId, Inventory playerInventory, Container backpackInventory, BackpackLevel backpackLevel) {
        super(menuType, containerId);

        this.backpackInventory = backpackInventory;
        this.backpackLevel = backpackLevel;

        backpackInventory.startOpen(playerInventory.player);

        addBackpackSlots();

        int xPos = ((backpackLevel.getColumns() - 9) * 18) / 2;
        this.addStandardInventorySlots(playerInventory, xPos + 8, (backpackLevel.getRows() * 18) + 31);
    }

    private void addBackpackSlots() {
        int index = 0;
        for (int i = 0; i < backpackLevel.getRows(); i++) {
            for (int y = 0; y < backpackLevel.getColumns(); y++) {
                this.addSlot(new BackpackSlot(backpackInventory, index, 8 + y * 18, 18 + i * 18));
                index++;
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
                if (!this.moveItemStackTo(stack, this.backpackInventory.getContainerSize(), this.slots.size(), true)) {
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

        public boolean mayPickup(Player playerEntity) {
            return this.canMoveStack(this.getItem());
        }

        public boolean mayPlace(ItemStack stack) {
            return this.canMoveStack(stack);
        }

        public boolean canMoveStack(ItemStack stack) {
            return stack.getItem().canFitInsideContainerItems();
        }
    }
}
