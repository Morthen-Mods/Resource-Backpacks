package net.xstopho.resource_backpacks.client.screen;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.backpack.BackpackItem;
import net.xstopho.resource_backpacks.backpack.util.BackpackLevel;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BackpackMenu extends AbstractContainerMenu {

    private final Container backpackInventory;
    private final BackpackLevel backpackLevel;
    private final boolean externalBackpack;

    private BackpackMenu(MenuType<?> menuType, int containerId, Inventory playerInventory, BackpackLevel backpackLevel, boolean externalBackpack) {
        this(menuType, containerId, playerInventory, new SimpleContainer(backpackLevel.getSize()), backpackLevel, externalBackpack);
    }

    public static BackpackMenu leatherMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.LEATHER_MENU.get(), containerId, playerInventory, BackpackLevel.LEATHER, false);
    }

    public static BackpackMenu leatherMenu(int containerId, Inventory playerInventory, Container backpackInventory, boolean externalBackpack) {
        return new BackpackMenu(MenuTypeRegistry.LEATHER_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.LEATHER, externalBackpack);
    }

    public static BackpackMenu copperMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.COPPER_MENU.get(), containerId, playerInventory, BackpackLevel.COPPER, false);
    }

    public static BackpackMenu copperMenu(int containerId, Inventory playerInventory, Container backpackInventory, boolean externalBackpack) {
        return new BackpackMenu(MenuTypeRegistry.COPPER_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.COPPER, externalBackpack);
    }

    public static BackpackMenu goldMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.GOLD_MENU.get(), containerId, playerInventory, BackpackLevel.GOLD, false);
    }

    public static BackpackMenu goldMenu(int containerId, Inventory playerInventory, Container backpackInventory, boolean externalBackpack) {
        return new BackpackMenu(MenuTypeRegistry.GOLD_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.GOLD, externalBackpack);
    }

    public static BackpackMenu ironMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.IRON_MENU.get(), containerId, playerInventory, BackpackLevel.IRON, false);
    }

    public static BackpackMenu ironMenu(int containerId, Inventory playerInventory, Container backpackInventory, boolean externalBackpack) {
        return new BackpackMenu(MenuTypeRegistry.IRON_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.IRON, externalBackpack);
    }

    public static BackpackMenu diamondMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.DIAMOND_MENU.get(), containerId, playerInventory, BackpackLevel.DIAMOND, false);
    }

    public static BackpackMenu diamondMenu(int containerId, Inventory playerInventory, Container backpackInventory, boolean externalBackpack) {
        return new BackpackMenu(MenuTypeRegistry.DIAMOND_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.DIAMOND, externalBackpack);
    }

    public static BackpackMenu netheriteMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.NETHERITE_MENU.get(), containerId, playerInventory, BackpackLevel.NETHERITE, false);
    }

    public static BackpackMenu netheriteMenu(int containerId, Inventory playerInventory, Container backpackInventory, boolean externalBackpack) {
        return new BackpackMenu(MenuTypeRegistry.NETHERITE_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.NETHERITE, externalBackpack);
    }

    public static BackpackMenu endMenu(int containerId, Inventory playerInventory) {
        return new BackpackMenu(MenuTypeRegistry.END_MENU.get(), containerId, playerInventory, BackpackLevel.END, false);
    }

    public static BackpackMenu endMenu(int containerId, Inventory playerInventory, Container backpackInventory, boolean externalBackpack) {
        return new BackpackMenu(MenuTypeRegistry.END_MENU.get(), containerId, playerInventory, backpackInventory, BackpackLevel.END, externalBackpack);
    }

    public BackpackMenu(MenuType<?> menuType, int containerId, Inventory playerInventory, Container backpackInventory, BackpackLevel backpackLevel, boolean externalBackpack) {
        super(menuType, containerId);

        this.backpackInventory = backpackInventory;
        this.externalBackpack = externalBackpack;
        this.backpackLevel = backpackLevel;

        backpackInventory.startOpen(playerInventory.player);

        addBackpackSlots();

        int xPos = ((backpackLevel.getColumns() - 9) * 18) / 2;
        addStandardInventorySlots(playerInventory, xPos + 8, (backpackLevel.getRows() * 18) + 30);
    }

    private void addBackpackSlots() {
        int rows = backpackLevel.getRows();
        int columns = backpackLevel.getColumns();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                this.addSlot(new BackpackInventorySlot(backpackInventory, column + (row * columns), 8 + column * 18, 18 + row * 18, this.externalBackpack));
            }
        }
    }

    protected void addStandardInventorySlots(@NotNull Container playerInventory, int xPos, int yPos) {
        addInventoryExtendedSlots(playerInventory, xPos, yPos);
        addInventoryHotbarSlots(playerInventory, xPos, yPos + 58);
    }

    protected void addInventoryHotbarSlots(@NotNull Container playerInventory, int xPos, int yPos) {
        for(int i = 0; i < 9; ++i) {
            this.addSlot(new BackpackInventorySlot(playerInventory, i, xPos + i * 18, yPos, this.externalBackpack));
        }
    }

    protected void addInventoryExtendedSlots(@NotNull Container playerInventory, int xPos, int yPos) {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new BackpackInventorySlot(playerInventory, j + (i + 1) * 9, xPos + j * 18, yPos + i * 18, this.externalBackpack));
            }
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
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
    public boolean stillValid(@NotNull Player player) {
        return this.backpackInventory.stillValid(player);
    }

    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);
        this.backpackInventory.stopOpen(player);
    }

    public BackpackLevel getBackpackLevel() {
        return backpackLevel;
    }

    private static class BackpackInventorySlot extends Slot {
        private final boolean externalBackpack;

        public BackpackInventorySlot(Container inventory, int index, int x, int y, boolean externalBackpack) {
            super(inventory, index, x, y);
            this.externalBackpack = externalBackpack;
        }

        @Override
        public boolean mayPickup(Player player) {
            ItemStack handItem = player.getMainHandItem();
            ItemStack slotItem = this.getItem();

            if (handItem.getItem() instanceof BackpackItem && !externalBackpack) {
                return !Objects.equals(handItem, slotItem);
            }

            return true;
        }

        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            return BackpackConfig.allowBackpacksInsideBackpacks || this.canMoveStack(stack);
        }

        public boolean canMoveStack(ItemStack stack) {
            if (this.container instanceof Inventory) return true;

            return stack.getItem().canFitInsideContainerItems();
        }
    }
}
