package net.xstopho.resource_backpacks;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.xstopho.resource_backpacks.registries.BlockEntityRegistry;
import net.xstopho.resource_backpacks.registries.BlockRegistry;
import net.xstopho.resource_backpacks.registries.CreativeTabRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_backpacks.screen.BackpackMenuScreen;
import net.xstopho.resource_backpacks.util.BackpackUtils;
import net.xstopho.resourceconfigapi.api.ConfigRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackpackConstants {
    public static final String MOD_ID = "resource_backpacks";
    public static final String MOD_NAME = "Resource Backpacks";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID =
            ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace("equipment_asset"));
    public static final ResourceKey<EquipmentAsset> BACKPACK_EQUIPMENT_ASSET = createId("backpack");


    public static void commonInit() {
        ConfigRegistry.register(MOD_ID, BackpackConfig.BUILDER, false);

        BlockRegistry.init();
        BlockEntityRegistry.init();

        MenuTypeRegistry.init();

        CreativeTabRegistry.init();
    }

    public static void clientInit() {
        MenuScreens.register(MenuTypeRegistry.DEFAULT_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.LEATHER_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.COPPER_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.GOLD_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.IRON_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.DIAMOND_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.NETHERITE_MENU.get(), BackpackMenuScreen::new);
        MenuScreens.register(MenuTypeRegistry.END_MENU.get(), BackpackMenuScreen::new);
    }

    public static ResourceLocation of(String id) {

        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public static boolean hasKeyDown(KeyMapping keyMapping) {
        int keyCode = ((BackpackUtils.KeyMappingAccess) keyMapping).getKey().getValue();
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), keyCode);
    }

    static ResourceKey<EquipmentAsset> createId(String name) {
        return ResourceKey.create(ROOT_ID, ResourceLocation.withDefaultNamespace(name));
    }
}
