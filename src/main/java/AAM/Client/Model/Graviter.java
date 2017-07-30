package AAM.Client.Model;

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
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.shape9 = new ModelRenderer(this, 0, 9);
		this.shape9.setRotationPoint(-0.5F, 17.0F, -0.5F);
		this.shape9.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
		this.shape6 = new ModelRenderer(this, 0, 48);
		this.shape6.setRotationPoint(-2.0F, 20.0F, -2.0F);
		this.shape6.addBox(0.0F, 0.0F, 0.0F, 8, 4, 8, 0.0F);
		this.shape8 = new ModelRenderer(this, 0, 26);
		this.shape8.setRotationPoint(-1.0F, 18.0F, -1.0F);
		this.shape8.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
		this.shape12_3 = new ModelRenderer(this, 16, 0);
		this.shape12_3.setRotationPoint(-8.0F, 9.0F, -8.0F);
		this.shape12_3.addBox(0.0F, 0.0F, 0.0F, 4, 28, 4, 0.0F);
		this.shape4 = new ModelRenderer(this, 32, 12);
		this.shape4.setRotationPoint(-4.0F, 22.0F, -4.0F);
		this.shape4.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16, 0.0F);
		this.shape11 = new ModelRenderer(this, 36, 0);
		this.shape11.setRotationPoint(0.0F, 14.8F, 0.0F);
		this.shape11.addBox(0.7F, 0.7F, -4.6F, 4, 4, 4, 0.0F);
		this.setRotateAngle(shape11, 0.7853981633974483F, 0.0F, 0.6154030942532006F);
		this.shape12_2 = new ModelRenderer(this, 16, 0);
		this.shape12_2.setRotationPoint(6.0F, 9.0F, -8.0F);
		this.shape12_2.addBox(0.0F, 0.0F, 0.0F, 4, 28, 4, 0.0F);
		this.shape12_1 = new ModelRenderer(this, 16, 0);
		this.shape12_1.setRotationPoint(-8.0F, 9.0F, 6.0F);
		this.shape12_1.addBox(0.0F, 0.0F, 0.0F, 4, 28, 4, 0.0F);
		this.shape2 = new ModelRenderer(this, 0, 30);
		this.shape2.mirror = true;
		this.shape2.setRotationPoint(-8.0F, 8.0F, -8.0F);
		this.shape2.addBox(0.0F, 0.0F, 0.0F, 32, 2, 32, 0.0F);
		this.shape7 = new ModelRenderer(this, 0, 26);
		this.shape7.setRotationPoint(-1.0F, 12.0F, -1.0F);
		this.shape7.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
		this.shape1 = new ModelRenderer(this, 0, 30);
		this.shape1.setRotationPoint(-8.0F, 23.0F, -8.0F);
		this.shape1.addBox(0.0F, 0.0F, 0.0F, 32, 2, 32, 0.0F);
		this.shape10 = new ModelRenderer(this, 0, 9);
		this.shape10.setRotationPoint(-0.5F, 14.0F, -0.5F);
		this.shape10.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
		this.shape5 = new ModelRenderer(this, 0, 48);
		this.shape5.setRotationPoint(-2.0F, 10.0F, -2.0F);
		this.shape5.addBox(0.0F, 0.0F, 0.0F, 8, 4, 8, 0.0F);
		this.shape12 = new ModelRenderer(this, 16, 0);
		this.shape12.setRotationPoint(6.0F, 9.0F, 6.0F);
		this.shape12.addBox(0.0F, 0.0F, 0.0F, 4, 28, 4, 0.0F);
		this.shape3 = new ModelRenderer(this, 32, 12);
		this.shape3.mirror = true;
		this.shape3.setRotationPoint(-4.0F, 9.0F, -4.0F);
		this.shape3.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape9.offsetX, this.shape9.offsetY, this.shape9.offsetZ);
		GL11.glTranslatef(this.shape9.rotationPointX * f5, this.shape9.rotationPointY * f5, this.shape9.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape9.offsetX, -this.shape9.offsetY, -this.shape9.offsetZ);
		GL11.glTranslatef(-this.shape9.rotationPointX * f5, -this.shape9.rotationPointY * f5, -this.shape9.rotationPointZ * f5);
		this.shape9.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape6.offsetX, this.shape6.offsetY, this.shape6.offsetZ);
		GL11.glTranslatef(this.shape6.rotationPointX * f5, this.shape6.rotationPointY * f5, this.shape6.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape6.offsetX, -this.shape6.offsetY, -this.shape6.offsetZ);
		GL11.glTranslatef(-this.shape6.rotationPointX * f5, -this.shape6.rotationPointY * f5, -this.shape6.rotationPointZ * f5);
		this.shape6.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape8.offsetX, this.shape8.offsetY, this.shape8.offsetZ);
		GL11.glTranslatef(this.shape8.rotationPointX * f5, this.shape8.rotationPointY * f5, this.shape8.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape8.offsetX, -this.shape8.offsetY, -this.shape8.offsetZ);
		GL11.glTranslatef(-this.shape8.rotationPointX * f5, -this.shape8.rotationPointY * f5, -this.shape8.rotationPointZ * f5);
		this.shape8.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape12_3.offsetX, this.shape12_3.offsetY, this.shape12_3.offsetZ);
		GL11.glTranslatef(this.shape12_3.rotationPointX * f5, this.shape12_3.rotationPointY * f5, this.shape12_3.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape12_3.offsetX, -this.shape12_3.offsetY, -this.shape12_3.offsetZ);
		GL11.glTranslatef(-this.shape12_3.rotationPointX * f5, -this.shape12_3.rotationPointY * f5, -this.shape12_3.rotationPointZ * f5);
		this.shape12_3.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape4.offsetX, this.shape4.offsetY, this.shape4.offsetZ);
		GL11.glTranslatef(this.shape4.rotationPointX * f5, this.shape4.rotationPointY * f5, this.shape4.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape4.offsetX, -this.shape4.offsetY, -this.shape4.offsetZ);
		GL11.glTranslatef(-this.shape4.rotationPointX * f5, -this.shape4.rotationPointY * f5, -this.shape4.rotationPointZ * f5);
		this.shape4.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape11.offsetX, this.shape11.offsetY, this.shape11.offsetZ);
		GL11.glTranslatef(this.shape11.rotationPointX * f5, this.shape11.rotationPointY * f5, this.shape11.rotationPointZ * f5);
		GL11.glScaled(0.13D, 0.25D, 0.13D);
		GL11.glTranslatef(-this.shape11.offsetX, -this.shape11.offsetY, -this.shape11.offsetZ);
		GL11.glTranslatef(-this.shape11.rotationPointX * f5, -this.shape11.rotationPointY * f5, -this.shape11.rotationPointZ * f5);
		GL11.glRotatef(Minecraft.getSystemTime() % 2880 / 8, 0, 1, 0);

		this.shape11.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape12_2.offsetX, this.shape12_2.offsetY, this.shape12_2.offsetZ);
		GL11.glTranslatef(this.shape12_2.rotationPointX * f5, this.shape12_2.rotationPointY * f5, this.shape12_2.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape12_2.offsetX, -this.shape12_2.offsetY, -this.shape12_2.offsetZ);
		GL11.glTranslatef(-this.shape12_2.rotationPointX * f5, -this.shape12_2.rotationPointY * f5, -this.shape12_2.rotationPointZ * f5);
		this.shape12_2.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape12_1.offsetX, this.shape12_1.offsetY, this.shape12_1.offsetZ);
		GL11.glTranslatef(this.shape12_1.rotationPointX * f5, this.shape12_1.rotationPointY * f5, this.shape12_1.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape12_1.offsetX, -this.shape12_1.offsetY, -this.shape12_1.offsetZ);
		GL11.glTranslatef(-this.shape12_1.rotationPointX * f5, -this.shape12_1.rotationPointY * f5, -this.shape12_1.rotationPointZ * f5);
		this.shape12_1.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape2.offsetX, this.shape2.offsetY, this.shape2.offsetZ);
		GL11.glTranslatef(this.shape2.rotationPointX * f5, this.shape2.rotationPointY * f5, this.shape2.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape2.offsetX, -this.shape2.offsetY, -this.shape2.offsetZ);
		GL11.glTranslatef(-this.shape2.rotationPointX * f5, -this.shape2.rotationPointY * f5, -this.shape2.rotationPointZ * f5);
		this.shape2.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape7.offsetX, this.shape7.offsetY, this.shape7.offsetZ);
		GL11.glTranslatef(this.shape7.rotationPointX * f5, this.shape7.rotationPointY * f5, this.shape7.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape7.offsetX, -this.shape7.offsetY, -this.shape7.offsetZ);
		GL11.glTranslatef(-this.shape7.rotationPointX * f5, -this.shape7.rotationPointY * f5, -this.shape7.rotationPointZ * f5);
		this.shape7.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape1.offsetX, this.shape1.offsetY, this.shape1.offsetZ);
		GL11.glTranslatef(this.shape1.rotationPointX * f5, this.shape1.rotationPointY * f5, this.shape1.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape1.offsetX, -this.shape1.offsetY, -this.shape1.offsetZ);
		GL11.glTranslatef(-this.shape1.rotationPointX * f5, -this.shape1.rotationPointY * f5, -this.shape1.rotationPointZ * f5);
		this.shape1.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape10.offsetX, this.shape10.offsetY, this.shape10.offsetZ);
		GL11.glTranslatef(this.shape10.rotationPointX * f5, this.shape10.rotationPointY * f5, this.shape10.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape10.offsetX, -this.shape10.offsetY, -this.shape10.offsetZ);
		GL11.glTranslatef(-this.shape10.rotationPointX * f5, -this.shape10.rotationPointY * f5, -this.shape10.rotationPointZ * f5);
		this.shape10.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape5.offsetX, this.shape5.offsetY, this.shape5.offsetZ);
		GL11.glTranslatef(this.shape5.rotationPointX * f5, this.shape5.rotationPointY * f5, this.shape5.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape5.offsetX, -this.shape5.offsetY, -this.shape5.offsetZ);
		GL11.glTranslatef(-this.shape5.rotationPointX * f5, -this.shape5.rotationPointY * f5, -this.shape5.rotationPointZ * f5);
		this.shape5.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape12.offsetX, this.shape12.offsetY, this.shape12.offsetZ);
		GL11.glTranslatef(this.shape12.rotationPointX * f5, this.shape12.rotationPointY * f5, this.shape12.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape12.offsetX, -this.shape12.offsetY, -this.shape12.offsetZ);
		GL11.glTranslatef(-this.shape12.rotationPointX * f5, -this.shape12.rotationPointY * f5, -this.shape12.rotationPointZ * f5);
		this.shape12.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape3.offsetX, this.shape3.offsetY, this.shape3.offsetZ);
		GL11.glTranslatef(this.shape3.rotationPointX * f5, this.shape3.rotationPointY * f5, this.shape3.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape3.offsetX, -this.shape3.offsetY, -this.shape3.offsetZ);
		GL11.glTranslatef(-this.shape3.rotationPointX * f5, -this.shape3.rotationPointY * f5, -this.shape3.rotationPointZ * f5);
		this.shape3.render(f5);
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
