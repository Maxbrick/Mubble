package hugman.mod.objects.block;

import hugman.mod.Mubble;
import hugman.mod.init.MubbleBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.item.ItemGroup;

public class BlockWall extends net.minecraft.block.BlockWall implements IBucketPickupHandler, ILiquidContainer
{
    public BlockWall(String name, Block base_block)
    {
        super(Properties.from(base_block));
        setRegistryName(Mubble.MOD_ID, name + "_wall");
        MubbleBlocks.register(this, ItemGroup.DECORATIONS);
    }
    
    public BlockWall(Block base_block)
    {
        super(Properties.from(base_block));
        setRegistryName(base_block.getRegistryName() + "_wall");
        MubbleBlocks.register(this, ItemGroup.DECORATIONS);
    }
}