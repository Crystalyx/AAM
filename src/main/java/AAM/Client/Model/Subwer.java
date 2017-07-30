package AAM.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * nares - Smashe
 * Created using Tabula 4.1.1
 */
public class Subwer extends ModelBase {
    public ModelRenderer body2;
    public ModelRenderer kr1right;
    public ModelRenderer kr1left;
    public ModelRenderer head;
    public ModelRenderer body3;
    public ModelRenderer body4;
    public ModelRenderer kr2right;
    public ModelRenderer kr2left;
    public ModelRenderer ebasos;
    public ModelRenderer rog23;
    public ModelRenderer rog32;
    public ModelRenderer iese;
    public ModelRenderer iese2;

    public Subwer() {
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
        this.kr2right = new ModelRenderer(this, 8, 6);
        this.kr2right.setRotationPoint(0.6F, 1.0F, 0.0F);
        this.kr2right.addBox(-0.5F, 0.0F, 0.0F, 7, 1, 4, 0.0F);
        this.setRotateAngle(kr2right, 0.0F, 0.0F, 2.049016541841343F);
        this.kr1right = new ModelRenderer(this, 7, 14);
        this.kr1right.setRotationPoint(-7.1F, 0.0F, 5.0F);
        this.kr1right.addBox(0.0F, 0.0F, 0.0F, 7, 1, 4, 0.0F);
        this.setRotateAngle(kr1right, 0.0F, 0.0F, 0.5009094953223726F);
        this.kr2left = new ModelRenderer(this, 8, 6);
        this.kr2left.setRotationPoint(0.5F, -1.0F, 0.0F);
        this.kr2left.addBox(3.5F, -5.6F, 0.0F, 7, 1, 4, 0.0F);
        this.setRotateAngle(kr2left, 0.0F, 0.0F, 1.1833332328521553F);
        this.ebasos = new ModelRenderer(this, 4, 24);
        this.ebasos.setRotationPoint(0.0F, 3.7F, -1.5F);
        this.ebasos.addBox(0.5F, 0.0F, 0.0F, 3, 3, 2, 0.0F);
        this.setRotateAngle(ebasos, 1.5707963267948966F, 0.0F, 0.0F);
        this.iese2 = new ModelRenderer(this, 2, 2);
        this.iese2.setRotationPoint(2.5F, 0.3F, 0.6F);
        this.iese2.addBox(0.0F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.body3 = new ModelRenderer(this, 36, 22);
        this.body3.setRotationPoint(0.0F, 0.7F, 8.4F);
        this.body3.addBox(-1.5F, 0.5F, -4.0F, 3, 3, 5, 0.0F);
        this.setRotateAngle(body3, -0.136659280431156F, 0.0F, 0.0F);
        this.rog32 = new ModelRenderer(this, 2, 8);
        this.rog32.setRotationPoint(1.7F, 0.0F, 2.0F);
        this.rog32.addBox(1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.head = new ModelRenderer(this, 35, 0);
        this.head.setRotationPoint(-1.5F, 0.7F, 0.9F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.rog23 = new ModelRenderer(this, 2, 8);
        this.rog23.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.rog23.addBox(0.3F, -1.0F, 0.0F, 1, 1, 1, 0.0F);
        this.kr1left = new ModelRenderer(this, 7, 14);
        this.kr1left.setRotationPoint(1.9F, 3.3F, 5.0F);
        this.kr1left.addBox(0.0F, 0.0F, 0.0F, 7, 1, 4, 0.0F);
        this.setRotateAngle(kr1left, 0.0F, 0.0F, -0.5009094953223726F);
        this.body2 = new ModelRenderer(this, 34, 10);
        this.body2.setRotationPoint(0.4F, 1.0F, 5.0F);
        this.body2.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 5, 0.0F);
        this.setRotateAngle(body2, -0.5462880558742251F, 0.0F, 0.0F);
        this.body3.addChild(this.body4);
        this.head.addChild(this.iese);
        this.kr1right.addChild(this.kr2right);
        this.kr1left.addChild(this.kr2left);
        this.head.addChild(this.ebasos);
        this.head.addChild(this.iese2);
        this.body2.addChild(this.body3);
        this.head.addChild(this.rog32);
        this.head.addChild(this.rog23);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.kr1right.render(f5);
        this.head.render(f5);
        this.kr1left.render(f5);
        this.body2.render(f5);
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
