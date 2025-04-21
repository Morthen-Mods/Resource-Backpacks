package net.xstopho.resource_backpacks.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.client.model.BackpackModel;
import net.xstopho.resource_backpacks.client.util.BackpackRenderState;

public class CreeperBackpackLayer extends RenderLayer<CreeperRenderState, CreeperModel> {
    private final BackpackModel<CreeperRenderState> backpackModel;

    public CreeperBackpackLayer(RenderLayerParent<CreeperRenderState, CreeperModel> renderer, EntityModelSet modelSet) {
        super(renderer);

        this.backpackModel = new BackpackModel<>(modelSet.bakeLayer(BackpackModel.BACKPACK_LAYER));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int light, CreeperRenderState state, float v, float v1) {
        ItemStack backpack = ((BackpackRenderState) state).getBackpack();

        if (!backpack.isEmpty()) {
            poseStack.pushPose();

            poseStack.scale(0.75f, 0.75f, 0.75f);
            poseStack.translate(0f, 0.5f, 0f);

            VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(BackpackModel.getTexture(backpack)));
            backpackModel.renderToBuffer(poseStack, consumer, light, OverlayTexture.NO_OVERLAY);

            poseStack.popPose();
        }
    }
}
