package net.xstopho.resource_backpacks.util;

import java.util.function.Supplier;

import static net.xstopho.resource_backpacks.BackpackConfig.*;

public enum BackpackLevel {

    DEFAULT(() -> 5, () -> 14),
    LEATHER(LEATHER_ROWS, LEATHER_COLUMNS),
    COPPER(COPPER_ROWS, COPPER_COLUMNS),
    GOLD(GOLD_ROWS, GOLD_COLUMNS),
    IRON(IRON_ROWS, IRON_COLUMNS),
    DIAMOND(DIAMOND_ROWS, DIAMOND_COLUMNS),
    NETHERITE(NETHERITE_ROWS, NETHERITE_COLUMNS),
    END(() -> 3, () -> 9);

    final Supplier<Integer> rows, columns;

    BackpackLevel(Supplier<Integer> rows, Supplier<Integer> columns) {
        this.rows = rows;
        this.columns = columns;
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

    @Override
    public String toString() {

        return this.name().toLowerCase();
    }
}
