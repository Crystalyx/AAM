package AAM.Client.Model;

import org.lwjgl.opengl.GL11;

import AAM.API.Animation.AnimatedModel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Alemental guard - Smashe Created using Tabula 4.1.1
 */
public class Elemental extends AnimatedModel
{
	public double[] modelScale = new double[] { 4.0D, 4.0D, 4.0D };
	public ModelRenderer bodyBaseRight;
	public ModelRenderer bodyBaseLeft;
	public ModelRenderer bodyBaseCenter;
	public ModelRenderer bodyBase;
	public ModelRenderer bodyBaseBot;
	public ModelRenderer body;
	public ModelRenderer backBase;

	public ModelRenderer bot1;
	public ModelRenderer bot2;
	public ModelRenderer bot3;
	public ModelRenderer bot4;

	public ModelRenderer headBasedMB;
	public ModelRenderer headBaseS;
	public ModelRenderer headBase2;
	public ModelRenderer headBase1;
	public ModelRenderer headBaseCircle;

	public ModelRenderer ribright;
	public ModelRenderer ribleft;
	public ModelRenderer ribleft2;
	public ModelRenderer ribright2;
	public ModelRenderer ribright3;
	public ModelRenderer ribleft3;
	public ModelRenderer ribleft55;

	public ModelRenderer leftHand2;
	public ModelRenderer leftHand1;
	public ModelRenderer rightHand2;
	public ModelRenderer rightHand1;
	public ModelRenderer rightHandS1;
	public ModelRenderer leftHandS2;
	public ModelRenderer strapBaseRight;
	public ModelRenderer strapBaseLeft;

	public ModelRenderer crown12;
	public ModelRenderer crown32;
	public ModelRenderer crown322;
	public ModelRenderer crown122;

