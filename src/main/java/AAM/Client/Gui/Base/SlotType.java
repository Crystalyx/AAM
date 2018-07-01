package AAM.Client.Gui.Base;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;

public class SlotType extends ObjType
{

	public SlotType(int id, int px, int py, int size)
	{
		super(Obj.Slot, id, px, py, size, size);
	}

	public void render(int k, int l, int x, int y)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(k / 2 + x / 2 - 1, l / 2 + y / 2 - 0.5, 0);
		GL11.glScaled(0.5, 0.5, 0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(GuiOBJ.baseTexture);
		this.gui.drawTexturedModalRect(k + x, l + y, this.px + 2, this.py + 2, this.sizex, this.sizey);
		GL11.glPopMatrix();
	}

}
