package me.sixteen_.candlestick.tag;

import net.minecraft.block.Block;
import net.minecraft.tag.RequiredTagList;
import net.minecraft.tag.RequiredTagListRegistry;
import net.minecraft.tag.Tag;
import net.minecraft.util.registry.Registry;

public final class CandlestickBlockTags {

    protected static final RequiredTagList<Block> REQUIRED_TAGS;
    public static final Tag.Identified<Block> CANDLE_CANDLESTICKS;

    static {
        REQUIRED_TAGS = RequiredTagListRegistry.register(Registry.BLOCK_KEY, "tags/blocks");
        CANDLE_CANDLESTICKS = register("candle_candlesticks");
    }

    private CandlestickBlockTags() {
    }

    private static final Tag.Identified<Block> register(final String id) {
        return REQUIRED_TAGS.add(id);
    }
}