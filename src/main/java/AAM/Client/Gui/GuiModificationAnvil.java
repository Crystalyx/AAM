package AAM.Client.Gui;

import AAM.Client.Gui.Base.GuiBase;
import AAM.Common.Container.ModificationAnvilContainer;

public class GuiModificationAnvil extends GuiBase
{

	public GuiModificationAnvil(ModificationAnvilContainer cont)
	{
		super(cont);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float f1, int x, int y)
	{
		// if (this.cont.objs.get(2) instanceof GuiBar)
		// {
		// ((GuiBar) this.cont.objs.get(2)).updBar(((TESoulAltar)
		// this.cont.tile).value, ((TESoulAltar) this.cont.tile).maxValue);
		// }
		super.drawGuiContainerBackgroundLayer(f1, x, y);

	}

}
