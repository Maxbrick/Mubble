package fr.hugman.mubble.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import fr.hugman.mubble.block.MubbleBlockEntityTypes;
import net.minecraft.world.World;

/**
 * @author MaxBrick
 * @since v4.0.0
 */

public class WarpBlockEntity extends BlockEntity {
    private BlockPos destinationPos;

    //Copied code from BumpableBlockEntity. Sorry, Hugman X)
    public WarpBlockEntity(BlockPos pos, BlockState state) {
        super(MubbleBlockEntityTypes.WARP_BLOCK, pos, state);
        this.destinationPos = pos;
    }

    /*=======*/
    /*  NBT  */
    /*=======*/

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.put("DestinationPos", NbtHelper.fromBlockPos(this.destinationPos));
        super.writeNbt(nbt, registryLookup);
    }
    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        this.destinationPos = NbtHelper.toBlockPos(nbt, "DestinationPos").get();
    }

    public static void tick(World world, BlockPos pos, BlockState state, WarpBlockEntity blockEntity) {
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
