package me.sixteen_.candlestick.block;

import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.AbstractCandleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.Tag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
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
public class CandleCandlestickBlock extends AbstractCandleBlock {

	public static final DirectionProperty FACING;
	private static final BooleanProperty LIT;
	private static final VoxelShape DOWN_SHAPE, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE;
	private static final Tag<Block> CANDLESTICKS;
	private static final Map<Block, CandleCandlestickBlock> CANDLES_TO_CANDLESTICK;
	private static final Iterable<Vec3d> DOWN_PARTICLE_OFFSETS, NORTH_PARTICLE_OFFSETS, EAST_PARTICLE_OFFSETS, SOUTH_PARTICLE_OFFSETS, WEST_PARTICLE_OFFSETS;

	static {
		FACING = Properties.HOPPER_FACING;
		LIT = AbstractCandleBlock.LIT;
		DOWN_SHAPE = VoxelShapes.union(CandlestickBlock.DOWN_SHAPE, Block.createCuboidShape(7.0D, 4.0D, 7.0D, 9.0D, 10.0D, 9.0D));
		NORTH_SHAPE = VoxelShapes.union(CandlestickBlock.NORTH_SHAPE, Block.createCuboidShape(7.0D, 6.0D, 1.0D, 9.0D, 12.0D, 3.0D));
		EAST_SHAPE = VoxelShapes.union(CandlestickBlock.EAST_SHAPE, Block.createCuboidShape(13.0D, 6.0D, 7.0D, 15.0D, 12.0D, 9.0D));
		SOUTH_SHAPE = VoxelShapes.union(CandlestickBlock.SOUTH_SHAPE, Block.createCuboidShape(7.0D, 6.0D, 13.0D, 9.0D, 12.0D, 15.0D));
		WEST_SHAPE = VoxelShapes.union(CandlestickBlock.WEST_SHAPE, Block.createCuboidShape(1.0D, 6.0D, 7.0D, 3.0D, 12.0D, 9.0D));
		CANDLESTICKS = TagFactory.BLOCK.create(new Identifier("candlestick", "candle_candlesticks"));
		CANDLES_TO_CANDLESTICK = Maps.newHashMap();
		DOWN_PARTICLE_OFFSETS = ImmutableList.of(new Vec3d(0.5D, 0.75D, 0.5D));
		NORTH_PARTICLE_OFFSETS = ImmutableList.of(new Vec3d(0.5D, 0.85D, 0.125D));
		EAST_PARTICLE_OFFSETS = ImmutableList.of(new Vec3d(0.875D, 0.85D, 0.5D));
		SOUTH_PARTICLE_OFFSETS = ImmutableList.of(new Vec3d(0.5D, 0.85D, 0.875D));
		WEST_PARTICLE_OFFSETS = ImmutableList.of(new Vec3d(0.125D, 0.85D, 0.5D));
	}

	protected CandleCandlestickBlock(Block candle, Settings settings) {
		super(settings);
		setDefaultState(stateManager.getDefaultState().with(LIT, false).with(FACING, Direction.DOWN));
		CANDLES_TO_CANDLESTICK.put(candle, this);
	}

	public static BlockState getCandlestickFromCandle(Block candle) {
		return CANDLES_TO_CANDLESTICK.get(candle).getDefaultState();
	}

	public static boolean canBeLit(BlockState state) {
		return state.isIn(CANDLESTICKS, (statex) -> {
			return statex.contains(LIT) && !state.get(LIT);
		});
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
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

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return false;
	}

	@Override
	public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
		return new ItemStack(CandlestickBlocks.CANDLESTICK);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (!(itemStack.isOf(Items.FLINT_AND_STEEL) || itemStack.isOf(Items.FIRE_CHARGE))) {
			if (player.getStackInHand(hand).isEmpty() && state.get(LIT)) {
				extinguish(player, state, world, pos);
				return ActionResult.success(world.isClient);
			}
		}
		return ActionResult.PASS;
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	protected Iterable<Vec3d> getParticleOffsets(BlockState state) {
		switch (state.get(FACING)) {
			case DOWN:
			default:
				return DOWN_PARTICLE_OFFSETS;
			case NORTH:
				return NORTH_PARTICLE_OFFSETS;
			case SOUTH:
				return SOUTH_PARTICLE_OFFSETS;
			case WEST:
				return WEST_PARTICLE_OFFSETS;
			case EAST:
				return EAST_PARTICLE_OFFSETS;
		}
	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(LIT, FACING);
	}
}