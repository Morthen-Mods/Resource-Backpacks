package net.xstopho.resource_backpacks.client.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface BackpackRenderer<T extends LivingEntity> {

    void copyProperties();
    void renderBackpackModel(PoseStack poseStack, MultiBufferSource buffer, int light, ItemStack backpack);

    default void renderOnHumanoid(PoseStack poseStack, MultiBufferSource buffer, T entity, int light, ItemStack backpack) {

        if (backpack.isEmpty()) return;

        poseStack.pushPose();
        copyProperties();

        if (entity.isCrouching()) {
            poseStack.mulPose(Axis.XP.rotationDegrees(29));
            poseStack.translate(0, 0.17, -0.095);
        }

        renderBackpackModel(poseStack, buffer, light, backpack);

        poseStack.popPose();

    }

    default void renderOnCreeper(PoseStack poseStack, MultiBufferSource buffer, int light, ItemStack backpack) {
        if (backpack.isEmpty()) return;

        poseStack.pushPose();
        copyProperties();

        poseStack.scale(0.75f, 0.75f, 0.75f);
        poseStack.translate(0f, 0.5f, 0f);

        renderBackpackModel(poseStack, buffer, light, backpack);

        poseStack.popPose();
    }

    default void renderOnArmorStand(PoseStack poseStack, MultiBufferSource buffer, int light, ItemStack backpack) {
        if (backpack.isEmpty()) return;

        poseStack.pushPose();
        copyProperties();
        poseStack.scale(0.75f, 0.75f, 0.75f);
        poseStack.mulPose(Axis.YP.rotationDegrees(180));
        poseStack.translate(0f, 1.225f, 0.14f);

        renderBackpackModel(poseStack, buffer, light, backpack);

        poseStack.popPose();
    }
}
