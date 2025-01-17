package net.xstopho.resource_backpacks.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.decoration.ArmorStand;

public class ArmorStandBackpackRenderLayer extends RenderLayer<ArmorStand, HumanoidModel<ArmorStand>> {

    public ArmorStandBackpackRenderLayer(RenderLayerParent<ArmorStand, HumanoidModel<ArmorStand>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int lightness, ArmorStand player, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}
