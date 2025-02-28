package fr.hugman.mubble.block;
import com.mojang.serialization.MapCodec;
import fr.hugman.mubble.block.entity.WarpBlockEntity;
import fr.hugman.mubble.item.MakerGloveItem;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * @author MaxBrick
 * @since v4.0.0
 */
public class WarpBlock extends BlockWithEntity {

    public WarpBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends WarpBlock> getCodec() {
        return createCodec(WarpBlock::new);
    }
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(
                VoxelShapes.cuboid(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.125f, 0.9375f),
                VoxelShapes.cuboid(0.0625f, 0.0f, 0.0625f, 0.125f, 1.0f, 0.9375f),
                VoxelShapes.cuboid(0.0625f, 0.0f, 0.0625f, 0.9375f, 1.0f, 0.125f),
                VoxelShapes.cuboid(0.0625f, 0.0f, 0.875f, 0.9375f, 1.0f, 0.9375f),
                VoxelShapes.cuboid(0.875f, 0.0f, 0.0625f, 0.9375f, 1.0f, 0.9375f),
                VoxelShapes.cuboid(0.0f, 0.625f, 0.0f, 0.125f, 1.0f, 1.0f),
                VoxelShapes.cuboid(0.0f, 0.625f, 0.0f, 1.0f, 1.0f, 0.125f),
                VoxelShapes.cuboid(0.875f, 0.625f, 0.0f, 1.0f, 1.0f, 1.0f),
                VoxelShapes.cuboid(0.0f, 0.625f, 0.875f, 1.0f, 1.0f, 1.0f)
        );
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WarpBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        // Make sure to check world.isClient if you only want to tick only on serverside.
        return validateTicker(type, MubbleBlockEntityTypes.WARP_BLOCK, WarpBlockEntity::tick);
    }
    //Copies coordinates to the Maker Glove if no coordinates are saved
    //If coordinates are saved then set destination to glove's coordinates and remove the saved coordinates from glove
    @Override
    public ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient) {
            if(stack.getItem() instanceof MakerGloveItem gloveItem) {
                if(gloveItem.getDestinationPos() == null) {
                    gloveItem.setDestinationPos(pos);
                } else {
                    if(world.getBlockEntity(pos) instanceof WarpBlockEntity warpBlockEntity) {
                        warpBlockEntity.setDestinationPos(gloveItem.getDestinationPos());
                        gloveItem.setDestinationPos(null);
                    }
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.CONSUME;
    }

    //Players need to crouch to enter pipe, hence the separate event caller thing
    //I don't know how to properly center a location, so I added .5 to x and z
    //TODO: Make it so that the entity can step *inside* the block, and ONLY inside the block, to teleport
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if (!world.isClient){

            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof WarpBlockEntity warpBlockEntity) {
                if(entity instanceof PlayerEntity playerEntity) {
                    playerEntity.sendMessage(Text.of(pos.toString()), true);
                }
                /*This long "if" statement effectively makes sure the destination block is the corresponding warp block
                  (in case the destination block is modified for example)
                  Also, it won't teleport you to the same block (which could soft-lock you)
                 */
                if(
                        entity.isPlayer()
                                && entity.isInSneakingPose()
                                && world.getBlockState(warpBlockEntity.getDestinationPos()).getBlock() == state.getBlock()
                                && blockEntity.getPos() != warpBlockEntity.getDestinationPos()
                ) {
                    entity.requestTeleport(
                            warpBlockEntity.getDestinationPos().getX() + 0.5,
                            warpBlockEntity.getDestinationPos().getY() + 0.126,
                            warpBlockEntity.getDestinationPos().getZ() + 0.5
                    );
                }
            }
        }
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!world.isClient){
            super.onLandedUpon(world, state, pos, entity, fallDistance);
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof WarpBlockEntity warpBlockEntity){

                if(
                        !entity.isPlayer()
                                && world.getBlockState(warpBlockEntity.getDestinationPos()).getBlock() == state.getBlock()
                                && blockEntity.getPos() != warpBlockEntity.getDestinationPos()
                ) {
                    entity.requestTeleport(
                            warpBlockEntity.getDestinationPos().getX() + 0.5,
                            warpBlockEntity.getDestinationPos().getY() + 0.126,
                            warpBlockEntity.getDestinationPos().getZ() + 0.5
                    );
                }
            }
        }
    }
}
