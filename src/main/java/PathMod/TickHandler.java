package PathMod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.block.Block;

public class TickHandler
{
	@SubscribeEvent
	public void tick(PlayerTickEvent e)
	{
		int x = (int) e.player.posX;
		int y = (int) e.player.posY;
		int z = (int) e.player.posZ;

		Block b = e.player.worldObj.getBlock(x, y, z);

		if (b == PathMod.path)
		{
			e.player.capabilities.setPlayerWalkSpeed(0.15f);
		}
		else
			e.player.capabilities.setPlayerWalkSpeed(0.1f);

	}
}
