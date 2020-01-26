package hugman.mubble.objects.item;

import hugman.mubble.objects.entity.FireballEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.World;

public class FireballItem extends Item
{
	public FireballItem(Properties builder)
	{
		super(builder);
		
	    DispenserBlock.registerDispenseBehavior(Items.SNOWBALL, new ProjectileDispenseBehavior()
	    {
	    	protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack)
	    	{
	    		return Util.make(new SnowballEntity(world, pos.getX(), pos.getY(), pos.getZ()), (entity) ->
	    		{
	    			entity.setItem(stack);
	    		});
	    	}
	    });
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		if(!world.isRemote)
		{
			FireballEntity entity = new FireballEntity(world, player);
			entity.setItem(stack);
			entity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.addEntity(entity);
		}
		
		player.addStat(Stats.ITEM_USED.get(this));
		if(!player.abilities.isCreativeMode)
		{
			stack.shrink(1);
		}
		
		return ActionResult.success(stack);
	}
}