package aam.utils.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx
 */
public class Cube extends ModelBase
{
	public float px = 0;
	public float py = 0;
	public float pz = 0;

	public int dx = 16;
	public int dy = 16;
	public int dz = 16;

	public float angle = 0;

	public float rotationx;
	public float rotationy;
	public float rotationz;

	public float scale = 1 / 3;

	public float offsetx = 0;
	public float offsety = 0;
	public float offsetz = 0;

	public float textoffsetx = 0;
	public float textoffsety = 0;

	public ModelRenderer shape;

	@Deprecated
	public Cube()
	{
		textureWidth = 128;
		textureHeight = 64;

		shape = new ModelRenderer(this, 0, 0);
		shape.setRotationPoint(offsetx, offsety, offsetz);
		shape.addBox(px, py, pz, dx, dy, dz, scale / 16);
	}

	/**
	 * 
	 * @param px
	 *            minX
	 * @param py
	 *            minY
	 * @param pz
	 *            minZ
	 * @param dx
	 *            maxX
	 * @param dy
	 *            maxY
	 * @param dz
	 *            maxZ
	 * @param textoffsetx
	 *            textureOffsetX
	 * @param textoffsety
	 *            textureOffsetY
	 * @param scale
	 *            modelScale
	 * @param texturelength
	 *            textureSideLength
	 */
	public Cube(float px, float py, float pz, int dx, int dy, int dz, float scale, int texturelength)
	{
		textureWidth = texturelength;
		textureHeight = texturelength;

		this.dx = dx;
		this.dy = dy;
		this.dz = dz;

		this.scale = scale / (texturelength * 4);

		this.px = px - this.dx / 2 * this.scale;
		this.py = py - this.dy / 2 * this.scale;
		this.pz = pz - this.dz / 2 * this.scale;

		rotationx = px;
		rotationy = py;
		rotationz = pz;

		shape = new ModelRenderer(this, 0, 0);
		shape.setRotationPoint(rotationx, rotationy, rotationz);
		shape.addBox(this.px, this.py, this.pz, this.dx, this.dy, this.dz, scale);

	}

	/**
	 * @param texture
	 *            texture
	 */
	public void render(ResourceLocation texture)
	{
		Tessellator t = Tessellator.instance;

		GL11.glPushMatrix();
		GL11.glTranslatef(px, py + 1 * scale, pz);

		GL11.glScalef(scale, scale, scale);

		GL11.glRotatef(angle, rotationx, rotationy, rotationz);
		if (texture != null)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		}

		shape.render(1.0F);

		GL11.glPopMatrix();
	}

	/**
	 * @param b
	 *            block that should be rendered
	 */
	public void render(Block b)
	{
		Tessellator t = Tessellator.instance;

		GL11.glPushMatrix();
		GL11.glTranslatef(px, py - 1 / 16, pz);

		GL11.glScalef(scale, scale, scale);

		GL11.glRotatef(angle, rotationx, rotationy, rotationz);

		if (b != null)
		{
			IIcon i = RenderBlocks.getInstance().getBlockIcon(b);
			ResourceLocation text = new ResourceLocation(i.getIconName());
		}
		shape.render(1.0F);

		GL11.glPopMatrix();
	}

	/**
	 * 
	 * @param angle
	 *            rotationAngle
	 * @param rotx
	 *            vecX
	 * @param roty
	 *            vecY
	 * @param rotz
	 *            vecZ
	 */
	public void setRotation(int angle, int rotx, int roty, int rotz)
	{
		this.angle = angle;
		rotationx = rotx;
		rotationy = roty;
		rotationz = rotz;
	}

	/**
	 * 
	 * @param px
	 *            minX
	 * @param py
	 *            minY
	 * @param pz
	 *            minZ
	 * @param dx
	 *            maxX
	 * @param dy
	 *            maxY
	 * @param dz
	 *            maxZ
	 **/
	public void setBounds(int px, int py, int pz, int dx, int dy, int dz)
	{
		this.px = px;
		this.py = py;
		this.pz = pz;
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}
}
