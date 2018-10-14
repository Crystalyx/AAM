package aam.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.abstraction.IRecipeRenderer;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.util.GuiHelper;
import amerifrance.guideapi.gui.GuiBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class MultiRecipeRenderer implements IRecipeRenderer
{
	IRecipe[] recipes;

	public MultiRecipeRenderer(IRecipe[] recipes)
	{
		this.recipes = recipes;
	}

	@Override
	public void draw(Book book, CategoryAbstract category, EntryAbstract entry, int guiLeft, int guiTop, int mouseX, int mouseY, GuiBase guiBase, FontRenderer fontRenderer)
	{
		Minecraft mc = Minecraft.getMinecraft();
		long time = mc.theWorld.getTotalWorldTime();
		if (lastCycle < 0L || lastCycle < time - 20L)
		{
			if (lastCycle > 0L)
			{
				++cycleIdx;
				cycleIdx = Math.max(0, cycleIdx);
			}

			lastCycle = mc.theWorld.getTotalWorldTime();
		}

		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("guideapi:textures/gui/recipe_elements.png"));
		guiBase.drawTexturedModalRect(guiLeft + 42, guiTop + 53, 0, 0, 105, 65);
		guiBase.drawCenteredString(fontRenderer, this.getRecipeName(), guiLeft + guiBase.xSize / 2, guiTop + 12, 0);
		int outputX = 90 + guiLeft + guiBase.xSize / 7;
		int outputY = 36 + guiTop + guiBase.xSize / 5;

		int cycle = (int) (Minecraft.getMinecraft().theWorld.getWorldTime() / 40 % recipes.length);

		GuiHelper.drawItemStack(recipes[cycle].getRecipeOutput(), outputX, outputY);
		if (GuiHelper.isMouseBetween(mouseX, mouseY, outputX, outputY, 15, 15))
		{
			tooltips = GuiHelper.getTooltip(recipes[cycle].getRecipeOutput());
		}
		for (int y = 0; y < 3; ++y)
		{
			for (int x = 0; x < 3; ++x)
			{
				int i = 3 * y + x;
				if (i < ((ShapelessOreRecipe) recipes[cycle]).getRecipeSize())
				{
					int stackX = (x + 1) * 17 + guiLeft + 29;
					int stackY = (y + 1) * 17 + guiTop + 40;
					Object component = ((ShapelessOreRecipe) recipes[cycle]).getInput().get(i);
					if (component != null)
					{
						if (component instanceof ItemStack)
						{
							GuiHelper.drawScaledItemStack((ItemStack) component, stackX, stackY, 1);
							if (GuiHelper.isMouseBetween(mouseX, mouseY, stackX, stackY, 15, 15))
							{
								tooltips = GuiHelper.getTooltip((ItemStack) component);
							}
						}
						else
						{
							ArrayList list = (ArrayList) component;
							if (!list.isEmpty())
							{
								ItemStack stack = (ItemStack) list.get(this.getRandomizedCycle(x + y * 3, list.size()));
								GuiHelper.drawScaledItemStack(stack, stackX, stackY, 1);
								if (GuiHelper.isMouseBetween(mouseX, mouseY, stackX, stackY, 15, 15))
								{
									tooltips = GuiHelper.getTooltip(stack);
								}
							}
						}
					}
				}
			}
		}
	}

	public Random rand = new Random();
	public long lastCycle = -1L;
	public int cycleIdx = 0;

	public int getRandomizedCycle(int index, int max)
	{
		rand.setSeed(index);
		return (index + rand.nextInt(max) + cycleIdx) % max;
	}

	public List tooltips = Lists.newArrayList();

	@Override
	public void drawExtras(Book book, CategoryAbstract category, EntryAbstract entry, int guiLeft, int guiTop, int mouseX, int mouseY, GuiBase guiBase, FontRenderer fontRenderer)
	{
		guiBase.func_146283_a(tooltips, mouseX, mouseY);
		tooltips.clear();
	}

	public String getRecipeName()
	{
		return StatCollector.translateToLocal("text.multi.shapeless.crafting");
	}
}
