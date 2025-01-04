package net.xstopho.resource_backpacks.config;

import net.xstopho.resource_backpacks.backpack.util.BackpackStyle;
import net.xstopho.resourceconfigapi.annotations.Config;
import net.xstopho.resourceconfigapi.annotations.ConfigEntry;
import net.xstopho.resourceconfigapi.api.ConfigType;

@Config(fileName = "appearance", type = ConfigType.CLIENT)
public class ClientConfig {

    @ConfigEntry(category = "Backpack Appearance")
    public static BackpackStyle style = BackpackStyle.VANILLA;
}
