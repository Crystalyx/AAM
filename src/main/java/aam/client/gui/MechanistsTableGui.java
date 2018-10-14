package aam.client.gui;

import aam.client.gui.base.GuiBase;
import aam.common.container.MechanistsTableContainer;
import aam.network.packages.AlchemicalDispatcher;
import aam.network.packages.ChangeWeaponPackage;
import aam.network.packages.ForgingPackage;
import aam.utils.Logger;
import aam.utils.vectors.Wec3;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;

public class MechanistsTableGui extends GuiBase
{

	public MechanistsTableContainer cont;

	public MechanistsTableGui(MechanistsTableContainer cont)
	{
		super(cont);
		this.cont = cont;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		int k = +(width - xSize) / 2;
		int l = (height - ySize) / 2;
		GuiButton gb = new GuiButton(0, k + 96, l + 58, 80, 10, "Forge");
		this.buttonList.add(gb);
		GuiButton gbch = new GuiButton(1, k + 96, l + 68, 80, 10, this.cont.tile.melee ? "Melee Weapon" : "Ranged Weapon");
		this.buttonList.add(gbch);
		textBoxFilter = new GuiTextField(fontRendererObj, k + 24, l + 12, 128, 15);
		textBoxFilter.setMaxStringLength(128);
		textBoxFilter.setText("");
		textBoxFilter.setEnabled(true);
	}

	@Override
	protected void actionPerformed(GuiButton gb)
	{
		super.actionPerformed(gb);
		if (gb.id == 0)
		{
			Wec3 pos = new Wec3(this.cont.tile);
			ForgingPackage fp = new ForgingPackage(pos.ix, pos.iy, pos.iz, this.textBoxFilter.getText());
			AlchemicalDispatcher.sendToServer(fp);
		}
		if (gb.id == 1)
		{
			Wec3 pos = new Wec3(this.cont.tile);
			Logger.info(this.cont.tile.melee);
			gb.displayString = gb.displayString == "Melee Weapon" ? "Ranged Weapon" : "Melee Weapon";
			ChangeWeaponPackage fp = new ChangeWeaponPackage(pos.ix, pos.iy, pos.iz);
			AlchemicalDispatcher.sendToServer(fp);
		}
	}

	private GuiTextField textBoxFilter;

	@Override
	public void drawScreen(int ix, int iy, float fl)
	{
		super.drawScreen(ix, iy, fl);
		textBoxFilter.drawTextBox();
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
		textBoxFilter.updateCursorCounter();
		textBoxFilter.setMaxStringLength(128);
	}

	@Override
	protected void keyTyped(char par1, int par2)
	{
		if (textBoxFilter.isFocused())
		{
			textBoxFilter.textboxKeyTyped(par1, par2);
		}

		if (par2 == 1 || par2 == mc.gameSettings.keyBindInventory.getKeyCode() && !textBoxFilter.isFocused())
		{
			mc.thePlayer.closeScreen();
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int mouseButton)
	{
		super.mouseClicked(x, y, mouseButton);

		int minX = textBoxFilter.xPosition;
		int minY = textBoxFilter.yPosition;
		int maxX = minX + textBoxFilter.width;
		int maxY = minY + textBoxFilter.height;

		// if (mouseButton == 1 && x >= minX && x <= maxX && y <= maxY)
		// {
		// textBoxFilter.setText("");
		// }

		textBoxFilter.mouseClicked(x, y, mouseButton);
	}

}
