package me.sixteen_.candlestick.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CandleBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

/**
 * @author 16_
 */
public class CandlestickBlock extends Block {

    private static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D);

    protected CandlestickBlock(final Settings settings) {
        super(settings);
    }

    @Override
    public final VoxelShape getOutlineShape(final BlockState state, final BlockView world, final BlockPos pos, final ShapeContext context) {
        return BOUNDING_SHAPE;
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
            sound = SoundEvents.ENTITY_SLIME_JUMP;
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
}