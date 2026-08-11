package net.morthen.resource_backpacks.config.common;

import net.morthen.resourceconfigapi.annotations.Config;
import net.morthen.resourceconfigapi.annotations.ConfigEntry;
import net.morthen.resourceconfigapi.annotations.RangedEntry;
import net.morthen.resourceconfigapi.api.ConfigType;

@Config(fileName = "chest_loot", type = ConfigType.COMMON)
public class ChestLootConfig {

    ////////////////////////
    /// Leather Backpack ///
    ////////////////////////
    @ConfigEntry(category = "Leather Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float leatherBackpackSpawnChest = 0.05f;

    @ConfigEntry(category = "Leather Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float leatherBackpackVillage = 0.05f;

    @ConfigEntry(category = "Leather Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float leatherBackpackStronghold = 0.05f;

    @ConfigEntry(category = "Leather Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float leatherBackpackTreasure = 0.05f;

    @ConfigEntry(category = "Leather Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float leatherBackpackTrialChamber = 0.05f;

    @ConfigEntry(category = "Leather Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float leatherBackpackShipwreck = 0.05f;

    @ConfigEntry(category = "Leather Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float leatherBackpackOther = 0.05f;


    /////////////////////////
    ///  Copper Backpack  ///
    /////////////////////////
    @ConfigEntry(category = "Copper Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float copperBackpackVillage = 0.05f;

    @ConfigEntry(category = "Copper Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float copperBackpackStronghold = 0.05f;

    @ConfigEntry(category = "Copper Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float copperBackpackTreasure = 0.05f;

    @ConfigEntry(category = "Copper Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float copperBackpackTrialChamber = 0.05f;

    @ConfigEntry(category = "Copper Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float copperBackpackOther = 0.05f;


    /////////////////////
    /// Iron Backpack ///
    /////////////////////
    @ConfigEntry(category = "Iron Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float ironBackpackVillage = 0.05f;

    @ConfigEntry(category = "Iron Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float ironBackpackStronghold = 0.05f;

    @ConfigEntry(category = "Iron Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float ironBackpackTreasure = 0.05f;

    @ConfigEntry(category = "Iron Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float ironBackpackTrialChamber = 0.05f;

    @ConfigEntry(category = "Iron Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float ironBackpackOther = 0.05f;


    /////////////////////
    /// Gold Backpack ///
    /////////////////////
    @ConfigEntry(category = "Gold Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float goldBackpackStronghold = 0.02f;

    @ConfigEntry(category = "Gold Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float goldBackpackTreasure = 0.02f;

    @ConfigEntry(category = "Gold Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float goldBackpackTrialChamber = 0.02f;

    @ConfigEntry(category = "Gold Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float goldBackpackOther = 0.02f;

    ////////////////////////
    /// Diamond Backpack ///
    ////////////////////////
    @ConfigEntry(category = "Diamond Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float diamondBackpackAncientCity = 0.01f;

    @ConfigEntry(category = "Diamond Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float diamondBackpackEndCity = 0.01f;

    @ConfigEntry(category = "Diamond Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float diamondBackpackTreasure = 0.01f;

    @ConfigEntry(category = "Diamond Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float diamondBackpackTrialChambers = 0.01f;

    //////////////////////////
    /// Netherite Backpack ///
    //////////////////////////
    @ConfigEntry(category = "Netherite Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float netheriteBackpackAncientCity = 0.01f;

    @ConfigEntry(category = "Netherite Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float netheriteBackpackBastion = 0.01f;

    @ConfigEntry(category = "Netherite Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float netheriteBackpackTreasure = 0.01f;

    @ConfigEntry(category = "Netherite Backpack", needsWorldRestart = true)
    @RangedEntry(minValue = 0, maxValue = 1)
    public static float netheriteBackpackTrialChambers = 0.01f;

}
