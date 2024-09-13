package net.sntyhzrd.coffeemod.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sntyhzrd.coffeemod.CoffeeMod;
import net.sntyhzrd.coffeemod.block.ModBlocks;
import net.sntyhzrd.coffeemod.item.ModItems;

import java.util.List;

public class ModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CoffeeMod.MODID);

    public static final RegistryObject<CreativeModeTab> COFFEE_TAB = CREATIVE_MODE_TABS.register(
            "coffee_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("tabs.coffeemod.coffee_tab"))
                    .icon(() -> new ItemStack(ModBlocks.CEZVE.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.COFFEE_BEANS.get());
                        output.accept(ModItems.ROASTED_COFFEE_BEANS.get());
                        output.accept(ModItems.GROUND_COFFEE.get());
                        output.accept(ModItems.PESTLE.get());
                        output.accept(ModItems.FILLED_COFFEE_CUP.get());

                        output.accept(ModBlocks.CEZVE.get());
                        output.accept(ModBlocks.STONE_MORTAR.get());
                        output.accept(ModBlocks.COFFEE_BUSH.get());
                        output.accept(ModBlocks.COFFEE_CUP.get());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
