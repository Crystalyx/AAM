package AAM.API;

import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.gui.GuiBase;
import amerifrance.guideapi.pages.PageText;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.crafting.IRecipe;

public class PageIMultiRecipe extends PageText
{
	public IRecipe[] recipes;
	public MultiRecipeRenderer renderer;

	public PageIMultiRecipe(String draw, IRecipe[] stack)
	{
		super(draw, 60);
		this.recipes = stack;
		this.renderer = new MultiRecipeRenderer(recipes);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void draw(Book book, CategoryAbstract category, EntryAbstract entry, int guiLeft, int guiTop, int mouseX, int mouseY, GuiBase guiBase, FontRenderer fontRenderer)
	{
		super.draw(book, category, entry, guiLeft, guiTop, mouseX, mouseY, guiBase, fontRenderer);
		renderer.draw(book, category, entry, guiLeft, guiTop, mouseX, mouseY, guiBase, fontRenderer);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawExtras(Book book, CategoryAbstract category, EntryAbstract entry, int guiLeft, int guiTop, int mouseX, int mouseY, GuiBase guiBase, FontRenderer fontRenderer)
	{
		super.drawExtras(book, category, entry, guiLeft, guiTop, mouseX, mouseY, guiBase, fontRenderer);
		renderer.drawExtras(book, category, entry, guiLeft, guiTop, mouseX, mouseY, guiBase, fontRenderer);
	}

	@Override
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
					PageIMultiRecipe that = (PageIMultiRecipe) o;
					if (this.recipes != null)
					{
						if (this.recipes != that.recipes)
						{
							return false;
						}
					}
					else
						if (that.recipes != null)
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

	@Override
	public int hashCode()
	{
		return this.recipes != null ? this.recipes.hashCode() : 0;
	}
}
