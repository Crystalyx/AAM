package AAM.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Hammer - Lord_Crystalyx
 * Created using Tabula 4.1.1
 */
public class Hammer extends ModelBase {
    public double[] modelScale = new double[] { 4.0D, 4.0D, 4.0D };
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape6;

    public Hammer() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.shape5 = new ModelRenderer(this, 0, 0);
        this.shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape5.addBox(-5.0F, -5.0F, 14.0F, 10, 10, 1, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 21);
        this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3.addBox(-6.0F, -6.0F, 7.0F, 12, 12, 7, 0.0F);
        this.shape1 = new ModelRenderer(this, 112, 0);
        this.shape1.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.shape1.addBox(-2.0F, 0.0F, -2.0F, 4, 54, 4, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 40);
        this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2.addBox(-5.0F, -5.0F, -7.0F, 10, 10, 14, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 0);
        this.shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape6.addBox(-5.0F, -5.0F, -15.0F, 10, 10, 1, 0.0F);
        this.shape4 = new ModelRenderer(this, 0, 21);
        this.shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4.addBox(-6.0F, -6.0F, -14.0F, 12, 12, 7, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GL11.glPushMatrix();
        GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
        this.shape5.render(f5);
        this.shape3.render(f5);
        this.shape1.render(f5);
        this.shape2.render(f5);
        this.shape6.render(f5);
        this.shape4.render(f5);
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
