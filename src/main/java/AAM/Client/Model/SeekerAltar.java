package AAM.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class SeekerAltar extends ModelBase
{
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape2_1;
	public ModelRenderer shape5;
	public ModelRenderer shape6;
	public ModelRenderer shape7;
	public ModelRenderer shape7_1;
	public ModelRenderer shape2_2;

	public SeekerAltar()
	{
		this.textureWidth = 512;
		this.textureHeight = 256;
		this.shape2 = new ModelRenderer(this, 0, 198);
		this.shape2.mirror = true;
		this.shape2.setRotationPoint(4.0F, 11.0F, -8.0F);
		this.shape2.addBox(0.0F, 0.0F, 0.0F, 16, 8, 48, 0.0F);
		this.shape1 = new ModelRenderer(this, 286, 162);
		this.shape1.setRotationPoint(-8.0F, 13.0F, -8.0F);
		this.shape1.addBox(0.0F, 0.0F, 0.0F, 64, 44, 48, 0.0F);
		this.shape6 = new ModelRenderer(this, 0, 89);
		this.shape6.setRotationPoint(-0.5F, 5.0F, -6.5F);
		this.shape6.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
		this.shape7 = new ModelRenderer(this, 0, 50);
		this.shape7.setRotationPoint(-0.5F, 3.95F, -6.25F);
		this.shape7.addBox(0.0F, 0.0F, 0.0F, 16, 8, 2, 0.0F);
		this.setRotateAngle(shape7, 0.5497787143782138F, -0.03051939125806341F, -0.20943951023931953F);
		this.shape5 = new ModelRenderer(this, 0, 137);
		this.shape5.setRotationPoint(-1.0F, 8.0F, -7.0F);
		this.shape5.addBox(0.0F, 0.0F, 0.0F, 8, 4, 8, 0.0F);
		this.shape7_1 = new ModelRenderer(this, 0, 50);
		this.shape7_1.setRotationPoint(-3.5F, 2.5F, -4.25F);
		this.shape7_1.addBox(1.0F, -2.2F, -8.0F, 16, 8, 2, 0.0F);
		this.setRotateAngle(shape7_1, 0.5497787143782138F, 0.0F, 0.20943951023931953F);
		this.shape2_2 = new ModelRenderer(this, 0, 198);
		this.shape2_2.setRotationPoint(-8.0F, 11.0F, -8.0F);
		this.shape2_2.addBox(0.0F, 0.0F, 0.0F, 16, 8, 48, 0.0F);
		this.shape2_1 = new ModelRenderer(this, 0, 162);
		this.shape2_1.setRotationPoint(-8.0F, 9.0F, -8.0F);
		this.shape2_1.addBox(0.0F, 0.0F, 0.0F, 64, 16, 20, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape2.offsetX, this.shape2.offsetY, this.shape2.offsetZ);
		GL11.glTranslatef(this.shape2.rotationPointX * f5, this.shape2.rotationPointY * f5, this.shape2.rotationPointZ * f5);
		GL11.glScaled(0.25D, 0.25D, 0.25D);
		GL11.glTranslatef(-this.shape2.offsetX, -this.shape2.offsetY, -this.shape2.offsetZ);
		GL11.glTranslatef(-this.shape2.rotationPointX * f5, -this.shape2.rotationPointY * f5, -this.shape2.rotationPointZ * f5);
		this.shape2.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape1.offsetX, this.shape1.offsetY, this.shape1.offsetZ);
		GL11.glTranslatef(this.shape1.rotationPointX * f5, this.shape1.rotationPointY * f5, this.shape1.rotationPointZ * f5);
		GL11.glScaled(0.25D, 0.25D, 0.25D);
		GL11.glTranslatef(-this.shape1.offsetX, -this.shape1.offsetY, -this.shape1.offsetZ);
		GL11.glTranslatef(-this.shape1.rotationPointX * f5, -this.shape1.rotationPointY * f5, -this.shape1.rotationPointZ * f5);
		this.shape1.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape6.offsetX, this.shape6.offsetY, this.shape6.offsetZ);
		GL11.glTranslatef(this.shape6.rotationPointX * f5, this.shape6.rotationPointY * f5, this.shape6.rotationPointZ * f5);
		GL11.glScaled(0.25D, 0.25D, 0.25D);
		GL11.glTranslatef(-this.shape6.offsetX, -this.shape6.offsetY, -this.shape6.offsetZ);
		GL11.glTranslatef(-this.shape6.rotationPointX * f5, -this.shape6.rotationPointY * f5, -this.shape6.rotationPointZ * f5);
		this.shape6.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape7.offsetX, this.shape7.offsetY, this.shape7.offsetZ);
		GL11.glTranslatef(this.shape7.rotationPointX * f5, this.shape7.rotationPointY * f5, this.shape7.rotationPointZ * f5);
		GL11.glScaled(0.25D, 0.25D, 0.25D);
		GL11.glTranslatef(-this.shape7.offsetX, -this.shape7.offsetY, -this.shape7.offsetZ);
		GL11.glTranslatef(-this.shape7.rotationPointX * f5, -this.shape7.rotationPointY * f5, -this.shape7.rotationPointZ * f5);
		this.shape7.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape5.offsetX, this.shape5.offsetY, this.shape5.offsetZ);
		GL11.glTranslatef(this.shape5.rotationPointX * f5, this.shape5.rotationPointY * f5, this.shape5.rotationPointZ * f5);
		GL11.glScaled(0.25D, 0.25D, 0.25D);
		GL11.glTranslatef(-this.shape5.offsetX, -this.shape5.offsetY, -this.shape5.offsetZ);
		GL11.glTranslatef(-this.shape5.rotationPointX * f5, -this.shape5.rotationPointY * f5, -this.shape5.rotationPointZ * f5);
		this.shape5.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape7_1.offsetX, this.shape7_1.offsetY, this.shape7_1.offsetZ);
		GL11.glTranslatef(this.shape7_1.rotationPointX * f5, this.shape7_1.rotationPointY * f5, this.shape7_1.rotationPointZ * f5);
		GL11.glScaled(0.25D, 0.25D, 0.25D);
		GL11.glTranslatef(-this.shape7_1.offsetX, -this.shape7_1.offsetY, -this.shape7_1.offsetZ);
		GL11.glTranslatef(-this.shape7_1.rotationPointX * f5, -this.shape7_1.rotationPointY * f5, -this.shape7_1.rotationPointZ * f5);
		this.shape7_1.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape2_2.offsetX, this.shape2_2.offsetY, this.shape2_2.offsetZ);
		GL11.glTranslatef(this.shape2_2.rotationPointX * f5, this.shape2_2.rotationPointY * f5, this.shape2_2.rotationPointZ * f5);
		GL11.glScaled(0.25D, 0.25D, 0.25D);
		GL11.glTranslatef(-this.shape2_2.offsetX, -this.shape2_2.offsetY, -this.shape2_2.offsetZ);
		GL11.glTranslatef(-this.shape2_2.rotationPointX * f5, -this.shape2_2.rotationPointY * f5, -this.shape2_2.rotationPointZ * f5);
		this.shape2_2.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape2_1.offsetX, this.shape2_1.offsetY, this.shape2_1.offsetZ);
		GL11.glTranslatef(this.shape2_1.rotationPointX * f5, this.shape2_1.rotationPointY * f5, this.shape2_1.rotationPointZ * f5);
		GL11.glScaled(0.25D, 0.25D, 0.25D);
		GL11.glTranslatef(-this.shape2_1.offsetX, -this.shape2_1.offsetY, -this.shape2_1.offsetZ);
		GL11.glTranslatef(-this.shape2_1.rotationPointX * f5, -this.shape2_1.rotationPointY * f5, -this.shape2_1.rotationPointZ * f5);
		this.shape2_1.render(f5);
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
