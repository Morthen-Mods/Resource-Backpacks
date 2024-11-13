package net.xstopho.resource_backpacks.util;

public enum BackpackLevel {

    DEFAULT(5, 14),
    LEATHER(3, 9),
    COPPER(3, 10),
    GOLD(4, 11),
    IRON(5, 12),
    DIAMOND(6, 12),
    NETHERITE(7, 13),
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
        return this.columns * this.rows;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
