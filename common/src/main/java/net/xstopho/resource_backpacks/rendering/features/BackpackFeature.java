package net.xstopho.resource_backpacks.rendering.features;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.rendering.BackpackRenderer;

public class BackpackFeature extends RenderLayer<PlayerRenderState, PlayerModel> {


    public BackpackFeature(RenderLayerParent<PlayerRenderState, PlayerModel> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, PlayerRenderState playerRenderState, float v, float v1) {
        ItemStack chestSlot = playerRenderState.chestItem;
        Item chestItem = chestSlot.getItem();

        if (chestItem instanceof BackpackItem) {
            BackpackRenderer.render(chestSlot, poseStack, multiBufferSource, light, playerRenderState);
        }
    }
}
