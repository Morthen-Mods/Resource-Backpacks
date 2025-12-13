package net.xstopho.resource_backpacks.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.client.model.BackpackModel;
import net.xstopho.resource_backpacks.client.util.BackpackRenderState;

public class ZombieBackpackLayer extends RenderLayer<ZombieRenderState, ZombieModel<ZombieRenderState>> {
    private final BackpackModel<ZombieRenderState> backpackModel;

    public ZombieBackpackLayer(RenderLayerParent<ZombieRenderState, ZombieModel<ZombieRenderState>> renderer, EntityModelSet modelSet) {
        super(renderer);

        this.backpackModel = new BackpackModel<>(modelSet.bakeLayer(BackpackModel.BACKPACK_LAYER));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector node, int light, ZombieRenderState state, float v, float v1) {
        ItemStack backpack = ((BackpackRenderState) state).getBackpack();

        if (!backpack.isEmpty()) {
            node.submitModel(this.backpackModel, state, poseStack,
                    RenderTypes.entityCutoutNoCull(BackpackModel.getTexture(backpack)),
                    light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        }
    }
}
