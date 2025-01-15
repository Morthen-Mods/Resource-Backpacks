package net.xstopho.resource_backpacks.client.slot;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.BackpackItem;
import org.jetbrains.annotations.Nullable;

public class SurvivalBackpackSlot extends Slot {

    private static final ResourceLocation BACKPACK_SPRITE = BackpackConstants.of("item/empty_slot_backpack");

    public SurvivalBackpackSlot(Container container, int x, int y) {
        super(container, container.getContainerSize() - 1, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() instanceof BackpackItem;
    }

    @Override
    public @Nullable Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
        return Pair.of(InventoryMenu.BLOCK_ATLAS, BACKPACK_SPRITE);
    }
}
