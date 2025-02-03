package net.xstopho.resource_backpacks.client.slot;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.BackpackItem;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.network.payloads.SyncEntityBackpackPayload;
import org.jetbrains.annotations.Nullable;

public class BackpackSlot extends Slot {

    private static final ResourceLocation BACKPACK_SPRITE = BackpackConstants.of("item/empty_slot_backpack");
    private final LivingEntity entity;

    public BackpackSlot(Container container, LivingEntity entity) {
        super(container, 0, 26, 62);
        this.entity = entity;
    }

    @Override
    public void setChanged() {
        ((BackpackHolder) entity).setBackpack(getItem());
        if (!entity.level().isClientSide()) {
            BackpackNetwork.INSTANCE.sendToClientsTrackingEntity(entity, new SyncEntityBackpackPayload(entity.getId(), getItem()));
        }
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
