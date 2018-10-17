package hugman.mod.objects.items;

import hugman.mod.Main;
import hugman.mod.init.ItemInit;
import hugman.mod.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemHelmet extends ItemBase implements IHasModel
{	
	String name;
	SoundEvent sound;
	private PotionEffect[] effects;
    public final EntityEquipmentSlot armorType = EntityEquipmentSlot.HEAD;
    
	public ItemHelmet(String name, SoundEvent sound, PotionEffect...potionEffect)
	{
		super(name, 1, Main.MUBBLE_COSTUMES);
		if(name == "wing_cap") this.setMaxDamage(600);
		
		ItemInit.ITEMS.add(this);
		this.effects = potionEffect;
		this.name = name;
		this.sound = sound;
	}
	
	public static boolean isUsable(ItemStack stack)
    {
        return stack.getItemDamage() < stack.getMaxDamage() - 1;
    }
	
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
		if(this.name == "wing_cap") return repair.getItem() == Items.FEATHER;
		else return false;
    }
	
	@Override
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity)
    {
		return armorType == EntityEquipmentSlot.HEAD;
    }
	
	@Override
	public void onArmorTick(World world, EntityPlayer playerIn, ItemStack armor)
	{
		for(PotionEffect effect : effects)
		{
			playerIn.addPotionEffect(new PotionEffect(effect));
		}
		if(this.name == "mayro_cap")
		{
			playerIn.inventory.clearMatchingItems(ItemInit.YELLOW_COIN, 0, 1, null);
			playerIn.inventory.clearMatchingItems(ItemInit.RED_COIN, 0, 1, null);
			playerIn.inventory.clearMatchingItems(ItemInit.BLUE_COIN, 0, 1, null);
		}
		if(this.name == "vanish_cap" && playerIn.isSneaking()) playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 2, 0));
		if(this.name == "wing_cap" && this.isUsable(armor) && playerIn.isSprinting())
		{
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(25), 1, 2));
			playerIn.fallDistance = 0f;
			armor.damageItem(1, playerIn);
		}
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        EntityEquipmentSlot entityequipmentslot = EntityEquipmentSlot.HEAD;
        ItemStack itemstack1 = playerIn.getItemStackFromSlot(entityequipmentslot);

        if (itemstack1.isEmpty())
        {
            playerIn.setItemStackToSlot(entityequipmentslot, itemstack.copy());
            if (!playerIn.capabilities.isCreativeMode) itemstack.setCount(0);
            worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, sound, SoundCategory.PLAYERS, 1f, 1f);
            playerIn.addStat(StatList.getObjectUseStats(this));
            if(this.name == "mayro_cap" && playerIn.getName() == "MayroSMM") playerIn.sendStatusMessage(new TextComponentTranslation("status.no_need", new Object[0]), true);
            if(this.name == "noteblock_head" && playerIn.getName() == "NoteBlockRemix") playerIn.sendStatusMessage(new TextComponentTranslation("status.no_need", new Object[0]), true);
            if(this.name == "bandana" && playerIn.getName() == "Pixelcraftian") playerIn.sendStatusMessage(new TextComponentTranslation("status.for_you", new Object[0]), true);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
        else
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }
}
