package net.xstopho.resource_backpacks.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class BackpackRenderer {

    public static void render(ItemStack stack, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, PlayerRenderState player) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        poseStack.translate(0, -0.345, -0.16);

        if (player.isCrouching) {
            poseStack.mulPose(Axis.XP.rotationDegrees(29));
            poseStack.translate(0, -0.15, -0.09);
        }

        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, Minecraft.getInstance().level, 0);
        poseStack.popPose();
    }
}
