package me.sixteen_.candlestick.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public abstract class AbstractCandlestickBlock extends Block {

	public static final DirectionProperty FACING;

	static {
		FACING = Properties.HOPPER_FACING;
	}

	protected AbstractCandlestickBlock(final Settings settings) {
		super(settings);
		setDefaultState((BlockState) ((BlockState) stateManager.getDefaultState()).with(FACING, Direction.DOWN));
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
	protected final void appendProperties(final Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}