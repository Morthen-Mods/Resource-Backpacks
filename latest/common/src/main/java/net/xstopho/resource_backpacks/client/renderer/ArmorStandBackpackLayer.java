package net.xstopho.resource_backpacks.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.ArmorStandArmorModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ArmorStandRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.client.model.BackpackModel;
import net.xstopho.resource_backpacks.client.util.BackpackRenderState;

public class ArmorStandBackpackLayer extends RenderLayer<ArmorStandRenderState, ArmorStandArmorModel> {
    private final BackpackModel<ArmorStandRenderState> backpackModel;

    public ArmorStandBackpackLayer(RenderLayerParent<ArmorStandRenderState, ArmorStandArmorModel> renderer, EntityModelSet modelSet) {
        super(renderer);

        this.backpackModel = new BackpackModel<>(modelSet.bakeLayer(BackpackModel.BACKPACK_LAYER));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector node, int light, ArmorStandRenderState state, float v, float v1) {
        ItemStack backpack = ((BackpackRenderState) state).getBackpack();

        if (!backpack.isEmpty()) {
            poseStack.pushPose();

            poseStack.scale(0.75f, 0.75f, 0.75f);
            poseStack.mulPose(Axis.YP.rotationDegrees(180));
            poseStack.translate(0f, 1.225f, 0.14f);


            node.submitModel(this.backpackModel, state, poseStack,
                    RenderType.entityCutoutNoCull(BackpackModel.getTexture(backpack)),
                    light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);

            poseStack.popPose();
        }
    }
}
