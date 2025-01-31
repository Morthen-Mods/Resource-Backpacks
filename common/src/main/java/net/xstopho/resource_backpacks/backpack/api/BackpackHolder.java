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

    ItemStack resource_backpacks$getBackpack();
    void resource_backpacks$setBackpack(ItemStack backpack);

    static void resource_backpacks$restoreBackpack(ServerPlayer oldPlayer, ServerPlayer newPlayer) {
        ItemStack backpack = ((BackpackHolder) oldPlayer).resource_backpacks$getBackpack();
        for (Slot slot : newPlayer.inventoryMenu.slots) {
            if (slot instanceof BackpackSlot) {
                slot.set(backpack);
            }
        }
    }

    default void resource_backpacks$dropBackpack(LivingEntity entity) {
        ItemStack backpack = ((BackpackHolder) entity).resource_backpacks$getBackpack();
        if (!backpack.isEmpty()) {
            if (!entity.level().isClientSide()) {
                if (!entity.getServer().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
                    entity.spawnAtLocation(backpack);
                    this.resource_backpacks$setBackpack(ItemStack.EMPTY);
                }
            }
        }
    }

    default void resource_backpacks$readBackpack(CompoundTag tag, HolderLookup.Provider registryAccess) {
        if (tag.contains("resource_backpacks$backpack")) {
            ItemStack backpack = ItemStack.parse(registryAccess, tag.getCompound("resource_backpacks$backpack")).orElse(ItemStack.EMPTY);
            this.resource_backpacks$setBackpack(backpack);
        }
    }

    default void resource_backpacks$saveBackpack(CompoundTag tag, HolderLookup.Provider registryAccess) {
        if (!this.resource_backpacks$getBackpack().isEmpty()) {
            Tag backpack = this.resource_backpacks$getBackpack().save(registryAccess);
            tag.put("resource_backpacks$backpack", backpack);
        }
    }
}
