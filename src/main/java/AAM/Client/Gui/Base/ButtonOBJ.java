package aam.client.gui.base;

import org.lwjgl.opengl.GL11;

import aam.utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.GameSettings;
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
		GL11.glRotated(90 * rotation, 0, 0, 1);
		this.bindTexture();
		field_146123_n = MathUtils.isInLimit(mx, xPosition, xPosition + width) && MathUtils.isInLimit(my, yPosition, yPosition + height);
		int k = this.getHoverState(field_146123_n);
		if (disabled)
		{
			k = 0;
		}
		this.drawTexturedModalRect(xPosition, yPosition, 24, 196 + k * 20, width / 2, height);
		GL11.glPopMatrix();
		this.mouseDragged(m, mx, my);
	}

	@Override
	protected void mouseDragged(Minecraft m, int x, int y)
	{
		if (this.getHoverState(field_146123_n) == 2)
		{
			if (GameSettings.isKeyDown(m.gameSettings.keyBindAttack))
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
		hidden = hide;
		return this;
	}

	public void setCustomTexture(String way)
	{
		CustomTexture = new ResourceLocation(way);
		custText = true;
	}

	public void setCustomTexture(String head, String way)
	{
		CustomTexture = new ResourceLocation(head, way);
		custText = true;
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
		CustomTexture = null;
		custText = false;
	}

	public void reloadBaseTexture()
	{
		baseTexture = vanillaTexture;
	}

	public void bindTexture()
	{
		if (custText)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(CustomTexture);
		}
		else
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(baseTexture);
		}
	}

}
