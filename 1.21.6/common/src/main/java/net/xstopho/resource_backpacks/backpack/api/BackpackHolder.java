package net.xstopho.resource_backpacks.backpack.api;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.xstopho.resource_backpacks.client.slot.BackpackSlot;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface BackpackHolder {
    String tagId = "resource_backpacks$backpack";

    ItemStack getBackpack();
    void setBackpack(ItemStack backpack);

    static void restorePlayerBackpack(Player oldPlayer, Player newPlayer) {
        ItemStack itemStack = ((BackpackHolder) oldPlayer).getBackpack();
        for (Slot slot : newPlayer.inventoryMenu.slots) {
            if (slot instanceof BackpackSlot) {
                slot.set(itemStack);
            }
        }
    }

    default void dropBackpack(Level level, BlockPos pos) {
        ItemStack itemStack = this.getBackpack();
        if (!itemStack.isEmpty() && !level.isClientSide()) {
            ItemEntity entity = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), itemStack);
            entity.setDefaultPickUpDelay();
            level.addFreshEntity(entity);
        }
    }

    default void readBackpackFromValueInput(ValueInput valueInput) {
        ItemStack backpack = valueInput.read(tagId, ItemStack.CODEC).orElse(ItemStack.EMPTY);
        this.setBackpack(backpack);
    }

    default void saveBackpackToValueOutput(ValueOutput valueOutput) {
        valueOutput.store(tagId, ItemStack.CODEC, this.getBackpack());
    }
}
