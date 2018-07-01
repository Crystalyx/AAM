package AAM.Common.Transmutations.Actions;

import AAM.Common.Items.ModItems;
import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.TransAction;
import AAM.Common.Transmutations.TransAction;
import AAM.Utils.Wec3;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActCreateObol extends TransAction
{

	public ActCreateObol()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{
		if (!w.isRemote)
		{
			for (int i = 0; i < potency; i++)
			{
				ItemStack is = new ItemStack(ModItems.LinkObol);
				tile = tile.centralize();
				EntityItem ei = new EntityItem(w, tile.x, tile.y, tile.z, is);

				w.spawnEntityInWorld(ei);
			}
		}
	}

}
