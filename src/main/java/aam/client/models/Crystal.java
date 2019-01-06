package aam.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Crystal - Lord_Crystalyx Created using Tabula 4.1.1
 */
public class Crystal extends ModelBase
{
	public double[] modelScale = new double[] { 4.0D, 4.0D, 4.0D };
	public ModelRenderer shape00;

	public Crystal()
	{
		textureWidth = 256;
		textureHeight = 128;
		shape00 = new ModelRenderer(this, 0, 20);
		shape00.setRotationPoint(0.0F, -10.0F, 0.0F);
		shape00.addBox(-20.0F, -20.0F, -20.0F, 40, 40, 40, 0.0F);
		this.setRotateAngle(shape00, 0.0F, 0.7853981633974483F, 0.9552186996164964F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
		GL11.glPushMatrix();
		GL11.glTranslatef(shape00.offsetX, shape00.offsetY, shape00.offsetZ);
		GL11.glTranslatef(shape00.rotationPointX * f5, shape00.rotationPointY * f5, shape00.rotationPointZ * f5);
		GL11.glScaled(1.0D, 2.0D, 1.0D);
		GL11.glTranslatef(-shape00.offsetX, -shape00.offsetY, -shape00.offsetZ);
		GL11.glTranslatef(-shape00.rotationPointX * f5, -shape00.rotationPointY * f5, -shape00.rotationPointZ * f5);
		shape00.render(f5);
		GL11.glPopMatrix();
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
