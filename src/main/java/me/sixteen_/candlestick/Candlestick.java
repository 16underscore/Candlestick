package me.sixteen_.candlestick;

import me.sixteen_.candlestick.block.CandlestickBlocks;
import me.sixteen_.candlestick.item.CandlestickItems;
import net.fabricmc.api.ModInitializer;

/**
 * @author 16_
 */
public class Candlestick implements ModInitializer {

	@Override
	public void onInitialize() {
		new CandlestickBlocks();
		new CandlestickItems();
	}
}