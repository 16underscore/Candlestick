package me.sixteen_.candlestick.block;

import java.util.function.ToIntFunction;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * @author 16_
 */
public final class CandlestickBlocks {

	public static final Block//
	CANDLESTICK, //
	CANDLE_CANDLESTICK, //
	WHITE_CANDLE_CANDLESTICK, //
	ORANGE_CANDLE_CANDLESTICK, //
	MAGENTA_CANDLE_CANDLESTICK, //
	LIGHT_BLUE_CANDLE_CANDLESTICK, //
	YELLOW_CANDLE_CANDLESTICK, //
	LIME_CANDLE_CANDLESTICK, //
	PINK_CANDLE_CANDLESTICK, //
	GRAY_CANDLE_CANDLESTICK, //
	LIGHT_GRAY_CANDLE_CANDLESTICK, //
	CYAN_CANDLE_CANDLESTICK, //
	PURPLE_CANDLE_CANDLESTICK, //
	BLUE_CANDLE_CANDLESTICK, //
	BROWN_CANDLE_CANDLESTICK, //
	GREEN_CANDLE_CANDLESTICK, //
	RED_CANDLE_CANDLESTICK, //
	BLACK_CANDLE_CANDLESTICK, //
	SEA_PICKLE_CANDLESTICK;

	static {
		CANDLESTICK = register("candlestick", new CandlestickBlock(FabricBlockSettings.of(Material.METAL).strength(4.0F)));
		CANDLE_CANDLESTICK = register("candle_candlestick", new CandleCandlestickBlock(Blocks.CANDLE, AbstractBlock.Settings.copy(CANDLESTICK).luminance(createLightLevelFromLitBlockState(3))));
		WHITE_CANDLE_CANDLESTICK = register("white_candle_candlestick", new CandleCandlestickBlock(Blocks.WHITE_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		ORANGE_CANDLE_CANDLESTICK = register("orange_candle_candlestick", new CandleCandlestickBlock(Blocks.ORANGE_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		MAGENTA_CANDLE_CANDLESTICK = register("magenta_candle_candlestick", new CandleCandlestickBlock(Blocks.MAGENTA_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		LIGHT_BLUE_CANDLE_CANDLESTICK = register("light_blue_candle_candlestick", new CandleCandlestickBlock(Blocks.LIGHT_BLUE_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		YELLOW_CANDLE_CANDLESTICK = register("yellow_candle_candlestick", new CandleCandlestickBlock(Blocks.YELLOW_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		LIME_CANDLE_CANDLESTICK = register("lime_candle_candlestick", new CandleCandlestickBlock(Blocks.LIME_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		PINK_CANDLE_CANDLESTICK = register("pink_candle_candlestick", new CandleCandlestickBlock(Blocks.PINK_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		GRAY_CANDLE_CANDLESTICK = register("gray_candle_candlestick", new CandleCandlestickBlock(Blocks.GRAY_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		LIGHT_GRAY_CANDLE_CANDLESTICK = register("light_gray_candle_candlestick", new CandleCandlestickBlock(Blocks.LIGHT_GRAY_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		CYAN_CANDLE_CANDLESTICK = register("cyan_candle_candlestick", new CandleCandlestickBlock(Blocks.CYAN_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		PURPLE_CANDLE_CANDLESTICK = register("purple_candle_candlestick", new CandleCandlestickBlock(Blocks.PURPLE_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		BLUE_CANDLE_CANDLESTICK = register("blue_candle_candlestick", new CandleCandlestickBlock(Blocks.BLUE_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		BROWN_CANDLE_CANDLESTICK = register("brown_candle_candlestick", new CandleCandlestickBlock(Blocks.BROWN_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		GREEN_CANDLE_CANDLESTICK = register("green_candle_candlestick", new CandleCandlestickBlock(Blocks.GREEN_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		RED_CANDLE_CANDLESTICK = register("red_candle_candlestick", new CandleCandlestickBlock(Blocks.RED_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		BLACK_CANDLE_CANDLESTICK = register("black_candle_candlestick", new CandleCandlestickBlock(Blocks.BLACK_CANDLE, AbstractBlock.Settings.copy(CANDLE_CANDLESTICK)));
		SEA_PICKLE_CANDLESTICK = register("sea_pickle_candlestick", new SeaPickleCandlestickBlock(AbstractBlock.Settings.copy(CANDLESTICK)));
	}

	private CandlestickBlocks() {
	}

	public static final void load() {
	}

	private static final ToIntFunction<BlockState> createLightLevelFromLitBlockState(final int litLevel) {
		return (state) -> {
			return (Boolean) state.get(Properties.LIT) ? litLevel : 0;
		};
	}

	private static final Block register(final String id, final Block block) {
		return (Block) Registry.register(Registry.BLOCK, new Identifier("candlestick", id), block);
	}
}