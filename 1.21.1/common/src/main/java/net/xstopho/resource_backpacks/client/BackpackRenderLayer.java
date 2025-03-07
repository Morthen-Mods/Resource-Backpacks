package net.xstopho.resource_backpacks.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmorStandModel;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.client.util.BackpackRenderer;

public class BackpackRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> implements BackpackRenderer<T> {

    private final BackpackModel<T> backpackModel;

    public BackpackRenderLayer(RenderLayerParent<T, M> renderer, EntityModelSet modelSet) {
        super(renderer);

        this.backpackModel = new BackpackModel<>(modelSet.bakeLayer(BackpackModel.BACKPACK_LAYER));
        this.getParentModel().copyPropertiesTo(this.backpackModel);
    }


    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, T entity, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        ((BackpackHolder) entity).getBackpack().ifPresent(itemStack -> {
            if (getParentModel() instanceof ArmorStandModel) {
                renderOnArmorStand(poseStack,  bufferSource, light, itemStack);

            } else if (getParentModel() instanceof CreeperModel<?>){
                renderOnCreeper(poseStack, bufferSource, light, itemStack);

            } else {
                renderOnHumanoid(poseStack, bufferSource, entity, light, itemStack);
            }
        });
    }

    @Override
    protected ResourceLocation getTextureLocation(LivingEntity entity) {
        ItemStack backpack = ((BackpackHolder) entity).getBackpack().orElse(ItemStack.EMPTY);
        return BackpackModel.getTexture(backpack);
    }

    @Override
    public void renderBackpackModel(PoseStack poseStack, MultiBufferSource buffer, int light, ItemStack backpack) {
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(BackpackModel.getTexture(backpack)));
        backpackModel.renderToBuffer(poseStack, consumer, light, OverlayTexture.NO_OVERLAY);
    }
}
