package net.xstopho.resource_backpacks.features;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.items.BackpackItem;

public class BackpackFeature extends RenderLayer<PlayerRenderState, PlayerModel> {
    public BackpackFeature(RenderLayerParent<PlayerRenderState, PlayerModel> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, PlayerRenderState player, float v, float v1) {
        ItemStack chest = player.chestItem;

        if (chest.getItem() instanceof BackpackItem) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.XP.rotationDegrees(180));
            poseStack.scale(1.25f, 1.25f, 1.25f);

            poseStack.translate(0, -0.325, -0.19);

            if (player.isCrouching) {
                poseStack.mulPose(Axis.XP.rotationDegrees(29));
                poseStack.translate(0, -0.1, -0.095);
            }

            Minecraft.getInstance().getItemRenderer().renderStatic(chest, ItemDisplayContext.GROUND, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, Minecraft.getInstance().level, 0);
            poseStack.popPose();
        }
    }
}
