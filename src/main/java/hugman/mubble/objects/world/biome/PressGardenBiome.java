package hugman.mubble.objects.world.biome;

import com.google.common.collect.ImmutableList;

import hugman.mubble.init.MubbleBlocks;
import hugman.mubble.init.world.MubbleFeatures;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleStateProvider;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class PressGardenBiome extends Biome
{
	public PressGardenBiome()
	{
		super((new Biome.Settings()).configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.SNOW)
				.category(Biome.Category.TAIGA)
				.depth(0.2F)
				.scale(0.2F)
				.temperature(-0.6F)
				.downfall(0.4F)
				.waterColor(4020182)
				.waterFogColor(329011)
				.parent((String) null));
		this.addStructureFeature(Feature.IGLOO.configure(FeatureConfig.DEFAULT));
		this.addStructureFeature(Feature.MINESHAFT.configure(new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL)));
		this.addStructureFeature(Feature.STRONGHOLD.configure(FeatureConfig.DEFAULT));
		DefaultBiomeFeatures.addLandCarvers(this);
		DefaultBiomeFeatures.addDefaultStructures(this);
		DefaultBiomeFeatures.addDefaultLakes(this);
		DefaultBiomeFeatures.addDungeons(this);
		DefaultBiomeFeatures.addLargeFerns(this);
	    this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(MubbleFeatures.PINK_PRESS_GARDEN_TREE.configure(FeatureConfig.DEFAULT).withChance(0.1F), MubbleFeatures.PINK_PRESS_GARDEN_GROUND_BUSH.configure(FeatureConfig.DEFAULT).withChance(0.3F), MubbleFeatures.MEGA_RED_PRESS_GARDEN_TREE.configure(DefaultBiomeFeatures.DARK_OAK_TREE_CONFIG).withChance(0.6F)), MubbleFeatures.RED_PRESS_GARDEN_TREE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(50, 0.1F, 1))))));
		DefaultBiomeFeatures.addMineables(this);
		DefaultBiomeFeatures.addDefaultOres(this);
		DefaultBiomeFeatures.addDefaultDisks(this);
		DefaultBiomeFeatures.addDefaultFlowers(this);
		DefaultBiomeFeatures.addDefaultMushrooms(this);
		DefaultBiomeFeatures.addDefaultVegetation(this);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new SimpleStateProvider(MubbleBlocks.RED_PRESS_GARDEN_LEAF_PILE.getDefaultState()), new SimpleBlockPlacer()).build()).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(4))));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new SimpleStateProvider(MubbleBlocks.PINK_PRESS_GARDEN_LEAF_PILE.getDefaultState()), new SimpleBlockPlacer()).build()).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(2))));
	 	this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MubbleFeatures.BLUEBERRY_BUSH.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceDecoratorConfig(12))));
	 	this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MubbleFeatures.TOMATOES.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(1))));
	 	DefaultBiomeFeatures.addFrozenTopLayer(this);
	 	this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
	 	this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.PIG, 10, 4, 4));
	 	this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
	 	this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.COW, 8, 4, 4));
	 	this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.WOLF, 8, 4, 4));
	 	this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.RABBIT, 4, 2, 3));
	 	this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.FOX, 8, 2, 4));
	 	this.addSpawn(EntityCategory.AMBIENT, new Biome.SpawnEntry(EntityType.BAT, 10, 8, 8));
	 	this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
	 	this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
	 	this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
	 	this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
	 	this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
	 	this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.SLIME, 100, 4, 4));
	 	this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
	 	this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.WITCH, 5, 1, 1));
	}
	
	@Override
	public int getFoliageColor()
	{
		return 15594485;
	}
	
	@Override
	public int getGrassColorAt(double x, double z)
	{
		return 15594485;
	}
}