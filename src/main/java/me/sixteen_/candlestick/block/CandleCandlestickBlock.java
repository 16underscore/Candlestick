package me.sixteen_.candlestick.block;

import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

import net.minecraft.block.AbstractCandleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

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

	public CandleCandlestickBlock(final Block candle, final Settings settings) {
		super(settings);
		this.setDefaultState((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(LIT, false));
		CANDLES_TO_CANDLESTICK.put(candle, this);
	}

	@Override
	public final VoxelShape getOutlineShape(final BlockState state, final BlockView world, final BlockPos pos,
			final ShapeContext context) {
		return SHAPE;
	}

	@Override
	protected final Iterable<Vec3d> getParticleOffsets(final BlockState state) {
		return PARTICLE_OFFSETS;
	}

	@Override
	protected final void appendProperties(final StateManager.Builder<Block, BlockState> builder) {
		builder.add(LIT);
	}

	@Override
	public final boolean canPathfindThrough(final BlockState state, final BlockView world, final BlockPos pos,
			final NavigationType type) {
		return false;
	}

	@Override
	public final ItemStack getPickStack(final BlockView world, final BlockPos pos, final BlockState state) {
		return new ItemStack(CandlestickBlocks.CANDLESTICK);
	}

	public final static BlockState getCandlestickFromCandle(final Block candle) {
		return ((CandleCandlestickBlock) CANDLES_TO_CANDLESTICK.get(candle)).getDefaultState();
	}
}