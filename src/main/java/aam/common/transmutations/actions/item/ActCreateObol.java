package aam.common.transmutations.actions.item;

import aam.common.items.ModItems;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.TransAction;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActCreateObol extends TransAction
{

	public ActCreateObol(int size)
	{
		super(size);
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
