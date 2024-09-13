package net.sntyhzrd.coffeemod.datagen;

import net.minecraft.client.model.Model;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.sntyhzrd.coffeemod.CoffeeMod;
import net.sntyhzrd.coffeemod.block.ModBlocks;
import net.sntyhzrd.coffeemod.block.custom.CezveBlock;
import net.sntyhzrd.coffeemod.block.custom.CoffeeCropBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CoffeeMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.STONE_MORTAR.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/stone_mortar")));

        makeCoffeeCrop(((CropBlock) ModBlocks.COFFEE_BUSH.get()), "coffee_stage_", "coffee_stage_");

        simpleBlockItem(ModBlocks.CEZVE.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/cezve")));

        simpleBlockWithItem(ModBlocks.COFFEE_CUP.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/coffee_cup")));

        simpleBlock(ModBlocks.FILLED_COFFEE_CUP.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/filled_coffee_cup")));

        getVariantBuilder(ModBlocks.CEZVE.get())
                .partialState()
                    .with(CezveBlock.HAS_WATER, false)
                    .with(CezveBlock.HAS_COFFEE, false)
                    .with(CezveBlock.IS_BREWED, false)
                    .modelForState()
                    .modelFile(new ModelFile.UncheckedModelFile(
                            new ResourceLocation(CoffeeMod.MODID, "block/cezve")
                    ))
                    .addModel()
                .partialState()
                    .with(CezveBlock.HAS_WATER, true)
                    .with(CezveBlock.HAS_COFFEE, true)
                    .with(CezveBlock.IS_BREWED, false)
                    .modelForState()
                    .modelFile(new ModelFile.UncheckedModelFile(
                            new ResourceLocation(CoffeeMod.MODID, "block/cezve_with_unbrewed_coffee")
                    ))
                    .addModel()
                .partialState()
                    .with(CezveBlock.HAS_WATER, true)
                    .with(CezveBlock.HAS_COFFEE, true)
                    .with(CezveBlock.IS_BREWED, true)
                    .modelForState()
                    .modelFile(new ModelFile.UncheckedModelFile(
                            new ResourceLocation(CoffeeMod.MODID, "block/cezve_with_brewed_coffee")
                    ))
                    .addModel()
                .partialState()
                    .with(CezveBlock.HAS_WATER, false)
                    .with(CezveBlock.HAS_COFFEE, true)
                    .with(CezveBlock.IS_BREWED, false)
                    .modelForState()
                    .modelFile(new ModelFile.UncheckedModelFile(
                            new ResourceLocation(CoffeeMod.MODID, "block/cezve_with_coffee")
                    ))
                    .addModel()
                .partialState()
                    .with(CezveBlock.HAS_WATER, true)
                    .with(CezveBlock.HAS_COFFEE, false)
                    .with(CezveBlock.IS_BREWED, false)
                    .modelForState()
                    .modelFile(new ModelFile.UncheckedModelFile(
                            new ResourceLocation(CoffeeMod.MODID, "block/cezve_with_water")
                    ))
                    .addModel()
                .partialState()
                    .with(CezveBlock.HAS_WATER, false)
                    .with(CezveBlock.HAS_COFFEE, false)
                    .with(CezveBlock.IS_BREWED, true)
                    .modelForState()
                    .modelFile(new ModelFile.UncheckedModelFile(
                            new ResourceLocation(CoffeeMod.MODID, "block/cezve")
                    ))
                    .addModel()
                .partialState()
                    .with(CezveBlock.HAS_WATER, true)
                    .with(CezveBlock.HAS_COFFEE, false)
                    .with(CezveBlock.IS_BREWED, true)
                    .modelForState()
                    .modelFile(new ModelFile.UncheckedModelFile(
                            new ResourceLocation(CoffeeMod.MODID, "block/cezve")
                    ))
                    .addModel()
                .partialState()
                    .with(CezveBlock.HAS_WATER, false)
                    .with(CezveBlock.HAS_COFFEE, true)
                    .with(CezveBlock.IS_BREWED, true)
                    .modelForState()
                    .modelFile(new ModelFile.UncheckedModelFile(
                            new ResourceLocation(CoffeeMod.MODID, "block/cezve")
                    ))
                    .addModel();
    }

    public void makeCoffeeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cornStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cornStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CoffeeCropBlock) block).getAgeProperty()),
                new ResourceLocation(CoffeeMod.MODID, "block/" + textureName + state.getValue(((CoffeeCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
