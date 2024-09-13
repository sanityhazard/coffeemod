package net.sntyhzrd.coffeemod.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;
import net.sntyhzrd.coffeemod.block.ModBlocks;
import net.sntyhzrd.coffeemod.block.custom.CoffeeCropBlock;
import net.sntyhzrd.coffeemod.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.CEZVE.get());
        this.dropSelf(ModBlocks.STONE_MORTAR.get());

        LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.COFFEE_BUSH.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CoffeeCropBlock.AGE, 7));


        this.add(ModBlocks.COFFEE_BUSH.get(), createCropDrops(
                ModBlocks.COFFEE_BUSH.get(),
                ModItems.COFFEE_BEANS.get(),
                ModItems.COFFEE_BEANS.get(),
                lootitemcondition$builder1));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}