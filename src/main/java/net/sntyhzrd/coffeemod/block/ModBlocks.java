package net.sntyhzrd.coffeemod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sntyhzrd.coffeemod.CoffeeMod;
import net.sntyhzrd.coffeemod.block.custom.*;
import net.sntyhzrd.coffeemod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CoffeeMod.MODID);


    public static final RegistryObject<Block> CEZVE = registerBlock("cezve",
            () -> new CezveBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).instabreak().noOcclusion()));

    public static final RegistryObject<Block> STONE_MORTAR = registerBlock("stone_mortar",
            () -> new StoneMortarBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).instabreak().noOcclusion()));

    public static final RegistryObject<Block> COFFEE_BUSH = BLOCKS.register("coffee_crop",
            () -> new CoffeeCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> COFFEE_CUP = registerBlock("coffee_cup_block",
            () -> new CoffeeCupBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion()));

    public static final RegistryObject<Block> FILLED_COFFEE_CUP = registerBlock("filled_coffee_cup_block",
            () -> new FilledCoffeeCupBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, Supplier<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
