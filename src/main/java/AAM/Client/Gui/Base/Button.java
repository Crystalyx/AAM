package AAM.Client.Gui.Base;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class Button extends GuiButton
{
	public GuiScreen gui;

	public Button(int id, int x, int y, int sx, int sy, String text, GuiScreen g)
	{
		super(id, x, y, sx, sy, text);
		this.gui = g;
	}

}
