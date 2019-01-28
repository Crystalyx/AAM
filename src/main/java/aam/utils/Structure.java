package aam.utils;

import aam.utils.vectors.Wec3;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class Structure
{
	public Structure(String structure)
	{
		blocks = structure;
		strtag = disparse(structure);
	}

	public Structure(NBTTagCompound structure)
	{
		strtag = structure;
		blocks = parse(structure);
	}

	public static NBTTagCompound disparse(String blocks)
	{
		NBTTagCompound tg = new NBTTagCompound();
		List<String> l = MiscUtils.unpact(blocks);
		int tx = Integer.parseInt(l.get(0));
		int ty = Integer.parseInt(l.get(1));
		int tz = Integer.parseInt(l.get(2));
		tg.setString("Box", MiscUtils.compact(tx, ty, tz));

		for (int i = 0; i < (l.size() - 3) / 5; i++)
		{
			int j = i * 5 + 3;

			int x = Integer.parseInt(l.get(j));
			int y = Integer.parseInt(l.get(j + 1));
			int z = Integer.parseInt(l.get(j + 2));

			String name = l.get(j + 3);
			String meta = l.get(j + 4);

			String key = MiscUtils.compact(x, y, z);
			tg.setString(key, MiscUtils.compact((Object) name, meta));
		}

		return tg;
	}

	public static String parse(NBTTagCompound strctag)
	{
		String st = "";
		List<String> l = MiscUtils.unpact(strctag.getString("Box"));
		if (l.size() > 1)
		{
			int tx = Integer.parseInt(l.get(0));
			int ty = Integer.parseInt(l.get(1));
			int tz = Integer.parseInt(l.get(2));

			st = MiscUtils.compact(tx, ty, tz);

			for (int i = 0; i < tx; i++)
			{
				for (int j = 0; j < ty; j++)
				{
					for (int k = 0; k < tz; k++)
					{
						String key = MiscUtils.compact(i, j, k);
						if (strctag.hasKey(key))
						{
							st = MiscUtils.compact((Object) st, key, strctag.getString(key));
						}
					}
				}
			}
		}

		return st;
	}

	public boolean checkStructure(World w, int x, int y, int z)
	{
		List<String> l = MiscUtils.unpact(blocks);

		int tx = Integer.parseInt(l.get(0));
		int ty = Integer.parseInt(l.get(1));
		int tz = Integer.parseInt(l.get(2));

		boolean ret = true;

		for (int i = 0; i < (l.size() - 3) / 5; i++)
		{
			int j = i * 5 + 3;

			int cx = Integer.parseInt(l.get(j)) + x;
			int cy = Integer.parseInt(l.get(j + 1)) + y;
			int cz = Integer.parseInt(l.get(j + 2)) + z;

			String name = l.get(j + 3);
			String meta = l.get(j + 4);

			Block b = GameRegistry.findBlock(name.substring(0, name.indexOf(':')), name.substring(name.indexOf(':') + 1));
			int data = Integer.parseInt(meta);

			if (w.getBlock(cx, cy, cz) != b || w.getBlockMetadata(cx, cy, cz) != data)
			{
				ret = false;
				break;
			}
		}

		return ret;
	}

	public boolean checkStructure(World w, Wec3 pos)
	{
		int x = (int) pos.x;
		int y = (int) pos.y;
		int z = (int) pos.z;

		List<String> l = MiscUtils.unpact(blocks);

		int tx = Integer.parseInt(l.get(0));
		int ty = Integer.parseInt(l.get(1));
		int tz = Integer.parseInt(l.get(2));

		boolean ret = true;

		for (int i = 0; i < (l.size() - 3) / 5; i++)
		{
			int j = i * 5 + 3;

			int cx = Integer.parseInt(l.get(j)) + x;
			int cy = Integer.parseInt(l.get(j + 1)) + y;
			int cz = Integer.parseInt(l.get(j + 2)) + z;

			String name = l.get(j + 3);
			String meta = l.get(j + 4);

			Block b = GameRegistry.findBlock(name.substring(0, name.indexOf(':')), name.substring(name.indexOf(':') + 1));
			int data = Integer.parseInt(meta);

			if (w.getBlock(cx, cy, cz) != b || w.getBlockMetadata(cx, cy, cz) != data && data != -1)
			{
				ret = false;
				// String worldBlockName =
				// GameRegistry.findUniqueIdentifierFor(w.getBlock(cx, cy,
				// cz)).toString();
				// String bBlockName =
				// GameRegistry.findUniqueIdentifierFor(b).toString();
				// int worldMeta = w.getBlockMetadata(cx, cy, cz);
				// Logger.warn("World Block " + worldBlockName + ":" + worldMeta
				// + " at (" + x + ", " + y + ", " + z + ") is inequal to " +
				// bBlockName + ":" + data);
				break;
			}
		}

		return ret;
	}

	public boolean printStructure(World w, Wec3 pos)
	{
		int x = (int) pos.x;
		int y = (int) pos.y;
		int z = (int) pos.z;

		List<String> l = MiscUtils.unpact(blocks);

		int tx = Integer.parseInt(l.get(0));
		int ty = Integer.parseInt(l.get(1));
		int tz = Integer.parseInt(l.get(2));

		boolean ret = true;

		for (int i = 0; i < (l.size() - 3) / 5; i++)
		{
			int j = i * 5 + 3;

			int cx = Integer.parseInt(l.get(j)) + x;
			int cy = Integer.parseInt(l.get(j + 1)) + y;
			int cz = Integer.parseInt(l.get(j + 2)) + z;

			String name = l.get(j + 3);
			String meta = l.get(j + 4);

			Block b = GameRegistry.findBlock(name.substring(0, name.indexOf(':')), name.substring(name.indexOf(':') + 1));
			int data = Integer.parseInt(meta);
			if (data == -1)
			{
				data = 0;
			}
			if (b != null)
				w.setBlock(cx, cy, cz, b, data, 2);
		}

		return ret;
	}

	public NBTTagCompound strtag;
	public String blocks;
}
