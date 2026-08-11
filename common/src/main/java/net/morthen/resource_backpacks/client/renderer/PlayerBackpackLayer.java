package net.morthen.resource_backpacks.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.morthen.resource_backpacks.client.model.BackpackModel;
import net.morthen.resource_backpacks.client.util.BackpackRenderState;

public class PlayerBackpackLayer extends RenderLayer<AvatarRenderState, PlayerModel> {
    private final BackpackModel<AvatarRenderState> backpackModel;

    public PlayerBackpackLayer(RenderLayerParent<AvatarRenderState, PlayerModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.backpackModel = new BackpackModel<>(modelSet.bakeLayer(BackpackModel.BACKPACK_LAYER));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector node, int light, AvatarRenderState state, float v, float v1) {
        ItemStack backpack = ((BackpackRenderState) state).getBackpack();

        if (!backpack.isEmpty()) {

            poseStack.pushPose();
            this.getParentModel().body.translateAndRotate(poseStack);
            node.submitModel(this.backpackModel, state, poseStack,
                    RenderTypes.entityCutout(BackpackModel.getTexture(backpack)),
                    light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
            poseStack.popPose();
        }
    }
}
