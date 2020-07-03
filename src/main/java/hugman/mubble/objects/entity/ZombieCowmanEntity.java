package hugman.mubble.objects.entity;

import hugman.mubble.init.MubbleSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;
import java.util.UUID;

public class ZombieCowmanEntity extends ZombifiedPiglinEntity {
	private static final UUID ATTACK_SPEED_BOOST_MODIFIER_UUID = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
	private static final AttributeModifier ATTACK_SPEED_BOOST_MODIFIER = new AttributeModifier(ATTACK_SPEED_BOOST_MODIFIER_UUID, "Attacking speed boost", 0.05D, AttributeModifier.Operation.ADDITION);
	private int angerLevel;
	private int randomSoundDelay;
	private UUID angerTargetUUID;

	public ZombieCowmanEntity(EntityType<? extends ZombieCowmanEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathPriority(PathNodeType.WATER, 8.0F);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return MubbleSounds.ENTITY_ZOMBIE_COWMAN_AMBIENT;
	}

	@Override
	protected SoundEvent getStepSound() {
		return MubbleSounds.ENTITY_ZOMBIE_COWMAN_STEP;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return MubbleSounds.ENTITY_ZOMBIE_COWMAN_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return MubbleSounds.ENTITY_ZOMBIE_COWMAN_DEATH;
	}

	@Override
	protected void applyEntityAI() {
		this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.targetSelector.addGoal(1, new ZombieCowmanEntity.HurtByAggressorGoal(this));
		this.targetSelector.addGoal(2, new ZombieCowmanEntity.TargetAggressorGoal(this));
	}

	public static AttributeModifierMap.MutableAttribute createZombieCowmanAttributes() {
		return ZombifiedPiglinEntity.func_234352_eU_()
				.func_233815_a_(Attributes.field_233821_d_, 0.35D);
	}

	@Override
	protected void func_230291_eT_() {
		super.func_230291_eT_();
		this.getAttribute(Attributes.field_233821_d_).setBaseValue(0.34D);
		this.getAttribute(Attributes.field_233823_f_).setBaseValue(5.0D);
	}

	public static boolean canSpawn(EntityType<ZombieCowmanEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
		return world.getDifficulty() != Difficulty.PEACEFUL;
	}

	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
	}

	@Override
	protected void updateAITasks() {
		ModifiableAttributeInstance attribute = this.getAttribute(Attributes.field_233821_d_);
		if(this.isAngry()) {
			if(!this.isChild() && !attribute.hasModifier(ATTACK_SPEED_BOOST_MODIFIER)) {
				attribute.func_233767_b_(ATTACK_SPEED_BOOST_MODIFIER);
			}
			--this.angerLevel;
		}
		else if(attribute.hasModifier(ATTACK_SPEED_BOOST_MODIFIER)) {
			attribute.removeModifier(ATTACK_SPEED_BOOST_MODIFIER);
		}
		if(this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
			this.playSound(MubbleSounds.ENTITY_ZOMBIE_COWMAN_ANGRY, this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
		}
		if(this.angerLevel > 0 && this.angerTargetUUID != null && this.getRevengeTarget() == null) {
			PlayerEntity entityplayer = this.world.getPlayerByUuid(this.angerTargetUUID);
			this.setRevengeTarget(entityplayer);
			this.attackingPlayer = entityplayer;
			this.recentlyHit = this.getRevengeTimer();
		}
		super.updateAITasks();
	}

	private boolean becomeAngryAt(Entity p_70835_1_) {
		this.angerLevel = 400 + this.rand.nextInt(400);
		this.randomSoundDelay = this.rand.nextInt(40);
		if(p_70835_1_ instanceof LivingEntity) {
			this.setRevengeTarget((LivingEntity) p_70835_1_);
		}
		return true;
	}

	private boolean isAngry() {
		return this.angerLevel > 0;
	}

	static class HurtByAggressorGoal extends HurtByTargetGoal {
		public HurtByAggressorGoal(ZombieCowmanEntity p_i45828_1_) {
			super(p_i45828_1_);
			this.setCallsForHelp(ZombieEntity.class);
		}

		protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
			if(mobIn instanceof ZombieCowmanEntity && this.goalOwner.canEntityBeSeen(targetIn) && ((ZombieCowmanEntity) mobIn).becomeAngryAt(targetIn)) {
				mobIn.setAttackTarget(targetIn);
			}
		}
	}

	static class TargetAggressorGoal extends NearestAttackableTargetGoal<PlayerEntity> {
		public TargetAggressorGoal(ZombieCowmanEntity p_i45829_1_) {
			super(p_i45829_1_, PlayerEntity.class, true);
		}

		public boolean shouldExecute() {
			return ((ZombieCowmanEntity) this.goalOwner).isAngry() && super.shouldExecute();
		}
	}

	@Override
	protected boolean shouldBurnInDay() {
		return false;
	}
}