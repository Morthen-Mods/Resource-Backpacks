package net.xstopho.resource_backpacks;

import net.xstopho.resourceconfigapi.builder.ResourceConfigBuilder;

import java.util.function.Supplier;

public class BackpackConfig {

    public static final ResourceConfigBuilder BUILDER = new ResourceConfigBuilder();

    public static final Supplier<Boolean> OPEN_FROM_INVENTORY, TAKE_CONTAINER_ITEMS_FROM_END_BACKPACK;

    public static final Supplier<Integer> LEATHER_ROWS, LEATHER_COLUMNS,
                                        COPPER_ROWS, COPPER_COLUMNS,
                                        GOLD_ROWS, GOLD_COLUMNS,
                                        IRON_ROWS, IRON_COLUMNS,
                                        DIAMOND_ROWS, DIAMOND_COLUMNS,
                                        NETHERITE_ROWS, NETHERITE_COLUMNS;

    static {
        BUILDER.push("General");
        OPEN_FROM_INVENTORY = BUILDER.sync().comment("Enable the ability to open Backpacks from inventory.")
                .comment("When disabled, backpacks has to be placed like Shulker Boxes.")
                .comment("Only allows opening backpacks via right-click from inventory,")
                .comment("the KeyBind still only works when the Backpack is equipped in the Chest slot")
                .define("open_from_inventory", false);
        TAKE_CONTAINER_ITEMS_FROM_END_BACKPACK = BUILDER.sync()
                .comment("Enable the ability to take Backpacks and Shulker Boxes from End Backpack")
                .define("unlock_end_backpack", false);

        BUILDER.pop().push("Leather Backpack");
        LEATHER_ROWS = BUILDER.sync().defineInRange("rows", 1, 1, 2);
        LEATHER_COLUMNS = BUILDER.sync().defineInRange("columns", 9, 9, 11);

        BUILDER.pop().push("Copper Backpack");
        COPPER_ROWS = BUILDER.sync().defineInRange("rows", 2, 1, 3);
        COPPER_COLUMNS = BUILDER.sync().defineInRange("columns", 9, 9, 14);

        BUILDER.pop().push("Gold Backpack");
        GOLD_ROWS = BUILDER.sync().defineInRange("rows", 3, 1, 4);
        GOLD_COLUMNS = BUILDER.sync().defineInRange("columns", 11, 9, 16);

        BUILDER.pop().push("Iron Backpack");
        IRON_ROWS = BUILDER.sync().defineInRange("rows", 4, 1, 6);
        IRON_COLUMNS = BUILDER.sync().defineInRange("columns", 12, 9, 18);

        BUILDER.pop().push("Diamond Backpack");
        DIAMOND_ROWS = BUILDER.sync().defineInRange("rows", 6, 1, 8);
        DIAMOND_COLUMNS = BUILDER.sync().defineInRange("columns", 14, 9, 20);

        BUILDER.pop().push("Netherite Backpack");
        NETHERITE_ROWS = BUILDER.sync().defineInRange("rows", 8, 1, 10);
        NETHERITE_COLUMNS = BUILDER.sync().defineInRange("columns", 16, 9, 25);
        BUILDER.pop();
    }
}
