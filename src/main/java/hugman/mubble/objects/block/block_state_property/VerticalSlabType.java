package hugman.mubble.objects.block.block_state_property;

import net.minecraft.util.IStringSerializable;

public enum VerticalSlabType implements IStringSerializable {
	NORTH("north"),
	SOUTH("south"),
	EAST("east"),
	WEST("west"),
	DOUBLE("double");

	private final String name;

	VerticalSlabType(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public String getName() {
		return this.name;
	}
}
