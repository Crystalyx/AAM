package aam.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class Graviter extends ModelBase
{
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
	public ModelRenderer shape11;
	public ModelRenderer shape12;
	public ModelRenderer shape12_1;
	public ModelRenderer shape12_2;
	public ModelRenderer shape12_3;

	public Graviter()
	{
		textureWidth = 128;
		textureHeight = 64;
		shape9 = new ModelRenderer(this, 0, 9);
		shape9.setRotationPoint(-0.5F, 17.0F, -0.5F);
		shape9.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
		shape6 = new ModelRenderer(this, 0, 48);
		shape6.setRotationPoint(-2.0F, 20.0F, -2.0F);
		shape6.addBox(0.0F, 0.0F, 0.0F, 8, 4, 8, 0.0F);
		shape8 = new ModelRenderer(this, 0, 26);
		shape8.setRotationPoint(-1.0F, 18.0F, -1.0F);
		shape8.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
		shape12_3 = new ModelRenderer(this, 16, 0);
		shape12_3.setRotationPoint(-8.0F, 9.0F, -8.0F);
		shape12_3.addBox(0.0F, 0.0F, 0.0F, 4, 28, 4, 0.0F);
		shape4 = new ModelRenderer(this, 32, 12);
		shape4.setRotationPoint(-4.0F, 22.0F, -4.0F);
		shape4.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16, 0.0F);
		shape11 = new ModelRenderer(this, 36, 0);
		shape11.setRotationPoint(0.0F, 14.8F, 0.0F);
		shape11.addBox(0.7F, 0.7F, -4.6F, 4, 4, 4, 0.0F);
		this.setRotateAngle(shape11, 0.7853981633974483F, 0.0F, 0.6154030942532006F);
		shape12_2 = new ModelRenderer(this, 16, 0);
		shape12_2.setRotationPoint(6.0F, 9.0F, -8.0F);
		shape12_2.addBox(0.0F, 0.0F, 0.0F, 4, 28, 4, 0.0F);
		shape12_1 = new ModelRenderer(this, 16, 0);
		shape12_1.setRotationPoint(-8.0F, 9.0F, 6.0F);
		shape12_1.addBox(0.0F, 0.0F, 0.0F, 4, 28, 4, 0.0F);
		shape2 = new ModelRenderer(this, 0, 30);
		shape2.mirror = true;
		shape2.setRotationPoint(-8.0F, 8.0F, -8.0F);
		shape2.addBox(0.0F, 0.0F, 0.0F, 32, 2, 32, 0.0F);
		shape7 = new ModelRenderer(this, 0, 26);
		shape7.setRotationPoint(-1.0F, 12.0F, -1.0F);
		shape7.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
		shape1 = new ModelRenderer(this, 0, 30);
		shape1.setRotationPoint(-8.0F, 23.0F, -8.0F);
		shape1.addBox(0.0F, 0.0F, 0.0F, 32, 2, 32, 0.0F);
		shape10 = new ModelRenderer(this, 0, 9);
		shape10.setRotationPoint(-0.5F, 14.0F, -0.5F);
		shape10.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
		shape5 = new ModelRenderer(this, 0, 48);
		shape5.setRotationPoint(-2.0F, 10.0F, -2.0F);
		shape5.addBox(0.0F, 0.0F, 0.0F, 8, 4, 8, 0.0F);
		shape12 = new ModelRenderer(this, 16, 0);
		shape12.setRotationPoint(6.0F, 9.0F, 6.0F);
		shape12.addBox(0.0F, 0.0F, 0.0F, 4, 28, 4, 0.0F);
		shape3 = new ModelRenderer(this, 32, 12);
		shape3.mirror = true;
		shape3.setRotationPoint(-4.0F, 9.0F, -4.0F);
		shape3.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(shape9.offsetX, shape9.offsetY, shape9.offsetZ);
		GL11.glTranslatef(shape9.rotationPointX * f5, shape9.rotationPointY * f5, shape9.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape9.offsetX, -shape9.offsetY, -shape9.offsetZ);
		GL11.glTranslatef(-shape9.rotationPointX * f5, -shape9.rotationPointY * f5, -shape9.rotationPointZ * f5);
		shape9.render(f5);
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
		GL11.glTranslatef(shape8.offsetX, shape8.offsetY, shape8.offsetZ);
		GL11.glTranslatef(shape8.rotationPointX * f5, shape8.rotationPointY * f5, shape8.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape8.offsetX, -shape8.offsetY, -shape8.offsetZ);
		GL11.glTranslatef(-shape8.rotationPointX * f5, -shape8.rotationPointY * f5, -shape8.rotationPointZ * f5);
		shape8.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape12_3.offsetX, shape12_3.offsetY, shape12_3.offsetZ);
		GL11.glTranslatef(shape12_3.rotationPointX * f5, shape12_3.rotationPointY * f5, shape12_3.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape12_3.offsetX, -shape12_3.offsetY, -shape12_3.offsetZ);
		GL11.glTranslatef(-shape12_3.rotationPointX * f5, -shape12_3.rotationPointY * f5, -shape12_3.rotationPointZ * f5);
		shape12_3.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape4.offsetX, shape4.offsetY, shape4.offsetZ);
		GL11.glTranslatef(shape4.rotationPointX * f5, shape4.rotationPointY * f5, shape4.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape4.offsetX, -shape4.offsetY, -shape4.offsetZ);
		GL11.glTranslatef(-shape4.rotationPointX * f5, -shape4.rotationPointY * f5, -shape4.rotationPointZ * f5);
		shape4.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape11.offsetX, shape11.offsetY, shape11.offsetZ);
		GL11.glTranslatef(shape11.rotationPointX * f5, shape11.rotationPointY * f5, shape11.rotationPointZ * f5);
		GL11.glScaled(0.13D, 0.25D, 0.13D);
		GL11.glTranslatef(-shape11.offsetX, -shape11.offsetY, -shape11.offsetZ);
		GL11.glTranslatef(-shape11.rotationPointX * f5, -shape11.rotationPointY * f5, -shape11.rotationPointZ * f5);
		GL11.glRotatef(Minecraft.getSystemTime() % 2880 / 8, 0, 1, 0);

		shape11.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape12_2.offsetX, shape12_2.offsetY, shape12_2.offsetZ);
		GL11.glTranslatef(shape12_2.rotationPointX * f5, shape12_2.rotationPointY * f5, shape12_2.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape12_2.offsetX, -shape12_2.offsetY, -shape12_2.offsetZ);
		GL11.glTranslatef(-shape12_2.rotationPointX * f5, -shape12_2.rotationPointY * f5, -shape12_2.rotationPointZ * f5);
		shape12_2.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape12_1.offsetX, shape12_1.offsetY, shape12_1.offsetZ);
		GL11.glTranslatef(shape12_1.rotationPointX * f5, shape12_1.rotationPointY * f5, shape12_1.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape12_1.offsetX, -shape12_1.offsetY, -shape12_1.offsetZ);
		GL11.glTranslatef(-shape12_1.rotationPointX * f5, -shape12_1.rotationPointY * f5, -shape12_1.rotationPointZ * f5);
		shape12_1.render(f5);
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
		GL11.glTranslatef(shape7.offsetX, shape7.offsetY, shape7.offsetZ);
		GL11.glTranslatef(shape7.rotationPointX * f5, shape7.rotationPointY * f5, shape7.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape7.offsetX, -shape7.offsetY, -shape7.offsetZ);
		GL11.glTranslatef(-shape7.rotationPointX * f5, -shape7.rotationPointY * f5, -shape7.rotationPointZ * f5);
		shape7.render(f5);
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
		GL11.glTranslatef(shape10.offsetX, shape10.offsetY, shape10.offsetZ);
		GL11.glTranslatef(shape10.rotationPointX * f5, shape10.rotationPointY * f5, shape10.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape10.offsetX, -shape10.offsetY, -shape10.offsetZ);
		GL11.glTranslatef(-shape10.rotationPointX * f5, -shape10.rotationPointY * f5, -shape10.rotationPointZ * f5);
		shape10.render(f5);
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
		GL11.glTranslatef(shape12.offsetX, shape12.offsetY, shape12.offsetZ);
		GL11.glTranslatef(shape12.rotationPointX * f5, shape12.rotationPointY * f5, shape12.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape12.offsetX, -shape12.offsetY, -shape12.offsetZ);
		GL11.glTranslatef(-shape12.rotationPointX * f5, -shape12.rotationPointY * f5, -shape12.rotationPointZ * f5);
		shape12.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(shape3.offsetX, shape3.offsetY, shape3.offsetZ);
		GL11.glTranslatef(shape3.rotationPointX * f5, shape3.rotationPointY * f5, shape3.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-shape3.offsetX, -shape3.offsetY, -shape3.offsetZ);
		GL11.glTranslatef(-shape3.rotationPointX * f5, -shape3.rotationPointY * f5, -shape3.rotationPointZ * f5);
		shape3.render(f5);
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
