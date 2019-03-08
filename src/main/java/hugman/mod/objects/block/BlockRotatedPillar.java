package hugman.mod.objects.block;

import hugman.mod.Reference;
import hugman.mod.init.MubbleBlocks;

public class BlockRotatedPillar extends net.minecraft.block.BlockRotatedPillar
{
    public BlockRotatedPillar(String name, Properties properties)
    {
        super(properties);
        setRegistryName(Reference.MOD_ID, name);
        MubbleBlocks.register(this);
    }
}