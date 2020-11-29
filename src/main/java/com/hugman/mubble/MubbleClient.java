package com.hugman.mubble;

import com.hugman.mubble.init.MubbleEntities;
import com.hugman.mubble.init.client.MubbleColorMaps;
import com.hugman.mubble.init.client.MubbleScreens;
import com.hugman.mubble.object.entity.render.*;
import com.hugman.mubble.object.event.LightsaberEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

@Environment(EnvType.CLIENT)
public class MubbleClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MubbleScreens.init();
		MubbleColorMaps.registerColors();
		registerEntityRenders();
		LightsaberEvents.init();
	}

	private void registerEntityRenders() {
		EntityRendererRegistry.INSTANCE.register(MubbleEntities.CHINCHO, ChinchoEntityRenderer::new);
		EntityRendererRegistry.INSTANCE.register(MubbleEntities.GOOMBA, GoombaEntityRenderer::new);
		EntityRendererRegistry.INSTANCE.register(MubbleEntities.TOAD, ToadEntityRenderer::new);
		EntityRendererRegistry.INSTANCE.register(MubbleEntities.CUSTOM_TNT, CustomTNTEntityRenderer::new);
		EntityRendererRegistry.INSTANCE.register(MubbleEntities.FLYING_BLOCK, FlyingBlockEntityRenderer::new);
		EntityRendererRegistry.INSTANCE.register(MubbleEntities.FIREBALL, (context) -> new FlyingItemEntityRenderer<>(context, 1.0F, true));
		EntityRendererRegistry.INSTANCE.register(MubbleEntities.ICEBALL, (context) -> new FlyingItemEntityRenderer<>(context));
		EntityRendererRegistry.INSTANCE.register(MubbleEntities.KIRBY_BALL, (context) -> new FlyingItemEntityRenderer<>(context));
	}
}
