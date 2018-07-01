package AAM.Utils;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class StructureApi
{
	public static String d = "|";

	public static NBTTagCompound readStructure(World w, Wec3 s, Wec3 t, Block... ignore)
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

		tag.setString("Box", MiscUtils.compact((int) (t.x - s.x), (int) (t.y - s.y), (int) (t.z - s.z)));

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
					{
						int meta = w.getBlockMetadata(i + (int) s.x, j + (int) s.y, k + (int) s.z);
						String uid = GameRegistry.findUniqueIdentifierFor(w.getBlock(i + (int) s.x, j + (int) s.y, k + (int) s.z)).toString();
						tag.setString(MiscUtils.compact(i, j, k), MiscUtils.compact((Object)uid, meta));
					}
				}
			}
		}
		return tag;
	}

	public static void print(int x, int y, int z, NBTTagCompound tag, World w)
	{
		List<String> l = MiscUtils.unpact(tag.getString("Box"));

		int tx = (int) Double.parseDouble(l.get(0));
		int ty = (int) Double.parseDouble(l.get(1));
		int tz = (int) Double.parseDouble(l.get(2));

		int dx = Math.floorDiv(tx, 2);
		int dz = Math.floorDiv(tz, 2);

		for (int i = 0; i <= tx+1; i++)
		{
			for (int j = 0; j <= ty+1; j++)
			{
				for (int k = 0; k <= tz+1; k++)
				{
					String key = MiscUtils.compact(i, j, k);
					if (tag.hasKey(key))
					{
						try
						{
							List<String> b = MiscUtils.unpact(tag.getString(key));
							String uid = b.get(0);
							String mod = uid.substring(0, uid.indexOf(':'));
							String block = uid.substring(uid.indexOf(':') + 1);
							int meta = Integer.parseInt(b.get(1));
							BlockState bs = new BlockState(GameRegistry.findBlock(mod, block), i + x - dx, j + y, k + z - dz, meta);
							bs.print(w);
						}
						catch (Exception e)
						{
							Logger.error("Err");
						}
					}
				}
			}
		}
	}

	public static void saveStructure(NBTTagCompound tag)
	{

	}
}
