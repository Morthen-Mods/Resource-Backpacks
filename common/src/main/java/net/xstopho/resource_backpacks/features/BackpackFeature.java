package net.xstopho.resource_backpacks.features;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.items.BackpackItem;

public class BackpackFeature extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public BackpackFeature(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, AbstractClientPlayer player, float v, float v1, float v2, float v3, float v4, float v5) {
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (chest.getItem() instanceof BackpackItem) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.XP.rotationDegrees(180));
            poseStack.scale(1.25f, 1.25f, 1.25f);

            poseStack.translate(0, -0.325, -0.19);

            if (player.isCrouching()) {
                poseStack.mulPose(Axis.XP.rotationDegrees(29));
                poseStack.translate(0, -0.1, -0.095);
            }

            Minecraft.getInstance().getItemRenderer().renderStatic(chest, ItemDisplayContext.GROUND, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, Minecraft.getInstance().level, 0);
            poseStack.popPose();
        }
    }
}
