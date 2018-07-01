package AAM.API;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import AAM.Utils.Color;
import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.abstraction.IRecipeRenderer;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.util.GuiHelper;
import amerifrance.guideapi.gui.GuiBase;
import amerifrance.guideapi.pages.PageItemStack;
import amerifrance.guideapi.pages.PageText;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

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
		if (this.unicode)
		{
			fontRenderer.setUnicodeFlag(true);
		}

		fontRenderer.drawString(this.draw, guiLeft + 60 + this.draw.length() / 2, guiTop + 12, Color.Black.hex, false);
		if (this.unicode && !startFlag)
		{
			fontRenderer.setUnicodeFlag(false);
		}

	}

	@SideOnly(Side.CLIENT)
	public void drawExtras(Book book, CategoryAbstract category, EntryAbstract entry, int guiLeft, int guiTop, int mouseX, int mouseY, GuiBase guiBase, FontRenderer fontRenderer)
	{
		for (int i = 0; i < this.stack.size(); i++)
		{
			GL11.glPushMatrix();
			GL11.glTranslated(guiLeft + 75, guiTop + 25 + 20 * i, 0);
			if (this.stack.getTip(i).length()*8 > 90)
				GL11.glScaled(0.55, 0.55, 0.55);
			fontRenderer.drawSplitString(this.stack.getTip(i), 0, 0, 90, Color.Black.hex);

			GL11.glPopMatrix();
		}
		for (int i = 0; i < this.stack.size(); i++)
		{
			if (this.stack.getStack(i) instanceof ItemStack)
			{
				GuiHelper.drawScaledItemStack((ItemStack) this.stack.getStack(i), guiLeft + 45, guiTop + 25 + 20 * i, 1.0F);
			}
			else
				if (this.stack.getStack(i) instanceof ItemStack[])
				{
					int l = ((ItemStack[]) this.stack.getStack(i)).length;
					int v = (int) (((Minecraft.getSystemTime() / 40d) % (l * 15)) / 15d);
					GuiHelper.drawScaledItemStack(((ItemStack[]) this.stack.getStack(i))[v], guiLeft + 45, guiTop + 25 + 20 * i, 1.0F);
				}
		}
	}

	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		else
			if (o != null && this.getClass() == o.getClass())
			{
				if (!super.equals(o))
				{
					return false;
				}
				else
				{
					PageItemList that = (PageItemList) o;
					if (this.stack != null)
					{
						if (!this.stack.isItemsEqual(that.stack))
						{
							return false;
						}
					}
					else
						if (that.stack != null)
						{
							return false;
						}

					return true;
				}
			}
			else
			{
				return false;
			}
	}

	public int hashCode()
	{
		return this.stack != null ? this.stack.hashCode() : 0;
	}
}
