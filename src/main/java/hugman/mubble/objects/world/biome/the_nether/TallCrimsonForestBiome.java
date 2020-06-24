package hugman.mubble.objects.world.biome.the_nether;

import com.google.common.collect.ImmutableList;
import hugman.mubble.init.world.MubbleFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class TallCrimsonForestBiome extends Biome {
	public TallCrimsonForestBiome() {
		super((new Settings())
				.configureSurfaceBuilder(SurfaceBuilder.NETHER_FOREST, SurfaceBuilder.CRIMSON_NYLIUM_CONFIG)
				.precipitation(Precipitation.NONE)
				.category(Category.NETHER)
				.depth(0.2F)
				.scale(0.4F)
				.temperature(2.0F)
				.downfall(0.0F)
				.effects((new BiomeEffects.Builder())
						.waterColor(4159204)
						.waterFogColor(329011)
						.fogColor(3343107)
						.particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.025F))
						.loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
						.moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D))
						.additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D))
						.music(MusicType.method_27283(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST))
						.build())
				.noises(ImmutableList.of(new MixedNoisePoint(0.4F, 0.0F, 0.1F, 0.0F, 0.0F)))
				.parent("crimson_forest"));
		this.addStructureFeature(DefaultBiomeFeatures.NETHER_RUINED_PORTAL);
		this.addCarver(GenerationStep.Carver.AIR, configureCarver(Carver.NETHER_CAVE, new ProbabilityConfig(0.2F)));
		this.addStructureFeature(DefaultBiomeFeatures.FORTRESS);
		this.addStructureFeature(DefaultBiomeFeatures.BASTION_REMNANT);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.SPRING_FEATURE.configure(DefaultBiomeFeatures.LAVA_SPRING_CONFIG).createDecoratedFeature(Decorator.COUNT_VERY_BIASED_RANGE.configure(new RangeDecoratorConfig(20, 8, 16, 256))));
		DefaultBiomeFeatures.addDefaultMushrooms(this);
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, Feature.SPRING_FEATURE.configure(DefaultBiomeFeatures.NETHER_SPRING_CONFIG).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(8, 4, 8, 128))));
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.NETHER_FIRE_CONFIG).createDecoratedFeature(Decorator.FIRE.configure(new CountDecoratorConfig(10))));
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, Feature.GLOWSTONE_BLOB.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.LIGHT_GEM_CHANCE.configure(new CountDecoratorConfig(10))));
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, Feature.GLOWSTONE_BLOB.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(10, 0, 0, 128))));
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NETHERRACK, Blocks.MAGMA_BLOCK.getDefaultState(), 33)).createDecoratedFeature(Decorator.MAGMA.configure(new CountDecoratorConfig(4))));
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, Feature.SPRING_FEATURE.configure(DefaultBiomeFeatures.ENCLOSED_NETHER_SPRING_CONFIG).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(16, 10, 20, 128))));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.WEEPING_VINES.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(10, 0, 0, 128))));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MubbleFeatures.TALL_HUGE_FUNGUS.configure(HugeFungusFeatureConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP.configure(new CountDecoratorConfig(8))));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.NETHER_FOREST_VEGETATION.configure(DefaultBiomeFeatures.CRIMSON_ROOTS_CONFIG).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP.configure(new CountDecoratorConfig(6))));
		DefaultBiomeFeatures.addNetherMineables(this);
		this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 2, 4));
		this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(EntityType.HOGLIN, 9, 3, 4));
		this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(EntityType.PIGLIN, 5, 3, 4));
		this.addSpawn(SpawnGroup.CREATURE, new SpawnEntry(EntityType.STRIDER, 60, 1, 2));
	}
}