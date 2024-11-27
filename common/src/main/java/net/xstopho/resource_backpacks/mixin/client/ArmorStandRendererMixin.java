package net.xstopho.resource_backpacks.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmorStandArmorModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ArmorStandRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.items.BackpackItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandRenderer.class)
public abstract class ArmorStandRendererMixin extends LivingEntityRenderer<ArmorStand, ArmorStandRenderState, ArmorStandArmorModel> {

    public ArmorStandRendererMixin(EntityRendererProvider.Context context, ArmorStandArmorModel model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addBackpackFeature(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new BackpackFeature(this));
    }

    private static class BackpackFeature extends RenderLayer<ArmorStandRenderState, ArmorStandArmorModel> {

        public BackpackFeature(RenderLayerParent<ArmorStandRenderState, ArmorStandArmorModel> renderer) {
            super(renderer);
        }

        @Override
        public void render(PoseStack poseStack, MultiBufferSource buffer, int light, ArmorStandRenderState armorStand, float v, float v1) {
            ItemStack chest = armorStand.chestItem;

            if (chest.getItem() instanceof BackpackItem) {
                poseStack.pushPose();
                poseStack.mulPose(Axis.XP.rotationDegrees(180));
                poseStack.scale(1.25f, 1.25f, 1.25f);

                poseStack.translate(0, -0.325, -0.19);

                Minecraft.getInstance().getItemRenderer().renderStatic(chest, ItemDisplayContext.GROUND, light, OverlayTexture.NO_OVERLAY, poseStack, buffer, Minecraft.getInstance().level, 0);
                poseStack.popPose();
            }
        }
    }
}
