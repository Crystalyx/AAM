
package AAM.Common.Items;

import AAM.Common.WorldGen.DungeonBase;
import AAM.Common.WorldGen.DungeonUnit;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.Structure;
import AAM.Utils.Structures;
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
		PlayerDataHandler.get(p).replenishSoul();

		DungeonBase base = new DungeonBase(x, y, z, Structures.elemCenter, new Structure[] { Structures.elemPathNZ, Structures.elemPathPZ, Structures.elemPathNX, Structures.elemPathPX }, 5, 5);

		for (int i = -5; i <= 5; i++)
		{
			for (int j = -5; j <= 5; j++)
			{
				DungeonUnit unit = new DungeonUnit(base, i, j);
				unit.able = base.getMap(i, j);
				if (i == 0 && j == 0)
				{
					unit.center = Structures.soulAltar;
				}
				base.units[i + 5][j + 5] = unit;
				unit.print(w);
			}
		}

		return true;
	}
}
