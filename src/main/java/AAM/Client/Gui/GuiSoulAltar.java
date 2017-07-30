package AAM.Client.Gui;

import AAM.Client.Gui.Base.GuiBar;
import AAM.Client.Gui.Base.GuiBase;
import AAM.Client.Gui.Base.GuiPicture;
import AAM.Client.Gui.Base.GuiText;
import AAM.Common.Container.SoulAltarContainer;
import AAM.Common.Items.SoulSword;
import AAM.Common.Tiles.TileSoulAltar;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.client.Minecraft;
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
			((GuiBar) this.cont.objs.get(2)).updBar(((TileSoulAltar) this.cont.tile).value, ((TileSoulAltar) this.cont.tile).maxValue);
		}
		super.drawGuiContainerBackgroundLayer(f1, x, y);

		boolean b = ((TileSoulAltar) this.cont.tile).formed && this.cont.tile.getStackInSlot(0) != null && this.cont.tile.getStackInSlot(0).getItem() instanceof SoulSword;

		for (int i = 4; i < 13; i++)
		{
			this.cont.objs.get(i).setHidden(!b);
		}
		this.cont.objs.get(14).setHidden(!b);
		this.cont.objs.get(15).setHidden(!b);
		ItemStack is = this.cont.tile.getStackInSlot(0);
		this.cont.objs.get(16).setHidden(PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer).xpPoints <= 0 && !b);

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
		// Logger.info(PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer).xpPoints);
	}

}
