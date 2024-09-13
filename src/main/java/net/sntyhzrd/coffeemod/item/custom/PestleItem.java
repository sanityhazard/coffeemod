package net.sntyhzrd.coffeemod.item.custom;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.sntyhzrd.coffeemod.item.ModItems;

public class PestleItem extends Item {
    public PestleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();
        if (!level.isClientSide()) {
            ItemStack leftHandItem = player.getOffhandItem();
            if (leftHandItem.is(ModItems.ROASTED_COFFEE_BEANS.get())) {
                leftHandItem.shrink(1);
                player.getInventory().add(new ItemStack(ModItems.GROUND_COFFEE.get(), 2));
            }
        }

        return super.useOn(pContext);
    }
}
