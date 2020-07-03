package hugman.mubble.init;

import hugman.mubble.Mubble;
import hugman.mubble.objects.enchantment.TelekinesisEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

import java.util.ArrayList;
import java.util.List;

public class MubbleEnchantments {
	public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();

	public static final Enchantment TELEKINESIS = register("telekinesis", new TelekinesisEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));

	private static Enchantment register(String name, Enchantment enchantment) {
		enchantment.setRegistryName(Mubble.MOD_ID, name);
		ENCHANTMENTS.add(enchantment);
		return enchantment;
	}
}
