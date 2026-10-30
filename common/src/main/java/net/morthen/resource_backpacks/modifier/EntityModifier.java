package net.morthen.resource_backpacks.modifier;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.morthen.resource_backpacks.backpack.api.BackpackHolder;
import net.morthen.resource_backpacks.config.common.EntityConfig;
import net.morthen.resource_backpacks.network.BackpackNetwork;
import net.morthen.resource_backpacks.network.payloads.SyncEntityBackpackPayload;
import net.morthen.resource_backpacks.registries.BlockRegistry;
import net.morthen.resourcelibrary.registration.RegistryObject;

import java.util.Random;
import java.util.function.Supplier;

public class EntityModifier {

    public static void modifyEntities(Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.getType() == EntityTypes.ZOMBIE) {
                modify(livingEntity, () -> EntityConfig.zombieSpawnWithLeatherBackpack, BlockRegistry.BACKPACK_LEATHER);
                modify(livingEntity, () -> EntityConfig.zombieSpawnWithCopperBackpack, BlockRegistry.BACKPACK_COPPER);
            }

            if (livingEntity.getType() == EntityTypes.CREEPER) {
                modify(livingEntity, () -> EntityConfig.creeperSpawnWithLeatherBackpack, BlockRegistry.BACKPACK_LEATHER);
                modify(livingEntity, () -> EntityConfig.creeperSpawnWithCopperBackpack, BlockRegistry.BACKPACK_COPPER);
            }

        }
    }

    private static void modify(LivingEntity livingEntity, Supplier<Float> chance, RegistryObject<Block> registryObject) {
        if (livingEntity instanceof BackpackHolder holder) {
            if (chance.get() == 0f || !holder.getBackpack().isEmpty()) return;

            ItemStack backpack = registryObject.get().asItem().getDefaultInstance();
            float rndFloat = livingEntity.getRandom().nextFloat();

            if (rndFloat <= chance.get()) {
                holder.setBackpack(backpack);

                if (!livingEntity.level().isClientSide()) {
                    BackpackNetwork.INSTANCE.sendToClientsTrackingEntity(livingEntity, new SyncEntityBackpackPayload(livingEntity.getId(), backpack));
                }
            }
        }
    }
}
