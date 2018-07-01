
package AAM.Common.Items.Debug;

import AAM.Utils.StructureApi;
import AAM.Utils.Structures;
import AAM.Utils.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Nullifier extends Item
{
	public Nullifier()
	{
		this.setTextureName("aam:tools/nullfier");
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p)
	{
		return is;
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int side, float hx, float hy, float hz)
	{
		// PlayerDataHandler.get(p).replenishSoul();

//		Structures.soulAltar.printStructure(w, new Wec3(x, y, z));
		StructureApi.print(x, y, z, Structures.soulAltar.strtag, w);
		return true;
	}
}
