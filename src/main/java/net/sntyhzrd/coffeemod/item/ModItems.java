package net.sntyhzrd.coffeemod.item;

import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sntyhzrd.coffeemod.CoffeeMod;
import net.sntyhzrd.coffeemod.block.ModBlocks;
import net.sntyhzrd.coffeemod.item.custom.FilledCoffeeCupItem;
import net.sntyhzrd.coffeemod.item.custom.PestleItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CoffeeMod.MODID);

    public static final RegistryObject<Item> COFFEE_BEANS = ITEMS.register("coffee_beans",
            () -> new ItemNameBlockItem(ModBlocks.COFFEE_BUSH.get(), new Item.Properties()));

    public static final RegistryObject<Item> ROASTED_COFFEE_BEANS = ITEMS.register("roasted_coffee_beans",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GROUND_COFFEE = ITEMS.register("ground_coffee",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PESTLE = ITEMS.register("pestle",
            () -> new PestleItem(new Item.Properties()));

    public static final RegistryObject<Item> FILLED_COFFEE_CUP = ITEMS.register("filled_coffee_cup",
            () -> new FilledCoffeeCupItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
