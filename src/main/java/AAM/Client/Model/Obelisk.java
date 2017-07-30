package AAM.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Obelisk - Lord_Crystalyx Created using Tabula 4.1.1
 */
public class Obelisk extends ModelBase
{
	public double[] modelScale = new double[]
	{ 4.0D, 4.0D, 4.0D };
	public ModelRenderer shape2;
	public ModelRenderer shape3;

	public Obelisk()
	{
		this.textureWidth = 512;
		this.textureHeight = 256;
		this.shape3 = new ModelRenderer(this, 0, 0);
		this.shape3.setRotationPoint(0.0F, 80.0F, 0.0F);
		this.shape3.addBox(-28.0F, 0.0F, -28.0F, 56, 16, 56, 0.0F);
		this.shape2 = new ModelRenderer(this, 0, 73);
		this.shape2.setRotationPoint(0.0F, -32.0F, 0.0F);
		this.shape2.addBox(-21.0F, 0.0F, -21.0F, 42, 104, 42, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
		this.shape3.render(f5);
		this.shape2.render(f5);
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
