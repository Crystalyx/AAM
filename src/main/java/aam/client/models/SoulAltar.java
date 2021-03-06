package aam.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Altar - Lord_Crystalyx Created using Tabula 4.1.1
 */
public class SoulAltar extends ModelBase
{
	public double[] modelScale = new double[] { 4.0D, 4.0D, 4.0D };
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape5;
	public ModelRenderer shape6;
	public ModelRenderer shape7;
	public ModelRenderer shape8;
	public ModelRenderer shape9;

	public SoulAltar()
	{
		textureWidth = 512;
		textureHeight = 256;
		shape4 = new ModelRenderer(this, 0, 0);
		shape4.setRotationPoint(-24.0F, 53.0F, -28.0F);
		shape4.addBox(-2.0F, -2.0F, -2.0F, 52, 4, 4, 0.0F);
		shape9 = new ModelRenderer(this, 0, 18);
		shape9.setRotationPoint(-28.0F, 92.0F, -28.0F);
		shape9.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 60, 0.0F);
		shape1 = new ModelRenderer(this, 0, 159);
		shape1.setRotationPoint(0.0F, 72.0F, 0.0F);
		shape1.addBox(-28.0F, -20.0F, -28.0F, 56, 40, 56, 0.0F);
		shape5 = new ModelRenderer(this, 0, 0);
		shape5.setRotationPoint(-24.0F, 92.0F, -28.0F);
		shape5.addBox(-2.0F, -2.0F, -2.0F, 52, 4, 4, 0.0F);
		shape3 = new ModelRenderer(this, 0, 18);
		shape3.setRotationPoint(28.0F, 53.0F, -28.0F);
		shape3.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 60, 0.0F);
		shape6 = new ModelRenderer(this, 0, 0);
		shape6.setRotationPoint(-24.0F, 92.0F, 28.0F);
		shape6.addBox(-2.0F, -2.0F, -2.0F, 52, 4, 4, 0.0F);
		shape8 = new ModelRenderer(this, 0, 18);
		shape8.setRotationPoint(-28.0F, 53.0F, -28.0F);
		shape8.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 60, 0.0F);
		shape7 = new ModelRenderer(this, 0, 18);
		shape7.setRotationPoint(28.0F, 92.0F, -28.0F);
		shape7.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 60, 0.0F);
		shape2 = new ModelRenderer(this, 0, 0);
		shape2.setRotationPoint(-24.0F, 53.0F, 28.0F);
		shape2.addBox(-2.0F, -2.0F, -2.0F, 52, 4, 4, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
		shape4.render(f5);
		shape9.render(f5);
		shape1.render(f5);
		shape5.render(f5);
		shape3.render(f5);
		shape6.render(f5);
		shape8.render(f5);
		shape7.render(f5);
		shape2.render(f5);
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
