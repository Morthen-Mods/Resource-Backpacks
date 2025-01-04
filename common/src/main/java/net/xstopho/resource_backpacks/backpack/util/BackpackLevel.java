package net.xstopho.resource_backpacks.backpack.util;

import net.xstopho.resource_backpacks.config.BackpackConfig;

import java.util.function.Supplier;

public enum BackpackLevel {

    DEFAULT(() -> 5, () -> 14, 256),
    LEATHER(() -> BackpackConfig.leatherRows, () -> BackpackConfig.leatherColumns, 22),
    COPPER(() -> BackpackConfig.copperRows, () -> BackpackConfig.copperColumns, 42),
    GOLD(() -> BackpackConfig.goldRows, () -> BackpackConfig.goldColumns, 64),
    IRON(() -> BackpackConfig.ironRows, () -> BackpackConfig.ironColumns, 108),
    DIAMOND(() -> BackpackConfig.diamondRows, () -> BackpackConfig.diamondColumns, 160),
    NETHERITE(() -> BackpackConfig.netheriteRows, () -> BackpackConfig.netheriteColumns, 250),
    END(() -> 3, () -> 9, 27);

    final Supplier<Integer> rows, columns;
    final int maxSize;

    BackpackLevel(Supplier<Integer> rows, Supplier<Integer> columns, int maxSize) {
        this.rows = rows;
        this.columns = columns;
        this.maxSize = maxSize;
    }

    public int getRows() {

        return rows.get();
    }

    public int getColumns() {

        return columns.get();
    }

    public int getSize() {

        return this.getRows() * this.getColumns();
    }

    public int getMaxSize() {

        return maxSize;
    }

    @Override
    public String toString() {

        return this.name().toLowerCase();
    }
}
