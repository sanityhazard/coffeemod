package net.sntyhzrd.coffeemod.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
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

public class ModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CoffeeMod.MODID);

    public static final RegistryObject<CreativeModeTab> COFFEE_TAB = CREATIVE_MODE_TABS.register(
            "coffee_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("tabs.coffeemod.coffee_tab"))
                    .icon(() -> new ItemStack(ModBlocks.CEZVE.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
                            output.accept(item.get());
                        }

                        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()) {
                            output.accept(block.get());
                        }
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
