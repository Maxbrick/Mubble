package com.hugman.mubble.object.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class ToadEntityModel<T extends Entity> extends AnimalModel<T> {
	public ModelPart head;
	public ModelPart mushroom;
	public ModelPart frontLamp;
	public ModelPart torso;
	public ModelPart backpack;
	public ModelPart rightArm;
	public ModelPart leftArm;
	public ModelPart rightLeg;
	public ModelPart leftLeg;

	public ToadEntityModel(ModelPart root) {
		super(true, 13.3F, 0.0F);

		this.head = root.getChild("head");
		this.mushroom = root.getChild("mushroom");
		this.frontLamp = root.getChild("front_lamp");
		this.torso = root.getChild("torso");
		this.backpack = root.getChild("backpack");
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8, 8, 8), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
		modelPartData.addChild("mushroom", ModelPartBuilder.create().uv(0, 29).cuboid(-5.0F, -10.0F, -5.0F, 10, 5, 10), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
		modelPartData.addChild("front_lamp", ModelPartBuilder.create().uv(30, 33).cuboid(-2.0F, -10.0F, -7.0F, 4, 4, 2), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
		modelPartData.addChild("torso", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2F, 8, 9, 4), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
		modelPartData.addChild("backpack", ModelPartBuilder.create().uv(0, 44).cuboid(-3.0F, 0.0F, 2.0F, 6, 8, 4), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
		modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-2.0F, -2.0F, -2.0F, 3, 7, 4), ModelTransform.pivot(-5.0F, 10.0F, 0.0F));
		modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -2.0F, -2.0F, 3, 7, 4).mirrored(), ModelTransform.pivot(5.0F, 10.0F, 0.0F));
		modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4, 7, 4), ModelTransform.pivot(-2F, 17.0F, 0F));
		modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4, 7, 4).mirrored(), ModelTransform.pivot(2F, 17.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float customAngle, float netHeadYaw, float headPitch) {
		//this.body.yaw = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * ((float) Math.PI * 2F)) * 0.2F;
		this.leftLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leftArm.pitch = MathHelper.cos(limbSwing * 0.8F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.pitch = MathHelper.cos(limbSwing * 0.8F) * 1.4F * limbSwingAmount;
		this.head.yaw = netHeadYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
		this.mushroom.yaw = netHeadYaw * 0.017453292F;
		this.mushroom.pitch = headPitch * 0.017453292F;
		this.frontLamp.yaw = netHeadYaw * 0.017453292F;
		this.frontLamp.pitch = headPitch * 0.017453292F;
	}

	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head, this.mushroom, this.frontLamp);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.torso, this.backpack, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg);
	}
}