package net.morthen.resource_backpacks.mixin.client;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.item.ItemStack;
import net.morthen.resource_backpacks.client.util.BackpackRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public abstract class LivingEntityRenderStateMixin extends EntityRenderState implements BackpackRenderState {

    @Unique
    public ItemStack backpack = ItemStack.EMPTY;

    @Override
    public ItemStack getBackpack() {
        return backpack;
    }

    @Override
    public void setBackpack(ItemStack backpack) {
        this.backpack = backpack;
    }
}
