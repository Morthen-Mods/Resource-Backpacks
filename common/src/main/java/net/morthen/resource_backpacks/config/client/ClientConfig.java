package net.morthen.resource_backpacks.config.client;

import net.morthen.resource_backpacks.backpack.util.BackpackPosition;
import net.morthen.resourceconfigapi.annotations.Config;
import net.morthen.resourceconfigapi.annotations.ConfigEntry;
import net.morthen.resourceconfigapi.api.ConfigType;

@Config(fileName = "appearance", type = ConfigType.CLIENT)
public class ClientConfig {

    @ConfigEntry(category = "Backpack Appearance")
    public static BackpackPosition position = BackpackPosition.BOTTOM_LEFT;
}
