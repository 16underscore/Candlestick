package me.sixteen_.candlestick.block;

import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

import me.sixteen_.candlestick.tag.CandlestickBlockTags;
import net.minecraft.block.AbstractCandleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

/**
 * @author 16_
 */
public final class CandleCandlestickBlock extends AbstractCandleBlock {

	public static final BooleanProperty LIT;
	private static final VoxelShape CANDLESTICK_SHAPE, CANDLE_SHAPE, SHAPE;
	private static final Map<Block, CandleCandlestickBlock> CANDLES_TO_CANDLESTICK;
	private static final Iterable<Vec3d> PARTICLE_OFFSETS;

	static {
		LIT = AbstractCandleBlock.LIT;
		CANDLESTICK_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D);
		CANDLE_SHAPE = Block.createCuboidShape(7.0D, 4.0D, 7.0D, 9.0D, 10.0D, 9.0D);
		SHAPE = VoxelShapes.union(CANDLESTICK_SHAPE, CANDLE_SHAPE);
		CANDLES_TO_CANDLESTICK = Maps.newHashMap();
		PARTICLE_OFFSETS = ImmutableList.of(new Vec3d(0.5D, 1.0D, 0.5D));
	}

	protected CandleCandlestickBlock(final Block candle, final Settings settings) {
		super(settings);
		this.setDefaultState((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(LIT, false));
		CANDLES_TO_CANDLESTICK.put(candle, this);
	}

	public static final BlockState getCandlestickFromCandle(final Block candle) {
		return ((CandleCandlestickBlock) CANDLES_TO_CANDLESTICK.get(candle)).getDefaultState();
	}

	public static final boolean canBeLit(final BlockState state) {
		return state.isIn(CandlestickBlockTags.CANDLE_CANDLESTICKS, (statex) -> {
			return statex.contains(LIT) && !(Boolean) state.get(LIT);
		});
	}

	@Override
	public final VoxelShape getOutlineShape(final BlockState state, final BlockView world, final BlockPos pos, final ShapeContext context) {
		return SHAPE;
	}

	@Override
	public final boolean canPathfindThrough(final BlockState state, final BlockView world, final BlockPos pos, final NavigationType type) {
		return false;
	}

	@Override
	public final ItemStack getPickStack(final BlockView world, final BlockPos pos, final BlockState state) {
		return new ItemStack(CandlestickBlocks.CANDLESTICK);
	}

	@Override
	public final ActionResult onUse(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockHitResult hit) {
		final ItemStack itemStack = player.getStackInHand(hand);
		if (!(itemStack.isOf(Items.FLINT_AND_STEEL) || itemStack.isOf(Items.FIRE_CHARGE))) {
			if (player.getStackInHand(hand).isEmpty() && (Boolean) state.get(LIT)) {
				extinguish(player, state, world, pos);
				return ActionResult.success(world.isClient);
			}
		}
		return ActionResult.PASS;
	}

	@Override
	public final BlockState getStateForNeighborUpdate(final BlockState state, final Direction direction, final BlockState neighborState, final WorldAccess world, final BlockPos pos, final BlockPos neighborPos) {
		return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	protected final Iterable<Vec3d> getParticleOffsets(final BlockState state) {
		return PARTICLE_OFFSETS;
	}

	@Override
	protected final void appendProperties(final StateManager.Builder<Block, BlockState> builder) {
		builder.add(LIT);
	}
}