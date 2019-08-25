package hugman.mubble.objects.block;

import java.util.List;

import hugman.mubble.init.MubbleBlocks;
import hugman.mubble.init.MubbleLootTables;
import hugman.mubble.init.MubbleSounds;
import hugman.mubble.init.MubbleTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;

public class QuestionBlock extends Block
{
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.05D, 0.0D, 16.0D, 16.0D, 16.0D);
	
    public QuestionBlock()
    {
        super(Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1.5F, 6.0F));
    }
    
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
    	return SHAPE;
    }
    
    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean p_220082_5_)
    {
    	if(worldIn.isBlockPowered(pos)) loot(worldIn, pos);
    }
    
    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean p_220069_6_)
    {
    	if(worldIn.isBlockPowered(pos)) loot(worldIn, pos);
    }
    
    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
    	loot(worldIn, pos);
    }
    
    public void loot(World worldIn, BlockPos pos)
	{
        if(!worldIn.isRemote)
        {
            BlockState emptyBlock = Blocks.AIR.getDefaultState();
            if(this == MubbleBlocks.SMB_QUESTION_BLOCK)
            {
            	emptyBlock = MubbleBlocks.SMB_EMPTY_BLOCK.getDefaultState();
            }
            else if(this == MubbleBlocks.SMB3_QUESTION_BLOCK)
            {
            	emptyBlock = MubbleBlocks.SMB3_EMPTY_BLOCK.getDefaultState();
            }
            else if(this == MubbleBlocks.SMW_QUESTION_BLOCK)
            {
            	emptyBlock = MubbleBlocks.SMW_EMPTY_BLOCK.getDefaultState();
            }
            else if(this == MubbleBlocks.NSMBU_QUESTION_BLOCK)
            {
            	emptyBlock = MubbleBlocks.NSMBU_EMPTY_BLOCK.getDefaultState();
            }
            final double x = pos.getX() + 0.5D;
            final double y = pos.getY() + 0.5D + 0.6D;
            final double z = pos.getZ() + 0.5D;
            LootTable lootTable = worldIn.getServer().getLootTableManager().getLootTableFromLocation(MubbleLootTables.QUESTION_BLOCK);
            LootContext lootContext = new LootContext.Builder((ServerWorld) worldIn)
            		.withParameter(LootParameters.BLOCK_STATE, this.getDefaultState())
            		.withParameter(LootParameters.POSITION, pos)
            		.withParameter(LootParameters.TOOL, new ItemStack(Items.AIR))
            		.build(LootParameterSets.BLOCK);
            List<ItemStack> items = lootTable.generate(lootContext);
            for(ItemStack item : items)
            {
            	worldIn.addEntity(new ItemEntity(worldIn, x, y, z, item));
            	if(item.getItem().isIn(MubbleTags.Items.COINS))
            	{
            		worldIn.playSound((PlayerEntity)null, x, y - 0.6D, z, MubbleSounds.BLOCK_QUESTION_BLOCK_LOOT_COIN, SoundCategory.BLOCKS, 1f, 1f);
            	}
            	else
            	{
            		worldIn.playSound((PlayerEntity)null, x, y - 0.6D, z, MubbleSounds.BLOCK_QUESTION_BLOCK_LOOT_POWER_UP, SoundCategory.BLOCKS, 1f, 1f);
            	}
            }
            worldIn.setBlockState(pos, emptyBlock);
        }
	}
}