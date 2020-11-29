package com.hugman.mubble.object.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class GoombaEntityModel<T extends Entity> extends CompositeEntityModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart top;
	private final ModelPart bottom;
	private final ModelPart leftFoot;
	private final ModelPart rightFoot;

	public GoombaEntityModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.top = this.head.getChild("top");
		this.bottom = this.head.getChild("bottom");
		this.leftFoot = root.getChild("left_foot");
		this.rightFoot = root.getChild("right_foot");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 21.0F, 0.0F));
		modelPartData.getChild("body").addChild("head", ModelPartBuilder.create().uv(0, 11).cuboid(-2.5F, 1.0F, -2.5F, 5, 3, 5), ModelTransform.pivot(0.0F, -2.0F, 0.0F));
		modelPartData.getChild("body").getChild("head").addChild("top", ModelPartBuilder.create().uv(32, 0).cuboid(-3.5F, -5.0F, -3.5F, 7, 4, 7), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData.getChild("body").getChild("head").addChild("bottom", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -1.0F, -4.0F, 8, 2, 8), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData.addChild("left_foot", ModelPartBuilder.create().uv(0, 19).cuboid(1.0F, -1.0F, -4.0F, 3, 2, 5), ModelTransform.of(0.0F, 23.0F, 0.0F, 0.0F, -0.1745F, 0.0F));
		modelPartData.addChild("right_foot", ModelPartBuilder.create().uv(20, 11).cuboid(-4.0F, -1.0F, -4.0F, 3, 2, 5), ModelTransform.of(0.0F, 23.0F, 0.0F, 0.0F, 0.1745F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float customAngle, float netHeadYaw, float headPitch) {
		leftFoot.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		rightFoot.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		body.yaw = netHeadYaw * 0.017453292F;
		body.pitch = headPitch * 0.017453292F;
	}

	@Override
	public Iterable<ModelPart> getParts() {
		return ImmutableList.of(this.body, this.leftFoot, this.rightFoot);
	}
}