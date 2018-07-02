package AAM.Client.Model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * nares - Smashe Created using Tabula 4.1.1
 */
public class Subwer extends ModelBase
{
	public ModelRenderer body2;
	public ModelRenderer wing1right;
	public ModelRenderer wing1left;
	public ModelRenderer head;
	public ModelRenderer body3;
	public ModelRenderer body4;
	public ModelRenderer wing2right;
	public ModelRenderer wing2left;
	public ModelRenderer nose;
	public ModelRenderer horn23;
	public ModelRenderer horn32;
	public ModelRenderer iese;
	public ModelRenderer iese2;

	public Subwer()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.body4 = new ModelRenderer(this, 16, 22);
		this.body4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body4.addBox(-1.0F, 1.0F, 0.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(body4, -0.182212373908208F, 0.0F, 0.0F);
		this.iese = new ModelRenderer(this, 2, 2);
		this.iese.setRotationPoint(0.5F, 0.3F, 0.6F);
		this.iese.addBox(0.0F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(iese, 0.0F, -0.01064650843716541F, 0.0F);
		this.wing2right = new ModelRenderer(this, 8, 6);
		this.wing2right.setRotationPoint(0.6F, 1.0F, 0.0F);
		this.wing2right.addBox(-0.5F, 0.0F, 0.0F, 7, 1, 4, 0.0F);
		this.setRotateAngle(wing2right, 0.0F, 0.0F, 2.049016541841343F);
		this.wing1right = new ModelRenderer(this, 7, 14);
		this.wing1right.setRotationPoint(-7.1F, 0.0F, 5.0F);
		this.wing1right.addBox(0.0F, 0.0F, 0.0F, 7, 1, 4, 0.0F);
		this.setRotateAngle(wing1right, 0.0F, 0.0F, 0.5009094953223726F);
		this.wing2left = new ModelRenderer(this, 8, 6);
		this.wing2left.setRotationPoint(0.5F, -1.0F, 0.0F);
		this.wing2left.addBox(3.5F, -5.6F, 0.0F, 7, 1, 4, 0.0F);
		this.setRotateAngle(wing2left, 0.0F, 0.0F, 1.1833332328521553F);
		this.nose = new ModelRenderer(this, 4, 24);
		this.nose.setRotationPoint(0.0F, 3.7F, -1.5F);
		this.nose.addBox(0.5F, 0.0F, 0.0F, 3, 3, 2, 0.0F);
		this.setRotateAngle(nose, 1.5707963267948966F, 0.0F, 0.0F);
		this.iese2 = new ModelRenderer(this, 2, 2);
		this.iese2.setRotationPoint(2.5F, 0.3F, 0.6F);
		this.iese2.addBox(0.0F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
		this.body3 = new ModelRenderer(this, 36, 22);
		this.body3.setRotationPoint(0.0F, 0.7F, 8.4F);
		this.body3.addBox(-1.5F, 0.5F, -4.0F, 3, 3, 5, 0.0F);
		this.setRotateAngle(body3, -0.136659280431156F, 0.0F, 0.0F);
		this.horn32 = new ModelRenderer(this, 2, 8);
		this.horn32.setRotationPoint(1.7F, 0.0F, 2.0F);
		this.horn32.addBox(1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
		this.head = new ModelRenderer(this, 35, 0);
		this.head.setRotationPoint(-1.5F, 0.7F, 0.9F);
		this.head.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
		this.horn23 = new ModelRenderer(this, 2, 8);
		this.horn23.setRotationPoint(0.0F, 0.0F, 2.0F);
		this.horn23.addBox(0.3F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
		this.wing1left = new ModelRenderer(this, 7, 14);
		this.wing1left.setRotationPoint(1.9F, 3.3F, 5.0F);
		this.wing1left.addBox(0.0F, 0.0F, 0.0F, 7, 1, 4, 0.0F);
		this.setRotateAngle(wing1left, 0.0F, 0.0F, -0.5009094953223726F);
		this.body2 = new ModelRenderer(this, 34, 10);
		this.body2.setRotationPoint(0.4F, 1.0F, 5.0F);
		this.body2.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 5, 0.0F);
		this.setRotateAngle(body2, -0.5462880558742251F, 0.0F, 0.0F);
		this.body3.addChild(this.body4);
		this.head.addChild(this.iese);
		this.wing1right.addChild(this.wing2right);
		this.wing1left.addChild(this.wing2left);
		this.head.addChild(this.nose);
		this.head.addChild(this.iese2);
		this.body2.addChild(this.body3);
		this.head.addChild(this.horn32);
		this.head.addChild(this.horn23);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(0, 0.75, -0.5);
		this.wing1right.render(f5);
		this.head.render(f5);
		this.wing1left.render(f5);
		this.body2.render(f5);
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
