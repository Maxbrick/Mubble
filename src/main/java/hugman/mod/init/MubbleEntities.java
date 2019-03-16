package hugman.mod.init;

import hugman.mod.Mubble;
import hugman.mod.objects.entity.EntityChincho;
import hugman.mod.objects.entity.EntityFlyingBlock;
import hugman.mod.objects.entity.EntityToad;
import hugman.mod.objects.entity.render.RenderChincho;
import hugman.mod.objects.entity.render.RenderFlyingBlock;
import hugman.mod.objects.entity.render.RenderToad;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class MubbleEntities
{
	public static final EntityType<EntityToad> TOAD = EntityType.register(Mubble.MOD_PREFIX + "toad", EntityType.Builder.create(EntityToad.class, EntityToad::new));
	public static final EntityType<EntityChincho> CHINCHO = EntityType.register(Mubble.MOD_PREFIX + "chincho", EntityType.Builder.create(EntityChincho.class, EntityChincho::new));
	public static final EntityType<EntityFlyingBlock> FLYING_BLOCK = EntityType.register(Mubble.MOD_PREFIX + "flying_block", EntityType.Builder.create(EntityFlyingBlock.class, EntityFlyingBlock::new));
    
    public static void registerRenders()
    {
    	RenderingRegistry.registerEntityRenderingHandler(EntityToad.class, RenderToad::new);
    	RenderingRegistry.registerEntityRenderingHandler(EntityChincho.class, RenderChincho::new);
    	RenderingRegistry.registerEntityRenderingHandler(EntityFlyingBlock.class, RenderFlyingBlock::new);
    }
}