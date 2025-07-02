package net.xstopho.resource_backpacks.backpack.util;

import net.minecraft.network.chat.Component;
import net.xstopho.resource_backpacks.config.common.BackpackConfig;

import java.util.function.Supplier;

public enum BackpackLevel {
    LEATHER(() -> BackpackConfig.leatherRows, () -> BackpackConfig.leatherColumns, 22),
    COPPER(() -> BackpackConfig.copperRows, () -> BackpackConfig.copperColumns, 42),
    GOLD(() -> BackpackConfig.goldRows, () -> BackpackConfig.goldColumns, 64),
    IRON(() -> BackpackConfig.ironRows, () -> BackpackConfig.ironColumns, 108),
    DIAMOND(() -> BackpackConfig.diamondRows, () -> BackpackConfig.diamondColumns, 160),
    NETHERITE(() -> BackpackConfig.netheriteRows, () -> BackpackConfig.netheriteColumns, 250),
    END(() -> 3, () -> 9, 27);

    final Supplier<Integer> rows, columns;
    final Component defaultName;
    final int maxSize;

    BackpackLevel(Supplier<Integer> rows, Supplier<Integer> columns, int maxSize) {
        this.rows = rows;
        this.columns = columns;
        this.maxSize = maxSize;

        this.defaultName = Component.translatable("block.resource_backpacks.backpack_" + this);
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

    public Component getDefaultName() {

        return defaultName;
    }

    @Override
    public String toString() {

        return this.name().toLowerCase();
    }
}
