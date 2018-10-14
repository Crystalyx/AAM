package aam.common.worldgen;

import aam.utils.Structure;
import aam.utils.StructureApi;
import net.minecraft.world.World;

public class DungeonUnit
{
	public boolean[] able = new boolean[] { true, true, true, true };
	public int cx, cz;
	public Structure center;
	public DungeonBase base;

	public DungeonUnit(DungeonBase base, int cx, int cz)
	{
		this.base = base;
		this.cx = cx;
		this.cz = cz;
	}

	public void print(World w)
	{
		if (center == null)
		{
			StructureApi.print(base.x + cx * 14, base.y, base.z + cz * 14, base.center.strtag, w);
		}
		else
		{
			StructureApi.print(base.x + cx * 14, base.y, base.z + cz * 14, center.strtag, w);
		}
		for (int i = 0; i < 4; i++)
		{
			if (able[i])
			{
				StructureApi.print(base.x + cx * 14, base.y, base.z + cz * 14, base.path[i].strtag, w);
			}
		}
	}

}
