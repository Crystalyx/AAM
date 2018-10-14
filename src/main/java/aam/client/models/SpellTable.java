package aam.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SpellTable - Lord_Crystalyx Created using Tabula 4.1.1
 */
public class SpellTable extends ModelBase
{
	public double[] modelScale = new double[] { 2.0D, 2.0D, 2.0D };
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape5;
	public ModelRenderer shape6;
	public ModelRenderer shape7;
	public ModelRenderer shape8;
	public ModelRenderer shape9;
	public ModelRenderer shape10;

	public SpellTable()
	{
		textureWidth = 256;
		textureHeight = 128;
		shape3 = new ModelRenderer(this, 0, 3);
		shape3.setRotationPoint(0.0F, 23.0F, 0.0F);
		shape3.addBox(-8.0F, 0.0F, -8.0F, 16, 25, 2, 0.0F);
		shape10 = new ModelRenderer(this, 3, 55);
		shape10.setRotationPoint(0.0F, 46.0F, 0.0F);
		shape10.addBox(-9.0F, 0.0F, -9.0F, 18, 1, 19, 0.0F);
		shape1 = new ModelRenderer(this, 0, 101);
		shape1.setRotationPoint(0.0F, 20.0F, 0.0F);
		shape1.addBox(-13.0F, 0.0F, -13.0F, 26, 1, 26, 0.0F);
		this.setRotateAngle(shape1, 0.32288591161895097F, 0.0F, 0.0F);
		shape9 = new ModelRenderer(this, 0, 54);
		shape9.setRotationPoint(0.0F, 47.0F, 0.0F);
		shape9.addBox(-10.0F, 0.0F, -10.0F, 20, 1, 20, 0.0F);
		shape6 = new ModelRenderer(this, 39, -1);
		shape6.setRotationPoint(0.0F, 20.0F, 8.0F);
		shape6.addBox(-8.0F, 0.0F, -8.0F, 16, 28, 3, 0.0F);
		shape2 = new ModelRenderer(this, 27, 126);
		shape2.setRotationPoint(-12.0F, 19.0F, 0.0F);
		shape2.addBox(0.0F, 0.0F, -12.0F, 24, 1, 1, 0.0F);
		this.setRotateAngle(shape2, 0.32288591161895097F, 0.0F, 0.0F);
		shape7 = new ModelRenderer(this, 77, -2);
		shape7.setRotationPoint(0.0F, 19.0F, 11.0F);
		shape7.addBox(-8.0F, 0.0F, -8.0F, 16, 29, 3, 0.0F);
		shape4 = new ModelRenderer(this, 151, 1);
		shape4.setRotationPoint(0.0F, 22.0F, 2.0F);
		shape4.addBox(-8.0F, 0.0F, -8.0F, 16, 26, 3, 0.0F);
		this.setRotateAngle(shape4, 0.0F, 0.006981317007977318F, 0.0F);
		shape8 = new ModelRenderer(this, 115, -2);
		shape8.setRotationPoint(0.0F, 18.0F, 14.0F);
		shape8.addBox(-8.0F, 0.0F, -8.0F, 16, 30, 2, 0.0F);
		shape5 = new ModelRenderer(this, 190, 0);
		shape5.setRotationPoint(0.0F, 21.0F, 5.0F);
		shape5.addBox(-8.0F, 0.0F, -8.0F, 16, 27, 3, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
		shape3.render(f5);
		shape10.render(f5);
		shape1.render(f5);
		shape9.render(f5);
		shape6.render(f5);
		shape2.render(f5);
		shape7.render(f5);
		shape4.render(f5);
		shape8.render(f5);
		shape5.render(f5);
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
