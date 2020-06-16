package hugman.mubble.objects.world.sapling_generator;

import hugman.mubble.init.world.MubbleFeatureConfigs;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class WhiteCherryOakSaplingGenerator extends SaplingGenerator
{
	@Override
	protected ConfiguredFeature<BranchedTreeFeatureConfig, ?> createTreeFeature(Random random, boolean canHaveBeeHive)
	{
		return random.nextInt(10) == 0 ? Feature.FANCY_TREE.configure(canHaveBeeHive ? MubbleFeatureConfigs.FANCY_WHITE_CHERRY_OAK_TREE_B1_CONFIG : MubbleFeatureConfigs.FANCY_WHITE_CHERRY_OAK_TREE_CONFIG) : Feature.NORMAL_TREE.configure(canHaveBeeHive ? MubbleFeatureConfigs.WHITE_CHERRY_OAK_TREE_B1_CONFIG : MubbleFeatureConfigs.WHITE_CHERRY_OAK_TREE_CONFIG);
	}
}