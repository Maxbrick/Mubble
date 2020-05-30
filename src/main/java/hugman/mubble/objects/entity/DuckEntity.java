package hugman.mubble.objects.entity;

import hugman.mubble.init.MubbleEntities;
import hugman.mubble.init.MubbleSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

import java.util.*;
import java.util.stream.Collectors;

public class DuckEntity extends AnimalEntity
{
	private static final TrackedData<Integer> DUCK_TYPE = DataTracker.registerData(DuckEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.ofItems(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
	public float wingRotation;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;

	public DuckEntity(EntityType<? extends DuckEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
	}

	@Override
	protected void initDataTracker()
	{
		super.initDataTracker();
		this.dataTracker.startTracking(DUCK_TYPE, 0);
	}

	@Override
	public EntityData initialize(WorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, net.minecraft.entity.EntityData data, CompoundTag compound)
	{
		Biome biome = world.getBiome(this.getBlockPos());
		DuckEntity.Type type = DuckEntity.Type.getTypeByBiome(biome);
		if (data instanceof DuckEntity.DuckData)
		{
			type = ((DuckEntity.DuckData) data).type;
		}
		else
		{
			data = new DuckEntity.DuckData(type);
		}
		this.setVariantType(type);
		return super.initialize(world, difficulty, spawnReason, data, compound);
	}

	@Override
	protected void initGoals()
	{
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4D));
		this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
		this.goalSelector.add(3, new TemptGoal(this, 1.0D, false, TEMPTATION_ITEMS));
		this.goalSelector.add(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(7, new LookAroundGoal(this));
	}

	@Override
	public void writeCustomDataToTag(CompoundTag compound)
	{
		super.writeCustomDataToTag(compound);
		compound.putString("Type", this.getVariantType().getName());
	}

	@Override
	public void readCustomDataFromTag(CompoundTag compound)
	{
		super.readCustomDataFromTag(compound);
		this.setVariantType(DuckEntity.Type.getTypeByName(compound.getString("Type")));
	}

	public static Builder createDuckAttributes()
	{
		return MobEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
	}

	@Override
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions size)
	{
		return this.isBaby() ? size.height * 0.85F : size.height * 0.92F;
	}

	@Override
	public void tickMovement()
	{
		super.tickMovement();
		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 4) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);
		if (!this.onGround && this.wingRotDelta < 0.55F)
		{
			this.wingRotDelta = 0.55F;
		}
		this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);
		Vec3d vec3d = this.getVelocity();
		if (!this.onGround && vec3d.y < 0.0D)
		{
			this.setVelocity(vec3d.multiply(1.0D, 0.75D, 1.0D));
		}
		this.wingRotation += this.wingRotDelta * 2.0F;
	}

	@Override
	public boolean handleFallDamage(float distance, float multiplier)
	{
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return MubbleSounds.ENTITY_DUCK_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return MubbleSounds.ENTITY_DUCK_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return MubbleSounds.ENTITY_DUCK_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state)
	{
		this.playSound(MubbleSounds.ENTITY_DUCK_STEP, 0.15F, 1.0F);
	}

	@Override
	public DuckEntity createChild(PassiveEntity parent)
	{
		DuckEntity entity = MubbleEntities.DUCK.create(this.world);
		entity.setVariantType(((DuckEntity) parent).getVariantType());
		return entity;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return TEMPTATION_ITEMS.test(stack);
	}

	@Override
	public void updatePassengerPosition(Entity entity)
	{
		super.updatePassengerPosition(entity);
		float f = MathHelper.sin(this.bodyYaw * ((float) Math.PI / 180F));
		float f1 = MathHelper.cos(this.bodyYaw * ((float) Math.PI / 180F));
		entity.updatePosition(this.getX() + (double) (0.1F * f), this.getBodyY(0.5D) + entity.getHeightOffset() + 0.0D, this.getZ() - (double) (0.1F * f1));
		if (entity instanceof LivingEntity)
		{
			((LivingEntity) entity).bodyYaw = this.bodyYaw;
		}
	}

	public static class DuckData extends PassiveEntity.PassiveData
	{
		public final DuckEntity.Type type;

		public DuckData(DuckEntity.Type typeIn)
		{
			this.setBabyAllowed(false);
			this.type = typeIn;
		}
	}

	public DuckEntity.Type getVariantType()
	{
		return DuckEntity.Type.getTypeByIndex(this.dataTracker.get(DUCK_TYPE));
	}

	private void setVariantType(DuckEntity.Type type)
	{
		this.dataTracker.set(DUCK_TYPE, type.getIndex());
	}

	public static List<Biome> getSpawnBiomes()
	{
		ArrayList<Biome> biomeList = new ArrayList<Biome>();
		for (DuckEntity.Type type : Type.typeList)
		{
			biomeList.addAll(type.getSpawnBiomes());
		}
		List<Biome> fBiomeList = biomeList.stream().distinct().collect(Collectors.toList());
		return fBiomeList;
	}

	public static enum Type
	{
		PEKIN(0, "pekin", Biomes.PLAINS, Biomes.FOREST),
		MALLARD(1, "mallard", Biomes.PLAINS, Biomes.RIVER, Biomes.SWAMP);

		private static final DuckEntity.Type[] typeList = Arrays.stream(values()).sorted(Comparator.comparingInt(DuckEntity.Type::getIndex)).toArray((index) ->
		{
			return new DuckEntity.Type[index];
		});
		private static final Map<String, DuckEntity.Type> TYPES_BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(DuckEntity.Type::getName, (type) ->
		{
			return type;
		}));
		private final int index;
		private final String name;
		private final List<Biome> spawnBiomes;

		private Type(int indexIn, String nameIn, Biome... spawnBiomesIn)
		{
			this.index = indexIn;
			this.name = nameIn;
			this.spawnBiomes = Arrays.asList(spawnBiomesIn);
		}

		public String getName()
		{
			return this.name;
		}

		public List<Biome> getSpawnBiomes()
		{
			return this.spawnBiomes;
		}

		public int getIndex()
		{
			return this.index;
		}

		public static DuckEntity.Type getTypeByName(String name)
		{
			return TYPES_BY_NAME.getOrDefault(name, PEKIN);
		}

		public static DuckEntity.Type getTypeByIndex(int index)
		{
			if (index < 0 || index > typeList.length)
			{
				index = 0;
			}
			return typeList[index];
		}

		public static DuckEntity.Type getTypeByBiome(Biome biome)
		{
			List<Type> shuffledList = Arrays.asList(typeList.clone());
			Collections.shuffle(shuffledList);
			for (DuckEntity.Type type : shuffledList)
			{
				if (type.getSpawnBiomes().contains(biome))
				{
					return type;
				}
				else
				{
					continue;
				}
			}
			return PEKIN;
		}
	}
}