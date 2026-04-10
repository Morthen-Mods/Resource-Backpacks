package net.xstopho.resource_backpacks.mixin.common;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.TooltipProvider;
import net.xstopho.resource_backpacks.registries.DataComponentRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    public abstract <T extends TooltipProvider> void addToTooltip(DataComponentType<T> component, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag);

    @Inject( at = @At("TAIL"),
            method = "addDetailsToTooltip(Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/TooltipFlag;Ljava/util/function/Consumer;)V")
    public void resource_backpacks$addDetailsToTooltip(Item.TooltipContext context, TooltipDisplay display, Player player, TooltipFlag flag, Consumer<Component> consumer, CallbackInfo info) {
        this.addToTooltip(DataComponentRegistry.BACKPACK_CONTAINER.get(), context, display, consumer, flag);
    }
}
