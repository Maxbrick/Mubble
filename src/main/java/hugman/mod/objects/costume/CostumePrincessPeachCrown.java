package hugman.mod.objects.costume;

import hugman.mod.init.elements.MubbleBlocks;
import hugman.mod.objects.block.BlockKoretato;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CostumePrincessPeachCrown extends CostumeSimple
{    
    public CostumePrincessPeachCrown()
    {
        super("princess_peach_crown", SoundEvents.ITEM_ARMOR_EQUIP_IRON, EntityEquipmentSlot.HEAD);
    }
    
    @Override
    public EnumActionResult onItemUse(ItemUseContext context)
    {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        IBlockState iblockstate = world.getBlockState(blockpos);
        if (iblockstate.getBlock() == MubbleBlocks.KORETATO_BLOCK && !iblockstate.get(BlockKoretato.PRINCESS))
        {
            world.spawnParticle(Particles.HEART, blockpos.getX() + 0.5D, blockpos.getY() + 0.3D, blockpos.getX() + 0.5D, 0.0D, 0.0D, 0.0D);
        	if (world.isRemote) return EnumActionResult.SUCCESS;
        	else
        	{
        		IBlockState iblockstate1 = iblockstate.with(BlockKoretato.PRINCESS, true);
        		world.setBlockState(blockpos, iblockstate1, 2);
        		context.getItem().shrink(1);
        		world.playSound((EntityPlayer)null, blockpos, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.BLOCKS, 1.0F, 1.0F);
        		world.playSound((EntityPlayer)null, blockpos, SoundEvents.ITEM_ARMOR_EQUIP_IRON, SoundCategory.BLOCKS, 1.0F, 1.0F);
        		return EnumActionResult.SUCCESS;
        	}
        }
        else return EnumActionResult.PASS;
     }
}