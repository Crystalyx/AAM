package AAM.Client.Model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Golem_koloss - Smashe Created using Tabula 4.1.1
 */
public class Golem extends ModelBase
{
	public ModelRenderer tazik;
	public ModelRenderer bodyosnova;
	public ModelRenderer bodyosnova2;
	public ModelRenderer bodyosnova3;
	public ModelRenderer bodyosnova4;
	public ModelRenderer sheya;
	public ModelRenderer headosnova;
	public ModelRenderer jow;
	public ModelRenderer teeth;
	public ModelRenderer teeth2;
	public ModelRenderer teeth3;
	public ModelRenderer teeth4;
	public ModelRenderer lob;
	public ModelRenderer hendleftplecho;
	public ModelRenderer hendleft3;
	public ModelRenderer hendleftlokot;
	public ModelRenderer hendleft2;
	public ModelRenderer hendleft;
	public ModelRenderer hendrightplecho;
	public ModelRenderer hendright3;
	public ModelRenderer hendrightlokot;
	public ModelRenderer hendright2;
	public ModelRenderer hendright;
	public ModelRenderer footleft5;
	public ModelRenderer footleft4;
	public ModelRenderer footleft3;
	public ModelRenderer footleft2;
	public ModelRenderer footleft;
	public ModelRenderer footright5;
	public ModelRenderer footright4;
	public ModelRenderer footright3;
	public ModelRenderer footright2;
	public ModelRenderer footright;

