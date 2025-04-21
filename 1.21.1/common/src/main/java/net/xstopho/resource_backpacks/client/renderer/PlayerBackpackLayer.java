package net.xstopho.resource_backpacks.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.client.model.BackpackModel;

public class PlayerBackpackLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private final BackpackModel<AbstractClientPlayer> backpackModel;

    public PlayerBackpackLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.backpackModel = new BackpackModel<>(modelSet.bakeLayer(BackpackModel.BACKPACK_LAYER));
        this.getParentModel().copyPropertiesTo(this.backpackModel);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int light, AbstractClientPlayer entity,
                       float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack backpack = ((BackpackHolder) entity).getBackpack();

        if (!backpack.isEmpty()) {
            this.backpackModel.setupAngles(this.getParentModel());

            poseStack.pushPose();
            poseStack.translate(-0.25,0,0.125);

            if (entity.isCrouching()) {
                poseStack.translate(0, -0.0625, -0.015);
            }

            VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(BackpackModel.getTexture(backpack)));
            backpackModel.renderToBuffer(poseStack, consumer, light, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
    }
}
