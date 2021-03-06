package com.hugman.mubble.object.item.costume;

import com.hugman.mubble.Mubble;
import com.hugman.mubble.init.MubbleCostumes;
import com.hugman.mubble.init.MubbleSlots;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.Trinket;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HatItem extends WearableItem {
	public HatItem(Settings settings, SoundEvent equipSound, Identifier shader, StatusEffectInstance... potionEffects) {
		super(SlotGroups.HEAD, MubbleSlots.HAT, settings, equipSound, shader);
	}

	public HatItem(Settings settings, SoundEvent equipSound, StatusEffectInstance... potionEffects) {
		this(settings, equipSound, null, potionEffects);
	}

	@Override
	public void onEquip(PlayerEntity player, ItemStack stack) {
		if(this == MubbleCostumes.MAYRO_CAP && player.getGameProfile().getId().toString().equals("8cf61519-4ac2-4d60-9d65-d0c7abcf4524")) {
			player.sendMessage(new TranslatableText("item.mubble.mayro_cap.secret_status"), true);
		}
		else if(this == MubbleCostumes.NOTEBLOCK_HEAD && player.getGameProfile().getId().toString().equals("5a68af56-e293-44e9-bbf8-21d58300b3f3")) {
			player.sendMessage(new TranslatableText("item.mubble.noteblock_head.secret_status"), true);
		}
		else if(this == MubbleCostumes.BANDANA && player.getGameProfile().getId().toString().equals("1805e857-329e-463e-8ca8-122fcc686996")) {
			player.sendMessage(new TranslatableText("item.mubble.bandana.secret_status"), true);
		}
		super.onEquip(player, stack);
	}

	@Override
	public void render(String slot, MatrixStack matrixStack, VertexConsumerProvider vcp, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
		ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
		Trinket.translateToFace(matrixStack, model, player, headYaw, headPitch);
		matrixStack.scale(0.6F, 0.6F, 0.6F);
		matrixStack.translate(0.0D, 0.0D, 0.475D);
		if(player.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof ArmorItem) {
			matrixStack.translate(0.0D, -0.1D, 0.0D);
			matrixStack.scale(1.1F, 1.1F, 1.1F);
		}
		matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(180));
		//itemRenderer.renderItem(new ItemStack(this), ModelTransformation.Mode.HEAD, light, OverlayTexture.DEFAULT_UV, matrixStack, vcp);

		//BakedModel bakedModel = itemRenderer.getModels().getModel(this);
		BakedModel bakedModel = itemRenderer.getModels().getModelManager().getModel(new ModelIdentifier("mubble:" + Registry.ITEM.getId(this).getPath() + "#inventory"));
		itemRenderer.renderItem(new ItemStack(this), ModelTransformation.Mode.HEAD, false, matrixStack, vcp, light, OverlayTexture.DEFAULT_UV, bakedModel);
		//itemRenderer.renderItem(new ItemStack(this), ModelTransformation.Mode.HEAD, false, matrixStack, vcp, light, OverlayTexture.DEFAULT_UV, itemRenderer.getModels().getModelManager().getModel(new ModelIdentifier(Registry.ITEM.getId(this), "costume")));
	}
}
