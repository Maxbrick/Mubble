package com.hugman.mubble.object.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class ChinchoEntityModel<T extends Entity> extends CompositeEntityModel<T> {
	public ModelPart head;
	public ModelPart torso;
	public ModelPart rightArm;
	public ModelPart leftArm;
	public ModelPart rightLeg;
	public ModelPart leftLeg;
	public ModelPart rightTooth;
	public ModelPart middleTooth;
	public ModelPart leftTooth;

	public ChinchoEntityModel(ModelPart root) {
		this.head = root.getChild("head");
		this.torso = root.getChild("torso");
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
		this.rightTooth = root.getChild("right_tooth");
		this.middleTooth = root.getChild("middle_tooth");
		this.leftTooth = root.getChild("left_tooth");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8, 8, 8), ModelTransform.pivot(0.0F, 13.0F, 0.0F));
		modelPartData.addChild("torso", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 5.0F, -2F, 8, 7, 4), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
		modelPartData.addChild("right_tooth", ModelPartBuilder.create().uv(0, 24).cuboid(1.8F, -2.0F, -5.0F, 3, 3, 2), ModelTransform.pivot(0.0F, 13.0F, 0.0F));
		modelPartData.addChild("middle_tooth", ModelPartBuilder.create().uv(0, 34).cuboid(-1.5F, -2.0F, -5.0F, 3, 3, 2), ModelTransform.pivot(0.0F, 13.0F, 0.0F));
		modelPartData.addChild("left_tooth", ModelPartBuilder.create().uv(0, 29).cuboid(-4.8F, -2.0F, -5.0F, 3, 3, 2), ModelTransform.pivot(0.0F, 13.0F, 0.0F));
		modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-2.0F, -2.0F, -2.0F, 3, 6, 4), ModelTransform.pivot(-5.0F, 15.0F, 0.0F));
		modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -2.0F, -2.0F, 3, 6, 4).mirrored(), ModelTransform.pivot(5.0F, 15.0F, 0.0F));
		modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 3.0F, -2.0F, 4, 4, 4), ModelTransform.pivot(-2F, 17.0F, 0.0F));
		modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 3.0F, -2.0F, 4, 4, 4).mirrored(), ModelTransform.pivot(2F, 17.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float customAngle, float netHeadYaw, float headPitch) {
		this.rightArm.pitch = 3.7699115F;
		this.leftArm.pitch = 3.7699115F;
		this.rightLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.rightLeg.yaw = 0.0F;
		this.leftLeg.yaw = 0.0F;
		this.head.yaw = netHeadYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
		this.rightTooth.yaw = netHeadYaw * 0.017453292F;
		this.rightTooth.pitch = headPitch * 0.017453292F;
		this.middleTooth.yaw = netHeadYaw * 0.017453292F;
		this.middleTooth.pitch = headPitch * 0.017453292F;
		this.leftTooth.yaw = netHeadYaw * 0.017453292F;
		this.leftTooth.pitch = headPitch * 0.017453292F;
	}

	@Override
	public Iterable<ModelPart> getParts() {
		return ImmutableList.of(this.torso, this.head, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg, this.rightTooth, this.middleTooth, this.leftTooth);
	}
}