	public Elemental()
	{
		this.textureWidth = 256;
		this.textureHeight = 128;
		this.headBase2 = new ModelRenderer(this, 190, 101);
		this.headBase2.setRotationPoint(3.0F, 35.5F, -20.4F);
		this.headBase2.addBox(-8.0F, -10.5F, -8.0F, 13, 13, 13, 0.0F);
		addPart(headBase2);
		this.ribleft2 = new ModelRenderer(this, 130, 79);
		this.ribleft2.setRotationPoint(3.6F, 46.8F, -2.7F);
		this.ribleft2.addBox(-8.0F, -10.5F, -8.0F, 3, 31, 3, 0.0F);
		this.setRotateAngle(ribleft2, 0.6373942428283291F, 0.0F, 0.0F);
		addPart(ribleft2);
		this.ribright2 = new ModelRenderer(this, 130, 79);
		this.ribright2.setRotationPoint(10.9F, 46.8F, -2.7F);
		this.ribright2.addBox(-8.0F, -10.5F, -8.0F, 3, 31, 3, 0.0F);
		this.setRotateAngle(ribright2, 0.6373942428283291F, 0.0F, 0.0F);
		addPart(ribright2);
		this.leftHand1 = new ModelRenderer(this, 150, 100);
		this.leftHand1.setRotationPoint(18.7F, 49.3F, -14.2F);
		this.leftHand1.addBox(-8.0F, -10.5F, -8.0F, 9, 18, 9, 0.0F);
		this.setRotateAngle(leftHand1, 0.18203784098300857F, 0.0F, -0.5918411493512771F);
		addPart(leftHand1);
		this.crown12 = new ModelRenderer(this, 65, 60);
		this.crown12.setRotationPoint(-7.3F, 38.4F, -30.4F);
		this.crown12.addBox(-8.0F, -10.5F, -8.0F, 3, 3, 9, 0.0F);
		this.setRotateAngle(crown12, -0.5009094953223726F, 1.5707963267948966F, 0.0F);
		addPart(crown12);
		this.headBaseS = new ModelRenderer(this, 2, 29);
		this.headBaseS.setRotationPoint(-0.1F, 40.7F, -22.2F);
		this.headBaseS.addBox(-8.0F, -10.5F, -8.0F, 3, 3, 2, 0.0F);
		this.setRotateAngle(headBaseS, 0.0F, 0.0F, 0.7740535232594852F);
		addPart(headBaseS);
		this.rightHand2 = new ModelRenderer(this, 100, 0);
		this.rightHand2.setRotationPoint(-18.3F, 64.0F, -21.2F);
		this.rightHand2.addBox(-8.0F, -10.5F, -8.0F, 9, 18, 9, 0.0F);
		this.setRotateAngle(rightHand2, -0.9560913642424937F, 0.0F, 0.5918411493512771F);
		addPart(rightHand2);
		this.bot3 = new ModelRenderer(this, 0, 24);
		this.bot3.setRotationPoint(2.0F, 79.5F, -10.1F);
		this.bot3.addBox(-8.0F, -10.5F, -8.0F, 14, 9, 14, 0.0F);
		this.setRotateAngle(bot3, 0.40980330836826856F, 0.0F, 0.0F);
		addPart(bot3);
		this.ribleft3 = new ModelRenderer(this, 51, 84);
		this.ribleft3.setRotationPoint(-5.6F, 50.4F, -1.7F);
		this.ribleft3.addBox(-8.0F, -10.5F, -8.0F, 3, 14, 3, 0.0F);
		this.setRotateAngle(ribleft3, 0.5009094953223726F, -0.136659280431156F, 0.5288347633542818F);
		addPart(ribleft3);
		this.crown122 = new ModelRenderer(this, 100, 60);
		this.crown122.setRotationPoint(1.6F, 22.1F, -30.4F);
		this.crown122.addBox(-8.0F, -10.5F, -8.0F, 3, 3, 7, 0.0F);
		this.setRotateAngle(crown122, 1.5707963267948966F, 1.5707963267948966F, 0.2071705822117269F);
		addPart(crown122);
		this.strapBaseRight = new ModelRenderer(this, 71, 78);
		this.strapBaseRight.setRotationPoint(10.0F, 50.7F, -17.4F);
		this.strapBaseRight.addBox(-8.0F, -10.5F, -8.0F, 13, 5, 13, 0.0F);
		this.setRotateAngle(strapBaseRight, 0.0F, -0.22759093446006054F, 0.8651597102135892F);
		addPart(strapBaseRight);
		this.bot4 = new ModelRenderer(this, 1, 1);
		this.bot4.setRotationPoint(3.0F, 83.4F, -6.4F);
		this.bot4.addBox(-8.0F, -10.5F, -8.0F, 12, 9, 12, 0.0F);
		this.setRotateAngle(bot4, 0.36425021489121656F, 0.0F, 0.0F);
		addPart(bot4);
		this.bodyBase = new ModelRenderer(this, 0, 88);
		this.bodyBase.setRotationPoint(1.0F, 50.4F, -19.1F);
		this.bodyBase.addBox(-8.0F, -10.5F, -8.0F, 16, 23, 16, 0.0F);
		this.setRotateAngle(bodyBase, 0.18203784098300857F, 0.0F, 0.0F);
		addPart(bodyBase);
		this.body = new ModelRenderer(this, 185, 39);
		this.body.setRotationPoint(1.0F, 49.9F, -17.2F);
		this.body.addBox(-8.0F, -10.5F, -8.0F, 16, 12, 16, 0.0F);
		this.setRotateAngle(body, 0.27314402793711257F, 0.0F, 0.0F);
		addPart(body);
		this.strapBaseLeft = new ModelRenderer(this, 131, 50);
		this.strapBaseLeft.setRotationPoint(-6.2F, 48.4F, -18.1F);
		this.strapBaseLeft.addBox(-8.0F, -10.5F, -8.0F, 13, 5, 13, 0.0F);
		this.setRotateAngle(strapBaseLeft, 0.0F, 0.22759093446006054F, -0.8651597102135892F);
		addPart(strapBaseLeft);
		this.backBase = new ModelRenderer(this, 149, 77);
		this.backBase.setRotationPoint(-0.8F, 44.3F, -2.8F);
		this.backBase.addBox(-8.0F, -10.5F, -8.0F, 13, 13, 3, 0.0F);
		this.setRotateAngle(backBase, 0.31869712141416456F, -0.31869712141416456F, 0.7476990515543708F);
		addPart(backBase);
		this.crown32 = new ModelRenderer(this, 65, 60);
		this.crown32.setRotationPoint(16.0F, 35.3F, -30.4F);
		this.crown32.addBox(-8.0F, -10.5F, -8.0F, 3, 3, 9, 0.0F);
		this.setRotateAngle(crown32, 0.5009094953223726F, 1.5707963267948966F, 0.0F);
		addPart(crown32);
		this.headBase1 = new ModelRenderer(this, 200, 2);
		this.headBase1.setRotationPoint(5.5F, 44.6F, -16.7F);
		this.headBase1.addBox(-8.0F, -10.5F, -8.0F, 8, 8, 8, 0.0F);
		addPart(headBase1);
		this.ribleft55 = new ModelRenderer(this, 189, 73);
		this.ribleft55.setRotationPoint(7.3F, 47.0F, -2.7F);
		this.ribleft55.addBox(-8.0F, -10.5F, -8.0F, 3, 35, 3, 0.0F);
		this.setRotateAngle(ribleft55, 0.5009094953223726F, 0.0F, 0.0F);
		addPart(ribleft55);
		this.bodyBaseBot = new ModelRenderer(this, 208, 73);
		this.bodyBaseBot.setRotationPoint(-1.0F, 56.0F, -20.2F);
		this.bodyBaseBot.addBox(-8.0F, -10.5F, -8.0F, 20, 8, 1, 0.0F);
		this.setRotateAngle(bodyBaseBot, 0.091106186954104F, 0.0F, 0.0F);
		addPart(bodyBaseBot);
		this.ribright = new ModelRenderer(this, 237, 87);
		this.ribright.setRotationPoint(14.6F, 46.5F, -3.1F);
		this.ribright.addBox(-8.0F, -10.5F, -8.0F, 3, 22, 3, 0.0F);
		this.setRotateAngle(ribright, 0.7285004297824331F, 0.136659280431156F, 0.0F);
		addPart(ribright);
		this.ribleft = new ModelRenderer(this, 237, 87);
		this.ribleft.setRotationPoint(0.0F, 46.8F, -1.2F);
		this.ribleft.addBox(-8.0F, -10.5F, -8.0F, 3, 22, 3, 0.0F);
		this.setRotateAngle(ribleft, 0.7285004297824331F, -0.136659280431156F, 0.0F);
		addPart(ribright);
		this.bodyBaseLeft = new ModelRenderer(this, 50, 0);
		this.bodyBaseLeft.setRotationPoint(-4.5F, 50.4F, -15.8F);
		this.bodyBaseLeft.addBox(-8.0F, -10.5F, -8.0F, 13, 6, 11, 0.0F);
		this.setRotateAngle(bodyBaseLeft, 0.1869247628885927F, 0.12740903539558604F, 0.0F);
		addPart(bodyBaseLeft);
		this.ribright3 = new ModelRenderer(this, 51, 84);
		this.ribright3.setRotationPoint(17.9F, 44.0F, -3.1F);
		this.ribright3.addBox(-8.0F, -10.5F, -8.0F, 3, 14, 3, 0.0F);
		this.setRotateAngle(ribright3, 0.5009094953223726F, 0.136659280431156F, -0.5291838292046808F);
		addPart(ribright3);
		this.bot2 = new ModelRenderer(this, 59, 28);
		this.bot2.setRotationPoint(1.0F, 75.3F, -13.7F);
		this.bot2.addBox(-8.0F, -10.5F, -8.0F, 16, 9, 16, 0.0F);
		this.setRotateAngle(bot2, 0.22759093446006054F, 0.0F, 0.0F);
		addPart(bot2);
		this.bot1 = new ModelRenderer(this, 126, 18);
		this.bot1.setRotationPoint(0.0F, 70.4F, -16.0F);
		this.bot1.addBox(-8.0F, -10.5F, -8.0F, 18, 9, 18, 0.0F);
		this.setRotateAngle(bot1, 0.22759093446006054F, 0.0F, 0.0F);
		addPart(bot1);
		this.bodyBaseRight = new ModelRenderer(this, 50, 0);
		this.bodyBaseRight.setRotationPoint(9.3F, 50.4F, -15.8F);
		this.bodyBaseRight.addBox(-8.0F, -10.5F, -8.0F, 13, 6, 11, 0.0F);
		this.setRotateAngle(bodyBaseRight, 0.1869247628885927F, -0.12740903539558604F, 0.0F);
		addPart(bodyBaseRight);
		this.rightHand1 = new ModelRenderer(this, 150, 100);
		this.rightHand1.setRotationPoint(-11.2F, 53.4F, -14.2F);
		this.rightHand1.addBox(-8.0F, -10.5F, -8.0F, 9, 18, 9, 0.0F);
		this.setRotateAngle(rightHand1, 0.18203784098300857F, 0.0F, 0.5918411493512771F);
		addPart(rightHand1);
		this.crown322 = new ModelRenderer(this, 100, 60);
		this.crown322.setRotationPoint(18.6F, 18.2F, -30.8F);
		this.crown322.addBox(-8.4F, -10.5F, -8.0F, 3, 3, 7, 0.0F);
		this.setRotateAngle(crown322, 1.5707963267948966F, 1.5707963267948966F, -0.2071705822117269F);
		addPart(crown322);
		this.headBaseCircle = new ModelRenderer(this, 0, 56);
		this.headBaseCircle.setRotationPoint(2.1F, 38.3F, -21.1F);
		this.headBaseCircle.addBox(-8.0F, -10.5F, -8.0F, 15, 3, 15, 0.0F);
		addPart(headBaseCircle);
		this.leftHandS2 = new ModelRenderer(this, 150, 0);
		this.leftHandS2.setRotationPoint(26.3F, 63.0F, -28.2F);
		this.leftHandS2.addBox(-8.0F, -10.5F, -8.0F, 11, 6, 11, 0.0F);
		this.setRotateAngle(leftHandS2, -0.9560913642424937F, 0.0F, -0.5918411493512771F);
		addPart(leftHandS2);
		this.headBasedMB = new ModelRenderer(this, 1, 85);
		this.headBasedMB.setRotationPoint(8.0F, 28.6F, -21.3F);
		this.headBasedMB.addBox(-8.0F, -10.5F, -8.0F, 3, 12, 3, 0.0F);
		addPart(headBasedMB);
		this.rightHandS1 = new ModelRenderer(this, 150, 0);
		this.rightHandS1.setRotationPoint(-21.0F, 66.5F, -27.8F);
		this.rightHandS1.addBox(-8.0F, -10.5F, -8.0F, 11, 6, 11, 0.0F);
		this.setRotateAngle(rightHandS1, -0.9560913642424937F, 0.0F, 0.5918411493512771F);
		addPart(rightHandS1);
		this.bodyBaseCenter = new ModelRenderer(this, 69, 100);
		this.bodyBaseCenter.setRotationPoint(-1.0F, 50.7F, -21.0F);
		this.bodyBaseCenter.addBox(-8.0F, -10.5F, -8.0F, 20, 11, 16, 0.0F);
		this.setRotateAngle(bodyBaseCenter, 0.091106186954104F, 0.0F, 0.0F);
		addPart(bodyBaseCenter);
		this.leftHand2 = new ModelRenderer(this, 100, 0);
		this.leftHand2.setRotationPoint(25.2F, 58.9F, -20.9F);
		this.leftHand2.addBox(-8.0F, -10.5F, -8.0F, 9, 18, 9, 0.0F);
		this.setRotateAngle(leftHand2, -0.9560913642424937F, 0.0F, -0.5918411493512771F);
		addPart(leftHand2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glScaled(5D / modelScale[0], 5D / modelScale[1], 5D / modelScale[2]);
		GL11.glTranslated(1, -4, 0);
		GL11.glRotated(90, 0, 1, 0);
		this.headBase2.render(f5);
		this.ribleft2.render(f5);
		this.ribright2.render(f5);
		this.leftHand1.render(f5);
		this.crown12.render(f5);
		this.headBaseS.render(f5);
		this.rightHand2.render(f5);
		this.bot3.render(f5);
		this.ribleft3.render(f5);
		this.crown122.render(f5);
		this.strapBaseRight.render(f5);
		this.bot4.render(f5);
		this.bodyBase.render(f5);
		this.body.render(f5);
		this.strapBaseLeft.render(f5);
		this.backBase.render(f5);
		this.crown32.render(f5);
		this.headBase1.render(f5);
		this.ribleft55.render(f5);
		this.bodyBaseBot.render(f5);
		this.ribright.render(f5);
		this.ribleft.render(f5);
		this.bodyBaseLeft.render(f5);
		this.ribright3.render(f5);
		this.bot2.render(f5);
		this.bot1.render(f5);
		this.bodyBaseRight.render(f5);
		this.rightHand1.render(f5);
		this.crown322.render(f5);
		this.headBaseCircle.render(f5);
		this.leftHandS2.render(f5);
		this.headBasedMB.render(f5);
		this.rightHandS1.render(f5);
		this.bodyBaseCenter.render(f5);
		this.leftHand2.render(f5);
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
