package net.xstopho.resource_backpacks.custom.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.xstopho.resource_backpacks.custom.util.BackpackLevel;

public class BackpackItem extends BlockItem {

    private final BackpackLevel level;

    public BackpackItem(Block block, BackpackLevel level, Properties properties) {
        super(block, properties.useBlockDescriptionPrefix());
        this.level = level;
    }
}
