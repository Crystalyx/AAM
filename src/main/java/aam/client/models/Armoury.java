package aam.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Armoury - Lord_Crystalyx Created using Tabula 4.1.1
 */
public class Armoury extends ModelBase
{
	public double[] modelScale = new double[] { 4.0D, 4.0D, 4.0D };
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape5;
	public ModelRenderer shape8;
	public ModelRenderer shape7;
	public ModelRenderer shape10;
	public ModelRenderer shape11;
	public ModelRenderer shape12;
	public ModelRenderer shape13;
	public ModelRenderer shape14;
	public ModelRenderer shape15;
	public ModelRenderer shape16;
	public ModelRenderer shape17;
	public ModelRenderer shape10h;
	public ModelRenderer shape11h;
	public ModelRenderer shape12h;
	public ModelRenderer shape13h;
	public ModelRenderer shape14h;
	public ModelRenderer shape15h;
	public ModelRenderer shape16h;
	public ModelRenderer shape17h;

	public Armoury()
	{
		textureWidth = 256;
		textureHeight = 128;
		shape10 = new ModelRenderer(this, 0, 0);
		shape10.setRotationPoint(0.0F, 55.0F, 0.0F);
		shape10.addBox(-3.0F, 10.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape10, 0.0F, 0.0F, 1.5707963267948966F);
		shape11 = new ModelRenderer(this, 0, 0);
		shape11.setRotationPoint(0.0F, 55.0F, 0.0F);
		shape11.addBox(-3.0F, 10.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape11, 0.7853981633974483F, 0.0F, 1.5707963267948966F);
		shape4 = new ModelRenderer(this, 147, 16);
		shape4.setRotationPoint(0.0F, 94.0F, 0.0F);
		shape4.addBox(-23.0F, -1.0F, -2.0F, 46, 1, 4, 0.0F);
		shape16h = new ModelRenderer(this, 0, 0);
		shape16h.setRotationPoint(0.0F, 30.0F, 0.0F);
		shape16h.addBox(-9.0F, 30.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape16h, 4.71238898038469F, 0.0F, 1.5707963267948966F);
		shape12 = new ModelRenderer(this, 0, 0);
		shape12.setRotationPoint(0.0F, 55.0F, 0.0F);
		shape12.addBox(-3.0F, 10.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape12, 1.5707963267948966F, 0.0F, 1.5707963267948966F);
		shape3 = new ModelRenderer(this, 0, 42);
		shape3.setRotationPoint(0.0F, 56.0F, 0.0F);
		shape3.addBox(-3.0F, -40.0F, -3.0F, 6, 80, 6, 0.0F);
		shape16 = new ModelRenderer(this, 0, 0);
		shape16.setRotationPoint(0.0F, 55.0F, 0.0F);
		shape16.addBox(-3.0F, 10.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape16, 4.71238898038469F, 0.0F, 1.5707963267948966F);
		shape17h = new ModelRenderer(this, 0, 0);
		shape17h.setRotationPoint(0.0F, 30.0F, 0.0F);
		shape17h.addBox(-9.0F, 30.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape17h, -0.7853981633974483F, 0.0F, 1.5707963267948966F);
		shape13h = new ModelRenderer(this, 0, 0);
		shape13h.setRotationPoint(0.0F, 30.0F, 0.0F);
		shape13h.addBox(-9.0F, 30.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape13h, 2.356194490192345F, 0.0F, 1.5707963267948966F);
		shape14 = new ModelRenderer(this, 0, 0);
		shape14.setRotationPoint(0.0F, 55.0F, 0.0F);
		shape14.addBox(-3.0F, 10.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape14, 3.141592653589793F, 0.0F, 1.5707963267948966F);
		shape11h = new ModelRenderer(this, 0, 0);
		shape11h.setRotationPoint(0.0F, 30.0F, 0.0F);
		shape11h.addBox(-9.0F, 30.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape11h, 0.7853981633974483F, 0.0F, 1.5707963267948966F);
		shape12h = new ModelRenderer(this, 0, 0);
		shape12h.setRotationPoint(0.0F, 30.0F, 0.0F);
		shape12h.addBox(-9.0F, 30.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape12h, 1.5707963267948966F, 0.0F, 1.5707963267948966F);
		shape15h = new ModelRenderer(this, 0, 0);
		shape15h.setRotationPoint(0.0F, 30.0F, 0.0F);
		shape15h.addBox(-9.0F, 30.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape15h, 3.9269908169872414F, 0.0F, 1.5707963267948966F);
		shape17 = new ModelRenderer(this, 0, 0);
		shape17.setRotationPoint(0.0F, 55.0F, 0.0F);
		shape17.addBox(-3.0F, 10.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape17, -0.7853981633974483F, 0.0F, 1.5707963267948966F);
		shape2 = new ModelRenderer(this, 147, 0);
		shape2.setRotationPoint(0.0F, 94.0F, 0.0F);
		shape2.addBox(-24.0F, 0.0F, -3.0F, 48, 2, 6, 0.0F);
		this.setRotateAngle(shape2, 0.0F, 1.5707963267948966F, 0.0F);
		shape7 = new ModelRenderer(this, 30, 0);
		shape7.setRotationPoint(1.0F, 55.0F, 0.0F);
		shape7.addBox(-1.5F, -2.0F, -13.0F, 3, 3, 24, 0.0F);
		this.setRotateAngle(shape7, 0.0F, 1.5707963267948966F, 0.0F);
		shape14h = new ModelRenderer(this, 0, 0);
		shape14h.setRotationPoint(0.0F, 30.0F, 0.0F);
		shape14h.addBox(-9.0F, 30.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape14h, 3.141592653589793F, 0.0F, 1.5707963267948966F);
		shape5 = new ModelRenderer(this, 147, 16);
		shape5.setRotationPoint(0.0F, 94.0F, 0.0F);
		shape5.addBox(-23.0F, -1.0F, -2.0F, 46, 1, 4, 0.0F);
		this.setRotateAngle(shape5, 0.0F, 1.5707963267948966F, 0.0F);
		shape1 = new ModelRenderer(this, 147, 0);
		shape1.setRotationPoint(0.0F, 94.0F, 0.0F);
		shape1.addBox(-24.0F, 0.0F, -3.0F, 48, 2, 6, 0.0F);
		shape13 = new ModelRenderer(this, 0, 0);
		shape13.setRotationPoint(0.0F, 55.0F, 0.0F);
		shape13.addBox(-3.0F, 10.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape13, 2.356194490192345F, 0.0F, 1.5707963267948966F);
		shape15 = new ModelRenderer(this, 0, 0);
		shape15.setRotationPoint(0.0F, 55.0F, 0.0F);
		shape15.addBox(-3.0F, 10.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape15, 3.9269908169872414F, 0.0F, 1.5707963267948966F);
		shape8 = new ModelRenderer(this, 30, 0);
		shape8.setRotationPoint(0.0F, 55.0F, 1.0F);
		shape8.addBox(-1.5F, -2.0F, -13.0F, 3, 3, 24, 0.0F);
		shape10h = new ModelRenderer(this, 0, 0);
		shape10h.setRotationPoint(0.0F, 30.0F, 0.0F);
		shape10h.addBox(-9.0F, 30.0F, -5.5F, 6, 3, 11, 0.0F);
		this.setRotateAngle(shape10h, 0.0F, 0.0F, 1.5707963267948966F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
		shape10.render(f5);
		shape11.render(f5);
		shape4.render(f5);
		shape12.render(f5);
		shape3.render(f5);
		shape16.render(f5);
		shape14.render(f5);
		shape17.render(f5);
		shape2.render(f5);
		shape7.render(f5);
		shape5.render(f5);
		shape1.render(f5);
		shape13.render(f5);
		shape15.render(f5);
		shape8.render(f5);

		GL11.glRotated(Minecraft.getMinecraft().theWorld.getWorldTime() % 360, 0, 1, 0);
		shape10h.render(f5);
		shape11h.render(f5);
		shape12h.render(f5);
		shape13h.render(f5);
		shape14h.render(f5);
		shape15h.render(f5);
		shape16h.render(f5);
		shape17h.render(f5);
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
