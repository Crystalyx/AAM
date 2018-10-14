package aam.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Cauldron - Lord_Crystalyx Created using Tabula 4.1.1
 */
public class Cauldron extends ModelBase
{
	public double[] modelScale = new double[] { 2.0D, 2.0D, 2.0D };
	public ModelRenderer Leg1;
	public ModelRenderer Leg2;
	public ModelRenderer Leg3;
	public ModelRenderer Leg4;
	public ModelRenderer Bottom;
	public ModelRenderer Side1;
	public ModelRenderer Side2;
	public ModelRenderer Side3;
	public ModelRenderer Side4;
	public ModelRenderer Fluid;

	public Cauldron()
	{
		textureWidth = 128;
		textureHeight = 64;
		Leg3 = new ModelRenderer(this, 0, 0);
		Leg3.setRotationPoint(10.0F, 42.0F, 10.0F);
		Leg3.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
		Side2 = new ModelRenderer(this, 0, 30);
		Side2.setRotationPoint(14.0F, 20.0F, -12.0F);
		Side2.addBox(0.0F, 0.0F, 0.0F, 26, 20, 2, 0.0F);
		this.setRotateAngle(Side2, 0.0F, -1.5707963267948966F, 0.0F);
		Leg1 = new ModelRenderer(this, 0, 0);
		Leg1.setRotationPoint(-13.0F, 42.0F, 10.0F);
		Leg1.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
		Side4 = new ModelRenderer(this, 0, 30);
		Side4.setRotationPoint(12.0F, 20.0F, 14.0F);
		Side4.addBox(0.0F, 0.0F, 0.0F, 26, 20, 2, 0.0F);
		this.setRotateAngle(Side4, 0.0F, 3.141592653589793F, 0.0F);
		Leg2 = new ModelRenderer(this, 0, 0);
		Leg2.setRotationPoint(10.0F, 42.0F, -13.0F);
		Leg2.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
		Leg4 = new ModelRenderer(this, 0, 0);
		Leg4.setRotationPoint(-13.0F, 42.0F, -13.0F);
		Leg4.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
		Side1 = new ModelRenderer(this, 0, 30);
		Side1.setRotationPoint(-14.0F, 20.0F, 12.0F);
		Side1.addBox(0.0F, 0.0F, 0.0F, 26, 20, 2, 0.0F);
		this.setRotateAngle(Side1, 0.0F, 1.5707963267948966F, 0.0F);
		Bottom = new ModelRenderer(this, 0, 0);
		Bottom.setRotationPoint(-14.0F, 40.0F, -14.0F);
		Bottom.addBox(0.0F, 0.0F, 0.0F, 28, 2, 28, 0.0F);
		Side3 = new ModelRenderer(this, 0, 30);
		Side3.setRotationPoint(-12.0F, 20.0F, -14.0F);
		Side3.addBox(0.0F, 0.0F, 0.0F, 26, 20, 2, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
		Leg3.render(f5);
		Side2.render(f5);
		Leg1.render(f5);
		Side4.render(f5);
		Leg2.render(f5);
		Leg4.render(f5);
		Side1.render(f5);
		Bottom.render(f5);
		Side3.render(f5);
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
