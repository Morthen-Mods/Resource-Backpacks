package net.xstopho.resource_backpacks.mixin.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.client.BackpackRenderLayer;
import net.xstopho.resource_backpacks.client.util.BackpackRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {

    @Invoker("addLayer")
    public abstract boolean invokeAddLayer(RenderLayer<?, ?> feature);

    @Inject(at = @At("RETURN"), method = "<init>")
    public void init(EntityRendererProvider.Context context, EntityModel<?> model, float shadowRadius, CallbackInfo info) {
        this.invokeAddLayer(new BackpackRenderLayer<>((LivingEntityRenderer) (Object) this, context.getModelSet()));
    }

    @Inject(method = "extractRenderState", at = @At("TAIL"))
    public void resource_backpacks$extractRenderState(LivingEntity livingEntity, LivingEntityRenderState renderState, float partialTicks, CallbackInfo info) {
        ItemStack backpack = ((BackpackHolder) livingEntity).getBackpack();
        ((BackpackRenderState) renderState).setBackpack(backpack);
    }
}
