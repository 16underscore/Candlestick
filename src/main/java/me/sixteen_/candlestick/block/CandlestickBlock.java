package me.sixteen_.candlestick.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CandleBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

/**
 * @author 16_
 */
public final class CandlestickBlock extends Block {

    private static final DirectionProperty FACING;
    private static final VoxelShape DOWN_SHAPE, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE;

    static {
        FACING = Properties.HOPPER_FACING;
        DOWN_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D);
        EAST_SHAPE = Block.createCuboidShape(12.0D, 2.0D, 6.0D, 16.0D, 7.0D, 10.0D);
        NORTH_SHAPE = Block.createCuboidShape(6.0D, 2.0D, 0.0D, 10.0D, 7.0D, 4.0D);
        SOUTH_SHAPE = Block.createCuboidShape(6.0D, 2.0D, 12.0D, 10.0D, 7.0D, 16.0D);
        WEST_SHAPE = Block.createCuboidShape(0.0D, 2.0D, 6.0D, 4.0D, 7.0D, 10.0D);
    }

    protected CandlestickBlock(final Settings settings) {
        super(settings);
        setDefaultState((BlockState) ((BlockState) stateManager.getDefaultState()).with(FACING, Direction.DOWN));
    }

    @Override
    public final VoxelShape getOutlineShape(final BlockState state, final BlockView world, final BlockPos pos, final ShapeContext context) {
        switch ((Direction) state.get(FACING)) {
        case DOWN:
        default:
            return DOWN_SHAPE;
        case NORTH:
            return NORTH_SHAPE;
        case SOUTH:
            return SOUTH_SHAPE;
        case WEST:
            return WEST_SHAPE;
        case EAST:
            return EAST_SHAPE;
        }
    }

    @Override
    public final BlockState getPlacementState(final ItemPlacementContext ctx) {
        final Direction direction = ctx.getSide().getOpposite();
        return (BlockState) ((BlockState) this.getDefaultState().with(FACING, direction.getAxis() == Direction.Axis.Y ? Direction.DOWN : direction));
    }

    @Override
    public final ActionResult onUse(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockHitResult hit) {
        final ItemStack itemStack = player.getStackInHand(hand);
        final Item item = itemStack.getItem();
        SoundEvent sound;
        BlockState blockState;
        if (itemStack.isIn(ItemTags.CANDLES)) {
            final Block block = Block.getBlockFromItem(item);
            if (!(block instanceof CandleBlock)) {
                return ActionResult.PASS;
            }
            sound = SoundEvents.BLOCK_CANDLE_PLACE;
            blockState = CandleCandlestickBlock.getCandlestickFromCandle(block);
        } else if (itemStack.isOf(Items.SEA_PICKLE)) {
            sound = SoundEvents.BLOCK_SLIME_BLOCK_PLACE;
            blockState = CandlestickBlocks.SEA_PICKLE_CANDLESTICK.getDefaultState();
        } else {
            return ActionResult.PASS;
        }
        if (!player.isCreative()) {
            itemStack.decrement(1);
        }
        world.setBlockState(pos, blockState);
        world.playSound((PlayerEntity) null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        player.incrementStat(Stats.USED.getOrCreateStat(item));
        return ActionResult.SUCCESS;
    }

    @Override
    protected final void appendProperties(final Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}