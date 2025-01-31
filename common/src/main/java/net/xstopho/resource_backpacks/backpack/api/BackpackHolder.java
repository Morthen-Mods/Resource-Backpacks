package net.xstopho.resource_backpacks.backpack.api;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface BackpackHolder {

    ItemStack resource_backpacks$getBackpack();
    void resource_backpacks$setBackpack(ItemStack backpack);

    default void resource_backpacks$dropBackpack(LivingEntity entity) {
        //TODO: add Gamerule Keep Inventory check
        ItemStack backpack = ((BackpackHolder) entity).resource_backpacks$getBackpack();
        if (!backpack.isEmpty()) {
            if (!entity.level().isClientSide()) {
                entity.spawnAtLocation(backpack);
                this.resource_backpacks$setBackpack(ItemStack.EMPTY);
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
