package aam.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class AtrilliumTransporter extends ModelBase
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
	public ModelRenderer shape13;
	public ModelRenderer shape13_1;

	public AtrilliumTransporter()
	{
		textureWidth = 128;
		textureHeight = 64;
		shape3 = new ModelRenderer(this, 0, 52);
		shape3.setRotationPoint(4.0F, 8.0F, -8.0F);
		shape3.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
		shape2 = new ModelRenderer(this, 23, 44);
		shape2.setRotationPoint(-3.0F, 16.0F, 0.0F);
		shape2.addBox(7.0F, 0.0F, -8.0F, 4, 4, 16, 0.0F);
		shape4 = new ModelRenderer(this, 0, 25);
		shape4.setRotationPoint(4.0F, 4.0F, -4.0F);
		shape4.addBox(0.0F, 0.0F, 0.0F, 4, 4, 8, 0.0F);
		shape11 = new ModelRenderer(this, 0, 0);
		shape11.setRotationPoint(0.0F, 12.0F, 0.0F);
		shape11.addBox(0.0F, -3.9F, -1.0F, 2, 8, 2, 0.0F);
		shape7 = new ModelRenderer(this, 0, 11);
		shape7.setRotationPoint(4.0F, 6.7F, 0.9F);
		shape7.addBox(0.0F, 0.0F, 0.0F, 4, 6, 4, 0.0F);
		this.setRotateAngle(shape7, 0.7853981633974483F, 0.0F, 0.0F);
		shape1 = new ModelRenderer(this, 64, 44);
		shape1.setRotationPoint(-8.0F, 20.0F, -8.0F);
		shape1.addBox(0.0F, 0.0F, 0.0F, 16, 4, 16, 0.0F);
		shape6 = new ModelRenderer(this, 0, 12);
		shape6.setRotationPoint(4.0F, 17.3F, -0.9F);
		shape6.addBox(0.0F, 0.0F, 0.0F, 4, 6, 4, 0.0F);
		this.setRotateAngle(shape6, -2.356194490192345F, 0.0F, 0.0F);
		shape8 = new ModelRenderer(this, 0, 11);
		shape8.setRotationPoint(4.0F, 3.9F, -3.8F);
		shape8.addBox(0.0F, 0.0F, 0.0F, 4, 6, 4, 0.0F);
		this.setRotateAngle(shape8, -0.7853981633974483F, 0.0F, 0.0F);
		shape5 = new ModelRenderer(this, 0, 52);
		shape5.mirror = true;
		shape5.setRotationPoint(4.0F, 8.0F, 4.0F);
		shape5.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
		shape13_1 = new ModelRenderer(this, 19, 0);
		shape13_1.setRotationPoint(4.0F, 11.0F, 6.0F);
		shape13_1.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
		this.setRotateAngle(shape13_1, 0.0F, 0.0F, 0.7853981633974483F);
		shape13 = new ModelRenderer(this, 18, 0);
		shape13.setRotationPoint(4.0F, 11.0F, -8.0F);
		shape13.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
		this.setRotateAngle(shape13, 0.0F, 0.0F, 0.7853981633974483F);
		shape12 = new ModelRenderer(this, 0, 0);
		shape12.setRotationPoint(0.0F, 12.0F, 0.0F);
		shape12.addBox(0.0F, -4.0F, -1.15F, 2, 8, 2, 0.0F);
		this.setRotateAngle(shape12, 1.5707963267948966F, 0.0F, 0.0F);
		shape10 = new ModelRenderer(this, 0, 42);
		shape10.setRotationPoint(-2.0F, 12.15F, 0.0F);
		shape10.addBox(0.0F, -1.0F, -1.0F, 10, 2, 2, 0.0F);
		shape9 = new ModelRenderer(this, 0, 11);
		shape9.setRotationPoint(8.0F, 18.1F, 0.1F);
		shape9.addBox(0.0F, 0.0F, 0.0F, 4, 6, 4, 0.0F);
		this.setRotateAngle(shape9, -2.356194490192345F, 3.141592653589793F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		shape3.render(f5);
		shape2.render(f5);
		shape4.render(f5);
		shape11.render(f5);
		shape7.render(f5);
		shape1.render(f5);
		shape6.render(f5);
		shape8.render(f5);
		shape5.render(f5);
		shape13_1.render(f5);
		shape13.render(f5);
		shape12.render(f5);
		shape10.render(f5);
		shape9.render(f5);
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
