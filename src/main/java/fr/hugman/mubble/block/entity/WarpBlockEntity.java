package fr.hugman.mubble.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import fr.hugman.mubble.block.MubbleBlockEntityTypes;

/**
 * @author MaxBrick
 * @since v4.0.0
 */

public class WarpBlockEntity extends BlockEntity {
    private BlockPos destinationPos;

    //Copied code from BumpableBlockEntity. Sorry, Hugman X)
    public WarpBlockEntity(BlockPos pos, BlockState state) {
        super(MubbleBlockEntityTypes.WARP_BLOCK, pos, state);
    }

    /*=======*/
    /*  NBT  */
    /*=======*/

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.put("DestinationPos", NbtHelper.fromBlockPos(this.destinationPos));
    }
    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        nbt = nbt.getCompound("DestinationPos");
        this.destinationPos = NbtHelper.toBlockPos(nbt, "DestinationPos").get();
    }

    /*=====================*/
    /*  GETTERS & SETTERS  */
    /*=====================*/

    public BlockPos getDestinationPos() {
        return this.destinationPos;
    }
    public void setDestinationPos(BlockPos pos) {
        this.destinationPos = pos;
    }
}
