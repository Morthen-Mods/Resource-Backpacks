package net.morthen.resource_backpacks.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.morthen.resource_backpacks.client.model.BackpackModel;
import net.morthen.resource_backpacks.client.util.BackpackRenderState;

public class CreeperBackpackLayer extends RenderLayer<CreeperRenderState, CreeperModel> {
    private final BackpackModel<CreeperRenderState> backpackModel;

    public CreeperBackpackLayer(RenderLayerParent<CreeperRenderState, CreeperModel> renderer, EntityModelSet modelSet) {
        super(renderer);

        this.backpackModel = new BackpackModel<>(modelSet.bakeLayer(BackpackModel.BACKPACK_LAYER));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector node, int light, CreeperRenderState state, float v, float v1) {
        ItemStack backpack = ((BackpackRenderState) state).getBackpack();

        if (!backpack.isEmpty()) {
            poseStack.pushPose();

            poseStack.scale(0.75f, 0.75f, 0.75f);
            poseStack.translate(0f, 0.5f, 0f);

            node.submitModel(this.backpackModel, state, poseStack,
                    RenderTypes.entityCutout(BackpackModel.getTexture(backpack)),
                    light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);

            poseStack.popPose();
        }
    }
}
