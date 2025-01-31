package net.xstopho.resource_backpacks.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;

public class BackpackRenderLayer extends RenderLayer<LivingEntity, HumanoidModel<LivingEntity>> {

    private final BackpackModel<LivingEntity> backpackModel;

    public BackpackRenderLayer(RenderLayerParent<LivingEntity, HumanoidModel<LivingEntity>> renderer, EntityModelSet modelSet) {
        super(renderer);

        this.backpackModel = new BackpackModel<>(modelSet.bakeLayer(BackpackModel.BACKPACK_LAYER));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int lightness, LivingEntity entity, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        ItemStack backpack = ((BackpackHolder) entity).getBackpack();

        if (!backpack.isEmpty()) {
            poseStack.pushPose();
            this.getParentModel().copyPropertiesTo(backpackModel);

            if (entity.isCrouching()) {
                poseStack.mulPose(Axis.XP.rotationDegrees(29));
                poseStack.translate(0, 0.17, -0.095);
            }

            VertexConsumer consumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(BackpackModel.getTexture(backpack)));
            this.backpackModel.renderToBuffer(poseStack, consumer, lightness, OverlayTexture.NO_OVERLAY);

            poseStack.popPose();
        }
    }

    @Override
    protected ResourceLocation getTextureLocation(LivingEntity entity) {
        ItemStack backpack = ((BackpackHolder) entity).getBackpack();
        return BackpackModel.getTexture(backpack);
    }
}
