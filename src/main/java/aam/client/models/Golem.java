package aam.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

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
		textureWidth = 256;
		textureHeight = 256;
		footleft2 = new ModelRenderer(this, 5, 128);
		footleft2.setRotationPoint(2.0F, 10.5F, -15.0F);
		footleft2.addBox(0.0F, 0.0F, 0.0F, 6, 7, 4, 0.0F);
		this.setRotateAngle(footleft2, -0.7853981633974483F, 0.0F, 0.0F);
		bodyosnova = new ModelRenderer(this, 53, 106);
		bodyosnova.setRotationPoint(-3.2F, -25.1F, 0.7F);
		bodyosnova.addBox(0.0F, 0.0F, 0.0F, 22, 12, 15, 0.0F);
		this.setRotateAngle(bodyosnova, 0.18203784098300857F, 0.0F, 0.0F);
		bodyosnova4 = new ModelRenderer(this, 63, 213);
		bodyosnova4.setRotationPoint(-1.2F, -13.1F, 23.5F);
		bodyosnova4.addBox(0.0F, 0.0F, 0.0F, 18, 11, 13, 0.0F);
		this.setRotateAngle(bodyosnova4, -1.0016444577195458F, 0.0F, 0.0F);
		hendright3 = new ModelRenderer(this, 0, 73);
		hendright3.setRotationPoint(2.4F, 4.6F, -3.9F);
		hendright3.addBox(0.0F, 0.0F, 0.0F, 7, 17, 7, 0.0F);
		this.setRotateAngle(hendright3, 0.0F, 0.0F, -0.5462880558742251F);
		footright = new ModelRenderer(this, 86, 59);
		footright.setRotationPoint(2.0F, 23.0F, -9.0F);
		footright.addBox(0.0F, 0.0F, -18.0F, 6, 3, 9, 0.0F);
		this.setRotateAngle(footright, -0.7853981633974483F, 0.0F, 0.0F);
		teeth = new ModelRenderer(this, 60, 10);
		teeth.setRotationPoint(2.3F, -33.1F, 1.5F);
		teeth.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
		hendleftlokot = new ModelRenderer(this, 9, 225);
		hendleftlokot.setRotationPoint(-7.0F, 11.0F, -4.9F);
		hendleftlokot.addBox(0.0F, 0.0F, 0.0F, 9, 9, 9, 0.0F);
		this.setRotateAngle(hendleftlokot, -0.136659280431156F, 0.0F, 0.31869712141416456F);
		hendrightlokot = new ModelRenderer(this, 9, 225);
		hendrightlokot.setRotationPoint(7.2F, 13.6F, -4.9F);
		hendrightlokot.addBox(0.0F, 0.0F, 0.0F, 9, 9, 9, 0.0F);
		this.setRotateAngle(hendrightlokot, -0.136659280431156F, 0.0F, -0.31869712141416456F);
		headosnova = new ModelRenderer(this, 150, 200);
		headosnova.setRotationPoint(2.5F, -39.1F, 3.0F);
		headosnova.addBox(0.0F, 0.0F, 0.0F, 11, 11, 11, 0.0F);
		footright3 = new ModelRenderer(this, 5, 145);
		footright3.setRotationPoint(2.0F, 1.0F, -15.5F);
		footright3.addBox(0.0F, 0.0F, 0.0F, 6, 4, 13, 0.0F);
		this.setRotateAngle(footright3, -1.240754565242769F, 0.0F, 0.0F);
		hendright2 = new ModelRenderer(this, 0, 100);
		hendright2.setRotationPoint(9.2F, 16.0F, -4.5F);
		hendright2.addBox(0.0F, 0.0F, 0.0F, 8, 12, 8, 0.0F);
		this.setRotateAngle(hendright2, -0.5918411493512771F, 0.0F, -0.136659280431156F);
		teeth4 = new ModelRenderer(this, 60, 10);
		teeth4.setRotationPoint(5.3F, -33.1F, 1.5F);
		teeth4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
		hendleft = new ModelRenderer(this, 86, 0);
		hendleft.setRotationPoint(-10.3F, 22.6F, -10.5F);
		hendleft.addBox(0.0F, 0.0F, 0.0F, 10, 10, 10, 0.0F);
		this.setRotateAngle(hendleft, -0.5918411493512771F, 0.0F, 0.0F);
		lob = new ModelRenderer(this, 6, 2);
		lob.setRotationPoint(2.5F, -38.6F, 2.1F);
		lob.addBox(0.0F, 0.0F, 0.0F, 11, 3, 5, 0.0F);
		hendleft2 = new ModelRenderer(this, 0, 100);
		hendleft2.setRotationPoint(-8.0F, 15.0F, -4.4F);
		hendleft2.addBox(0.0F, 0.0F, 0.0F, 8, 12, 8, 0.0F);
		this.setRotateAngle(hendleft2, -0.5918411493512771F, 0.0F, 0.136659280431156F);
		footleft3 = new ModelRenderer(this, 5, 145);
		footleft3.setRotationPoint(2.0F, 1.0F, -15.5F);
		footleft3.addBox(0.0F, 0.0F, 0.0F, 6, 4, 13, 0.0F);
		this.setRotateAngle(footleft3, -1.240754565242769F, 0.0F, 0.0F);
		footright5 = new ModelRenderer(this, 5, 197);
		footright5.setRotationPoint(12.0F, -1.0F, 23.0F);
		footright5.addBox(0.0F, 0.0F, -13.0F, 10, 8, 13, 0.0F);
		this.setRotateAngle(footright5, 0.7853981633974483F, 0.0F, 0.0F);
		hendrightplecho = new ModelRenderer(this, 0, 47);
		hendrightplecho.setRotationPoint(15.8F, -27.0F, 8.5F);
		hendrightplecho.addBox(0.0F, 0.0F, -6.0F, 9, 10, 13, 0.0F);
		footright2 = new ModelRenderer(this, 5, 128);
		footright2.setRotationPoint(2.0F, 10.5F, -15.0F);
		footright2.addBox(0.0F, 0.0F, 0.0F, 6, 7, 4, 0.0F);
		this.setRotateAngle(footright2, -0.7853981633974483F, 0.0F, 0.0F);
		tazik = new ModelRenderer(this, 61, 74);
		tazik.setRotationPoint(-2.7F, -2.2F, 18.6F);
		tazik.addBox(0.0F, 0.0F, 0.0F, 21, 10, 12, 0.0F);
		sheya = new ModelRenderer(this, 139, 0);
		sheya.setRotationPoint(4.5F, -29.1F, 4.5F);
		sheya.addBox(0.0F, 0.0F, 0.0F, 7, 7, 7, 0.0F);
		this.setRotateAngle(sheya, 0.4553564018453205F, 0.0F, 0.0F);
		bodyosnova3 = new ModelRenderer(this, 69, 181);
		bodyosnova3.setRotationPoint(-2.2F, -17.1F, 21.5F);
		bodyosnova3.addBox(0.0F, 0.0F, 0.0F, 20, 13, 8, 0.0F);
		this.setRotateAngle(bodyosnova3, -1.0016444577195458F, 0.0F, 0.0F);
		teeth3 = new ModelRenderer(this, 60, 10);
		teeth3.setRotationPoint(8.7F, -33.1F, 1.5F);
		teeth3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
		footright4 = new ModelRenderer(this, 5, 170);
		footright4.setRotationPoint(1.0F, 1.0F, -18.0F);
		footright4.addBox(0.0F, 0.0F, 0.0F, 8, 6, 13, 0.0F);
		footleft = new ModelRenderer(this, 86, 45);
		footleft.setRotationPoint(2.0F, 23.0F, -9.0F);
		footleft.addBox(0.0F, 0.0F, -18.0F, 6, 3, 9, 0.0F);
		this.setRotateAngle(footleft, -0.7853981633974483F, 0.0F, 0.0F);
		hendright = new ModelRenderer(this, 86, 22);
		hendright.setRotationPoint(9.5F, 22.6F, -10.5F);
		hendright.addBox(0.0F, 0.0F, 0.0F, 10, 10, 10, 0.0F);
		this.setRotateAngle(hendright, -0.5918411493512771F, 0.0F, 0.0F);
		footleft4 = new ModelRenderer(this, 5, 170);
		footleft4.setRotationPoint(1.0F, 1.0F, -18.0F);
		footleft4.addBox(0.0F, 0.0F, 0.0F, 8, 6, 13, 0.0F);
		jow = new ModelRenderer(this, 8, 24);
		jow.setRotationPoint(2.0F, -31.8F, 1.5F);
		jow.addBox(0.0F, 0.0F, 0.0F, 12, 4, 6, 0.0F);
		hendleft3 = new ModelRenderer(this, 0, 73);
		hendleft3.setRotationPoint(1.2F, 1.0F, -3.8F);
		hendleft3.addBox(0.0F, 0.0F, 0.0F, 7, 17, 7, 0.0F);
		this.setRotateAngle(hendleft3, 0.0F, 0.0F, 0.5462880558742251F);
		bodyosnova2 = new ModelRenderer(this, 58, 147);
		bodyosnova2.setRotationPoint(-3.2F, -26.1F, 16.5F);
		bodyosnova2.addBox(0.0F, 0.0F, 0.0F, 22, 16, 12, 0.0F);
		this.setRotateAngle(bodyosnova2, -1.0016444577195458F, 0.0F, 0.0F);
		teeth2 = new ModelRenderer(this, 60, 10);
		teeth2.setRotationPoint(11.7F, -33.1F, 1.5F);
		teeth2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
		hendleftplecho = new ModelRenderer(this, 0, 47);
		hendleftplecho.setRotationPoint(-9.0F, -27.0F, 8.5F);
		hendleftplecho.addBox(0.0F, 0.0F, -6.0F, 9, 10, 13, 0.0F);
		footleft5 = new ModelRenderer(this, 5, 197);
		footleft5.setRotationPoint(-6.0F, -1.0F, 23.0F);
		footleft5.addBox(0.0F, 0.0F, -13.0F, 10, 8, 13, 0.0F);
		this.setRotateAngle(footleft5, 0.7853981633974483F, 0.0F, 0.0F);
		footleft5.addChild(footleft2);
		hendrightplecho.addChild(hendright3);
		footright5.addChild(footright);
		hendleftplecho.addChild(hendleftlokot);
		hendrightplecho.addChild(hendrightlokot);
		footright5.addChild(footright3);
		hendrightplecho.addChild(hendright2);
		hendleftplecho.addChild(hendleft);
		hendleftplecho.addChild(hendleft2);
		footleft5.addChild(footleft3);
		footright5.addChild(footright2);
		footright5.addChild(footright4);
		footleft5.addChild(footleft);
		hendrightplecho.addChild(hendright);
		footleft5.addChild(footleft4);
		hendleftplecho.addChild(hendleft3);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glRotated(90, 0, 1, 0);
		GL11.glTranslated(-0.5, 0, -1);
		bodyosnova.render(f5);
		bodyosnova4.render(f5);
		teeth.render(f5);
		headosnova.render(f5);
		teeth4.render(f5);
		lob.render(f5);
		footright5.render(f5);
		hendrightplecho.render(f5);
		tazik.render(f5);
		sheya.render(f5);
		bodyosnova3.render(f5);
		teeth3.render(f5);
		jow.render(f5);
		bodyosnova2.render(f5);
		teeth2.render(f5);
		hendleftplecho.render(f5);
		footleft5.render(f5);
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
	}

	public void performSwing(int tick)
	{
	}
}
