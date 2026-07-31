package net.xstopho.resource_backpacks.modifier;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.xstopho.resource_backpacks.backpack.api.BackpackHolder;
import net.xstopho.resource_backpacks.config.common.EntityConfig;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.network.payloads.SyncEntityBackpackPayload;
import net.xstopho.resource_backpacks.registries.BlockRegistry;
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
        ItemStack backpack = registryObject.get().asItem().getDefaultInstance();
        Random random = new Random();
        float rndFloat = random.nextFloat();
        if (chance.get() == 0f || hasBackpack(livingEntity)) return;

        if (rndFloat <= chance.get()) {
            ((BackpackHolder) livingEntity).setBackpack(backpack);

            if (!livingEntity.level().isClientSide()) {
                BackpackNetwork.INSTANCE.sendToClientsTrackingEntity(livingEntity, new SyncEntityBackpackPayload(livingEntity.getId(), backpack));
            }
        }
    }

    private static boolean hasBackpack(LivingEntity livingEntity) {
        return !((BackpackHolder) livingEntity).getBackpack().isEmpty();
    }
}
