/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * SOWPModel - Lord_Crystalyx
 * Created using Tabula 4.1.1
 */
public class SOWPModel extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape8;
    public ModelRenderer shape9;
    public ModelRenderer shape10;
    public ModelRenderer shape11;
    public ModelRenderer shape12;

    public SOWPModel() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.shape11 = new ModelRenderer(this, 0, 0);
        this.shape11.setRotationPoint(6.0F, 10.0F, 7.0F);
        this.shape11.addBox(0.0F, 0.0F, 0.0F, 2, 6, 96, 0.0F);
        this.setRotateAngle(shape11, -0.22689280275926282F, 0.0F, 0.0F);
        this.shape10 = new ModelRenderer(this, 0, 0);
        this.shape10.setRotationPoint(-7.5F, 14.0F, -7.5F);
        this.shape10.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
        this.shape8 = new ModelRenderer(this, 0, 0);
        this.shape8.setRotationPoint(5.5F, 14.0F, -7.5F);
        this.shape8.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 0);
        this.shape6.setRotationPoint(7.0F, 10.0F, -8.0F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 2, 6, 32, 0.0F);
        this.shape9 = new ModelRenderer(this, 0, 0);
        this.shape9.setRotationPoint(-7.5F, 14.0F, 5.5F);
        this.shape9.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
        this.shape12 = new ModelRenderer(this, 0, 0);
        this.shape12.setRotationPoint(-7.0F, 10.0F, 7.0F);
        this.shape12.addBox(0.0F, 0.0F, 0.0F, 2, 6, 96, 0.0F);
        this.setRotateAngle(shape12, -0.22689280275926282F, 0.0F, 0.0F);
        this.shape4 = new ModelRenderer(this, 0, 0);
        this.shape4.setRotationPoint(-8.0F, 10.0F, -8.0F);
        this.shape4.addBox(0.0F, 0.0F, 0.0F, 2, 6, 32, 0.0F);
        this.shape7 = new ModelRenderer(this, 8, 5);
        this.shape7.setRotationPoint(-5.25F, 13.0F, 7.0F);
        this.shape7.addBox(-7.0F, 0.0F, 0.0F, 56, 4, 192, 0.0F);
        this.setRotateAngle(shape7, -0.22689280275926282F, 0.0F, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 52);
        this.shape3.setRotationPoint(-8.0F, 10.0F, -8.0F);
        this.shape3.addBox(0.0F, 0.0F, 0.0F, 32, 6, 2, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(5.5F, 14.0F, 5.5F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
        this.shape2 = new ModelRenderer(this, 314, 1);
        this.shape2.setRotationPoint(-8.0F, 13.0F, -8.0F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 32, 2, 32, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape11.offsetX, this.shape11.offsetY, this.shape11.offsetZ);
        GL11.glTranslatef(this.shape11.rotationPointX * f5, this.shape11.rotationPointY * f5, this.shape11.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.shape11.offsetX, -this.shape11.offsetY, -this.shape11.offsetZ);
        GL11.glTranslatef(-this.shape11.rotationPointX * f5, -this.shape11.rotationPointY * f5, -this.shape11.rotationPointZ * f5);
        this.shape11.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape10.offsetX, this.shape10.offsetY, this.shape10.offsetZ);
        GL11.glTranslatef(this.shape10.rotationPointX * f5, this.shape10.rotationPointY * f5, this.shape10.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.shape10.offsetX, -this.shape10.offsetY, -this.shape10.offsetZ);
        GL11.glTranslatef(-this.shape10.rotationPointX * f5, -this.shape10.rotationPointY * f5, -this.shape10.rotationPointZ * f5);
        this.shape10.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape8.offsetX, this.shape8.offsetY, this.shape8.offsetZ);
        GL11.glTranslatef(this.shape8.rotationPointX * f5, this.shape8.rotationPointY * f5, this.shape8.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.shape8.offsetX, -this.shape8.offsetY, -this.shape8.offsetZ);
        GL11.glTranslatef(-this.shape8.rotationPointX * f5, -this.shape8.rotationPointY * f5, -this.shape8.rotationPointZ * f5);
        this.shape8.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape6.offsetX, this.shape6.offsetY, this.shape6.offsetZ);
        GL11.glTranslatef(this.shape6.rotationPointX * f5, this.shape6.rotationPointY * f5, this.shape6.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.shape6.offsetX, -this.shape6.offsetY, -this.shape6.offsetZ);
        GL11.glTranslatef(-this.shape6.rotationPointX * f5, -this.shape6.rotationPointY * f5, -this.shape6.rotationPointZ * f5);
        this.shape6.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape9.offsetX, this.shape9.offsetY, this.shape9.offsetZ);
        GL11.glTranslatef(this.shape9.rotationPointX * f5, this.shape9.rotationPointY * f5, this.shape9.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.shape9.offsetX, -this.shape9.offsetY, -this.shape9.offsetZ);
        GL11.glTranslatef(-this.shape9.rotationPointX * f5, -this.shape9.rotationPointY * f5, -this.shape9.rotationPointZ * f5);
        this.shape9.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape12.offsetX, this.shape12.offsetY, this.shape12.offsetZ);
        GL11.glTranslatef(this.shape12.rotationPointX * f5, this.shape12.rotationPointY * f5, this.shape12.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.shape12.offsetX, -this.shape12.offsetY, -this.shape12.offsetZ);
        GL11.glTranslatef(-this.shape12.rotationPointX * f5, -this.shape12.rotationPointY * f5, -this.shape12.rotationPointZ * f5);
        this.shape12.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape4.offsetX, this.shape4.offsetY, this.shape4.offsetZ);
        GL11.glTranslatef(this.shape4.rotationPointX * f5, this.shape4.rotationPointY * f5, this.shape4.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.shape4.offsetX, -this.shape4.offsetY, -this.shape4.offsetZ);
        GL11.glTranslatef(-this.shape4.rotationPointX * f5, -this.shape4.rotationPointY * f5, -this.shape4.rotationPointZ * f5);
        this.shape4.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape7.offsetX, this.shape7.offsetY, this.shape7.offsetZ);
        GL11.glTranslatef(this.shape7.rotationPointX * f5, this.shape7.rotationPointY * f5, this.shape7.rotationPointZ * f5);
        GL11.glScaled(0.25D, 0.25D, 0.25D);
        GL11.glTranslatef(-this.shape7.offsetX, -this.shape7.offsetY, -this.shape7.offsetZ);
        GL11.glTranslatef(-this.shape7.rotationPointX * f5, -this.shape7.rotationPointY * f5, -this.shape7.rotationPointZ * f5);
        this.shape7.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape3.offsetX, this.shape3.offsetY, this.shape3.offsetZ);
        GL11.glTranslatef(this.shape3.rotationPointX * f5, this.shape3.rotationPointY * f5, this.shape3.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.shape3.offsetX, -this.shape3.offsetY, -this.shape3.offsetZ);
        GL11.glTranslatef(-this.shape3.rotationPointX * f5, -this.shape3.rotationPointY * f5, -this.shape3.rotationPointZ * f5);
        this.shape3.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape1.offsetX, this.shape1.offsetY, this.shape1.offsetZ);
        GL11.glTranslatef(this.shape1.rotationPointX * f5, this.shape1.rotationPointY * f5, this.shape1.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.shape1.offsetX, -this.shape1.offsetY, -this.shape1.offsetZ);
        GL11.glTranslatef(-this.shape1.rotationPointX * f5, -this.shape1.rotationPointY * f5, -this.shape1.rotationPointZ * f5);
        this.shape1.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape2.offsetX, this.shape2.offsetY, this.shape2.offsetZ);
        GL11.glTranslatef(this.shape2.rotationPointX * f5, this.shape2.rotationPointY * f5, this.shape2.rotationPointZ * f5);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glTranslatef(-this.shape2.offsetX, -this.shape2.offsetY, -this.shape2.offsetZ);
        GL11.glTranslatef(-this.shape2.rotationPointX * f5, -this.shape2.rotationPointY * f5, -this.shape2.rotationPointZ * f5);
        this.shape2.render(f5);
        GL11.glPopMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
