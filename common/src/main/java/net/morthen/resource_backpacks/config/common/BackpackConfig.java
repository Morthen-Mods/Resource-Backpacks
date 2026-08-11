package net.morthen.resource_backpacks.config.common;

import net.morthen.resourceconfigapi.annotations.Config;
import net.morthen.resourceconfigapi.annotations.ConfigEntry;
import net.morthen.resourceconfigapi.annotations.RangedEntry;
import net.morthen.resourceconfigapi.api.ConfigType;

@Config(fileName = "backpacks", type = ConfigType.COMMON)
public class BackpackConfig {

    @ConfigEntry(category = "General")
    public static boolean openFromInventory = false;

    @ConfigEntry(category = "General")
    public static boolean allowBackpacksInsideBackpacks = false;

    @ConfigEntry(category = "Leather Backpack", translation = "row")
    @RangedEntry(minValue = 1, maxValue = 2)
    public static int leatherRows = 1;

    @ConfigEntry(category = "Leather Backpack", translation = "column")
    @RangedEntry(minValue = 9, maxValue = 11)
    public static int leatherColumns = 9;

    @ConfigEntry(category = "Copper Backpack", translation = "row")
    @RangedEntry(minValue = 1, maxValue = 3)
    public static int copperRows = 2;

    @ConfigEntry(category = "Copper Backpack", translation = "column")
    @RangedEntry(minValue = 9, maxValue = 14)
    public static int copperColumns = 9;

    @ConfigEntry(category = "Gold Backpack", translation = "row")
    @RangedEntry(minValue = 1, maxValue = 4)
    public static int goldRows = 3;

    @ConfigEntry(category = "Gold Backpack", translation = "column")
    @RangedEntry(minValue = 9, maxValue = 16)
    public static int goldColumns = 11;

    @ConfigEntry(category = "Iron Backpack", translation = "row")
    @RangedEntry(minValue = 1, maxValue = 6)
    public static int ironRows = 4;

    @ConfigEntry(category = "Iron Backpack", translation = "column")
    @RangedEntry(minValue = 9, maxValue = 18)
    public static int ironColumns = 12;

    @ConfigEntry(category = "Diamond Backpack", translation = "row")
    @RangedEntry(minValue = 1, maxValue = 8)
    public static int diamondRows = 6;

    @ConfigEntry(category = "Diamond Backpack", translation = "column")
    @RangedEntry(minValue = 9, maxValue = 20)
    public static int diamondColumns = 14;

    @ConfigEntry(category = "Netherite Backpack", translation = "row")
    @RangedEntry(minValue = 1, maxValue = 10)
    public static int netheriteRows = 8;

    @ConfigEntry(category = "Netherite Backpack", translation = "column")
    @RangedEntry(minValue = 9, maxValue = 25)
    public static int netheriteColumns = 16;
}
