package com.hugman.mubble.init.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.hugman.dawn.api.util.DefaultBlockGetter;
import com.hugman.mubble.Mubble;
import com.hugman.mubble.init.MubbleBlockPack;
import com.hugman.mubble.object.world.gen.feature.HugeNetherMushroomFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.*;

import java.util.OptionalInt;
import java.util.Set;

public class MubbleConfiguredFeatures {
	private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Mubble.MOD_DATA.id(name), feature);
	}

	public static final ConfiguredFeature<?, ?> ORE_BLUNITE = register("ore_blunite", Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MubbleBlockPack.BLUNITE_BLOCKS.getBlock(DefaultBlockGetter.CUBE).getDefaultState(), 33)).method_30377(80).spreadHorizontally().repeat(10));
	public static final ConfiguredFeature<?, ?> ORE_CARBONITE = register("ore_carbonite", Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MubbleBlockPack.CARBONITE_BLOCKS.getBlock(DefaultBlockGetter.CUBE).getDefaultState(), 33)).method_30377(80).spreadHorizontally().repeat(10));
	public static final ConfiguredFeature<?, ?> ORE_VANADIUM = register("ore_vanadium", Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MubbleBlockPack.VANADIUM_ORE.getDefaultState(), 6)).method_30377(80).spreadHorizontally().repeat(10));

	public static final ConfiguredFeature<TreeFeatureConfig, ?> AUTUMN_OAK = register("autumn_oak", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.OAK_LOG), new SimpleBlockStateProvider(States.AUTUMN_OAK_LEAVES), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> AUTUMN_OAK_BEES_002 = register("autumn_oak_bees_002", Feature.TREE.configure(AUTUMN_OAK.getConfig().setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.REGULAR_BEEHIVES_TREES))));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_AUTUMN_OAK = register("fancy_autumn_oak", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.OAK_LOG), new SimpleBlockStateProvider(States.AUTUMN_OAK_LEAVES), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4), new LargeOakTrunkPlacer(3, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_AUTUMN_OAK_BEES_002 = register("fancy_autumn_oak_bees_002", Feature.TREE.configure(FANCY_AUTUMN_OAK.getConfig().setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.REGULAR_BEEHIVES_TREES))));
	public static final ConfiguredFeature<?, ?> PATCH_AUTUMN_OAK_LEAF_PILE = register("patch_autumn_oak_leaf_pile", Configs.patch(States.AUTUMN_OAK_LEAF_PILE, 32));
	public static final ConfiguredFeature<?, ?> PATCH_AUTUMN_OAK_LEAF_PILE_NORMAL = register("patch_autumn_oak_leaf_pile_normal", PATCH_AUTUMN_OAK_LEAF_PILE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(5));

	public static final ConfiguredFeature<TreeFeatureConfig, ?> AUTUMN_BIRCH = register("autumn_birch", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.BIRCH_LOG), new SimpleBlockStateProvider(States.AUTUMN_BIRCH_LEAVES), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> AUTUMN_BIRCH_BEES_002 = register("autumn_birch_bees_002", Feature.TREE.configure(AUTUMN_BIRCH.getConfig().setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.REGULAR_BEEHIVES_TREES))));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_AUTUMN_BIRCH = register("fancy_autumn_birch", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.BIRCH_LOG), new SimpleBlockStateProvider(States.AUTUMN_BIRCH_LEAVES), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4), new LargeOakTrunkPlacer(3, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_AUTUMN_BIRCH_BEES_002 = register("fancy_autumn_birch_bees_002", Feature.TREE.configure(FANCY_AUTUMN_BIRCH.getConfig().setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.REGULAR_BEEHIVES_TREES))));
	public static final ConfiguredFeature<?, ?> PATCH_AUTUMN_BIRCH_LEAF_PILE = register("patch_autumn_birch_leaf_pile", Configs.patch(States.AUTUMN_BIRCH_LEAF_PILE, 32));
	public static final ConfiguredFeature<?, ?> PATCH_AUTUMN_BIRCH_LEAF_PILE_NORMAL = register("patch_autumn_birch_leaf_pile_normal", PATCH_AUTUMN_BIRCH_LEAF_PILE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(5));

	public static final ConfiguredFeature<?, ?> PUMPKIN_PASTURES_TREES = register("pumpkin_pastures_trees", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(AUTUMN_OAK_BEES_002.withChance(0.2F), FANCY_AUTUMN_BIRCH_BEES_002.withChance(0.1F)), AUTUMN_BIRCH_BEES_002)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1))));

	public static final ConfiguredFeature<TreeFeatureConfig, ?> PINK_CHERRY_OAK = register("pink_cherry_oak", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.CHERRY_OAK_LOG), new SimpleBlockStateProvider(States.PINK_CHERRY_OAK_LEAVES), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(5, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> PINK_CHERRY_OAK_BEES_005 = register("pink_cherry_oak_bees_005", Feature.TREE.configure(PINK_CHERRY_OAK.getConfig().setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.MORE_BEEHIVES_TREES))));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_PINK_CHERRY_OAK = register("fancy_pink_cherry_oak", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.CHERRY_OAK_LOG), new SimpleBlockStateProvider(States.PINK_CHERRY_OAK_LEAVES), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4), new LargeOakTrunkPlacer(4, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_PINK_CHERRY_OAK_BEES_005 = register("fancy_pink_cherry_oak_bees_005", Feature.TREE.configure(FANCY_PINK_CHERRY_OAK.getConfig().setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.MORE_BEEHIVES_TREES))));
	public static final ConfiguredFeature<?, ?> PATCH_PINK_CHERRY_OAK_LEAF_PILE = register("patch_pink_cherry_oak_leaf_pile", Configs.patch(States.PINK_CHERRY_OAK_LEAF_PILE, 32));
	public static final ConfiguredFeature<?, ?> PATCH_PINK_CHERRY_OAK_LEAF_PILE_NORMAL = register("patch_pink_cherry_oak_leaf_pile_normal", PATCH_PINK_CHERRY_OAK_LEAF_PILE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(5));

	public static final ConfiguredFeature<TreeFeatureConfig, ?> WHITE_CHERRY_OAK = register("white_cherry_oak", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.CHERRY_OAK_LOG), new SimpleBlockStateProvider(States.WHITE_CHERRY_OAK_LEAVES), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(5, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> WHITE_CHERRY_OAK_BEES_005 = register("white_cherry_oak_bees_005", Feature.TREE.configure(WHITE_CHERRY_OAK.getConfig().setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.MORE_BEEHIVES_TREES))));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_WHITE_CHERRY_OAK = register("fancy_white_cherry_oak", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.CHERRY_OAK_LOG), new SimpleBlockStateProvider(States.WHITE_CHERRY_OAK_LEAVES), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4), new LargeOakTrunkPlacer(4, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_WHITE_CHERRY_OAK_BEES_005 = register("fancy_white_cherry_oak_bees_005", Feature.TREE.configure(FANCY_WHITE_CHERRY_OAK.getConfig().setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.MORE_BEEHIVES_TREES))));
	public static final ConfiguredFeature<?, ?> PATCH_WHITE_CHERRY_OAK_LEAF_PILE = register("patch_white_cherry_oak_leaf_pile", Configs.patch(States.WHITE_CHERRY_OAK_LEAF_PILE, 32));
	public static final ConfiguredFeature<?, ?> PATCH_WHITE_CHERRY_OAK_LEAF_PILE_NORMAL = register("patch_white_cherry_oak_leaf_pile_normal", PATCH_WHITE_CHERRY_OAK_LEAF_PILE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(5));

	public static final ConfiguredFeature<?, ?> PINK_CHERRY_OAK_FOREST_TREES = register("pink_cherry_oak_forest_trees", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(WHITE_CHERRY_OAK.withChance(0.2F), FANCY_PINK_CHERRY_OAK.withChance(0.1F)), PINK_CHERRY_OAK)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> WHITE_CHERRY_OAK_FOREST_TREES = register("white_cherry_oak_forest_trees", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(PINK_CHERRY_OAK.withChance(0.2F), FANCY_WHITE_CHERRY_OAK.withChance(0.1F)), WHITE_CHERRY_OAK)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1))));

	public static final ConfiguredFeature<TreeFeatureConfig, ?> PALM = register("palm", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.PALM_LOG), new SimpleBlockStateProvider(States.PALM_LEAVES), new AcaciaFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0)), new ForkingTrunkPlacer(16, 2, 2), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> PALM_TREES = register("palm_trees", PALM.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.12F, 1))));

	public static final ConfiguredFeature<?, ?> TALL_CRIMSON_FUNGI = register("tall_crimson_fungi", MubbleFeatures.TALL_HUGE_FUNGI.configure(HugeFungusFeatureConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(8))));
	public static final ConfiguredFeature<?, ?> TALL_WARPED_FUNGI = register("tall_warped_fungi", MubbleFeatures.TALL_HUGE_FUNGI.configure(HugeFungusFeatureConfig.WARPED_FUNGUS_NOT_PLANTED_CONFIG).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(8))));

	public static final ConfiguredFeature<?, ?> PATCH_BLUE_MUSHROOM = register("patch_blue_mushroom", Configs.patch(States.BLUE_MUSHROOM, 64));
	public static final ConfiguredFeature<?, ?> PATCH_CYAN_MUSHROOM = register("patch_cyan_mushroom", Configs.patch(States.CYAN_MUSHROOM, 64));
	public static final ConfiguredFeature<?, ?> PATCH_YELLOW_MUSHROOM = register("patch_yellow_mushroom", Configs.patch(States.YELLOW_MUSHROOM, 64));
	public static final ConfiguredFeature<?, ?> PATCH_ORANGE_MUSHROOM = register("patch_orange_mushroom", Configs.patch(States.ORANGE_MUSHROOM, 64));
	public static final ConfiguredFeature<?, ?> PATCH_PINK_MUSHROOM = register("patch_pink_mushroom", Configs.patch(States.PINK_MUSHROOM, 64));
	public static final ConfiguredFeature<?, ?> PATCH_MAGENTA_MUSHROOM = register("patch_magenta_mushroom", Configs.patch(States.MAGENTA_MUSHROOM, 64));
	public static final ConfiguredFeature<?, ?> PATCH_WHITE_MUSHROOM = register("patch_white_mushroom", Configs.patch(States.WHITE_MUSHROOM, 64));
	public static final ConfiguredFeature<?, ?> PATCH_LIGHT_GRAY_MUSHROOM = register("patch_light_gray_mushroom", Configs.patch(States.LIGHT_GRAY_MUSHROOM, 64));
	public static final ConfiguredFeature<?, ?> PATCH_GRAY_MUSHROOM = register("patch_gray_mushroom", Configs.patch(States.GRAY_MUSHROOM, 64));
	public static final ConfiguredFeature<?, ?> PATCH_BLACK_MUSHROOM = register("patch_black_mushroom", Configs.patch(States.BLACK_MUSHROOM, 64));

	public static final ConfiguredFeature<?, ?> PATCH_BLUE_MUSHROOM_NORMAL = register("patch_blue_mushroom_normal", PATCH_BLUE_MUSHROOM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(4));
	public static final ConfiguredFeature<?, ?> PATCH_CYAN_MUSHROOM_NORMAL = register("patch_cyan_mushroom_normal", PATCH_CYAN_MUSHROOM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(4));
	public static final ConfiguredFeature<?, ?> PATCH_YELLOW_MUSHROOM_NORMAL = register("patch_yellow_mushroom_normal", PATCH_YELLOW_MUSHROOM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(4));
	public static final ConfiguredFeature<?, ?> PATCH_ORANGE_MUSHROOM_NORMAL = register("patch_orange_mushroom_normal", PATCH_ORANGE_MUSHROOM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(4));
	public static final ConfiguredFeature<?, ?> PATCH_PINK_MUSHROOM_NORMAL = register("patch_pink_mushroom_normal", PATCH_PINK_MUSHROOM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(4));
	public static final ConfiguredFeature<?, ?> PATCH_MAGENTA_MUSHROOM_NORMAL = register("patch_magenta_mushroom_normal", PATCH_MAGENTA_MUSHROOM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(4));
	public static final ConfiguredFeature<?, ?> PATCH_WHITE_MUSHROOM_NORMAL = register("patch_white_mushroom_normal", PATCH_WHITE_MUSHROOM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(4));
	public static final ConfiguredFeature<?, ?> PATCH_LIGHT_GRAY_MUSHROOM_NORMAL = register("patch_light_gray_mushroom_normal", PATCH_LIGHT_GRAY_MUSHROOM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(4));
	public static final ConfiguredFeature<?, ?> PATCH_GRAY_MUSHROOM_NORMAL = register("patch_gray_mushroom_normal", PATCH_GRAY_MUSHROOM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(4));
	public static final ConfiguredFeature<?, ?> PATCH_BLACK_MUSHROOM_NORMAL = register("patch_black_mushroom_normal", PATCH_BLACK_MUSHROOM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(4));

	public static final ConfiguredFeature<?, ?> PATCH_BLUE_MUSHROOM_NETHER = register("patch_blue_mushroom_nether", PATCH_BLUE_MUSHROOM.method_30377(128)).applyChance(2);
	public static final ConfiguredFeature<?, ?> PATCH_CYAN_MUSHROOM_NETHER = register("patch_cyan_mushroom_nether", PATCH_CYAN_MUSHROOM.method_30377(128)).applyChance(2);
	public static final ConfiguredFeature<?, ?> PATCH_YELLOW_MUSHROOM_NETHER = register("patch_yellow_mushroom_nether", PATCH_YELLOW_MUSHROOM.method_30377(128)).applyChance(2);
	public static final ConfiguredFeature<?, ?> PATCH_ORANGE_MUSHROOM_NETHER = register("patch_orange_mushroom_nether", PATCH_ORANGE_MUSHROOM.method_30377(128)).applyChance(2);
	public static final ConfiguredFeature<?, ?> PATCH_PINK_MUSHROOM_NETHER = register("patch_pink_mushroom_nether", PATCH_PINK_MUSHROOM.method_30377(128)).applyChance(2);
	public static final ConfiguredFeature<?, ?> PATCH_MAGENTA_MUSHROOM_NETHER = register("patch_magenta_mushroom_nether", PATCH_MAGENTA_MUSHROOM.method_30377(128)).applyChance(2);
	public static final ConfiguredFeature<?, ?> PATCH_WHITE_MUSHROOM_NETHER = register("patch_white_mushroom_nether", PATCH_WHITE_MUSHROOM.method_30377(128)).applyChance(2);
	public static final ConfiguredFeature<?, ?> PATCH_LIGHT_GRAY_MUSHROOM_NETHER = register("patch_light_gray_mushroom_nether", PATCH_LIGHT_GRAY_MUSHROOM.method_30377(128)).applyChance(2);
	public static final ConfiguredFeature<?, ?> PATCH_GRAY_MUSHROOM_NETHER = register("patch_gray_mushroom_nether", PATCH_GRAY_MUSHROOM.method_30377(128)).applyChance(2);
	public static final ConfiguredFeature<?, ?> PATCH_BLACK_MUSHROOM_NETHER = register("patch_black_mushroom_nether", PATCH_BLACK_MUSHROOM.method_30377(128)).applyChance(2);

	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLUE = register("huge_nether_mushroom_blue", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLUE));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_CYAN = register("huge_nether_mushroom_cyan", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_CYAN));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_PINK = register("huge_nether_mushroom_pink", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_PINK));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_YELLOW = register("huge_nether_mushroom_yellow", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_YELLOW));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BROWN = register("huge_nether_mushroom_brown", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BROWN));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_WHITE = register("huge_nether_mushroom_white", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_WHITE));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_LIGHT_GRAY = register("huge_nether_mushroom_light_gray", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_GRAY = register("huge_nether_mushroom_gray", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_GRAY));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLACK = register("huge_nether_mushroom_black", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLACK));

	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLUE_UPSIDE = register("huge_nether_mushroom_blue_upside", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLUE.setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_CYAN_UPSIDE = register("huge_nether_mushroom_cyan_upside", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_CYAN.setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_PINK_UPSIDE = register("huge_nether_mushroom_pink_upside", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_PINK.setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE = register("huge_nether_mushroom_yellow_upside", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_YELLOW.setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BROWN_UPSIDE = register("huge_nether_mushroom_brown_upside", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BROWN.setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_WHITE_UPSIDE = register("huge_nether_mushroom_white_upside", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_WHITE.setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE = register("huge_nether_mushroom_light_gray_upside", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY.setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_GRAY_UPSIDE = register("huge_nether_mushroom_gray_upside", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_GRAY.setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLACK_UPSIDE = register("huge_nether_mushroom_black_upside", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLACK.setUpsideDown()));

	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLUE_FLAT = register("huge_nether_mushroom_blue_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLUE.setFlatHat()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_CYAN_FLAT = register("huge_nether_mushroom_cyan_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_CYAN.setFlatHat().setHatBaseSize(3)));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_PINK_FLAT = register("huge_nether_mushroom_pink_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_PINK.setFlatHat()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_YELLOW_FLAT = register("huge_nether_mushroom_yellow_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_YELLOW.setFlatHat()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BROWN_FLAT = register("huge_nether_mushroom_brown_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BROWN.setFlatHat()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_WHITE_FLAT = register("huge_nether_mushroom_white_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_WHITE.setFlatHat()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_LIGHT_GRAY_FLAT = register("huge_nether_mushroom_light_gray_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY.setFlatHat()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_GRAY_FLAT = register("huge_nether_mushroom_gray_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_GRAY.setFlatHat()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLACK_FLAT = register("huge_nether_mushroom_black_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLACK.setFlatHat()));

	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLUE_UPSIDE_FLAT = register("huge_nether_mushroom_blue_upside_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLUE.setFlatHat().setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_CYAN_UPSIDE_FLAT = register("huge_nether_mushroom_cyan_upside_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_CYAN.setFlatHat().setUpsideDown().setHatBaseSize(3)));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_PINK_UPSIDE_FLAT = register("huge_nether_mushroom_pink_upside_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_PINK.setFlatHat().setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE_FLAT = register("huge_nether_mushroom_yellow_upside_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_YELLOW.setFlatHat().setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BROWN_UPSIDE_FLAT = register("huge_nether_mushroom_brown_upside_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BROWN.setFlatHat().setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_WHITE_UPSIDE_FLAT = register("huge_nether_mushroom_white_upside_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_WHITE.setFlatHat().setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE_FLAT = register("huge_nether_mushroom_light_gray_upside_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY.setFlatHat().setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_GRAY_UPSIDE_FLAT = register("huge_nether_mushroom_gray_upside_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_GRAY.setFlatHat().setUpsideDown()));
	public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLACK_UPSIDE_FLAT = register("huge_nether_mushroom_black_upside_flat", MubbleFeatures.HUGE_NETHER_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLACK.setFlatHat().setUpsideDown()));

	public static final ConfiguredFeature<?, ?> AMARANTH_FUNGI = register("amaranth_fungi", Feature.HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(States.AMARANTH_DYLIUM, States.DARK_AMARANTH_STEM, States.AMARANTH_WART_BLOCK, States.COBWEB, false)).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(8))));
	public static final ConfiguredFeature<HugeFungusFeatureConfig, ?> AMARANTH_FUNGI_PLANTED = register("amaranth_fungi_planted", Feature.HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(States.AMARANTH_DYLIUM, States.DARK_AMARANTH_STEM, States.AMARANTH_WART_BLOCK, States.COBWEB, true)));
	public static final ConfiguredFeature<?, ?> AMARANTH_FOREST_VEGETATION = register("amaranth_forest_vegetation", Feature.NETHER_FOREST_VEGETATION.configure(ConfiguredFeatures.Configs.WARPED_ROOTS_CONFIG).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(6))));

	public static final ConfiguredFeature<?, ?> ENDER_BOULDER = register("ender_boulder", MubbleFeatures.ENDER_BOULDER.configure(new SingleStateFeatureConfig(Blocks.OBSIDIAN.getDefaultState())).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).repeatRandomly(2));

	public static final ConfiguredFeature<TreeFeatureConfig, ?> RED_PRESS_GARDEN_TREE = register("red_press_garden_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.PRESS_GARDEN_LOG), new SimpleBlockStateProvider(States.RED_PRESS_GARDEN_LEAVES), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_RED_PRESS_GARDEN_TREE = register("fancy_red_press_garden_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.PRESS_GARDEN_LOG), new SimpleBlockStateProvider(States.RED_PRESS_GARDEN_LEAVES), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4), new LargeOakTrunkPlacer(3, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> MEGA_RED_PRESS_GARDEN_TREE = register("mega_red_press_garden_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.PRESS_GARDEN_LOG), new SimpleBlockStateProvider(States.RED_PRESS_GARDEN_LEAVES), new JungleFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 2), new MegaJungleTrunkPlacer(30, 2, 20), new TwoLayersFeatureSize(1, 1, 2))).build()));
	public static final ConfiguredFeature<?, ?> PATCH_RED_PRESS_GARDEN_LEAF_PILE = register("patch_red_press_garden_leaf_pile", Configs.patch(States.RED_PRESS_GARDEN_LEAF_PILE, 32));
	public static final ConfiguredFeature<?, ?> PATCH_RED_PRESS_GARDEN_LEAF_PILE_NORMAL = register("patch_red_press_garden_leaf_pile_normal", PATCH_RED_PRESS_GARDEN_LEAF_PILE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(5));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> PINK_PRESS_GARDEN_TREE = register("pink_press_garden_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.PRESS_GARDEN_LOG), new SimpleBlockStateProvider(States.PINK_PRESS_GARDEN_LEAVES), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_PINK_PRESS_GARDEN_TREE = register("fancy_pink_press_garden_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.PRESS_GARDEN_LOG), new SimpleBlockStateProvider(States.PINK_PRESS_GARDEN_LEAVES), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4), new LargeOakTrunkPlacer(3, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> MEGA_PINK_PRESS_GARDEN_TREE = register("mega_press_garden_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.PRESS_GARDEN_LOG), new SimpleBlockStateProvider(States.PINK_PRESS_GARDEN_LEAVES), new JungleFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 2), new MegaJungleTrunkPlacer(30, 2, 20), new TwoLayersFeatureSize(1, 1, 2))).build()));
	public static final ConfiguredFeature<?, ?> PATCH_PINK_PRESS_GARDEN_LEAF_PILE = register("patch_pink_press_garden_leaf_pile", Configs.patch(States.RED_PRESS_GARDEN_LEAF_PILE, 32));
	public static final ConfiguredFeature<?, ?> PATCH_PINK_PRESS_GARDEN_LEAF_PILE_NORMAL = register("patch_pink_press_garden_leaf_pile_normal", PATCH_PINK_PRESS_GARDEN_LEAF_PILE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(5));
	public static final ConfiguredFeature<?, ?> PINK_PRESS_GARDEN_BUSH = register("pink_press_garden_bush", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.PRESS_GARDEN_LOG), new SimpleBlockStateProvider(States.PINK_PRESS_GARDEN_LEAVES), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 2), new StraightTrunkPlacer(1, 0, 0), new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));

	public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH = register("patch_blueberry_bush", Configs.patch(States.BLUEBERRY_BUSH, 64, ImmutableSet.of(States.GRASS_BLOCK.getBlock())));
	public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH_SPARSE = register("patch_blueberry_bush_sparse", PATCH_BLUEBERRY_BUSH.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE));

	public static final ConfiguredFeature<TreeFeatureConfig, ?> SCARLET_TREE = register("scarlet_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.SCARLET_LOG), new SimpleBlockStateProvider(States.SCARLET_LEAVES), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_SCARLET_TREE = register("fancy_scarlet_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.SCARLET_LOG), new SimpleBlockStateProvider(States.SCARLET_LEAVES), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4), new LargeOakTrunkPlacer(3, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> HUGE_SCARLET_TREE = register("huge_scarlet_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.SCARLET_LOG), new SimpleBlockStateProvider(States.SCARLET_LEAVES), new DarkOakFoliagePlacer(UniformIntDistribution.of(0), UniformIntDistribution.of(0)), new DarkOakTrunkPlacer(6, 2, 1), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).maxWaterDepth(Integer.MAX_VALUE).heightmap(Heightmap.Type.MOTION_BLOCKING).ignoreVines().build()));
	public static final ConfiguredFeature<?, ?> PATCH_SCARLET_LEAF_PILE = register("patch_scarlet_leaf_pile", Configs.patch(States.SCARLET_LEAF_PILE, 32));
	public static final ConfiguredFeature<?, ?> PATCH_SCARLET_LEAF_PILE_NORMAL = register("patch_scarlet_leaf_pile_normal", PATCH_SCARLET_LEAF_PILE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(5));
	public static final ConfiguredFeature<?, ?> SCARLET_BUSH = register("scarlet_bush", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.SCARLET_LOG), new SimpleBlockStateProvider(States.SCARLET_LEAVES), new BushFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 2), new StraightTrunkPlacer(1, 0, 0), new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));

	public static final ConfiguredFeature<?, ?> PATCH_SCARLET_MUSHROOM = register("patch_scarlet_mushroom", Configs.patch(States.SCARLET_MUSHROOM, 64));
	public static final ConfiguredFeature<?, ?> PATCH_SCARLET_MUSHROOM_NORMAL = register("patch_scarlet_mushroom_normal", PATCH_SCARLET_MUSHROOM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(4));
	public static final ConfiguredFeature<?, ?> PATCH_SCARLET_ORCHID = register("patch_scarlet_orchid", Feature.FLOWER.configure(Configs.patch(States.SCARLET_ORCHID, 64).getConfig()).decorate(ConfiguredFeatures.Decorators.SPREAD_32_ABOVE).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).repeat(4));

	public static final class Configs {
		protected static final HugeNetherMushroomFeatureConfig HUGE_NETHER_MUSHROOM_BLUE = new HugeNetherMushroomFeatureConfig(13, 8, States.BLUE_MUSHROOM_BLOCK, 3, 3, States.SHROOMLIGHT, 0.1F, 0.05F);
		protected static final HugeNetherMushroomFeatureConfig HUGE_NETHER_MUSHROOM_CYAN = new HugeNetherMushroomFeatureConfig(4, 3, States.CYAN_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT.getDefaultState(), 0.05F, 0.1F);
		protected static final HugeNetherMushroomFeatureConfig HUGE_NETHER_MUSHROOM_PINK = new HugeNetherMushroomFeatureConfig(5, 4, States.PINK_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT.getDefaultState(), 0.05F, 0.1F);
		protected static final HugeNetherMushroomFeatureConfig HUGE_NETHER_MUSHROOM_YELLOW = new HugeNetherMushroomFeatureConfig(4, 3, States.YELLOW_MUSHROOM_BLOCK, 3, 0, Blocks.SHROOMLIGHT.getDefaultState(), 0.1F, 0.0F);
		protected static final HugeNetherMushroomFeatureConfig HUGE_NETHER_MUSHROOM_BROWN = new HugeNetherMushroomFeatureConfig(4, 3, States.BROWN_MUSHROOM_BLOCK, 3, 0, Blocks.SHROOMLIGHT.getDefaultState(), 0.1F, 0.0F);
		protected static final HugeNetherMushroomFeatureConfig HUGE_NETHER_MUSHROOM_WHITE = new HugeNetherMushroomFeatureConfig(6, 2, States.WHITE_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT.getDefaultState(), 0.05F, 0.0F);
		protected static final HugeNetherMushroomFeatureConfig HUGE_NETHER_MUSHROOM_LIGHT_GRAY = new HugeNetherMushroomFeatureConfig(4, 7, States.LIGHT_GRAY_MUSHROOM_BLOCK, 3, 1, Blocks.SHROOMLIGHT.getDefaultState(), 0.05F, 0.0F);
		protected static final HugeNetherMushroomFeatureConfig HUGE_NETHER_MUSHROOM_GRAY = new HugeNetherMushroomFeatureConfig(4, 7, States.GRAY_MUSHROOM_BLOCK, 3, 1, Blocks.SHROOMLIGHT.getDefaultState(), 0.01F, 0.0F);
		protected static final HugeNetherMushroomFeatureConfig HUGE_NETHER_MUSHROOM_BLACK = new HugeNetherMushroomFeatureConfig(6, 2, States.BLACK_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT.getDefaultState(), 0.01F, 0.0F);

		public static final BlockPileFeatureConfig AMARANTH_ROOTS = new BlockPileFeatureConfig((new WeightedBlockStateProvider()).addState(MubbleBlockPack.AMARANTH_ROOTS.getDefaultState(), 87).addState(MubbleBlockPack.DARK_AMARANTH_WOOD.getFungus().getDefaultState(), 11));

		protected final static ConfiguredFeature<RandomPatchFeatureConfig, ?> patch(BlockState blockState, int tries) {
			return Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), SimpleBlockPlacer.INSTANCE)).tries(tries).cannotProject().build());
		}

		protected final static ConfiguredFeature<RandomPatchFeatureConfig, ?> patch(BlockState blockState, int tries, Set<Block> whitelist) {
			return Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(blockState), SimpleBlockPlacer.INSTANCE)).tries(tries).whitelist(whitelist).cannotProject().build());
		}
	}

	public static final class States {
		protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();

		protected static final BlockState OAK_LOG = Blocks.OAK_LOG.getDefaultState();
		protected static final BlockState AUTUMN_OAK_LEAVES = MubbleBlockPack.AUTUMN_OAK_LEAVES.getLeaves().getDefaultState();
		protected static final BlockState AUTUMN_OAK_LEAF_PILE = MubbleBlockPack.AUTUMN_OAK_LEAVES.getLeafPile().getDefaultState();

		protected static final BlockState BIRCH_LOG = Blocks.BIRCH_LOG.getDefaultState();
		protected static final BlockState AUTUMN_BIRCH_LEAVES = MubbleBlockPack.AUTUMN_BIRCH_LEAVES.getLeaves().getDefaultState();
		protected static final BlockState AUTUMN_BIRCH_LEAF_PILE = MubbleBlockPack.AUTUMN_BIRCH_LEAVES.getLeafPile().getDefaultState();

		protected static final BlockState CHERRY_OAK_LOG = MubbleBlockPack.CHERRY_OAK_WOOD.getLog().getDefaultState();
		protected static final BlockState PINK_CHERRY_OAK_LEAVES = MubbleBlockPack.PINK_CHERRY_OAK_LEAVES.getLeaves().getDefaultState();
		protected static final BlockState PINK_CHERRY_OAK_LEAF_PILE = MubbleBlockPack.PINK_CHERRY_OAK_LEAVES.getLeafPile().getDefaultState();
		protected static final BlockState WHITE_CHERRY_OAK_LEAVES = MubbleBlockPack.WHITE_CHERRY_OAK_LEAVES.getLeaves().getDefaultState();
		protected static final BlockState WHITE_CHERRY_OAK_LEAF_PILE = MubbleBlockPack.WHITE_CHERRY_OAK_LEAVES.getLeafPile().getDefaultState();

		protected static final BlockState PALM_LOG = MubbleBlockPack.PALM_WOOD.getLog().getDefaultState();
		protected static final BlockState PALM_LEAVES = MubbleBlockPack.PALM_WOOD.getLeaves().getDefaultState();

		protected static final BlockState PRESS_GARDEN_LOG = MubbleBlockPack.PRESS_GARDEN_WOOD.getLog().getDefaultState();
		protected static final BlockState RED_PRESS_GARDEN_LEAVES = MubbleBlockPack.RED_PRESS_GARDEN_LEAVES.getLeaves().getDefaultState();
		protected static final BlockState RED_PRESS_GARDEN_LEAF_PILE = MubbleBlockPack.RED_PRESS_GARDEN_LEAVES.getLeafPile().getDefaultState();
		protected static final BlockState PINK_PRESS_GARDEN_LEAVES = MubbleBlockPack.PINK_PRESS_GARDEN_LEAVES.getLeaves().getDefaultState();
		protected static final BlockState PINK_PRESS_GARDEN_LEAF_PILE = MubbleBlockPack.PINK_PRESS_GARDEN_LEAVES.getLeafPile().getDefaultState();

		protected static final BlockState BLUEBERRY_BUSH = MubbleBlockPack.BLUEBERRY_BUSH.getDefaultState();

		protected static final BlockState AMARANTH_DYLIUM = MubbleBlockPack.AMARANTH_DYLIUM.getDefaultState();
		protected static final BlockState DARK_AMARANTH_STEM = MubbleBlockPack.DARK_AMARANTH_WOOD.getStem().getDefaultState();
		protected static final BlockState AMARANTH_WART_BLOCK = MubbleBlockPack.AMARANTH_WART_BLOCK.getDefaultState();
		protected static final BlockState COBWEB = Blocks.COBWEB.getDefaultState();

		protected static final BlockState SCARLET_LOG = MubbleBlockPack.SCARLET_WOOD.getLog().getDefaultState();
		protected static final BlockState SCARLET_LEAVES = MubbleBlockPack.SCARLET_WOOD.getLeaves().getDefaultState();
		protected static final BlockState SCARLET_LEAF_PILE = MubbleBlockPack.SCARLET_WOOD.getLeafPile().getDefaultState();
		protected static final BlockState SCARLET_ORCHID = MubbleBlockPack.SCARLET_ORCHID.getPlant().getDefaultState();

		protected static final BlockState BLUE_MUSHROOM = MubbleBlockPack.BLUE_MUSHROOM.getDefaultState();
		protected static final BlockState CYAN_MUSHROOM = MubbleBlockPack.CYAN_MUSHROOM.getDefaultState();
		protected static final BlockState YELLOW_MUSHROOM = MubbleBlockPack.YELLOW_MUSHROOM.getDefaultState();
		protected static final BlockState ORANGE_MUSHROOM = MubbleBlockPack.ORANGE_MUSHROOM.getDefaultState();
		protected static final BlockState PINK_MUSHROOM = MubbleBlockPack.PINK_MUSHROOM.getDefaultState();
		protected static final BlockState MAGENTA_MUSHROOM = MubbleBlockPack.MAGENTA_MUSHROOM.getDefaultState();
		protected static final BlockState WHITE_MUSHROOM = MubbleBlockPack.WHITE_MUSHROOM.getDefaultState();
		protected static final BlockState LIGHT_GRAY_MUSHROOM = MubbleBlockPack.LIGHT_GRAY_MUSHROOM.getDefaultState();
		protected static final BlockState GRAY_MUSHROOM = MubbleBlockPack.GRAY_MUSHROOM.getDefaultState();
		protected static final BlockState BLACK_MUSHROOM = MubbleBlockPack.BLACK_MUSHROOM.getDefaultState();

		protected static final BlockState BLUE_MUSHROOM_BLOCK = MubbleBlockPack.BLUE_MUSHROOM_BLOCK.getDefaultState();
		protected static final BlockState CYAN_MUSHROOM_BLOCK = MubbleBlockPack.CYAN_MUSHROOM_BLOCK.getDefaultState();
		protected static final BlockState YELLOW_MUSHROOM_BLOCK = MubbleBlockPack.YELLOW_MUSHROOM_BLOCK.getDefaultState();
		protected static final BlockState BROWN_MUSHROOM_BLOCK = Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState();
		protected static final BlockState ORANGE_MUSHROOM_BLOCK = MubbleBlockPack.ORANGE_MUSHROOM_BLOCK.getDefaultState();
		protected static final BlockState PINK_MUSHROOM_BLOCK = MubbleBlockPack.PINK_MUSHROOM_BLOCK.getDefaultState();
		protected static final BlockState MAGENTA_MUSHROOM_BLOCK = MubbleBlockPack.MAGENTA_MUSHROOM_BLOCK.getDefaultState();
		protected static final BlockState WHITE_MUSHROOM_BLOCK = MubbleBlockPack.WHITE_MUSHROOM_BLOCK.getDefaultState();
		protected static final BlockState LIGHT_GRAY_MUSHROOM_BLOCK = MubbleBlockPack.LIGHT_GRAY_MUSHROOM_BLOCK.getDefaultState();
		protected static final BlockState GRAY_MUSHROOM_BLOCK = MubbleBlockPack.GRAY_MUSHROOM_BLOCK.getDefaultState();
		protected static final BlockState BLACK_MUSHROOM_BLOCK = MubbleBlockPack.BLACK_MUSHROOM_BLOCK.getDefaultState();

		protected static final BlockState SHROOMLIGHT = Blocks.SHROOMLIGHT.getDefaultState();

		protected static final BlockState SCARLET_MUSHROOM = MubbleBlockPack.SCARLET_MUSHROOM.getPlant().getDefaultState();
	}
}