package com.hugman.mubble.mixin;

import com.hugman.dawn.api.util.EnchantmentUtil;
import com.hugman.mubble.init.MubbleEffectPack;
import com.hugman.mubble.init.MubbleEnchantmentPack;
import com.hugman.mubble.init.data.MubbleTags;
import com.hugman.mubble.object.item.LightsaberItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@Inject(method = "jump", at = @At(value = "TAIL"), cancellable = true)
	private void mubble_jump(CallbackInfo info) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if(entity.hasStatusEffect(MubbleEffectPack.HEAVINESS)) {
			Vec3d vec3d = entity.getVelocity();
			entity.setVelocity(vec3d.x, vec3d.y - (float) (entity.getStatusEffect(MubbleEffectPack.HEAVINESS).getAmplifier() + 1) * 0.05F, vec3d.z);
		}
	}

	@Inject(method = "tick", at = @At(value = "TAIL"), cancellable = true)
	private void mubble_tick(CallbackInfo info) {
		LivingEntity entity = (LivingEntity) (Object) this;
		World world = entity.getEntityWorld();
		ItemStack headItem = entity.getEquippedStack(EquipmentSlot.HEAD);
		if(!world.isClient) {
			if(MubbleTags.Items.WEIGHT_HEAVY.contains(headItem.getItem())) {
				entity.addStatusEffect(new StatusEffectInstance(MubbleEffectPack.HEAVINESS, 200, 0, false, false, true));
			}
		}
	}

	@Inject(method = "swingHand", at = @At(value = "TAIL"), cancellable = true)
	private void mubble_swingHand(Hand hand, CallbackInfo info) {
		LivingEntity entity = (LivingEntity) (Object) this;
		ItemStack stack = entity.getMainHandStack();
		if(stack.getItem() instanceof LightsaberItem) {
			((LightsaberItem) stack.getItem()).onSwing(entity, false);
		}
	}

	@Inject(method = "dropLoot", at = @At(value = "HEAD"), cancellable = true)
	private void mubble_dropLoot(DamageSource source, boolean causedByPlayer, CallbackInfo info) {
		LivingEntity entity = (LivingEntity) (Object) this;
		World world = entity.getEntityWorld();
		if(causedByPlayer && source.getAttacker() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) source.getAttacker();
			LootTable lootTable = world.getServer().getLootManager().getTable(entity.getLootTable());
			LootContext.Builder builder = this.getLootContextBuilder(causedByPlayer, source);
			if(EnchantmentUtil.hasEnchantment(MubbleEnchantmentPack.TELEKINESIS, player.getMainHandStack())) {
				for(ItemStack stack : lootTable.generateLoot(builder.build(LootContextTypes.ENTITY))) {
					player.inventory.insertStack(stack);
				}
				info.cancel();
			}
		}
	}

	@Inject(method = "dropXp", at = @At(value = "HEAD"), cancellable = true)
	private void mubble_dropXp(CallbackInfo info) {
		LivingEntity entity = (LivingEntity) (Object) this;
		LivingEntity attacker = entity.getAttacker();
		if(attacker instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) attacker;
			if(!player.getMainHandStack().isEmpty()) {
				if(EnchantmentUtil.hasEnchantment(MubbleEnchantmentPack.TELEKINESIS, player.getMainHandStack())) {
					player.addExperience(this.getCurrentExperience(player));
					info.cancel();
				}
			}
		}
	}

	@Shadow
	protected abstract int getCurrentExperience(PlayerEntity player);

	@Shadow
	protected abstract LootContext.Builder getLootContextBuilder(boolean causedByPlayer, DamageSource source);
}