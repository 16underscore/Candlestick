package me.sixteen_.candlestick.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

/**
 * @author 16_
 */
public final class SeaPickleCandlestickBlock extends AbstractCandlestickBlock {

	private static final VoxelShape DOWN_SHAPE, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE;

	static {
		DOWN_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 11.0D, 10.0D);
		EAST_SHAPE = Block.createCuboidShape(12.0D, 2.0D, 6.0D, 16.0D, 13.0D, 10.0D);
		NORTH_SHAPE = Block.createCuboidShape(6.0D, 2.0D, 0.0D, 10.0D, 13.0D, 4.0D);
		SOUTH_SHAPE = Block.createCuboidShape(6.0D, 2.0D, 12.0D, 10.0D, 13.0D, 16.0D);
		WEST_SHAPE = Block.createCuboidShape(0.0D, 2.0D, 6.0D, 4.0D, 13.0D, 10.0D);
	}

	protected SeaPickleCandlestickBlock(final Settings settings) {
		super(settings);
	}

	@Override
	public final VoxelShape getOutlineShape(final BlockState state, final BlockView world, final BlockPos pos, final ShapeContext context) {
		switch (state.get(FACING)) {
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
}