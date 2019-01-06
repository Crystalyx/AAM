package aam.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class Altar extends ModelBase
{
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape5;
	public ModelRenderer shape6;
	public ModelRenderer shape7;

	public Altar()
	{
		textureWidth = 128;
		textureHeight = 64;
		shape7 = new ModelRenderer(this, 0, 0);
		shape7.setRotationPoint(4.5F, 14.0F, -4.0F);
		shape7.addBox(0.0F, 0.0F, 0.2F, 4, 20, 4, 0.0F);
		shape6 = new ModelRenderer(this, 56, 0);
		shape6.setRotationPoint(-6.5F, 14.0F, -4.0F);
		shape6.addBox(0.0F, 0.0F, 0.2F, 4, 20, 4, 0.0F);
		shape1 = new ModelRenderer(this, 16, 0);
		shape1.setRotationPoint(-1.5F, 11.0F, -1.5F);
		shape1.addBox(0.0F, 0.0F, 0.0F, 6, 26, 6, 0.0F);
		shape5 = new ModelRenderer(this, 72, 0);
		shape5.setRotationPoint(-6.5F, 14.0F, 2.0F);
		shape5.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
		shape2 = new ModelRenderer(this, 88, 0);
		shape2.setRotationPoint(-1.0F, 14.0F, 5.0F);
		shape2.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
		shape3 = new ModelRenderer(this, 40, 0);
		shape3.setRotationPoint(-1.0F, 14.0F, -7.0F);
		shape3.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
		shape4 = new ModelRenderer(this, 104, 0);
		shape4.setRotationPoint(4.5F, 14.0F, 2.0F);
		shape4.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(shape7.offsetX, shape7.offsetY, shape7.offsetZ);
		GL11.glTranslatef(shape7.rotationPointX * f5, shape7.rotationPointY * f5, shape7.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape7.offsetX, -shape7.offsetY, -shape7.offsetZ);
		GL11.glTranslatef(-shape7.rotationPointX * f5, -shape7.rotationPointY * f5, -shape7.rotationPointZ * f5);
		shape7.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape6.offsetX, shape6.offsetY, shape6.offsetZ);
		GL11.glTranslatef(shape6.rotationPointX * f5, shape6.rotationPointY * f5, shape6.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape6.offsetX, -shape6.offsetY, -shape6.offsetZ);
		GL11.glTranslatef(-shape6.rotationPointX * f5, -shape6.rotationPointY * f5, -shape6.rotationPointZ * f5);
		shape6.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape1.offsetX, shape1.offsetY, shape1.offsetZ);
		GL11.glTranslatef(shape1.rotationPointX * f5, shape1.rotationPointY * f5, shape1.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape1.offsetX, -shape1.offsetY, -shape1.offsetZ);
		GL11.glTranslatef(-shape1.rotationPointX * f5, -shape1.rotationPointY * f5, -shape1.rotationPointZ * f5);
		shape1.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape5.offsetX, shape5.offsetY, shape5.offsetZ);
		GL11.glTranslatef(shape5.rotationPointX * f5, shape5.rotationPointY * f5, shape5.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape5.offsetX, -shape5.offsetY, -shape5.offsetZ);
		GL11.glTranslatef(-shape5.rotationPointX * f5, -shape5.rotationPointY * f5, -shape5.rotationPointZ * f5);
		shape5.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape2.offsetX, shape2.offsetY, shape2.offsetZ);
		GL11.glTranslatef(shape2.rotationPointX * f5, shape2.rotationPointY * f5, shape2.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape2.offsetX, -shape2.offsetY, -shape2.offsetZ);
		GL11.glTranslatef(-shape2.rotationPointX * f5, -shape2.rotationPointY * f5, -shape2.rotationPointZ * f5);
		shape2.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape3.offsetX, shape3.offsetY, shape3.offsetZ);
		GL11.glTranslatef(shape3.rotationPointX * f5, shape3.rotationPointY * f5, shape3.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape3.offsetX, -shape3.offsetY, -shape3.offsetZ);
		GL11.glTranslatef(-shape3.rotationPointX * f5, -shape3.rotationPointY * f5, -shape3.rotationPointZ * f5);
		shape3.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape4.offsetX, shape4.offsetY, shape4.offsetZ);
		GL11.glTranslatef(shape4.rotationPointX * f5, shape4.rotationPointY * f5, shape4.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape4.offsetX, -shape4.offsetY, -shape4.offsetZ);
		GL11.glTranslatef(-shape4.rotationPointX * f5, -shape4.rotationPointY * f5, -shape4.rotationPointZ * f5);
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
