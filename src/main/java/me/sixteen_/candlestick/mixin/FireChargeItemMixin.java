package me.sixteen_.candlestick.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.sixteen_.candlestick.block.CandleCandlestickBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.FireChargeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

/**
 * @author 16_
 */
@Mixin(FireChargeItem.class)
public abstract class FireChargeItemMixin {

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    public final void useOnBlock(ItemUsageContext context, final CallbackInfoReturnable<ActionResult> info) {
        final World world = context.getWorld();
        final BlockPos blockPos = context.getBlockPos();
        final BlockState blockState = world.getBlockState(blockPos);
        if (CandleCandlestickBlock.canBeLit(blockState)) {
            world.setBlockState(blockPos, (BlockState) blockState.with(Properties.LIT, true));
            world.emitGameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, blockPos);
            context.getStack().decrement(1);
            info.setReturnValue(ActionResult.success(world.isClient));
        }
    }
}