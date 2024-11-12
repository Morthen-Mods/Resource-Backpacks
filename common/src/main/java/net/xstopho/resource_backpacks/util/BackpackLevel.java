package net.xstopho.resource_backpacks.util;

public enum BackpackLevel {

    SHULKER(3, 9),
    TEST(10, 25);

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
