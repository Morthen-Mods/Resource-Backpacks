package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

import java.util.function.Function;

public class ItemRegistry {

    public static final RegistryProvider<Item> ITEMS = RegistryProvider.get(BackpackConstants.MOD_ID, BuiltInRegistries.ITEM);

    public static final RegistryObject<Item> BACKPACK_LEATHER = register(BackpackLevel.LEATHER);
    public static final RegistryObject<Item> BACKPACK_COPPER = register(BackpackLevel.COPPER);
    public static final RegistryObject<Item> BACKPACK_GOLD = register(BackpackLevel.GOLD);
    public static final RegistryObject<Item> BACKPACK_IRON = register(BackpackLevel.IRON);
    public static final RegistryObject<Item> BACKPACK_DIAMOND = register(BackpackLevel.DIAMOND);
    public static final RegistryObject<Item> BACKPACK_NETHERITE = register(BackpackLevel.NETHERITE);
    public static final RegistryObject<Item> BACKPACK_ENDER = register(BackpackLevel.ENDER);

    private static RegistryObject<Item> register(String id, Function<Item.Properties, Item> function, Item.Properties properties) {
        ResourceKey<Item> key = createKey(id);
        return ITEMS.register(id, () -> function.apply(properties.setId(key)));
    }

    private static ResourceKey<Item> createKey(String id) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(ITEMS.getModId(), id));
    }

    private static RegistryObject<Item> register(BackpackLevel level) {
        Item.Properties fireproof = level.equals(BackpackLevel.NETHERITE) ? new Item.Properties().fireResistant() : new Item.Properties();
        return register("backpack_" + level.getName(), properties -> new BackpackItem(properties, level), fireproof);
    }

    public static void init() {};
}
