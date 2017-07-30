package AAM.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import AAM.Common.Tiles.CauldronTileEntity;

/**
 * Cauldron - Lord_Crystalyx Created using Tabula 4.1.1
 */
public class Cauldron extends ModelBase
{
	public double[] modelScale = new double[]
	{ 2.0D, 2.0D, 2.0D };
	public ModelRenderer Leg1;
	public ModelRenderer Leg2;
	public ModelRenderer Leg3;
	public ModelRenderer Leg4;
	public ModelRenderer Bottom;
	public ModelRenderer Side1;
	public ModelRenderer Side2;
	public ModelRenderer Side3;
	public ModelRenderer Side4;
	public ModelRenderer Fluid;

	public Cauldron()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.Leg3 = new ModelRenderer(this, 0, 0);
		this.Leg3.setRotationPoint(10.0F, 42.0F, 10.0F);
		this.Leg3.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
		this.Side2 = new ModelRenderer(this, 0, 30);
		this.Side2.setRotationPoint(14.0F, 20.0F, -12.0F);
		this.Side2.addBox(0.0F, 0.0F, 0.0F, 26, 20, 2, 0.0F);
		this.setRotateAngle(Side2, 0.0F, -1.5707963267948966F, 0.0F);
		this.Leg1 = new ModelRenderer(this, 0, 0);
		this.Leg1.setRotationPoint(-13.0F, 42.0F, 10.0F);
		this.Leg1.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
		this.Side4 = new ModelRenderer(this, 0, 30);
		this.Side4.setRotationPoint(12.0F, 20.0F, 14.0F);
		this.Side4.addBox(0.0F, 0.0F, 0.0F, 26, 20, 2, 0.0F);
		this.setRotateAngle(Side4, 0.0F, 3.141592653589793F, 0.0F);
		this.Leg2 = new ModelRenderer(this, 0, 0);
		this.Leg2.setRotationPoint(10.0F, 42.0F, -13.0F);
		this.Leg2.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
		this.Leg4 = new ModelRenderer(this, 0, 0);
		this.Leg4.setRotationPoint(-13.0F, 42.0F, -13.0F);
		this.Leg4.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
		this.Side1 = new ModelRenderer(this, 0, 30);
		this.Side1.setRotationPoint(-14.0F, 20.0F, 12.0F);
		this.Side1.addBox(0.0F, 0.0F, 0.0F, 26, 20, 2, 0.0F);
		this.setRotateAngle(Side1, 0.0F, 1.5707963267948966F, 0.0F);
		this.Bottom = new ModelRenderer(this, 0, 0);
		this.Bottom.setRotationPoint(-14.0F, 40.0F, -14.0F);
		this.Bottom.addBox(0.0F, 0.0F, 0.0F, 28, 2, 28, 0.0F);
		this.Side3 = new ModelRenderer(this, 0, 30);
		this.Side3.setRotationPoint(-12.0F, 20.0F, -14.0F);
		this.Side3.addBox(0.0F, 0.0F, 0.0F, 26, 20, 2, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
		this.Leg3.render(f5);
		this.Side2.render(f5);
		this.Leg1.render(f5);
		this.Side4.render(f5);
		this.Leg2.render(f5);
		this.Leg4.render(f5);
		this.Side1.render(f5);
		this.Bottom.render(f5);
		this.Side3.render(f5);
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
