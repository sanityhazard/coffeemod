package net.sntyhzrd.coffeemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagType;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.io.DataOutput;

public class CezveBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(5.5, 0, 5.5, 10.5, 5.5, 10.5);
    public static final BooleanProperty IS_BREWED = BooleanProperty.create("is_brewed");
    public static final BooleanProperty HAS_WATER = BooleanProperty.create("has_water");
    public static final BooleanProperty HAS_COFFEE = BooleanProperty.create("has_coffee");

    public CezveBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(
            this.defaultBlockState()
                .setValue(CezveBlock.IS_BREWED, false)
                .setValue(CezveBlock.HAS_WATER, false)
                    .setValue(CezveBlock.HAS_COFFEE, false)
        );
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HAS_COFFEE);
        pBuilder.add(HAS_WATER);
        pBuilder.add(IS_BREWED);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if (!pLevel.isClientSide()) {
            CompoundTag nbtData = pStack.getTag();
            if (nbtData != null && !nbtData.isEmpty()) {
                BlockState loadedState = pState
                        .setValue(CezveBlock.HAS_WATER, nbtData.getBoolean("has_water"))
                        .setValue(CezveBlock.HAS_COFFEE, nbtData.getBoolean("has_coffee"))
                        .setValue(CezveBlock.IS_BREWED, nbtData.getBoolean("is_brewed"));

                pLevel.setBlockAndUpdate(pPos, loadedState);
            }
        }
//        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!level.isClientSide()) {
            if (!player.isCreative()) {
                CompoundTag nbtData = new CompoundTag();
                nbtData.putBoolean("has_coffee", (boolean) state.getValue(CezveBlock.HAS_COFFEE));
                nbtData.putBoolean("has_water", (boolean) state.getValue(CezveBlock.HAS_WATER));
                nbtData.putBoolean("is_brewed", (boolean) state.getValue(CezveBlock.IS_BREWED));

                ItemStack itemStack = new ItemStack(this);
                itemStack.setTag(nbtData);
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), itemStack);
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
