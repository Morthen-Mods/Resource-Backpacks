package net.morthen.resource_backpacks.config.common;

import net.morthen.resourceconfigapi.annotations.Config;
import net.morthen.resourceconfigapi.annotations.ConfigEntry;
import net.morthen.resourceconfigapi.annotations.RangedEntry;
import net.morthen.resourceconfigapi.api.ConfigType;

@Config(fileName = "entity_config", type = ConfigType.COMMON)
public class EntityConfig {

    @ConfigEntry(category = "Spawn Chance")
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float zombieSpawnWithLeatherBackpack = 0.05f;

    @ConfigEntry(category = "Spawn Chance")
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float zombieSpawnWithCopperBackpack = 0.02f;

    @ConfigEntry(category = "Spawn Chance")
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float creeperSpawnWithLeatherBackpack = 0.05f;

    @ConfigEntry(category = "Spawn Chance")
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float creeperSpawnWithCopperBackpack = 0.02f;
}
