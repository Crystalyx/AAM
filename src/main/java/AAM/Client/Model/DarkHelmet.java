/**
 * This Class Created By Lord_Crystalyx.
 */
package AAM.Client.Model;

import java.util.ArrayList;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class DarkHelmet extends ModelBiped
{
	public ModelRenderer shape5;
	public ModelRenderer shape7;
	public ModelRenderer shape8;
	public ModelRenderer shape9;
	public ModelRenderer shape10;
	public ModelRenderer shape12;
	public ModelRenderer shape13;
	public ModelRenderer shape14;
	public ModelRenderer shape15;
	public ModelRenderer shape16;
	public ModelRenderer shape17;
	public ModelRenderer shape6;
	public ModelRenderer shape20;
	public ModelRenderer shape19;
	public ModelRenderer shape21;
	public ModelRenderer shape22;
	public ModelRenderer shape24;
	public ModelRenderer shape25;
	public ModelRenderer shape26;
	public ModelRenderer shape27;
	public ModelRenderer shape23;
	private ArrayList<ModelRenderer> list = new ArrayList<ModelRenderer>();

	public DarkHelmet()
	{

		this.textureWidth = 64;
		this.textureHeight = 32;
		this.shape5 = new ModelRenderer(this, 46, 4);
		this.shape5.setRotationPoint(-4.0F, -1.0F, 0.0F);
		this.shape5.addBox(0.0F, -3.0F, -5.0F, 8, 2, 1, 0.0F);
		this.shape15 = new ModelRenderer(this, 54, 0);
		this.shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape15.addBox(-5.0F, -4.2F, -4.0F, 1, 2, 1, 0.0F);
		this.shape17 = new ModelRenderer(this, 46, 7);
		this.shape17.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape17.addBox(-4.0F, -7.0F, 4.0F, 8, 4, 1, 0.0F);
		this.shape7 = new ModelRenderer(this, 54, 0);
		this.shape7.setRotationPoint(3.0F, -6.0F, -5.0F);
		this.shape7.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.shape21 = new ModelRenderer(this, 10, 23);
		this.shape21.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape21.addBox(-0.5F, -7.0F, -6.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(shape21, 0.0F, 2.356194490192345F, 0.0F);
		this.shape19 = new ModelRenderer(this, 10, 23);
		this.shape19.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape19.addBox(-0.5F, -7.0F, -6.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(shape19, 0.0F, 0.7853981633974483F, 0.0F);
		this.shape8 = new ModelRenderer(this, 54, 0);
		this.shape8.setRotationPoint(-4.0F, -6.0F, -5.0F);
		this.shape8.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.shape20 = new ModelRenderer(this, 0, 16);
		this.shape20.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape20.addBox(4.1F, -5.3F, -1.8F, 1, 2, 5, 0.0F);
		this.setRotateAngle(shape20, 0.4553564018453205F, 0.0F, 0.0F);
		this.shape9 = new ModelRenderer(this, 46, 4);
		this.shape9.setRotationPoint(-1.0F, 0.0F, 0.0F);
		this.shape9.addBox(-3.0F, -7.0F, -5.0F, 8, 1, 1, 0.0F);
		this.shape10 = new ModelRenderer(this, 0, 23);
		this.shape10.setRotationPoint(-4.0F, 0.0F, -4.0F);
		this.shape10.addBox(0.0F, -8.0F, 0.0F, 8, 1, 8, 0.0F);
		this.shape13 = new ModelRenderer(this, 32, 21);
		this.shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape13.addBox(-5.0F, -7.0F, -4.0F, 1, 3, 8, 0.0F);
		this.shape14 = new ModelRenderer(this, 0, 16);
		this.shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape14.addBox(-5.1F, -5.3F, -2.0F, 1, 2, 5, 0.0F);
		this.setRotateAngle(shape14, 0.40142572795869574F, 0.0F, 0.0F);
		this.shape6 = new ModelRenderer(this, 47, 0);
		this.shape6.setRotationPoint(-1.0F, -2.0F, -5.0F);
		this.shape6.addBox(0.0F, -4.0F, 0.0F, 2, 2, 1, 0.0F);
		this.shape26 = new ModelRenderer(this, 10, 23);
		this.shape26.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape26.addBox(-4.0F, -2.6F, -8.5F, 8, 1, 1, 0.0F);
		this.setRotateAngle(shape26, -0.7853981633974483F, 1.5707963267948966F, 0.0F);
		this.shape12 = new ModelRenderer(this, 32, 21);
		this.shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape12.addBox(4.0F, -7.0F, -4.0F, 1, 3, 8, 0.0F);
		this.shape27 = new ModelRenderer(this, 10, 23);
		this.shape27.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape27.addBox(-4.0F, -2.6F, -8.5F, 8, 1, 1, 0.0F);
		this.setRotateAngle(shape27, -0.7853981633974483F, 3.141592653589793F, 0.0F);
		this.shape16 = new ModelRenderer(this, 54, 0);
		this.shape16.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape16.addBox(4.0F, -4.2F, -4.0F, 1, 2, 1, 0.0F);
		this.shape25 = new ModelRenderer(this, 10, 23);
		this.shape25.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape25.addBox(-4.0F, -2.6F, -8.5F, 8, 1, 1, 0.0F);
		this.setRotateAngle(shape25, -0.7853981633974483F, 4.71238898038469F, 0.0F);
		this.shape23 = new ModelRenderer(this, 10, 23);
		this.shape23.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape23.addBox(-0.5F, -7.0F, -6.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(shape23, 0.0F, 5.497787143782138F, 0.0F);
		this.shape22 = new ModelRenderer(this, 10, 23);
		this.shape22.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape22.addBox(-0.5F, -7.0F, -6.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(shape22, 0.0F, 3.9269908169872414F, 0.0F);
		this.shape24 = new ModelRenderer(this, 10, 23);
		this.shape24.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape24.addBox(-4.0F, -2.6F, -8.5F, 8, 1, 1, 0.0F);
		this.setRotateAngle(shape24, -0.7853981633974483F, 0.0F, 0.0F);

		list.add(shape10);
		list.add(shape12);
		list.add(shape13);
		list.add(shape14);
		list.add(shape15);
		list.add(shape16);
		list.add(shape17);
		list.add(shape19);
		list.add(shape20);
		list.add(shape21);
		list.add(shape22);
		list.add(shape23);
		list.add(shape24);
		list.add(shape25);
		list.add(shape26);
		list.add(shape27);
		list.add(shape5);
		list.add(shape6);
		list.add(shape7);
		list.add(shape8);
		list.add(shape9);

		for (ModelRenderer modelRenderer : list)
		{
			this.bipedHeadwear.addChild(modelRenderer);
		}
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.shape5.render(f5);
		this.shape15.render(f5);
		this.shape17.render(f5);
		this.shape7.render(f5);
		this.shape21.render(f5);
		this.shape19.render(f5);
		this.shape8.render(f5);
		this.shape20.render(f5);
		this.shape9.render(f5);
		this.shape10.render(f5);
		this.shape13.render(f5);
		this.shape14.render(f5);
		this.shape6.render(f5);
		this.shape26.render(f5);
		this.shape12.render(f5);
		this.shape27.render(f5);
		this.shape16.render(f5);
		this.shape25.render(f5);
		this.shape23.render(f5);
		this.shape22.render(f5);
		this.shape24.render(f5);
	}

	public static EntityLiving enti;

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
		// if(enti != null)
		// {
		// this.bipedHead.rotateAngleY = enti.rotationPitch;
		// this.bipedHead.rotateAngleX = enti.rotationYawHead;
		// }
		// setRotationAngleY(this.bipedHead.rotateAngleY);
		// setRotationAngleX(this.bipedHead.rotateAngleX);
		// if (isSneak)
		// setRotationPointY(1.0F);
		// else
		// setRotationPointY(0.0F);
	}

	private void setRotationPointY(float par)
	{
		for (ModelRenderer modelRenderer : list)
		{
			modelRenderer.rotationPointY = par;
		}
	}

	private void setRotationAngleX(float angle)
	{
		for (ModelRenderer modelRenderer : list)
		{
			modelRenderer.rotateAngleX = angle;
		}
	}

	private void setRotationAngleY(float angle)
	{
		for (ModelRenderer modelRenderer : list)
		{
			modelRenderer.rotateAngleY = angle;
		}
	}
}
