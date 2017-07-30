package AAM.Utils;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Blocks.ModBlocks;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class StructureApi
{
	public static String d = "|";

	public static String toString(Object o)
	{
		return o != null ? o + "" : "null";
	}

	public static String compact(Object... ss)
	{
		String s = "";
		for (int i = 0; i < ss.length; i++)
		{
			s += toString(ss[i]);
			if (i != ss.length - 1)
				s += d;
		}

		return s;
	}

	public static List<String> unpact(String s)
	{
		List<String> l = new ArrayList<String>();

		while (s.indexOf(d) > -1)
		{
			String ds = s.substring(0, s.indexOf(d));
			l.add(ds);
			s = s.substring(s.indexOf(d) + 1);
		}
		l.add(s);

		return l;
	}

	public static NBTTagCompound readStructure(World w, WorldPos s, WorldPos t, Block... ignore)
	{
		NBTTagCompound tag = new NBTTagCompound();

		double dc;

		if (s.x > t.x)
		{
			dc = s.x;
			s.x = t.x;
			t.x = dc;
		}
		if (s.y > t.y)
		{
			dc = s.y;
			s.y = t.y;
			t.y = dc;
		}
		if (s.z > t.z)
		{
			dc = s.z;
			s.z = t.z;
			t.z = dc;
		}

		tag.setString("Box", compact((int) 0, (int) 0, (int) 0, (int) (t.x - s.x), (int) (t.y - s.y), (int) (t.z - s.z)));

		List<Block> igs = new ArrayList<Block>();
		for (int i = 0; i < ignore.length; i++)
		{
			igs.add(ignore[i]);
		}

		for (int i = 0; i <= t.x - s.x; i++)
		{
			for (int j = 0; j <= t.y - s.y; j++)
			{
				for (int k = 0; k <= t.z - s.z; k++)
				{
					if (!igs.contains(w.getBlock(i + (int) s.x, j + (int) s.y, k + (int) s.z)))
						tag.setString(compact(i, j, k), compact(GameRegistry.findUniqueIdentifierFor(w.getBlock(i + (int) s.x, j + (int) s.y, k + (int) s.z)).toString(), w.getBlockMetadata(i + (int) s.x, j + (int) s.y, k + (int) s.z)));
				}
			}
		}
		return tag;
	}

	public static void print(int x, int y, int z, NBTTagCompound tag, World w)
	{
		List<String> l = unpact(tag.getString("Box"));
		int sx = Integer.parseInt(l.get(0));
		int sy = Integer.parseInt(l.get(1));
		int sz = Integer.parseInt(l.get(2));

		int tx = (int) Double.parseDouble(l.get(3));
		int ty = (int) Double.parseDouble(l.get(4));
		int tz = (int) Double.parseDouble(l.get(5));
		Logger.info(ty);

		for (int i = 0; i <= tx; i++)
		{
			for (int j = 0; j <= ty; j++)
			{
				for (int k = 0; k <= tz; k++)
				{
					String key = compact(i, j, k);
					if (tag.hasKey(key))
					{
						List<String> b = unpact(tag.getString(key));
						BlockState bs = new BlockState(GameRegistry.findBlock(b.get(0).substring(0, b.get(0).indexOf(':')), b.get(0).substring(b.get(0).indexOf(':') + 1)), i + x - tx / 2, j + y, k + z - tz / 2, Integer.parseInt(b.get(1)));
						bs.print(w);
					}
				}
			}
		}
	}

	public static void saveStructure(NBTTagCompound tag)
	{
		ResourceLocation rl = new ResourceLocation("aam:structures/elemental/bossroom.str");

	}
}
