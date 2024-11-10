package net.xstopho.resource_backpacks;

import net.xstopho.resource_backpacks.registries.BlockEntityRegistry;
import net.xstopho.resource_backpacks.registries.BlockRegistry;
import net.xstopho.resource_backpacks.registries.CreativeTabRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackpackConstants {
    public static final String MOD_ID = "resource_backpacks";
    public static final String MOD_NAME = "Resource Backpacks";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void commonInit() {
        BlockRegistry.init();
        BlockEntityRegistry.init();

        CreativeTabRegistry.init();
    }

    public static void clientInit() {

    }

    public static void serverInit() {

    }
}
