package net.xstopho.resource_backpacks.backpack;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.xstopho.resource_backpacks.backpack.component.BackpackContainerComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.CompactClientTooltipComponent;
import net.xstopho.resource_backpacks.backpack.tooltip.InventoryClientTooltipComponent;
import net.xstopho.resource_backpacks.backpack.util.BackpackInventory;
import net.xstopho.resource_backpacks.backpack.util.BackpackLevel;
import net.xstopho.resource_backpacks.client.screen.BackpackMenu;
import net.xstopho.resource_backpacks.client.util.BackpackClientUtils;
import net.xstopho.resource_backpacks.config.common.BackpackConfig;
import net.xstopho.resource_backpacks.registries.DataComponentRegistry;

import java.util.Optional;

public class BackpackItem extends BlockItem {

    private final BackpackLevel backpackLevel;

    public BackpackItem(Block block, BackpackLevel backpackLevel, Properties properties) {
        super(block, properties.component(DataComponentRegistry.BACKPACK_CONTAINER.get(), BackpackContainerComponent.EMPTY));
        this.backpackLevel = backpackLevel;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (!level.isClientSide && BackpackConfig.openFromInventory) {
            player.openMenu(getMenuProvider(stack));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();

        if (player != null && (player.isCrouching() || !BackpackConfig.openFromInventory)) {
            super.useOn(context);
        }

        return InteractionResult.PASS;
    }

    public MenuProvider getMenuProvider(ItemStack stack) {
        BackpackInventory backpackInventory = new BackpackInventory(stack, backpackLevel);

        return switch(backpackLevel) {
            case LEATHER -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.leatherMenu(i, inventory, backpackInventory, false), this.getName());
            case COPPER -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.copperMenu(i, inventory, backpackInventory, false), this.getName());
            case GOLD -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.goldMenu(i, inventory, backpackInventory, false), this.getName());
            case IRON -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.ironMenu(i, inventory, backpackInventory, false), this.getName());
            case DIAMOND -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.diamondMenu(i, inventory, backpackInventory, false), this.getName());
            case NETHERITE -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.netheriteMenu(i, inventory, backpackInventory, false), this.getName());
            case END -> new SimpleMenuProvider((i, inventory, player) -> BackpackMenu.endMenu(i, inventory, player.getEnderChestInventory(), false), this.getName());
        };
    }

    public BackpackLevel getBackpackLevel() {
        return backpackLevel;
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;

    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        BackpackContainerComponent content = stack.get(DataComponentRegistry.BACKPACK_CONTAINER.get());
        Optional<TooltipComponent> tooltipComponent = Optional.empty();

        if (BackpackClientUtils.enableCompactPreview()) {
            tooltipComponent = Optional.of(new CompactClientTooltipComponent.CompactTooltipComponent(content, backpackLevel));

        } else if (BackpackClientUtils.enableInventoryPreview()) {
            tooltipComponent = Optional.of(new InventoryClientTooltipComponent.InventoryTooltipComponent(content, backpackLevel));
        }

        return tooltipComponent;
    }
}
