package net.xstopho.resource_backpacks.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.ArmorStandArmorModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.client.model.BackpackModel;

public class ArmorStandBackpackLayer extends RenderLayer<ArmorStand, ArmorStandArmorModel> {
    private final BackpackModel<ArmorStand> backpackModel;

    public ArmorStandBackpackLayer(RenderLayerParent<ArmorStand, ArmorStandArmorModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.backpackModel = new BackpackModel<>(modelSet.bakeLayer(BackpackModel.BACKPACK_LAYER));
        this.getParentModel().copyPropertiesTo(this.backpackModel);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int light, ArmorStand entity, float limbSwing,
                       float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack backpack = ((BackpackHolder) entity).getBackpack();

        if (!backpack.isEmpty()) {
            poseStack.pushPose();

            poseStack.scale(0.75f, 0.75f, 0.75f);
            poseStack.mulPose(Axis.YP.rotationDegrees(180));
            poseStack.translate(0f, 1.225f, 0.14f);

            VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(BackpackModel.getTexture(backpack)));
            backpackModel.renderToBuffer(poseStack, consumer, light, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
    }
}
