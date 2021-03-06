package aam.client.gui.base;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class GuiOBJ
{
	public String name = "";
	public int x = 0;
	public int y = 0;
	public int sizex = 0;
	public int sizey = 0;
	public int type = -1;
	public boolean hidden = false;

	public ResourceLocation CustomTexture = null;
	public boolean custText = false;
	public static ResourceLocation baseTexture = new ResourceLocation("aam", "textures/misc/gui/base.png");
	public static final ResourceLocation vanillaTexture = new ResourceLocation("aam", "textures/misc/gui/base.png");

	public GuiBase gui;

	public void render(int k, int l)
	{

	}

	public void setGui(GuiBase gui)
	{
		this.gui = gui;
	}

	public GuiOBJ setHidden(boolean hide)
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

	public void setType(int type)
	{
		this.type = type;
	}
}
