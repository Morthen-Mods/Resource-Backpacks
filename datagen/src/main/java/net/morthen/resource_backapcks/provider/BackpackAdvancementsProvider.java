package net.morthen.resource_backapcks.provider;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.morthen.resource_backpacks.BackpackConstants;
import net.morthen.resource_backpacks.registries.BlockRegistry;

public class BackpackAdvancementsProvider extends AdvancementSubProvider {
    public BackpackAdvancementsProvider(BootstrapContext<Advancement> output) {
        super(output);
    }

    @Override
    public void generate() {
        AdvancementHolder root = Advancement.Builder.advancement()
                .rootDisplay(BlockRegistry.BACKPACK_LEATHER.get().asItem(),
                        Component.translatable("advancements.resource_backpacks.root.title"),
                        Component.translatable("advancements.resource_backpacks.root.description"),
                        Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                        AdvancementType.TASK, true, true, false)
                .addCriterion("craft_leather_backpack", InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_LEATHER.get()))
                .save(this.output, BackpackConstants.of("root").toString());

        AdvancementHolder copper = Advancement.Builder.advancement()
                .parent(root)
                .display(BlockRegistry.BACKPACK_COPPER.get().asItem(),
                        Component.translatable("advancements.resource_backpacks.stage_1.title"),
                        Component.translatable("advancements.resource_backpacks.stage_1.description"),
                        AdvancementType.TASK, true, true, false)
                .addCriterion("craft_copper_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_COPPER.get()))
                .save(this.output, BackpackConstants.of("stage_1").toString());

        AdvancementHolder iron = Advancement.Builder.advancement()
                .parent(copper)
                .display(BlockRegistry.BACKPACK_IRON.get().asItem(),
                        Component.translatable("advancements.resource_backpacks.stage_2.title"),
                        Component.translatable("advancements.resource_backpacks.stage_2.description"),
                        AdvancementType.GOAL, true, true, false)
                .addCriterion("craft_iron_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_IRON.get()))
                .save(this.output, BackpackConstants.of("stage_2").toString());

        AdvancementHolder gold = Advancement.Builder.advancement()
                .parent(iron)
                .display(BlockRegistry.BACKPACK_GOLD.get().asItem(),
                        Component.translatable("advancements.resource_backpacks.stage_3.title"),
                        Component.translatable("advancements.resource_backpacks.stage_3.description"),
                        AdvancementType.GOAL, true, true, false)
                .addCriterion("craft_gold_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_GOLD.get()))
                .save(this.output, BackpackConstants.of("stage_3").toString());

        Advancement.Builder.advancement()
                .parent(gold)
                .display(BlockRegistry.BACKPACK_END.get().asItem(),
                        Component.translatable("advancements.resource_backpacks.stage_4.title"),
                        Component.translatable("advancements.resource_backpacks.stage_4.description"),
                        AdvancementType.CHALLENGE, true, true, false)
                .addCriterion("craft_end_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_END.get()))
                .save(this.output, BackpackConstants.of("stage_4").toString());

        AdvancementHolder diamond = Advancement.Builder.advancement()
                .parent(gold)
                .display(BlockRegistry.BACKPACK_DIAMOND.get().asItem(),
                        Component.translatable("advancements.resource_backpacks.stage_5.title"),
                        Component.translatable("advancements.resource_backpacks.stage_5.description"),
                        AdvancementType.TASK, true, true, false)
                .addCriterion("craft_diamond_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_DIAMOND.get()))
                .save(this.output, BackpackConstants.of("stage_5").toString());

        Advancement.Builder.advancement()
                .parent(diamond)
                .display(BlockRegistry.BACKPACK_NETHERITE.get().asItem(),
                        Component.translatable("advancements.resource_backpacks.stage_6.title"),
                        Component.translatable("advancements.resource_backpacks.stage_6.description"),
                        AdvancementType.CHALLENGE, true, true, false)
                .addCriterion("craft_netherite_backpack",  InventoryChangeTrigger.TriggerInstance.hasItems(BlockRegistry.BACKPACK_NETHERITE.get()))
                .save(this.output, BackpackConstants.of("stage_6").toString());
    }
}
