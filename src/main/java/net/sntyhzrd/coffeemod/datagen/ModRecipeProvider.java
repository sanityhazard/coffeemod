package net.sntyhzrd.coffeemod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.*;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.CompositeModel;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.sntyhzrd.coffeemod.block.ModBlocks;
import net.sntyhzrd.coffeemod.item.ModItems;

import java.util.Set;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CEZVE.get())
                .pattern("  S")
                .pattern("C C")
                .pattern(" C ")
                .define('C', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .showNotification(true)
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STONE_MORTAR.get())
                .pattern("   ")
                .pattern("S S")
                .pattern(" S ")
                .define('S', Items.STONE)
                .unlockedBy(getHasName(Items.STONE), has(Items.STONE))
                .showNotification(true)
                .save(consumer);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Items.STONE), RecipeCategory.MISC,
                ModItems.PESTLE.get())
                .unlockedBy(getHasName(Items.STONE), has(Items.STONE))
                .save(consumer);

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ModItems.COFFEE_BEANS.get()), RecipeCategory.FOOD,
                ModItems.ROASTED_COFFEE_BEANS.get(), 2f, 100)
                .unlockedBy(getHasName(ModItems.COFFEE_BEANS.get()), has(ModItems.COFFEE_BEANS.get()))
                .save(consumer);

    }
}
