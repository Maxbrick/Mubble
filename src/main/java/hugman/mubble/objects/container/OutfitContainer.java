package hugman.mubble.objects.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;

public class OutfitContainer extends Container {
	private static final ResourceLocation[] ARMOR_SLOT_TEXTURES = new ResourceLocation[]{PlayerContainer.EMPTY_BOOTS_SLOT_TEXTURE, PlayerContainer.EMPTY_LEGGINGS_SLOT_TEXTURE, PlayerContainer.EMPTY_CHESTPLATE_SLOT_TEXTURE, PlayerContainer.EMPTY_HELMET_SLOT_TEXTURE};
	private static final EquipmentSlotType[] VALID_EQUIPMENT_SLOTS = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};

	private static final int HOTBAR_SLOT_COUNT = 9;
	private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

	public static final int PLAYER_INVENTORY_YPOS = 84;

	public OutfitContainer(int windowID, PlayerInventory inventory) {
		super(null, windowID);
		PlayerInvWrapper playerInventoryForge = new PlayerInvWrapper(inventory);
		final int SLOT_X_SPACING = 18;
		final int SLOT_Y_SPACING = 18;
		final int HOTBAR_XPOS = 8;
		final int HOTBAR_YPOS = 142;
		final int PLAYER_INVENTORY_XPOS = 8;
		for(int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
			for(int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
				int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
				int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
				int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
				addSlot(new SlotItemHandler(playerInventoryForge, slotNumber, xpos, ypos));
			}
		}
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return true;
	}
}