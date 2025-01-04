package net.xstopho.resource_backpacks.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.backpack.BackpackItem;

public class BackpackRenderLayer<T extends LivingEntityRenderState, M extends EntityModel<T>> extends RenderLayer<T, M> {

    private final BackpackModel<T> backpackModel;

    public BackpackRenderLayer(RenderLayerParent<T, M> renderer, EntityModelSet modelSet) {
        super(renderer);

        this.backpackModel = new BackpackModel<>(modelSet.bakeLayer(BackpackModel.BACKPACK_LAYER));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int lightness, T entity, float limbSwing,
                       float limbSwingAmount) {

        ItemStack stack = ((HumanoidRenderState) entity).chestItem;

        if (stack.getItem() instanceof BackpackItem) {
            poseStack.pushPose();

            if (((HumanoidRenderState) entity).isCrouching) {
                poseStack.mulPose(Axis.XP.rotationDegrees(29));
                poseStack.translate(0, 0.17, -0.095);
            }

            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(BackpackModel.getTexture(stack)));
            this.backpackModel.renderToBuffer(poseStack, consumer, lightness, OverlayTexture.NO_OVERLAY);

            poseStack.popPose();
        }
    }
}
