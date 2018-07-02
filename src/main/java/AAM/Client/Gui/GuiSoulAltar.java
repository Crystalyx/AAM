package AAM.Client.Gui;

import AAM.Client.Gui.Base.GuiBar;
import AAM.Client.Gui.Base.GuiBase;
import AAM.Client.Gui.Base.GuiText;
import AAM.Common.Container.SoulAltarContainer;
import AAM.Common.Items.Soul.SoulSword;
import AAM.Common.Tiles.TESoulAltar;
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
		if (this.cont.objs.get(2) instanceof GuiBar)
		{
			((GuiBar) this.cont.objs.get(2)).updBar(((TESoulAltar) this.cont.tile).value, ((TESoulAltar) this.cont.tile).maxValue);
		}
		super.drawGuiContainerBackgroundLayer(f1, x, y);

		boolean b = ((TESoulAltar) this.cont.tile).formed && this.cont.tile.getStackInSlot(0) != null && this.cont.tile.getStackInSlot(0).getItem() instanceof SoulSword;

		for (int i = 4; i < 16; i++)
		{
			if (i != 13)
				this.cont.objs.get(i).setHidden(!b);
		}
		this.cont.objs.get(14).setHidden(!b);
		ItemStack is = this.cont.tile.getStackInSlot(0);

		if (this.cont.tile.getStackInSlot(0) != null)
		{
			if (is.hasTagCompound())
			{
				this.cont.objs.get(13).setHidden(false);

				if (this.cont.objs.get(13) instanceof GuiText)
				{
					GuiText text = (GuiText) this.cont.objs.get(13);
					text.text = "Owner: " + is.getTagCompound().getString("Owner");
				}

			}
			else
			{
				this.cont.objs.get(13).setHidden(true);
			}
		}
		else
			this.cont.objs.get(13).setHidden(true);
	}

}
