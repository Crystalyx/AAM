package AAM.Client.Model;

import org.lwjgl.opengl.GL11;

import AAM.Client.Renderer.Tile.ModificationAnvilRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class Altar extends ModelBase
{
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape5;
	public ModelRenderer shape6;
	public ModelRenderer shape7;

	public Altar()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.shape7 = new ModelRenderer(this, 0, 0);
		this.shape7.setRotationPoint(4.5F, 14.0F, -4.0F);
		this.shape7.addBox(0.0F, 0.0F, 0.2F, 4, 20, 4, 0.0F);
		this.shape6 = new ModelRenderer(this, 56, 0);
		this.shape6.setRotationPoint(-6.5F, 14.0F, -4.0F);
		this.shape6.addBox(0.0F, 0.0F, 0.2F, 4, 20, 4, 0.0F);
		this.shape1 = new ModelRenderer(this, 16, 0);
		this.shape1.setRotationPoint(-1.5F, 11.0F, -1.5F);
		this.shape1.addBox(0.0F, 0.0F, 0.0F, 6, 26, 6, 0.0F);
		this.shape5 = new ModelRenderer(this, 72, 0);
		this.shape5.setRotationPoint(-6.5F, 14.0F, 2.0F);
		this.shape5.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
		this.shape2 = new ModelRenderer(this, 88, 0);
		this.shape2.setRotationPoint(-1.0F, 14.0F, 5.0F);
		this.shape2.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
		this.shape3 = new ModelRenderer(this, 40, 0);
		this.shape3.setRotationPoint(-1.0F, 14.0F, -7.0F);
		this.shape3.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
		this.shape4 = new ModelRenderer(this, 104, 0);
		this.shape4.setRotationPoint(4.5F, 14.0F, 2.0F);
		this.shape4.addBox(0.0F, 0.0F, 0.0F, 4, 20, 4, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape7.offsetX, this.shape7.offsetY, this.shape7.offsetZ);
		GL11.glTranslatef(this.shape7.rotationPointX * f5, this.shape7.rotationPointY * f5, this.shape7.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape7.offsetX, -this.shape7.offsetY, -this.shape7.offsetZ);
		GL11.glTranslatef(-this.shape7.rotationPointX * f5, -this.shape7.rotationPointY * f5, -this.shape7.rotationPointZ * f5);
		this.shape7.render(f5);
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
		GL11.glTranslatef(this.shape1.offsetX, this.shape1.offsetY, this.shape1.offsetZ);
		GL11.glTranslatef(this.shape1.rotationPointX * f5, this.shape1.rotationPointY * f5, this.shape1.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape1.offsetX, -this.shape1.offsetY, -this.shape1.offsetZ);
		GL11.glTranslatef(-this.shape1.rotationPointX * f5, -this.shape1.rotationPointY * f5, -this.shape1.rotationPointZ * f5);
		this.shape1.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape5.offsetX, this.shape5.offsetY, this.shape5.offsetZ);
		GL11.glTranslatef(this.shape5.rotationPointX * f5, this.shape5.rotationPointY * f5, this.shape5.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape5.offsetX, -this.shape5.offsetY, -this.shape5.offsetZ);
		GL11.glTranslatef(-this.shape5.rotationPointX * f5, -this.shape5.rotationPointY * f5, -this.shape5.rotationPointZ * f5);
		this.shape5.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape2.offsetX, this.shape2.offsetY, this.shape2.offsetZ);
		GL11.glTranslatef(this.shape2.rotationPointX * f5, this.shape2.rotationPointY * f5, this.shape2.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape2.offsetX, -this.shape2.offsetY, -this.shape2.offsetZ);
		GL11.glTranslatef(-this.shape2.rotationPointX * f5, -this.shape2.rotationPointY * f5, -this.shape2.rotationPointZ * f5);
		this.shape2.render(f5);
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
		GL11.glTranslatef(this.shape4.offsetX, this.shape4.offsetY, this.shape4.offsetZ);
		GL11.glTranslatef(this.shape4.rotationPointX * f5, this.shape4.rotationPointY * f5, this.shape4.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape4.offsetX, -this.shape4.offsetY, -this.shape4.offsetZ);
		GL11.glTranslatef(-this.shape4.rotationPointX * f5, -this.shape4.rotationPointY * f5, -this.shape4.rotationPointZ * f5);
		this.shape4.render(f5);
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
