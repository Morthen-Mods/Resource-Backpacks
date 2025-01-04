package net.xstopho.resource_backpacks.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.BackpackItem;

public class BackpackModel<T extends LivingEntityRenderState> extends EntityModel<T> {

    public static final ModelLayerLocation BACKPACK_LAYER = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("player"), "backpack");

    public BackpackModel(ModelPart root) {
        super(root);

        root.getChild("backpack");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("backpack",
                CubeListBuilder.create()
                        .texOffs(0, 6).addBox("base", 0, 1, 0, 8, 9, 4)
                        .texOffs(25, 11).addBox("middle_pouch", 1, 3, 4, 6, 7, 1)
                        .texOffs(0, 0).addBox("bottom", 1, 10, 0, 6, 1, 4)
                        .texOffs(30, 3).addBox("decorator", 2.5f, 6.5f, 4.5f, 3, 3, 1)
                        .texOffs(21, 0).addBox("left_pouch", 8, 6, 0.5f, 1, 4, 3)
                        .texOffs(21, 0).addBox("right_pouch", -1, 6, 0.5f, 1, 4, 3),
                PartPose.offset(-4.0F, 0.0F, 2.0F));

        return LayerDefinition.create(meshdefinition, 39, 19);
    }

    public static ResourceLocation getTexture(ItemStack stack) {
        String type = "default";
        if (stack.getItem() instanceof BackpackItem backpackItem) {
            type = backpackItem.getBackpackLevel().name().toLowerCase();
        }

        return BackpackConstants.of("textures/entity/backpack_" + type + ".png");
    }
}