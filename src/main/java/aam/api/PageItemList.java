package aam.api;

import aam.utils.Color;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.util.GuiHelper;
import amerifrance.guideapi.gui.GuiBase;
import amerifrance.guideapi.pages.PageText;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class PageItemList extends PageText
{
	public StackList stack;

	public PageItemList(String draw, StackList stack)
	{
		super(draw, 60);
		this.stack = stack;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void draw(Book book, CategoryAbstract category, EntryAbstract entry, int guiLeft, int guiTop, int mouseX, int mouseY, GuiBase guiBase, FontRenderer fontRenderer)
	{
		boolean startFlag = fontRenderer.getUnicodeFlag();
		if (unicode)
		{
			fontRenderer.setUnicodeFlag(true);
		}

		fontRenderer.drawString(draw, guiLeft + 60 + draw.length() / 2, guiTop + 12, Color.Black.hex, false);
		if (unicode && !startFlag)
		{
			fontRenderer.setUnicodeFlag(false);
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawExtras(Book book, CategoryAbstract category, EntryAbstract entry, int guiLeft, int guiTop, int mouseX, int mouseY, GuiBase guiBase, FontRenderer fontRenderer)
	{
		for (int i = 0; i < stack.size(); i++)
		{
			GL11.glPushMatrix();
			GL11.glTranslated(guiLeft + 75, guiTop + 25 + 20 * i, 0);
			if (stack.getTip(i).length() * 8 > 90)
			{
				GL11.glScaled(0.55, 0.55, 0.55);
			}
			fontRenderer.drawSplitString(stack.getTip(i), 0, 0, 90, Color.Black.hex);

			GL11.glPopMatrix();
		}
		for (int i = 0; i < stack.size(); i++)
		{
			if (stack.getStack(i) instanceof ItemStack)
			{
				GuiHelper.drawScaledItemStack((ItemStack) stack.getStack(i), guiLeft + 45, guiTop + 25 + 20 * i, 1.0F);
			} else if (stack.getStack(i) instanceof ItemStack[])
			{
				int l = ((ItemStack[]) stack.getStack(i)).length;
				int v = (int) (Minecraft.getSystemTime() / 40d % (l * 15) / 15d);
				GuiHelper.drawScaledItemStack(((ItemStack[]) stack.getStack(i))[v], guiLeft + 45, guiTop + 25 + 20 * i, 1.0F);
			}
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		} else if (o != null && this.getClass() == o.getClass())
		{
			if (!super.equals(o))
			{
				return false;
			} else
			{
				PageItemList that = (PageItemList) o;
				if (stack != null)
				{
					if (!stack.isItemsEqual(that.stack))
					{
						return false;
					}
				} else if (that.stack != null)
				{
					return false;
				}

				return true;
			}
		} else
		{
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		return stack != null ? stack.hashCode() : 0;
	}
}
