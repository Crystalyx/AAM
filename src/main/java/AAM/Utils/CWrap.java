package AAM.Utils;

import AAM.Client.Gui.GuiAddMember;
import AAM.Core.AAMCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class CWrap
{
	public static void spawnParticle(EntityFX e)
	{
		AAMCore.proxy.spawnParticle(e);
	}

	public static World getClientWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}

	public static void addMember()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiAddMember());
	}
}
