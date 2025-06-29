package net.xstopho.resource_backpacks.config.client;

import net.xstopho.resource_backpacks.backpack.util.BackpackPosition;
import net.xstopho.resource_backpacks.backpack.util.BackpackStyle;
import net.xstopho.resourceconfigapi.annotations.Config;
import net.xstopho.resourceconfigapi.annotations.ConfigEntry;
import net.xstopho.resourceconfigapi.api.ConfigType;

@Config(fileName = "appearance", type = ConfigType.CLIENT)
public class ClientConfig {

    @ConfigEntry(category = "Backpack Appearance")
    public static BackpackStyle style = BackpackStyle.VANILLA;

    @ConfigEntry(category = "Backpack Appearance")
    public static BackpackPosition position = BackpackPosition.BOTTOM_LEFT;
}
