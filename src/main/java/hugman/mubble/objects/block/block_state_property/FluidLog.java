package hugman.mubble.objects.block.block_state_property;

import net.minecraft.util.IStringSerializable;

public enum FluidLog implements IStringSerializable {
	EMPTY("empty"),
	WATER("water"),
	LAVA("lava");

	private final String name;

	FluidLog(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public String getName() {
		return this.name;
	}
}
