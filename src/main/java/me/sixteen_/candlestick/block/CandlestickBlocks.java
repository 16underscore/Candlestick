package me.sixteen_.candlestick.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CandlestickBlocks {

	public static final Block
		CANDLESTICK,
		CANDLE_CANDLESTICK,
		WHITE_CANDLE_CANDLESTICK,
		ORANGE_CANDLE_CANDLESTICK,
		MAGENTA_CANDLE_CANDLESTICK,
		LIGHT_BLUE_CANDLE_CANDLESTICK,
		YELLOW_CANDLE_CANDLESTICK,
		LIME_CANDLE_CANDLESTICK,
		PINK_CANDLE_CANDLESTICK,
		GRAY_CANDLE_CANDLESTICK,
		LIGHT_GRAY_CANDLE_CANDLESTICK,
		CYAN_CANDLE_CANDLESTICK,
		PURPLE_CANDLE_CANDLESTICK,
		BLUE_CANDLE_CANDLESTICK,
		BROWN_CANDLE_CANDLESTICK,
		GREEN_CANDLE_CANDLESTICK,
		RED_CANDLE_CANDLESTICK,
		BLACK_CANDLE_CANDLESTICK;

	static {
		CANDLESTICK = register("candlestick", new CandlestickBlock(FabricBlockSettings.of(Material.METAL).strength(3.5F).nonOpaque()));
		CANDLE_CANDLESTICK = register("candle_candlestick", new CandleCandlestickBlock(Blocks.CANDLE, AbstractBlock.Settings.copy(CANDLESTICK).luminance(state -> state.get(Properties.LIT) ? 9 : 0)));
		WHITE_CANDLE_CANDLESTICK = register("white_candle_candlestick", create(Blocks.WHITE_CANDLE));
		ORANGE_CANDLE_CANDLESTICK = register("orange_candle_candlestick", create(Blocks.ORANGE_CANDLE));
		MAGENTA_CANDLE_CANDLESTICK = register("magenta_candle_candlestick", create(Blocks.MAGENTA_CANDLE));
		LIGHT_BLUE_CANDLE_CANDLESTICK = register("light_blue_candle_candlestick", create(Blocks.LIGHT_BLUE_CANDLE));
		YELLOW_CANDLE_CANDLESTICK = register("yellow_candle_candlestick", create(Blocks.YELLOW_CANDLE));
		LIME_CANDLE_CANDLESTICK = register("lime_candle_candlestick", create(Blocks.LIME_CANDLE));
		PINK_CANDLE_CANDLESTICK = register("pink_candle_candlestick", create(Blocks.PINK_CANDLE));
		GRAY_CANDLE_CANDLESTICK = register("gray_candle_candlestick", create(Blocks.GRAY_CANDLE));
		LIGHT_GRAY_CANDLE_CANDLESTICK = register("light_gray_candle_candlestick", create(Blocks.LIGHT_GRAY_CANDLE));
		CYAN_CANDLE_CANDLESTICK = register("cyan_candle_candlestick", create(Blocks.CYAN_CANDLE));
		PURPLE_CANDLE_CANDLESTICK = register("purple_candle_candlestick", create(Blocks.PURPLE_CANDLE));
		BLUE_CANDLE_CANDLESTICK = register("blue_candle_candlestick", create(Blocks.PURPLE_CANDLE));
		BROWN_CANDLE_CANDLESTICK = register("brown_candle_candlestick", create(Blocks.BROWN_CANDLE));
		GREEN_CANDLE_CANDLESTICK = register("green_candle_candlestick", create(Blocks.GREEN_CANDLE));
		RED_CANDLE_CANDLESTICK = register("red_candle_candlestick", create(Blocks.RED_CANDLE));
		BLACK_CANDLE_CANDLESTICK = register("black_candle_candlestick", create(Blocks.BLACK_CANDLE));
	}

	private static CandleCandlestickBlock create(Block candle) {
		return new CandleCandlestickBlock(candle, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK));
	}

	private static Block register(String id, Block block) {
		return Registry.register(Registry.BLOCK, new Identifier("candlestick", id), block);
	}
}