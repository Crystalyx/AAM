package AAM.Common.Transmutations;

import AAM.Common.Tiles.TETransCircle;
import AAM.Utils.WorldPos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TransAction
{
	public void act(World w, WorldPos tile, TETransCircle te, EntityPlayer p, double potency,ForgeDirection dir)
	{
		
	}

	public boolean actTick(World w, WorldPos tile, TETransCircle te, EntityPlayer p, int time, double potency,ForgeDirection dir)
	{
		return false;
	}

	public boolean renderTick(World w, WorldPos tile, TETransCircle te, int time)
	{
		return false;
	}
}
