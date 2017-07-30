package AAM.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Pillar - Lord_Crystalyx
 * Created using Tabula 4.1.1
 */
public class Pillar extends ModelBase {
    public double[] modelScale = new double[] { 4.0D, 4.0D, 4.0D };
    public ModelRenderer shape1;

    public Pillar() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 67.0F, 0.0F);
        this.shape1.addBox(-24.0F, -35.0F, -24.0F, 48, 64, 48, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GL11.glPushMatrix();
        GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
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
