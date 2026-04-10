package net.xstopho.resource_backpacks.modifier;

import net.xstopho.resource_backpacks.config.common.ChestLootConfig;
import net.xstopho.resource_backpacks.registries.BlockRegistry;
import net.xstopho.resourcelibrary.modifier.LootTableModifier;
import net.xstopho.resourcelibrary.modifier.loot_tables.ChestLootTables;
import net.xstopho.resourcelibrary.modifier.loot_tables.TrialChamberLootTables;

import java.util.List;

public class ChestLootModifier {

    public static void initLootModifier(LootTableModifier modifier) {

        ////////////////////////
        /// Leather Backpack ///
        ////////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackSpawnChest,
                List.of(ChestLootTables.SPAWN_BONUS_CHEST));

        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackVillage,
                List.of(ChestLootTables.VILLAGE_MASON,
                        ChestLootTables.VILLAGE_TEMPLE,
                        ChestLootTables.VILLAGE_FISHER,
                        ChestLootTables.VILLAGE_SHEPHERD,
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
                        ChestLootTables.VILLAGE_SAVANNA_HOUSE));

        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackStronghold,
                List.of(ChestLootTables.STRONGHOLD_CORRIDOR,
                        ChestLootTables.STRONGHOLD_CROSSING));

        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackTreasure,
                List.of(ChestLootTables.DESERT_PYRAMID,
                        ChestLootTables.SIMPLE_DUNGEON,
                        ChestLootTables.WOODLAND_MANSION));

        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackShipwreck,
                List.of(ChestLootTables.SHIPWRECK_SUPPLY,
                        ChestLootTables.SHIPWRECK_TREASURE));

        modifier.addBlocks(BlockRegistry.BACKPACK_LEATHER, 1f,
                () -> ChestLootConfig.leatherBackpackOther,
                List.of(ChestLootTables.IGLOO_CHEST,
                        ChestLootTables.PILLAGER_OUTPOST,
                        ChestLootTables.ABANDONED_MINESHAFT));


        /////////////////////////
        ///  Copper Backpack  ///
        /////////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_COPPER, 1f,
               () -> ChestLootConfig.copperBackpackVillage,
               List.of(ChestLootTables.VILLAGE_MASON,
                       ChestLootTables.VILLAGE_TEMPLE,
                       ChestLootTables.VILLAGE_ARMORER,
                       ChestLootTables.VILLAGE_TOOLSMITH,
                       ChestLootTables.VILLAGE_WEAPONSMITH));

        modifier.addBlocks(BlockRegistry.BACKPACK_COPPER, 1f,
                () -> ChestLootConfig.copperBackpackStronghold,
                List.of(ChestLootTables.STRONGHOLD_CORRIDOR,
                        ChestLootTables.STRONGHOLD_CROSSING));

        modifier.addBlocks(BlockRegistry.BACKPACK_COPPER, 1f,
                () -> ChestLootConfig.copperBackpackTreasure,
                List.of(ChestLootTables.SIMPLE_DUNGEON,
                        ChestLootTables.WOODLAND_MANSION));

        modifier.addBlocks(BlockRegistry.BACKPACK_COPPER, 1f,
                () -> ChestLootConfig.copperBackpackOther,
                List.of(ChestLootTables.PILLAGER_OUTPOST,
                        ChestLootTables.ABANDONED_MINESHAFT));

        /////////////////////
        /// Iron Backpack ///
        /////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_IRON,1f,
                () -> ChestLootConfig.ironBackpackVillage,
                List.of(ChestLootTables.VILLAGE_TOOLSMITH));

        modifier.addBlocks(BlockRegistry.BACKPACK_IRON,1f,
                () -> ChestLootConfig.ironBackpackStronghold,
                List.of(ChestLootTables.STRONGHOLD_CORRIDOR,
                        ChestLootTables.STRONGHOLD_CROSSING));

        modifier.addBlocks(BlockRegistry.BACKPACK_IRON,1f,
                () -> ChestLootConfig.ironBackpackTreasure,
                List.of(ChestLootTables.RUINED_PORTAL,
                        ChestLootTables.SIMPLE_DUNGEON,
                        ChestLootTables.WOODLAND_MANSION,
                        ChestLootTables.SHIPWRECK_TREASURE));

        modifier.addBlocks(BlockRegistry.BACKPACK_IRON,1f,
                () -> ChestLootConfig.ironBackpackOther,
                List.of(ChestLootTables.ABANDONED_MINESHAFT));

        /////////////////////
        /// Gold Backpack ///
        /////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_GOLD, 1f,
                () -> ChestLootConfig.goldBackpackStronghold,
                List.of(ChestLootTables.STRONGHOLD_CROSSING,
                        ChestLootTables.STRONGHOLD_CORRIDOR));

        modifier.addBlocks(BlockRegistry.BACKPACK_GOLD, 1f,
                () -> ChestLootConfig.goldBackpackTreasure,
                List.of(ChestLootTables.RUINED_PORTAL,
                        ChestLootTables.WOODLAND_MANSION,
                        ChestLootTables.SHIPWRECK_TREASURE));

        modifier.addBlocks(BlockRegistry.BACKPACK_GOLD, 1f,
                () -> ChestLootConfig.goldBackpackOther,
                List.of(ChestLootTables.ABANDONED_MINESHAFT));

        ////////////////////////
        /// Diamond Backpack ///
        ////////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_DIAMOND, 1f,
                () -> ChestLootConfig.diamondBackpackAncientCity,
                List.of(ChestLootTables.ANCIENT_CITY,
                        ChestLootTables.ANCIENT_CITY_ICE_BOX));

        modifier.addBlocks(BlockRegistry.BACKPACK_DIAMOND, 1f,
                () -> ChestLootConfig.diamondBackpackEndCity,
                List.of(ChestLootTables.END_CITY_TREASURE));

        modifier.addBlocks(BlockRegistry.BACKPACK_DIAMOND, 1f,
                () -> ChestLootConfig.diamondBackpackTreasure,
                List.of(ChestLootTables.BURIED_TREASURE,
                        ChestLootTables.WOODLAND_MANSION));

        modifier.addBlocks(BlockRegistry.BACKPACK_DIAMOND, 1f,
                () -> ChestLootConfig.diamondBackpackTrialChambers,
                List.of(TrialChamberLootTables.OMINOUS_REWARD_OMINOUS_UNIQUE));

        //////////////////////////
        /// Netherite Backpack ///
        //////////////////////////
        modifier.addBlocks(BlockRegistry.BACKPACK_NETHERITE, 1f,
                () -> ChestLootConfig.netheriteBackpackAncientCity,
                List.of(ChestLootTables.ANCIENT_CITY,
                        ChestLootTables.ANCIENT_CITY_ICE_BOX));

        modifier.addBlocks(BlockRegistry.BACKPACK_NETHERITE, 1f,
                () -> ChestLootConfig.netheriteBackpackBastion,
                List.of(ChestLootTables.BASTION_TREASURE));

        modifier.addBlocks(BlockRegistry.BACKPACK_NETHERITE, 1f,
                () -> ChestLootConfig.netheriteBackpackTreasure,
                List.of(ChestLootTables.WOODLAND_MANSION));


        modifier.addBlocks(BlockRegistry.BACKPACK_NETHERITE, 1f,
                () -> ChestLootConfig.netheriteBackpackTrialChambers,
                List.of(TrialChamberLootTables.REWARD_UNIQUE));
    }
}
