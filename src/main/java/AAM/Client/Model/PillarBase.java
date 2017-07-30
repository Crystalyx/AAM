package AAM.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * PillarBase - Lord_Crystalyx
 * Created using Tabula 4.1.1
 */
public class PillarBase extends ModelBase {
    public double[] modelScale = new double[] { 4.0D, 4.0D, 4.0D };
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape22;
    public ModelRenderer shape23;
    public ModelRenderer shape12;
    public ModelRenderer shape13;
    public ModelRenderer shape42;
    public ModelRenderer shape43;
    public ModelRenderer shape32;
    public ModelRenderer shape33;

    public PillarBase() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.shape43 = new ModelRenderer(this, 0, 110);
        this.shape43.setRotationPoint(23.0F, 69.0F, 0.0F);
        this.shape43.addBox(-3.0F, -3.0F, -12.0F, 6, 6, 24, 0.0F);
        this.shape33 = new ModelRenderer(this, 0, 110);
        this.shape33.setRotationPoint(-23.0F, 69.0F, 0.0F);
        this.shape33.addBox(-3.0F, -3.0F, -12.0F, 6, 6, 24, 0.0F);
        this.shape22 = new ModelRenderer(this, 120, 80);
        this.shape22.setRotationPoint(0.0F, 78.0F, -25.0F);
        this.shape22.addBox(-20.0F, -6.0F, -3.0F, 40, 12, 6, 0.0F);
        this.shape12 = new ModelRenderer(this, 120, 80);
        this.shape12.setRotationPoint(0.0F, 78.0F, 25.0F);
        this.shape12.addBox(-20.0F, -6.0F, -3.0F, 40, 12, 6, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(0.0F, 90.0F, -27.0F);
        this.shape2.addBox(-26.0F, -6.0F, -3.0F, 52, 12, 6, 0.0F);
        this.shape42 = new ModelRenderer(this, 120, 0);
        this.shape42.setRotationPoint(25.0F, 78.0F, 0.0F);
        this.shape42.addBox(-3.0F, -6.0F, -20.0F, 6, 12, 40, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 20);
        this.shape3.setRotationPoint(-27.0F, 90.0F, 0.0F);
        this.shape3.addBox(-3.0F, -6.0F, -26.0F, 6, 12, 52, 0.0F);
        this.shape23 = new ModelRenderer(this, 0, 96);
        this.shape23.setRotationPoint(0.0F, 69.0F, -23.0F);
        this.shape23.addBox(-12.0F, -3.0F, -3.0F, 24, 6, 6, 0.0F);
        this.shape32 = new ModelRenderer(this, 120, 0);
        this.shape32.setRotationPoint(-25.0F, 78.0F, 0.0F);
        this.shape32.addBox(-3.0F, -6.0F, -20.0F, 6, 12, 40, 0.0F);
        this.shape4 = new ModelRenderer(this, 0, 20);
        this.shape4.setRotationPoint(27.0F, 90.0F, 0.0F);
        this.shape4.addBox(-3.0F, -6.0F, -26.0F, 6, 12, 52, 0.0F);
        this.shape13 = new ModelRenderer(this, 0, 96);
        this.shape13.setRotationPoint(0.0F, 69.0F, 23.0F);
        this.shape13.addBox(-12.0F, -3.0F, -3.0F, 24, 6, 6, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 90.0F, 27.0F);
        this.shape1.addBox(-26.0F, -6.0F, -3.0F, 52, 12, 6, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GL11.glPushMatrix();
        GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
        this.shape43.render(f5);
        this.shape33.render(f5);
        this.shape22.render(f5);
        this.shape12.render(f5);
        this.shape2.render(f5);
        this.shape42.render(f5);
        this.shape3.render(f5);
        this.shape23.render(f5);
        this.shape32.render(f5);
        this.shape4.render(f5);
        this.shape13.render(f5);
        this.shape1.render(f5);
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
