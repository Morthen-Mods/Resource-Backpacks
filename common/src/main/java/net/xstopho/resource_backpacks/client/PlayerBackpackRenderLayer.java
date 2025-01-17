package net.xstopho.resource_backpacks.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.client.slot.BackpackHolder;

public class PlayerBackpackRenderLayer extends RenderLayer<PlayerRenderState, PlayerModel> {

    private final BackpackModel<PlayerRenderState> backpackModel;

    public PlayerBackpackRenderLayer(RenderLayerParent<PlayerRenderState, PlayerModel> renderer, EntityModelSet modelSet) {
        super(renderer);

        this.backpackModel = new BackpackModel<>(modelSet.bakeLayer(BackpackModel.BACKPACK_LAYER));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int light, PlayerRenderState renderState, float v, float v1) {
        Player player = Minecraft.getInstance().player;
        ItemStack backpack = ((BackpackHolder) player.getInventory()).resource_backpack$getBackpack();

        if (!backpack.isEmpty()) {
            poseStack.pushPose();

            if (renderState.isCrouching) {
                poseStack.mulPose(Axis.XP.rotationDegrees(29));
                poseStack.translate(0, 0.17, -0.095);
            }

            VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(BackpackModel.getTexture(backpack)));
            this.backpackModel.renderToBuffer(poseStack, consumer, light, OverlayTexture.NO_OVERLAY);

            poseStack.popPose();
        }
    }
}
