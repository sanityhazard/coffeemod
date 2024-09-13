package net.sntyhzrd.coffeemod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sntyhzrd.coffeemod.CoffeeMod;
import net.sntyhzrd.coffeemod.block.ModBlocks;
import net.sntyhzrd.coffeemod.block.custom.CezveBlock;
import net.sntyhzrd.coffeemod.item.ModItems;
import org.checkerframework.checker.index.qual.SubstringIndexUnknown;

@Mod.EventBusSubscriber(modid = CoffeeMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        ItemStack itemStack = player.getMainHandItem();
        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        BlockState state = level.getBlockState(pos);
        if (!level.isClientSide()) {
            if (state.getBlock() == ModBlocks.CEZVE.get()) {
                if (itemStack.is(ModItems.GROUND_COFFEE.get())) {
                    itemStack.shrink(1);
                    BlockState newState = state.setValue(CezveBlock.HAS_COFFEE, true);
                    level.setBlockAndUpdate(pos, newState);
                }

                else if (itemStack.is(Items.POTION) && PotionUtils.getPotion(itemStack) == Potions.WATER && event.getFace() != Direction.DOWN) {
                    itemStack.shrink(1);
                    ItemStack bottleStack = new ItemStack(Items.GLASS_BOTTLE);
                    if (!player.addItem(bottleStack)) {
                        Containers.dropItemStack(player.level(), player.getX(), player.getY(), player.getZ(), bottleStack);
                    }
                    BlockState newState = state.setValue(CezveBlock.HAS_WATER, true);
                    level.setBlockAndUpdate(pos, newState);
                }

                else if (itemStack.is(Items.FLINT_AND_STEEL)) {
                    if (state.getValue(CezveBlock.HAS_WATER) && state.getValue(CezveBlock.HAS_WATER)) {
                        itemStack.setDamageValue(itemStack.getDamageValue() - 1);
                        BlockState newState = state.setValue(CezveBlock.IS_BREWED, true);
                        level.setBlockAndUpdate(pos, newState);
                        level.addParticle(ParticleTypes.SMOKE,
                                pos.getX() + 1,
                                pos.getY() + 1,
                                pos.getZ() + 1,
                                1, 1, 1);
                    }
                }
            }
        }
    }
}
