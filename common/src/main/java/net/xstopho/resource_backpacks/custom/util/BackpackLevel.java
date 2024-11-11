package net.xstopho.resource_backpacks.custom.util;

public enum BackpackLevel {

    SHULKER(27),
    BIG_CHEST(54);


    final int size;

    BackpackLevel(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
