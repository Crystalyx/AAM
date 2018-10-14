package aam.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Hammer - Lord_Crystalyx Created using Tabula 4.1.1
 */
public class Hammer extends ModelBase
{
	public double[] modelScale = new double[] { 4.0D, 4.0D, 4.0D };
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape5;
	public ModelRenderer shape6;

	public Hammer()
	{
		textureWidth = 128;
		textureHeight = 64;
		shape5 = new ModelRenderer(this, 0, 0);
		shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape5.addBox(-5.0F, -5.0F, 14.0F, 10, 10, 1, 0.0F);
		shape3 = new ModelRenderer(this, 0, 21);
		shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape3.addBox(-6.0F, -6.0F, 7.0F, 12, 12, 7, 0.0F);
		shape1 = new ModelRenderer(this, 112, 0);
		shape1.setRotationPoint(0.0F, -6.0F, 0.0F);
		shape1.addBox(-2.0F, 0.0F, -2.0F, 4, 54, 4, 0.0F);
		shape2 = new ModelRenderer(this, 0, 40);
		shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape2.addBox(-5.0F, -5.0F, -7.0F, 10, 10, 14, 0.0F);
		shape6 = new ModelRenderer(this, 0, 0);
		shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape6.addBox(-5.0F, -5.0F, -15.0F, 10, 10, 1, 0.0F);
		shape4 = new ModelRenderer(this, 0, 21);
		shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
		shape4.addBox(-6.0F, -6.0F, -14.0F, 12, 12, 7, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
		shape5.render(f5);
		shape3.render(f5);
		shape1.render(f5);
		shape2.render(f5);
		shape6.render(f5);
		shape4.render(f5);
		GL11.glPopMatrix();
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
