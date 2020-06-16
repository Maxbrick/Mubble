package hugman.mubble.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxeItem.class)
public class AxeItemMixin
{
	@Inject(method = "useOnBlock", at = @At(value = "HEAD"), cancellable = true)
	private void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir)
	{
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		BlockState state = world.getBlockState(pos);
		Block block = hugman.mubble.objects.item.AxeItem.BLOCK_STRIPPING_MAP.get(state.getBlock());
		PlayerEntity player = context.getPlayer();
		if (block != null)
		{
			world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!world.isClient)
			{
				world.setBlockState(pos, block.getDefaultState().with(LogBlock.AXIS, state.get(LogBlock.AXIS)), 11);
				if (player != null)
				{
					context.getStack().damage(1, player, (p) ->
					{
						p.sendToolBreakStatus(context.getHand());
					});
				}
			}
			cir.setReturnValue(ActionResult.SUCCESS);
		}
		else
		{
			cir.setReturnValue(ActionResult.PASS);
		}
	}
}