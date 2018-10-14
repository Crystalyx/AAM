package aam.client.gui;

import aam.client.gui.base.GuiBase;
import aam.client.gui.base.GuiText;
import aam.common.container.SoulAltarContainer;
import aam.common.tiles.TESoulAltar;
import net.minecraft.item.ItemStack;

public class GuiSoulAltar extends GuiBase
{

	public GuiSoulAltar(SoulAltarContainer cont)
	{
		super(cont);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float f1, int x, int y)
	{
		super.drawGuiContainerBackgroundLayer(f1, x, y);

		boolean b = ((TESoulAltar) cont.tile).formed;

		for (int i = 3; i < 15; i++)
		{
			if (i != 13)
			{
				cont.objs.get(i).setHidden(!b);
			}
		}
		cont.objs.get(13).setHidden(!b);
		ItemStack is = cont.tile.getStackInSlot(0);

		if (cont.tile.getStackInSlot(0) != null)
		{
			if (is.hasTagCompound())
			{
				cont.objs.get(12).setHidden(false);

				if (cont.objs.get(12) instanceof GuiText)
				{
					GuiText text = (GuiText) cont.objs.get(12);
					text.text = "Owner: " + is.getTagCompound().getString("Owner");
				}

			}
			else
			{
				cont.objs.get(12).setHidden(true);
			}
		}
		else
		{
			cont.objs.get(12).setHidden(true);
		}
	}

}
