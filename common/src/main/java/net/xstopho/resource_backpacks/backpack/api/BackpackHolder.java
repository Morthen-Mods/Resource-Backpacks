package net.xstopho.resource_backpacks.backpack.api;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.xstopho.resource_backpacks.client.slot.BackpackSlot;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface BackpackHolder {

    ItemStack getBackpack();
    void setBackpack(ItemStack backpack);

    static void restorePlayerBackpack(ServerPlayer oldPlayer, ServerPlayer newPlayer) {
        ItemStack backpack = ((BackpackHolder) oldPlayer).getBackpack();
        for (Slot slot : newPlayer.inventoryMenu.slots) {
            if (slot instanceof BackpackSlot) {
                slot.set(backpack);
            }
        }
    }

    default void dropBackpack(LivingEntity entity) {
        ItemStack backpack = ((BackpackHolder) entity).getBackpack();
        if (!backpack.isEmpty()) {
            if (!entity.level().isClientSide()) {
                if (!entity.getServer().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
                    entity.spawnAtLocation(backpack);
                    this.setBackpack(ItemStack.EMPTY);
                }
            }
        }
    }

    default void readBackpackFromCompound(CompoundTag tag, HolderLookup.Provider registryAccess) {
        if (tag.contains("resource_backpacks$backpack")) {
            ItemStack backpack = ItemStack.parse(registryAccess, tag.getCompound("resource_backpacks$backpack")).orElse(ItemStack.EMPTY);
            this.setBackpack(backpack);
        }
    }

    default void saveBackpackOnCompound(CompoundTag tag, HolderLookup.Provider registryAccess) {
        if (!this.getBackpack().isEmpty()) {
            Tag backpack = this.getBackpack().save(registryAccess);
            tag.put("resource_backpacks$backpack", backpack);
        }
    }
}
