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
import net.xstopho.resource_backpacks.client.slot.BackpackSlot;
import org.jetbrains.annotations.ApiStatus;

import java.util.Optional;

@ApiStatus.Internal
public interface BackpackHolder {
    String tagId = "resource_backpacks$backpack";

    Optional<ItemStack> getBackpack();
    void setBackpack(ItemStack backpack);

    static void restorePlayerBackpack(Player oldPlayer, Player newPlayer) {
        ((BackpackHolder) oldPlayer).getBackpack().ifPresent(itemStack -> {
            for (Slot slot : newPlayer.inventoryMenu.slots) {
                if (slot instanceof BackpackSlot) {
                    slot.set(itemStack);
                }
            }
        });
    }

    default void dropBackpack(Level level, BlockPos pos) {
        this.getBackpack().ifPresent(itemStack -> {
            if (!itemStack.isEmpty() && !level.isClientSide()) {
                ItemEntity entity = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), itemStack);
                entity.setDefaultPickUpDelay();
                level.addFreshEntity(entity);
            }
        });
    }

    default void readBackpackFromCompound(CompoundTag tag, HolderLookup.Provider registryAccess) {
        if (tag.contains(tagId)) {
            ItemStack backpack = ItemStack.parse(registryAccess, tag.getCompound(tagId)).orElse(ItemStack.EMPTY);
            this.setBackpack(backpack);
        }
    }

    default void saveBackpackOnCompound(CompoundTag tag, HolderLookup.Provider registryAccess) {
        this.getBackpack().ifPresent(itemStack -> {
            if (itemStack.isEmpty()) return;
            Tag backpack = itemStack.save(registryAccess);
            tag.put(tagId, backpack);
        });
    }
}