	public Golem()
	{
		this.textureWidth = 256;
		this.textureHeight = 256;
		this.footleft2 = new ModelRenderer(this, 5, 128);
		this.footleft2.setRotationPoint(2.0F, 10.5F, -15.0F);
		this.footleft2.addBox(0.0F, 0.0F, 0.0F, 6, 7, 4, 0.0F);
		this.setRotateAngle(footleft2, -0.7853981633974483F, 0.0F, 0.0F);
		this.bodyosnova = new ModelRenderer(this, 53, 106);
		this.bodyosnova.setRotationPoint(-3.2F, -25.1F, 0.7F);
		this.bodyosnova.addBox(0.0F, 0.0F, 0.0F, 22, 12, 15, 0.0F);
		this.setRotateAngle(bodyosnova, 0.18203784098300857F, 0.0F, 0.0F);
		this.bodyosnova4 = new ModelRenderer(this, 63, 213);
		this.bodyosnova4.setRotationPoint(-1.2F, -13.1F, 23.5F);
		this.bodyosnova4.addBox(0.0F, 0.0F, 0.0F, 18, 11, 13, 0.0F);
		this.setRotateAngle(bodyosnova4, -1.0016444577195458F, 0.0F, 0.0F);
		this.hendright3 = new ModelRenderer(this, 0, 73);
		this.hendright3.setRotationPoint(2.4F, 4.6F, -3.9F);
		this.hendright3.addBox(0.0F, 0.0F, 0.0F, 7, 17, 7, 0.0F);
		this.setRotateAngle(hendright3, 0.0F, 0.0F, -0.5462880558742251F);
		this.footright = new ModelRenderer(this, 86, 59);
		this.footright.setRotationPoint(2.0F, 23.0F, -9.0F);
		this.footright.addBox(0.0F, 0.0F, -18.0F, 6, 3, 9, 0.0F);
		this.setRotateAngle(footright, -0.7853981633974483F, 0.0F, 0.0F);
		this.teeth = new ModelRenderer(this, 60, 10);
		this.teeth.setRotationPoint(2.3F, -33.1F, 1.5F);
		this.teeth.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
		this.hendleftlokot = new ModelRenderer(this, 9, 225);
		this.hendleftlokot.setRotationPoint(-7.0F, 11.0F, -4.9F);
		this.hendleftlokot.addBox(0.0F, 0.0F, 0.0F, 9, 9, 9, 0.0F);
		this.setRotateAngle(hendleftlokot, -0.136659280431156F, 0.0F, 0.31869712141416456F);
		this.hendrightlokot = new ModelRenderer(this, 9, 225);
		this.hendrightlokot.setRotationPoint(7.2F, 13.6F, -4.9F);
		this.hendrightlokot.addBox(0.0F, 0.0F, 0.0F, 9, 9, 9, 0.0F);
		this.setRotateAngle(hendrightlokot, -0.136659280431156F, 0.0F, -0.31869712141416456F);
		this.headosnova = new ModelRenderer(this, 150, 200);
		this.headosnova.setRotationPoint(2.5F, -39.1F, 3.0F);
		this.headosnova.addBox(0.0F, 0.0F, 0.0F, 11, 11, 11, 0.0F);
		this.footright3 = new ModelRenderer(this, 5, 145);
		this.footright3.setRotationPoint(2.0F, 1.0F, -15.5F);
		this.footright3.addBox(0.0F, 0.0F, 0.0F, 6, 4, 13, 0.0F);
		this.setRotateAngle(footright3, -1.240754565242769F, 0.0F, 0.0F);
		this.hendright2 = new ModelRenderer(this, 0, 100);
		this.hendright2.setRotationPoint(9.2F, 16.0F, -4.5F);
		this.hendright2.addBox(0.0F, 0.0F, 0.0F, 8, 12, 8, 0.0F);
		this.setRotateAngle(hendright2, -0.5918411493512771F, 0.0F, -0.136659280431156F);
		this.teeth4 = new ModelRenderer(this, 60, 10);
		this.teeth4.setRotationPoint(5.3F, -33.1F, 1.5F);
		this.teeth4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
		this.hendleft = new ModelRenderer(this, 86, 0);
		this.hendleft.setRotationPoint(-10.3F, 22.6F, -10.5F);
		this.hendleft.addBox(0.0F, 0.0F, 0.0F, 10, 10, 10, 0.0F);
		this.setRotateAngle(hendleft, -0.5918411493512771F, 0.0F, 0.0F);
		this.lob = new ModelRenderer(this, 6, 2);
		this.lob.setRotationPoint(2.5F, -38.6F, 2.1F);
		this.lob.addBox(0.0F, 0.0F, 0.0F, 11, 3, 5, 0.0F);
		this.hendleft2 = new ModelRenderer(this, 0, 100);
		this.hendleft2.setRotationPoint(-8.0F, 15.0F, -4.4F);
		this.hendleft2.addBox(0.0F, 0.0F, 0.0F, 8, 12, 8, 0.0F);
		this.setRotateAngle(hendleft2, -0.5918411493512771F, 0.0F, 0.136659280431156F);
		this.footleft3 = new ModelRenderer(this, 5, 145);
		this.footleft3.setRotationPoint(2.0F, 1.0F, -15.5F);
		this.footleft3.addBox(0.0F, 0.0F, 0.0F, 6, 4, 13, 0.0F);
		this.setRotateAngle(footleft3, -1.240754565242769F, 0.0F, 0.0F);
		this.footright5 = new ModelRenderer(this, 5, 197);
		this.footright5.setRotationPoint(12.0F, -1.0F, 23.0F);
		this.footright5.addBox(0.0F, 0.0F, -13.0F, 10, 8, 13, 0.0F);
		this.setRotateAngle(footright5, 0.7853981633974483F, 0.0F, 0.0F);
		this.hendrightplecho = new ModelRenderer(this, 0, 47);
		this.hendrightplecho.setRotationPoint(15.8F, -27.0F, 8.5F);
		this.hendrightplecho.addBox(0.0F, 0.0F, -6.0F, 9, 10, 13, 0.0F);
		this.footright2 = new ModelRenderer(this, 5, 128);
		this.footright2.setRotationPoint(2.0F, 10.5F, -15.0F);
		this.footright2.addBox(0.0F, 0.0F, 0.0F, 6, 7, 4, 0.0F);
		this.setRotateAngle(footright2, -0.7853981633974483F, 0.0F, 0.0F);
		this.tazik = new ModelRenderer(this, 61, 74);
		this.tazik.setRotationPoint(-2.7F, -2.2F, 18.6F);
		this.tazik.addBox(0.0F, 0.0F, 0.0F, 21, 10, 12, 0.0F);
		this.sheya = new ModelRenderer(this, 139, 0);
		this.sheya.setRotationPoint(4.5F, -29.1F, 4.5F);
		this.sheya.addBox(0.0F, 0.0F, 0.0F, 7, 7, 7, 0.0F);
		this.setRotateAngle(sheya, 0.4553564018453205F, 0.0F, 0.0F);
		this.bodyosnova3 = new ModelRenderer(this, 69, 181);
		this.bodyosnova3.setRotationPoint(-2.2F, -17.1F, 21.5F);
		this.bodyosnova3.addBox(0.0F, 0.0F, 0.0F, 20, 13, 8, 0.0F);
		this.setRotateAngle(bodyosnova3, -1.0016444577195458F, 0.0F, 0.0F);
		this.teeth3 = new ModelRenderer(this, 60, 10);
		this.teeth3.setRotationPoint(8.7F, -33.1F, 1.5F);
		this.teeth3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
		this.footright4 = new ModelRenderer(this, 5, 170);
		this.footright4.setRotationPoint(1.0F, 1.0F, -18.0F);
		this.footright4.addBox(0.0F, 0.0F, 0.0F, 8, 6, 13, 0.0F);
		this.footleft = new ModelRenderer(this, 86, 45);
		this.footleft.setRotationPoint(2.0F, 23.0F, -9.0F);
		this.footleft.addBox(0.0F, 0.0F, -18.0F, 6, 3, 9, 0.0F);
		this.setRotateAngle(footleft, -0.7853981633974483F, 0.0F, 0.0F);
		this.hendright = new ModelRenderer(this, 86, 22);
		this.hendright.setRotationPoint(9.5F, 22.6F, -10.5F);
		this.hendright.addBox(0.0F, 0.0F, 0.0F, 10, 10, 10, 0.0F);
		this.setRotateAngle(hendright, -0.5918411493512771F, 0.0F, 0.0F);
		this.footleft4 = new ModelRenderer(this, 5, 170);
		this.footleft4.setRotationPoint(1.0F, 1.0F, -18.0F);
		this.footleft4.addBox(0.0F, 0.0F, 0.0F, 8, 6, 13, 0.0F);
		this.jow = new ModelRenderer(this, 8, 24);
		this.jow.setRotationPoint(2.0F, -31.8F, 1.5F);
		this.jow.addBox(0.0F, 0.0F, 0.0F, 12, 4, 6, 0.0F);
		this.hendleft3 = new ModelRenderer(this, 0, 73);
		this.hendleft3.setRotationPoint(1.2F, 1.0F, -3.8F);
		this.hendleft3.addBox(0.0F, 0.0F, 0.0F, 7, 17, 7, 0.0F);
		this.setRotateAngle(hendleft3, 0.0F, 0.0F, 0.5462880558742251F);
		this.bodyosnova2 = new ModelRenderer(this, 58, 147);
		this.bodyosnova2.setRotationPoint(-3.2F, -26.1F, 16.5F);
		this.bodyosnova2.addBox(0.0F, 0.0F, 0.0F, 22, 16, 12, 0.0F);
		this.setRotateAngle(bodyosnova2, -1.0016444577195458F, 0.0F, 0.0F);
		this.teeth2 = new ModelRenderer(this, 60, 10);
		this.teeth2.setRotationPoint(11.7F, -33.1F, 1.5F);
		this.teeth2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
		this.hendleftplecho = new ModelRenderer(this, 0, 47);
		this.hendleftplecho.setRotationPoint(-9.0F, -27.0F, 8.5F);
		this.hendleftplecho.addBox(0.0F, 0.0F, -6.0F, 9, 10, 13, 0.0F);
		this.footleft5 = new ModelRenderer(this, 5, 197);
		this.footleft5.setRotationPoint(-6.0F, -1.0F, 23.0F);
		this.footleft5.addBox(0.0F, 0.0F, -13.0F, 10, 8, 13, 0.0F);
		this.setRotateAngle(footleft5, 0.7853981633974483F, 0.0F, 0.0F);
		this.footleft5.addChild(this.footleft2);
		this.hendrightplecho.addChild(this.hendright3);
		this.footright5.addChild(this.footright);
		this.hendleftplecho.addChild(this.hendleftlokot);
		this.hendrightplecho.addChild(this.hendrightlokot);
		this.footright5.addChild(this.footright3);
		this.hendrightplecho.addChild(this.hendright2);
		this.hendleftplecho.addChild(this.hendleft);
		this.hendleftplecho.addChild(this.hendleft2);
		this.footleft5.addChild(this.footleft3);
		this.footright5.addChild(this.footright2);
		this.footright5.addChild(this.footright4);
		this.footleft5.addChild(this.footleft);
		this.hendrightplecho.addChild(this.hendright);
		this.footleft5.addChild(this.footleft4);
		this.hendleftplecho.addChild(this.hendleft3);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glRotated(90, 0, 1, 0);
		GL11.glTranslated(-0.5, 0, -1);
		this.bodyosnova.render(f5);
		this.bodyosnova4.render(f5);
		this.teeth.render(f5);
		this.headosnova.render(f5);
		this.teeth4.render(f5);
		this.lob.render(f5);
		this.footright5.render(f5);
		this.hendrightplecho.render(f5);
		this.tazik.render(f5);
		this.sheya.render(f5);
		this.bodyosnova3.render(f5);
		this.teeth3.render(f5);
		this.jow.render(f5);
		this.bodyosnova2.render(f5);
		this.teeth2.render(f5);
		this.hendleftplecho.render(f5);
		this.footleft5.render(f5);
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

	public void performStep(int tick)
	{
		// TODO
	}

	public void performSwing(int tick)
	{
		// TODO
	}
}
