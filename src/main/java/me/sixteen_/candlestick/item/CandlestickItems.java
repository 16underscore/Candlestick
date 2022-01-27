package me.sixteen_.candlestick.item;

import me.sixteen_.candlestick.block.CandlestickBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * @author 16_
 */
public class CandlestickItems {

	public static final BlockItem CANDLESTICK;

	static {
		CANDLESTICK = register("candlestick", new BlockItem(CandlestickBlocks.CANDLESTICK, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
	}

	private static BlockItem register(String id, BlockItem block) {
		return Registry.register(Registry.ITEM, new Identifier("candlestick", id), block);
	}
}