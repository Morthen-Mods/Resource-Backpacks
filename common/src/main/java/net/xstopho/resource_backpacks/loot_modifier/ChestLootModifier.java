package net.xstopho.resource_backpacks.loot_modifier;

import net.xstopho.resource_backpacks.config.ChestLootConfig;
import net.xstopho.resource_backpacks.registries.BlockRegistry;
import net.xstopho.resourcelibrary.modifier.LootTableModifier;
import net.xstopho.resourcelibrary.modifier.loot_tables.ChestLootTables;
import net.xstopho.resourcelibrary.modifier.loot_tables.TrialChamberLootTables;

public class ChestLootModifier {

    public static void initLootModifier(LootTableModifier modifier) {

        ////////////////////////
        /// Leather Backpack ///
        ////////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackSpawnChest,
                ChestLootTables.SPAWN_BONUS_CHEST);

        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackVillage,
                ChestLootTables.VILLAGE_MASON,
                ChestLootTables.VILLAGE_TEMPLE,
                ChestLootTables.VILLAGE_FISHER,
                ChestLootTables.VILLAGE_SHEPARD,
                ChestLootTables.VILLAGE_TANNERY,
                ChestLootTables.VILLAGE_ARMORER,
                ChestLootTables.VILLAGE_BUTCHER,
                ChestLootTables.VILLAGE_FLETCHER,
                ChestLootTables.VILLAGE_TOOLSMITH,
                ChestLootTables.VILLAGE_WEAPONSMITH,
                ChestLootTables.VILLAGE_SNOWY_HOUSE,
                ChestLootTables.VILLAGE_TAIGA_HOUSE,
                ChestLootTables.VILLAGE_CARTOGRAPHER,
                ChestLootTables.VILLAGE_DESERT_HOUSE,
                ChestLootTables.VILLAGE_PLAINS_HOUSE,
                ChestLootTables.VILLAGE_SAVANNA_HOUSE);

        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackStronghold,
                ChestLootTables.STRONGHOLD_CORRIDOR,
                ChestLootTables.STRONGHOLD_CROSSING);

        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackTreasure,
                ChestLootTables.DESERT_PYRAMID,
                ChestLootTables.SIMPLE_DUNGEON,
                ChestLootTables.WOODLAND_MANSION);

        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackTrialChamber,
                TrialChamberLootTables.CHEST_REWARD_COMMON);

        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackShipwreck,
                ChestLootTables.SHIPWRECK_SUPPLY,
                ChestLootTables.SHIPWRECK_TREASURE);

        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackOther,
                ChestLootTables.IGLOO_CHEST,
                ChestLootTables.PILLAGER_OUTPOST,
                ChestLootTables.ABANDONED_MINESHAFT);


        /////////////////////////
        ///  Copper Backpack  ///
        /////////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_COPPER, 1f,
               () -> ChestLootConfig.copperBackpackVillage,
               ChestLootTables.VILLAGE_MASON,
               ChestLootTables.VILLAGE_TEMPLE,
               ChestLootTables.VILLAGE_ARMORER,
               ChestLootTables.VILLAGE_TOOLSMITH,
               ChestLootTables.VILLAGE_WEAPONSMITH);

        modifier.addBlocks(BlockRegistry.BACKPACK_COPPER, 1f,
                () -> ChestLootConfig.copperBackpackStronghold,
                ChestLootTables.STRONGHOLD_CORRIDOR,
                ChestLootTables.STRONGHOLD_CROSSING);

        modifier.addBlocks(BlockRegistry.BACKPACK_COPPER, 1f,
                () -> ChestLootConfig.copperBackpackTreasure,
                ChestLootTables.SIMPLE_DUNGEON,
                ChestLootTables.WOODLAND_MANSION);

        modifier.addBlocks(BlockRegistry.BACKPACK_COPPER, 1f,
                () -> ChestLootConfig.copperBackpackTrialChamber,
                TrialChamberLootTables.CHEST_REWARD_RARE);

        modifier.addBlocks(BlockRegistry.BACKPACK_COPPER, 1f,
                () -> ChestLootConfig.copperBackpackOther,
                ChestLootTables.PILLAGER_OUTPOST,
                ChestLootTables.ABANDONED_MINESHAFT);

        /////////////////////
        /// Iron Backpack ///
        /////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_IRON,1f,
                () -> ChestLootConfig.ironBackpackVillage,
                ChestLootTables.VILLAGE_TOOLSMITH);

        modifier.addBlocks(BlockRegistry.BACKPACK_IRON,1f,
                () -> ChestLootConfig.ironBackpackStronghold,
                ChestLootTables.STRONGHOLD_CORRIDOR,
                ChestLootTables.STRONGHOLD_CROSSING);

        modifier.addBlocks(BlockRegistry.BACKPACK_IRON,1f,
                () -> ChestLootConfig.ironBackpackTreasure,
                ChestLootTables.RUINED_PORTAL,
                ChestLootTables.SIMPLE_DUNGEON,
                ChestLootTables.WOODLAND_MANSION,
                ChestLootTables.SHIPWRECK_TREASURE);

        modifier.addBlocks(BlockRegistry.BACKPACK_IRON,1f,
                () -> ChestLootConfig.ironBackpackTrialChamber,
                TrialChamberLootTables.CHEST_REWARD_UNIQUE);

        modifier.addBlocks(BlockRegistry.BACKPACK_IRON,1f,
                () -> ChestLootConfig.ironBackpackOther,
                ChestLootTables.ABANDONED_MINESHAFT);

        /////////////////////
        /// Gold Backpack ///
        /////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_GOLD, 1f,
                () -> ChestLootConfig.goldBackpackStronghold,
                ChestLootTables.STRONGHOLD_CROSSING,
                ChestLootTables.STRONGHOLD_CORRIDOR);

        modifier.addBlocks(BlockRegistry.BACKPACK_GOLD, 1f,
                () -> ChestLootConfig.goldBackpackTreasure,
                ChestLootTables.RUINED_PORTAL,
                ChestLootTables.WOODLAND_MANSION,
                ChestLootTables.SHIPWRECK_TREASURE);

        modifier.addBlocks(BlockRegistry.BACKPACK_GOLD, 1f,
                () -> ChestLootConfig.goldBackpackTrialChamber,
                TrialChamberLootTables.CHEST_OMINOUS_RARE);

        modifier.addBlocks(BlockRegistry.BACKPACK_GOLD, 1f,
                () -> ChestLootConfig.goldBackpackOther,
                ChestLootTables.ABANDONED_MINESHAFT);

        ////////////////////////
        /// Diamond Backpack ///
        ////////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_DIAMOND, 1f,
                () -> ChestLootConfig.diamondBackpackAncientCity,
                ChestLootTables.ANCIENT_CITY,
                ChestLootTables.ANCIENT_CITY_ICE_BOX);

        modifier.addBlocks(BlockRegistry.BACKPACK_DIAMOND, 1f,
                () -> ChestLootConfig.diamondBackpackEndCity,
                ChestLootTables.END_CITY_TREASURE);

        modifier.addBlocks(BlockRegistry.BACKPACK_DIAMOND, 1f,
                () -> ChestLootConfig.diamondBackpackTreasure,
                ChestLootTables.BURIED_TREASURE,
                ChestLootTables.WOODLAND_MANSION);

        modifier.addBlocks(BlockRegistry.BACKPACK_DIAMOND, 1f,
                () -> ChestLootConfig.diamondBackpackTrialChambers,
                TrialChamberLootTables.CHEST_OMINOUS_UNIQUE);

        //////////////////////////
        /// Netherite Backpack ///
        //////////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_NETHERITE, 1f,
                () -> ChestLootConfig.netheriteBackpackAncientCity,
                ChestLootTables.ANCIENT_CITY,
                ChestLootTables.ANCIENT_CITY_ICE_BOX);

        modifier.addBlocks(BlockRegistry.BACKPACK_NETHERITE, 1f,
                () -> ChestLootConfig.netheriteBackpackBastion,
                ChestLootTables.BASTION_TREASURE);

        modifier.addBlocks(BlockRegistry.BACKPACK_NETHERITE, 1f,
                () -> ChestLootConfig.netheriteBackpackTreasure,
                ChestLootTables.WOODLAND_MANSION);


        modifier.addBlocks(BlockRegistry.BACKPACK_NETHERITE, 1f,
                () -> ChestLootConfig.netheriteBackpackTrialChambers,
                TrialChamberLootTables.CHEST_OMINOUS_UNIQUE);
    }
}
