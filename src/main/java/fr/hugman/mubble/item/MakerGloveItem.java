package fr.hugman.mubble.item;

import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

public class MakerGloveItem extends Item {
    public BlockPos destinationPos;
    public MakerGloveItem(Item.Settings builder, boolean infinite) {
        super(builder);
    }
    public BlockPos getDestinationPos() {
        return this.destinationPos;
    }

    public void setDestinationPos(BlockPos destinationPos) {
        this.destinationPos = destinationPos;
    }
}
