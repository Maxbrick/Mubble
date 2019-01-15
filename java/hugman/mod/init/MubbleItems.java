package hugman.mod.init;

import java.util.ArrayList;
import java.util.List;

import hugman.mod.objects.item.ItemAnnoyingDog;
import hugman.mod.objects.item.ItemBandage;
import hugman.mod.objects.item.ItemBase;
import hugman.mod.objects.item.ItemCapeFeather;
import hugman.mod.objects.item.ItemEdible;
import hugman.mod.objects.item.ItemEdibleEffect;
import hugman.mod.objects.item.ItemEdibleSeed;
import hugman.mod.objects.item.ItemMusicDisc;
import hugman.mod.objects.item.ItemSmashBall;
import hugman.mod.objects.item.ItemSuperStar;
import hugman.mod.util.handlers.SoundHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/** 
 * Init class - used to initialize items.
 */
public class MubbleItems
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item BANDAGE = new ItemBandage();
	public static final Item CARAMEL_CUBE = new ItemEdible("caramel_cube", CreativeTabs.FOOD, 4, 2.8f, false);
	public static final Item CREPE = new ItemEdible("crepe", CreativeTabs.FOOD, 3, 0.5f, false);
	public static final Item CHOCOLATE_CREPE = new ItemEdible("chocolate_crepe", CreativeTabs.FOOD, 8, 2f, false);
	public static final Item CARAMEL_CREPE = new ItemEdible("caramel_crepe", CreativeTabs.FOOD, 9, 3.4f, false);
	public static final Item CANDY_CANE = new ItemEdible("candy_cane", CreativeTabs.FOOD, 4, 1.8f, true);
	public static final Item BURGER = new ItemEdible("burger", CreativeTabs.FOOD, 7, 2F, false);
	public static final Item TOMATO = new ItemEdibleSeed("tomato", CreativeTabs.FOOD, 3, 0.6F);
	public static final Item SALAD = new ItemEdibleSeed("salad", CreativeTabs.FOOD, 1, 0.2F);
	public static final Item CHEESE = new ItemEdible("cheese", CreativeTabs.FOOD, 2, 0.4F, false);
	public static final Item WHEAT_FLOUR = new ItemBase("wheat_flour", CreativeTabs.FOOD);
	public static final Item VANADIUM = new ItemBase("vanadium", CreativeTabs.MATERIALS);
	public static final Item SUPER_MUSHROOM = new ItemEdibleEffect("super_mushroom", MubbleTabs.MUBBLE_ITEMS, 3, 1f, false, new PotionEffect(Potion.getPotionById(8), 550, 1));
	public static final Item PEACH = new ItemEdible("peach", MubbleTabs.MUBBLE_ITEMS, 4, 2.4f, true);
	public static final Item CAPE_FEATHER = new ItemCapeFeather("cape_feather");
	public static final Item SUPER_CAPE_FEATHER = new ItemCapeFeather("super_cape_feather");
	public static final Item SUPER_STAR = new ItemSuperStar();
	public static final Item YELLOW_COIN = new ItemBase("yellow_coin", MubbleTabs.MUBBLE_ITEMS);
	public static final Item RED_COIN = new ItemBase("red_coin", MubbleTabs.MUBBLE_ITEMS);
	public static final Item BLUE_COIN = new ItemBase("blue_coin", MubbleTabs.MUBBLE_ITEMS);
	public static final Item SMASH_BALL = new ItemSmashBall();
	public static final Item ANNOYING_DOG = new ItemAnnoyingDog();
	public static final Item RECORD_BLANK = new ItemBase("record_blank", CreativeTabs.MISC, 1);
	public static final Item RECORD_CHAMPIONS_ROAD = new ItemMusicDisc("champions_road", MubbleTabs.MUBBLE_ITEMS, SoundHandler.RECORD_CHAMPIONS_ROAD);
	public static final Item RECORD_VAMPIRE_KILLER = new ItemMusicDisc("vampire_killer", MubbleTabs.MUBBLE_ITEMS, SoundHandler.RECORD_VAMPIRE_KILLER);
	public static final Item RECORD_FLY_OCTO_FLY = new ItemMusicDisc("fly_octo_fly", MubbleTabs.MUBBLE_ITEMS, SoundHandler.RECORD_FLY_OCTO_FLY);
	public static final Item RECORD_NB_SWEDEN = new ItemMusicDisc("nb_sweden", MubbleTabs.MUBBLE_ITEMS, SoundHandler.RECORD_NB_SWEDEN);
	public static final Item RECORD_NB_BUOY_BASE_GALAXY = new ItemMusicDisc("nb_buoy_base_galaxy", MubbleTabs.MUBBLE_ITEMS, SoundHandler.RECORD_NB_BUOY_BASE_GALAXY);
	public static final Item RECORD_NB_WALUIGI_PINBALL = new ItemMusicDisc("nb_waluigi_pinball", MubbleTabs.MUBBLE_ITEMS, SoundHandler.RECORD_NB_WALUIGI_PINBALL);
	public static final Item RECORD_NB_HARVEST_HAZARDS = new ItemMusicDisc("nb_harvest_hazards", MubbleTabs.MUBBLE_ITEMS, SoundHandler.RECORD_NB_HARVEST_HAZARDS);
}