package net.morthen.resource_backapcks.provider;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.morthen.resource_backpacks.BackpackConstants;
import net.morthen.resource_backpacks.registries.BlockRegistry;

import java.util.function.Consumer;

public class BackpackAdvancementsProvider implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(BlockRegistry.BACKPACK_LEATHER.get(),
                        Component.translatable("advancements.resource_backpacks.root.title"),
                        Component.translatable("advancements.resource_backpacks.root.description"),
                        Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                        AdvancementType.TASK, true, true, false)
                .addCriterion("craft_leather_backpack", InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_LEATHER.get()))
                .save(consumer, BackpackConstants.of("root"));

        AdvancementHolder copper = Advancement.Builder.advancement()
                .parent(root)
                .display(BlockRegistry.BACKPACK_COPPER.get(),
                        Component.translatable("advancements.resource_backpacks.stage_1.title"),
                        Component.translatable("advancements.resource_backpacks.stage_1.description"),
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("craft_copper_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_COPPER.get()))
                .save(consumer, BackpackConstants.of("stage_1"));

        AdvancementHolder iron = Advancement.Builder.advancement()
                .parent(copper)
                .display(BlockRegistry.BACKPACK_IRON.get(),
                        Component.translatable("advancements.resource_backpacks.stage_2.title"),
                        Component.translatable("advancements.resource_backpacks.stage_2.description"),
                        null, AdvancementType.GOAL, true, true, false)
                .addCriterion("craft_iron_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_IRON.get()))
                .save(consumer, BackpackConstants.of("stage_2"));

        AdvancementHolder gold = Advancement.Builder.advancement()
                .parent(iron)
                .display(BlockRegistry.BACKPACK_GOLD.get(),
                        Component.translatable("advancements.resource_backpacks.stage_3.title"),
                        Component.translatable("advancements.resource_backpacks.stage_3.description"),
                        null, AdvancementType.GOAL, true, true, false)
                .addCriterion("craft_gold_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_GOLD.get()))
                .save(consumer, BackpackConstants.of("stage_3"));

        Advancement.Builder.advancement()
                .parent(gold)
                .display(BlockRegistry.BACKPACK_END.get(),
                        Component.translatable("advancements.resource_backpacks.stage_4.title"),
                        Component.translatable("advancements.resource_backpacks.stage_4.description"),
                        null, AdvancementType.CHALLENGE, true, true, false)
                .addCriterion("craft_end_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_END.get()))
                .save(consumer, BackpackConstants.of("stage_4"));

        AdvancementHolder diamond = Advancement.Builder.advancement()
                .parent(gold)
                .display(BlockRegistry.BACKPACK_DIAMOND.get(),
                        Component.translatable("advancements.resource_backpacks.stage_5.title"),
                        Component.translatable("advancements.resource_backpacks.stage_5.description"),
                        null, AdvancementType.TASK, true, true, false)
                .addCriterion("craft_diamond_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_DIAMOND.get()))
                .save(consumer, BackpackConstants.of("stage_5"));

        Advancement.Builder.advancement()
                .parent(diamond)
                .display(BlockRegistry.BACKPACK_NETHERITE.get(),
                        Component.translatable("advancements.resource_backpacks.stage_6.title"),
                        Component.translatable("advancements.resource_backpacks.stage_6.description"),
                        null, AdvancementType.CHALLENGE, true, true, false)
                .addCriterion("craft_netherite_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_NETHERITE.get()))
                .save(consumer, BackpackConstants.of("stage_6"));
    }
}
