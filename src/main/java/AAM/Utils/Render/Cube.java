package AAM.Utils.Render;

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
		this.textureWidth = 128;
		this.textureHeight = 64;

		this.shape = new ModelRenderer(this, 0, 0);
		this.shape.setRotationPoint(this.offsetx, this.offsety, this.offsetz);
		this.shape.addBox(this.px, this.py, this.pz, this.dx, this.dy, this.dz, this.scale / 16);
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
		this.textureWidth = texturelength;
		this.textureHeight = texturelength;

		this.dx = dx;
		this.dy = dy;
		this.dz = dz;

		this.scale = (float) scale / (texturelength * 4);

		this.px = px - (this.dx / 2) * (this.scale);
		this.py = py - (this.dy / 2) * (this.scale);
		this.pz = pz - (this.dz / 2) * (this.scale);

		this.rotationx = px;
		this.rotationy = py;
		this.rotationz = pz;

		this.shape = new ModelRenderer(this, 0, 0);
		this.shape.setRotationPoint(this.rotationx, this.rotationy, this.rotationz);
		this.shape.addBox(this.px, this.py, this.pz, this.dx, this.dy, this.dz, scale);

	}

	/**
	 * @param texture
	 *            texture
	 */
	public void render(ResourceLocation texture)
	{
		Tessellator t = Tessellator.instance;

		GL11.glPushMatrix();
		GL11.glTranslatef(this.px, this.py + 1 * this.scale, this.pz);

		GL11.glScalef(this.scale, this.scale, this.scale);

		GL11.glRotatef(this.angle, this.rotationx, this.rotationy, this.rotationz);
		if (texture != null)
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		this.shape.render(1.0F);

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
		GL11.glTranslatef(this.px, this.py - 1 / 16, this.pz);

		GL11.glScalef(this.scale, this.scale, this.scale);

		GL11.glRotatef(this.angle, this.rotationx, this.rotationy, this.rotationz);

		if (b != null)
		{
			IIcon i = RenderBlocks.getInstance().getBlockIcon(b);
			ResourceLocation text = new ResourceLocation(i.getIconName());
		}
		this.shape.render(1.0F);

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
		this.rotationx = rotx;
		this.rotationy = roty;
		this.rotationz = rotz;
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
