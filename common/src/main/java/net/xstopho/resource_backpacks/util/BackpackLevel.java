package net.xstopho.resource_backpacks.util;

import net.xstopho.resource_backpacks.config.BackpackConfig;

public enum BackpackLevel {

    DEFAULT(5, 14),
    LEATHER(BackpackConfig.leatherRows, BackpackConfig.leatherColumns),
    COPPER(BackpackConfig.copperRows, BackpackConfig.copperColumns),
    GOLD(BackpackConfig.goldRows, BackpackConfig.goldColumns),
    IRON(BackpackConfig.ironRows, BackpackConfig.ironColumns),
    DIAMOND(BackpackConfig.diamondRows, BackpackConfig.diamondColumns),
    NETHERITE(BackpackConfig.netheriteRows, BackpackConfig.netheriteColumns),
    END(3, 9);

    final int rows, columns;

    BackpackLevel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {

        return rows;
    }

    public int getColumns() {

        return columns;
    }

    public int getSize() {

        return this.getRows() * this.getColumns();
    }

    @Override
    public String toString() {

        return this.name().toLowerCase();
    }
}
