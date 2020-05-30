package hugman.mubble.objects.world.trees;

import hugman.mubble.init.world.MubbleFeatureConfigs;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class PinkCherryOakTree extends Tree
{
	@Nullable
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean canHaveBeeHive)
	{
		return random.nextInt(10) == 0 ? Feature.FANCY_TREE.configure(canHaveBeeHive ? MubbleFeatureConfigs.FANCY_PINK_CHERRY_OAK_TREE_B1_CONFIG : MubbleFeatureConfigs.FANCY_PINK_CHERRY_OAK_TREE_CONFIG) : Feature.NORMAL_TREE.configure(canHaveBeeHive ? MubbleFeatureConfigs.PINK_CHERRY_OAK_TREE_B1_CONFIG : MubbleFeatureConfigs.PINK_CHERRY_OAK_TREE_CONFIG);
	}
}