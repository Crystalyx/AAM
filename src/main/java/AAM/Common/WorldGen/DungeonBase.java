package aam.common.worldgen;

import java.util.ArrayList;
import java.util.List;

import aam.utils.MathUtils;
import aam.utils.Structure;
import net.minecraftforge.common.util.ForgeDirection;

public class DungeonBase
{
	public int x, y, z, sx, sz;
	public Structure center;
	public Structure[] path = new Structure[4];

	public DungeonUnit[][] units;

	public DungeonBase(int x, int y, int z, Structure center, Structure[] path, int sx, int sz)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.center = center;
		this.path = path;
		units = new DungeonUnit[sx * 2 + 1][sz * 2 + 1];
		this.sx = sx;
		this.sz = sz;
	}

	public boolean[] getMap(int x, int z)
	{
		boolean[] ret = new boolean[4];
		int px = ForgeDirection.EAST.ordinal() - 2;
		int nx = ForgeDirection.WEST.ordinal() - 2;
		int pz = ForgeDirection.SOUTH.ordinal() - 2;
		int nz = ForgeDirection.NORTH.ordinal() - 2;

		List<Integer> l = new ArrayList<>();
		l.add(px);
		l.add(nx);
		l.add(pz);
		l.add(nz);
		l = MathUtils.randomize(l);

		double perc = 100;

		for (int i = 0; i < 4; i++)
		{
			int k = l.get(i);
			switch (k)
			{
			case 3:
			{
				if (x >= sx)
				{
					ret[px] = false;
				}
				else
				{
					if (units[x + 1 + sx][z + sz] == null)
					{
						ret[px] = MathUtils.randWPercent(perc) || x == 0 && z == 0;
					}
					else
					{
						ret[px] = units[x + 1 + sx][z + sz].able[nx];
					}
				}
			}
			case 2:
			{
				if (x <= -sx)
				{
					ret[nx] = false;
				}
				else
				{
					if (units[x - 1 + sx][z + sz] == null)
					{
						ret[nx] = MathUtils.randWPercent(perc) || x == 0 && z == 0;
					}
					else
					{
						ret[nx] = units[x - 1 + sx][z + sz].able[px];
					}
				}
			}
			case 1:
			{
				if (z >= sz)
				{
					ret[pz] = false;
				}
				else
				{
					if (units[x + sx][z + 1 + sz] == null)
					{
						ret[pz] = MathUtils.randWPercent(perc) || x == 0 && z == 0;
					}
					else
					{
						ret[pz] = units[x + sx][z + 1 + sz].able[nz];
					}
				}
			}
			case 0:
			{
				if (z <= -sz)
				{
					ret[nz] = false;
				}
				else
				{
					if (units[x + sx][z - 1 + sz] == null)
					{
						ret[nz] = MathUtils.randWPercent(perc) || x == 0 && z == 0;
					}
					else
					{
						ret[nz] = units[x + sx][z - 1 + sz].able[pz];
					}
				}
			}
			}
			perc -= 9 * i;
		}

		return ret;
	}
}
