package me.sixteen_.candlestick.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CandleBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
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

public class CandlestickBlock extends AbstractCandlestickBlock {

	public static final VoxelShape DOWN_SHAPE, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE;

	static {
		DOWN_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D);
		EAST_SHAPE = Block.createCuboidShape(12.0D, 2.0D, 6.0D, 16.0D, 7.0D, 10.0D);
		NORTH_SHAPE = Block.createCuboidShape(6.0D, 2.0D, 0.0D, 10.0D, 7.0D, 4.0D);
		SOUTH_SHAPE = Block.createCuboidShape(6.0D, 2.0D, 12.0D, 10.0D, 7.0D, 16.0D);
		WEST_SHAPE = Block.createCuboidShape(0.0D, 2.0D, 6.0D, 4.0D, 7.0D, 10.0D);
	}

	protected CandlestickBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return switch (state.get(FACING)) {
		case NORTH -> NORTH_SHAPE;
		case SOUTH -> SOUTH_SHAPE;
		case WEST -> WEST_SHAPE;
		case EAST -> EAST_SHAPE;
		default -> DOWN_SHAPE;
		};
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = ctx.getSide().getOpposite();
		return this.getDefaultState().with(FACING, direction.getAxis() == Direction.Axis.Y ? Direction.DOWN : direction);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (itemStack.isIn(ItemTags.CANDLES)) {
			Item item = itemStack.getItem();
			Block block = Block.getBlockFromItem(item);
			if (!(block instanceof CandleBlock)) {
				return ActionResult.PASS;
			}
			if (!player.isCreative()) {
				itemStack.decrement(1);
			}
			BlockState blockState = CandleCandlestickBlock.getCandlestickFromCandle(block);
			world.setBlockState(pos, blockState.with(Properties.HOPPER_FACING, state.get(FACING)));
			world.playSound(null, pos, SoundEvents.BLOCK_CANDLE_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
			world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
			player.incrementStat(Stats.USED.getOrCreateStat(item));
			return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}
}