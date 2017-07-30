package AAM.Common.WorldGen;

import AAM.Utils.MiscUtils;
import AAM.Utils.Structure;
import AAM.Utils.StructureApi;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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
		if (this.center == null)
		{
			StructureApi.print(base.x + cx * 14, base.y, base.z + cz * 14, this.base.center.strtag, w);
		}
		else
		{
			StructureApi.print(base.x + cx * 14, base.y, base.z + cz * 14, this.center.strtag, w);
		}
		for (int i = 0; i < 4; i++)
		{
			if (able[i])
			{
				StructureApi.print(base.x + cx * 14, base.y, base.z + cz * 14, this.base.path[i].strtag, w);
			}
		}
	}

}
