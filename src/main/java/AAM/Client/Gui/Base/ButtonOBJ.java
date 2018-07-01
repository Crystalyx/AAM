package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

import AAM.Utils.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class ButtonOBJ extends GuiButton
{

	public ButtonOBJ(int id, int x, int y)
	{
		super(id, x, y, 20, 30, "");
	}

	public void setGui(GuiBase gui)
	{
		this.gui = gui;
	}

	public ResourceLocation CustomTexture = null;
	public boolean custText = false;
	public static ResourceLocation baseTexture = new ResourceLocation("aam", "textures/misc/gui/base.png");
	public static final ResourceLocation vanillaTexture = new ResourceLocation("aam", "textures/misc/gui/base.png");
	public boolean hidden = false;
	public GuiBase gui;
	public boolean disabled = false;
	/**
	 * 0 - up; 1 - right; 2 - down; 3 - left;
	 */
	public int rotation = 0;

	@Override
	public void drawButton(Minecraft m, int mx, int my)
	{
		GL11.glPushMatrix();
		GL11.glRotated(90 * this.rotation, 0, 0, 1);
		this.bindTexture();
		this.field_146123_n = MiscUtils.isInLimit(mx, this.xPosition, this.xPosition + this.width) && MiscUtils.isInLimit(my, this.yPosition, this.yPosition + this.height);
		int k = this.getHoverState(this.field_146123_n);
		if (this.disabled)
			k = 0;
		this.drawTexturedModalRect(this.xPosition, this.yPosition, 24, 196 + k * 20, this.width / 2, this.height);
		GL11.glPopMatrix();
		this.mouseDragged(m, mx, my);
	}

	@Override
	protected void mouseDragged(Minecraft m, int x, int y)
	{
		if (this.getHoverState(this.field_146123_n) == 2)
		{
			if (m.gameSettings.isKeyDown(m.gameSettings.keyBindAttack))
			{
				this.onPressed();
			}
		}
	}

	public void onPressed()
	{

	}

	public ButtonOBJ setHidden(boolean hide)
	{
		this.hidden = hide;
		return this;
	}

	public void setCustomTexture(String way)
	{
		this.CustomTexture = new ResourceLocation(way);
		this.custText = true;
	}

	public void setCustomTexture(String head, String way)
	{
		this.CustomTexture = new ResourceLocation(head, way);
		this.custText = true;
	}

	public static void setBaseTexture(String way)
	{
		baseTexture = new ResourceLocation(way);
	}

	public static void setBaseTexture(String head, String way)
	{
		baseTexture = new ResourceLocation(head, way);
	}

	public void disableCustomTexture()
	{
		this.CustomTexture = null;
		this.custText = false;
	}

	public void reloadBaseTexture()
	{
		baseTexture = vanillaTexture;
	}

	public void bindTexture()
	{
		if (this.custText)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(this.CustomTexture);
		}
		else
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(baseTexture);
		}
	}

}
