package hugman.mubble.init.data;

import hugman.mubble.Mubble;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

public class MubbleTags {
	public static class Blocks {
		public static final ITag.INamedTag<Block> FREEZABLE_TO_PACKED_ICE = tag("freezable/packed_ice");
		public static final ITag.INamedTag<Block> MELTABLE_TO_AIR = tag("meltable/air");
		public static final ITag.INamedTag<Block> MELTABLE_TO_ICE = tag("meltable/ice");
		public static final ITag.INamedTag<Block> MELTABLE_TO_WATER = tag("meltable/water");
		public static final ITag.INamedTag<Block> CLOUD_BLOCKS = tag("cloud_blocks");
		public static final ITag.INamedTag<Block> PALM_SAPLING_VALID_GROUND = tag("valid_ground/palm_sapling");

		private static ITag.INamedTag<Block> tag(String name) {
			return BlockTags.makeWrapperTag(Mubble.MOD_PREFIX + name);
		}
	}

	public static class Items {
		private static final TagRegistry<Item> collection = new TagRegistry<>();

		public static final List<ITag.INamedTag<Item>> TIMESWAP_TAGS = new ArrayList<ITag.INamedTag<Item>>();

		public static final ITag.INamedTag<Item> COINS = tag("coins");
		public static final ITag.INamedTag<Item> CROWNS = tag("crowns");
		public static final ITag.INamedTag<Item> WEIGHT_HEAVY = tag("weight/heavy");
		public static final ITag.INamedTag<Item> WEIGHT_LIGHT = tag("weight/light");
		public static final ITag.INamedTag<Item> TOAD_FEAR = tag("fear/toad");
		public static final ITag.INamedTag<Item> TOAD_FEEDING = tag("feeding/toad");
		public static final ITag.INamedTag<Item> DUCK_FEEDING = tag("feeding/duck");

		public static final ITag.INamedTag<Item> GEMS_BISMUTH = tag("gems/bismuth");
		public static final ITag.INamedTag<Item> GEMS_KYBER = tag("gems/kyber");
		public static final ITag.INamedTag<Item> GEMS_VANADIUM = tag("gems/vanadium");

		public static final ITag.INamedTag<Item> TIMESWAP_QUESTION_BLOCKS = timeswapTag("timeswap/question_blocks");
		public static final ITag.INamedTag<Item> TIMESWAP_GROUND_BLOCKS = timeswapTag("timeswap/ground_blocks");
		public static final ITag.INamedTag<Item> TIMESWAP_EMPTY_BLOCKS = timeswapTag("timeswap/empty_blocks");
		public static final ITag.INamedTag<Item> TIMESWAP_ROTATING_BLOCKS = timeswapTag("timeswap/rotating_blocks");
		public static final ITag.INamedTag<Item> TIMESWAP_BRICK_BLOCKS = timeswapTag("timeswap/brick_blocks");
		public static final ITag.INamedTag<Item> TIMESWAP_GOLDEN_BRICK_BLOCKS = timeswapTag("timeswap/golden_brick_blocks");
		public static final ITag.INamedTag<Item> TIMESWAP_HARD_BLOCKS = timeswapTag("timeswap/hard_blocks");
		public static final ITag.INamedTag<Item> TIMESWAP_ICE_BLOCKS = timeswapTag("timeswap/ice_blocks");
		public static final ITag.INamedTag<Item> TIMESWAP_NOTE_BLOCKS = timeswapTag("timeswap/note_blocks");
		public static final ITag.INamedTag<Item> TIMESWAP_SUPER_NOTE_BLOCKS = timeswapTag("timeswap/super_note_blocks");
		public static final ITag.INamedTag<Item> TIMESWAP_DOORS = timeswapTag("timeswap/doors");
		public static final ITag.INamedTag<Item> TIMESWAP_KEY_DOORS = timeswapTag("timeswap/key_doors");
		public static final ITag.INamedTag<Item> TIMESWAP_KEYS = timeswapTag("timeswap/keys");

		private static ITag.INamedTag<Item> tag(String name) {
			return ItemTags.makeWrapperTag(Mubble.MOD_PREFIX + name);
		}

		private static ITag.INamedTag<Item> timeswapTag(String name) {
			ITag.INamedTag<Item> fTag = ItemTags.makeWrapperTag(Mubble.MOD_PREFIX + name);
			TIMESWAP_TAGS.add(fTag);
			return fTag;
		}
	}

	public static class EntityTypes {
		public static final ITag.INamedTag<EntityType<?>> CAN_WEAR_HELMET = tag("can_wear_helmet");

		private static ITag.INamedTag<EntityType<?>> tag(String name) {
			return EntityTypeTags.func_232896_a_(Mubble.MOD_PREFIX + name);
		}
	}

	public static class Fluids {
		public static final ITag.INamedTag<Fluid> FREEZABLE_TO_ICE = tag("freezable/ice");

		private static ITag.INamedTag<Fluid> tag(String name) {
			return FluidTags.makeWrapperTag(Mubble.MOD_PREFIX + name);
		}
	}
}