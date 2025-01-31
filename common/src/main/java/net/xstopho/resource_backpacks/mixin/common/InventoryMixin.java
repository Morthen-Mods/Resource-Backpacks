package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.client.slot.BackpackHolderDeprecated;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

//@Deprecated(forRemoval = true, since = "0.13.0-BETA")
@Mixin(Inventory.class)
public class InventoryMixin implements BackpackHolderDeprecated {

    @Shadow @Final @Mutable
    private List<NonNullList<ItemStack>> compartments;

    @Shadow
    private Player player;

    public final NonNullList<ItemStack> backpack = NonNullList.withSize(1, ItemStack.EMPTY);

    @Inject(method = "<init>", at = @At("RETURN"))
    private void resource_backpack$addBackpackToCompartments(Player player, CallbackInfo info) {
        this.compartments = new ArrayList<>(this.compartments);
        this.compartments.addLast(this.backpack);
    }

    @Override
    public ItemStack resource_backpack$getBackpack() {
        return backpack.getFirst();
    }

    @Override
    public void resource_backpack$setBackpack(ItemStack backpack) {
        this.backpack.set(0, ItemStack.EMPTY);
    }

    @Inject(method = "getContainerSize", at = @At("RETURN"), cancellable = true)
    private void resource_backpack$modifyContainerSize(CallbackInfoReturnable<Integer> cir) {
        int size = 0;
        for (NonNullList<ItemStack> nonNullList : this.compartments) {
            size += nonNullList.size();
        }
        cir.setReturnValue(size);
    }

    @Inject(method = "isEmpty", at = @At("HEAD"), cancellable = true)
    private void resource_backpack$modifyIsEmpty(CallbackInfoReturnable<Boolean> cir) {
        if (!this.backpack.getFirst().isEmpty()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "save", at = @At("RETURN"), cancellable = true)
    private void resource_backpack$saveBackpack(ListTag listTag, CallbackInfoReturnable<ListTag> cir) {
        for (int index = 0; index < this.backpack.size(); index++) {
            if (!this.backpack.get(index).isEmpty()) {
                CompoundTag backpackTag = new CompoundTag();
                backpackTag.putByte("Slot", (byte) (index + 254));
                listTag.add(this.backpack.get(index).save(this.player.registryAccess(), backpackTag));
            }
        }
        cir.setReturnValue(listTag);
    }

    @Inject(method = "load", at = @At("HEAD"))
    private void resource_backpack$loadBackpack(ListTag listTag, CallbackInfo info) {
        this.backpack.clear();

        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag backpackTag = listTag.getCompound(i);
            int index = backpackTag.getByte("Slot") & 255;
            ItemStack backpack = ItemStack.parse(this.player.registryAccess(), backpackTag).orElse(ItemStack.EMPTY);
            if (index >= 254 && index < this.backpack.size() + 254) {
                this.backpack.set(index - 254, backpack);
            }
        }
    }
}
