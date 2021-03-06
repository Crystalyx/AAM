package aam.common.items.debug;

import aam.api.interfaces.IStructureCore;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CliserActivator extends Item
{
	public CliserActivator()
	{
		this.setTextureName("aam:cliser_activator");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		ph.arbitur = !ph.arbitur;
		return super.onItemRightClick(i, w, p);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if (w.getTileEntity(x, y, z) != null)
		{
			if (w.getTileEntity(x, y, z) instanceof IStructureCore)
			{
				IStructureCore core = (IStructureCore) w.getTileEntity(x, y, z);
				core.getStructure().printStructure(w, new Wec3(x, y, z).sub(core.getOffset()));
			}
		}
		return true;
	}
}
