package AAM.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Alemental guard - Smashe Created using Tabula 4.1.1
 */
public class ElementalGuard extends ModelBase
{
	public double[] modelScale = new double[] { 4.0D, 4.0D, 4.0D };
	public ModelRenderer bot1;
	public ModelRenderer bot2;
	public ModelRenderer bot3;
	public ModelRenderer bot4;

	public ModelRenderer baseleft;
	public ModelRenderer baseright;

	public ModelRenderer headbase1;
	public ModelRenderer headbase;

	public ModelRenderer body;
	public ModelRenderer bodybasecenter;
	public ModelRenderer bodybase;

	public ModelRenderer righthand2;
	public ModelRenderer righthand;
	public ModelRenderer lefthand2;
	public ModelRenderer lefthand;

	public ElementalGuard()
	{
		this.textureWidth = 256;
		this.textureHeight = 128;
		this.bodybasecenter = new ModelRenderer(this, 69, 100);
		this.bodybasecenter.setRotationPoint(-1.0F, 50.7F, -21.0F);
		this.bodybasecenter.addBox(-8.0F, -10.5F, -8.0F, 20, 11, 16, 0.0F);
		this.setRotateAngle(bodybasecenter, 0.091106186954104F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 191, 99);
		this.body.setRotationPoint(1.0F, 49.9F, -17.2F);
		this.body.addBox(-8.0F, -10.5F, -8.0F, 16, 12, 16, 0.0F);
		this.setRotateAngle(body, 0.27314402793711257F, 0.0F, 0.0F);
		this.bot3 = new ModelRenderer(this, 55, 31);
		this.bot3.setRotationPoint(2.0F, 79.5F, -10.1F);
		this.bot3.addBox(-8.0F, -10.5F, -8.0F, 14, 9, 14, 0.0F);
		this.setRotateAngle(bot3, 0.40980330836826856F, 0.0F, 0.0F);
		this.baseright = new ModelRenderer(this, 90, 0);
		this.baseright.setRotationPoint(9.3F, 50.4F, -15.8F);
		this.baseright.addBox(-8.0F, -10.5F, -8.0F, 13, 6, 11, 0.0F);
		this.setRotateAngle(baseright, 0.1869247628885927F, -0.12740903539558604F, 0.0F);
		this.bodybase = new ModelRenderer(this, 0, 88);
		this.bodybase.setRotationPoint(1.0F, 50.4F, -19.1F);
		this.bodybase.addBox(-8.0F, -10.5F, -8.0F, 16, 23, 16, 0.0F);
		this.setRotateAngle(bodybase, 0.18203784098300857F, 0.0F, 0.0F);
		this.bot1 = new ModelRenderer(this, 181, 56);
		this.bot1.setRotationPoint(0.0F, 70.4F, -16.0F);
		this.bot1.addBox(-8.0F, -10.5F, -8.0F, 18, 9, 18, 0.0F);
		this.setRotateAngle(bot1, 0.22759093446006054F, 0.0F, 0.0F);
		this.headbase1 = new ModelRenderer(this, 0, 60);
		this.headbase1.setRotationPoint(3.0F, 35.5F, -20.4F);
		this.headbase1.addBox(-8.0F, -10.5F, -8.0F, 13, 13, 13, 0.0F);
		this.baseleft = new ModelRenderer(this, 90, 0);
		this.baseleft.setRotationPoint(-4.5F, 50.4F, -15.8F);
		this.baseleft.addBox(-8.0F, -10.5F, -8.0F, 13, 6, 11, 0.0F);
		this.setRotateAngle(baseleft, 0.1869247628885927F, 0.12740903539558604F, 0.0F);
		this.bot4 = new ModelRenderer(this, 7, 9);
		this.bot4.setRotationPoint(3.0F, 83.4F, -6.4F);
		this.bot4.addBox(-8.0F, -10.5F, -8.0F, 12, 9, 12, 0.0F);
		this.setRotateAngle(bot4, 0.36425021489121656F, 0.0F, 0.0F);
		this.bot2 = new ModelRenderer(this, 114, 58);
		this.bot2.setRotationPoint(1.0F, 75.3F, -13.7F);
		this.bot2.addBox(-8.0F, -10.5F, -8.0F, 16, 9, 16, 0.0F);
		this.setRotateAngle(bot2, 0.22759093446006054F, 0.0F, 0.0F);
		this.righthand = new ModelRenderer(this, 150, 100);
		this.righthand.setRotationPoint(-11.2F, 53.4F, -14.2F);
		this.righthand.addBox(-8.0F, -10.5F, -8.0F, 9, 18, 9, 0.0F);
		this.setRotateAngle(righthand, 0.18203784098300857F, 0.0F, 0.5918411493512771F);
		this.lefthand2 = new ModelRenderer(this, 150, 0);
		this.lefthand2.setRotationPoint(25.2F, 58.9F, -20.9F);
		this.lefthand2.addBox(-8.0F, -10.5F, -8.0F, 9, 18, 9, 0.0F);
		this.setRotateAngle(lefthand2, -0.9560913642424937F, 0.0F, -0.5918411493512771F);
		this.headbase = new ModelRenderer(this, 200, 10);
		this.headbase.setRotationPoint(5.5F, 44.6F, -16.7F);
		this.headbase.addBox(-8.0F, -10.5F, -8.0F, 8, 8, 8, 0.0F);
		this.lefthand = new ModelRenderer(this, 150, 100);
		this.lefthand.setRotationPoint(18.7F, 49.3F, -14.2F);
		this.lefthand.addBox(-8.0F, -10.5F, -8.0F, 9, 18, 9, 0.0F);
		this.setRotateAngle(lefthand, 0.18203784098300857F, 0.0F, -0.5918411493512771F);
		this.righthand2 = new ModelRenderer(this, 150, 0);
		this.righthand2.setRotationPoint(-18.3F, 64.0F, -21.2F);
		this.righthand2.addBox(-8.0F, -10.5F, -8.0F, 9, 18, 9, 0.0F);
		this.setRotateAngle(righthand2, -0.9560913642424937F, 0.0F, 0.5918411493512771F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glScaled(4D / modelScale[0], 4D / modelScale[1], 4D / modelScale[2]);
		GL11.glTranslated(0, -4, 0);
		this.bodybasecenter.render(f5);
		this.body.render(f5);
		this.bot3.render(f5);
		this.baseright.render(f5);
		this.bodybase.render(f5);
		this.bot1.render(f5);
		this.headbase1.render(f5);
		this.baseleft.render(f5);
		this.bot4.render(f5);
		this.bot2.render(f5);
		this.righthand.render(f5);
		this.lefthand2.render(f5);
		this.headbase.render(f5);
		this.lefthand.render(f5);
		this.righthand2.render(f5);
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
