package aam.common.dungeon;

import aam.common.blocks.building.ModBlocks;
import aam.utils.MathUtils;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import java.lang.reflect.Field;

public class DungeonTeleporter extends Teleporter
{

	public DungeonTeleporter(WorldServer s)
	{
		super(s);
	}

	/**
	 * Place an entity in a nearby portal which already exists.
	 */
	@Override
	public boolean placeInExistingPortal(Entity e, double x, double y, double z, float f)
	{
		this.makePortal(e);
		if (e instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e;
			try
			{
				Class clazz = Class.forName("aam.common.dungeon.DungeonRegistry");// reflection
																					// cause
																					// I'm
																					// lazy
				int dung = 0;
				Field ms = clazz.getField("PDung" + dung);
				if (ms != null)
				{
					if (ms.getName().startsWith("P"))
					{
						if (ms.getType().equals(Wec3.class))
						{
							Wec3 wp = (Wec3) ms.get(null);
							e.setPosition((int) wp.x + MathUtils.r.nextDouble() * 2, (int) wp.y + 2, (int) wp.z + MathUtils.r.nextDouble() * 2);

						}
					}
				}
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean makePortal(Entity e)
	{
		if (e instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e;
			try
			{
				Class clazz = Class.forName("aam.common.dungeon.DungeonRegistry");
				int dung = 0;
				Field ms = clazz.getField("PDung" + dung);
				if (ms != null)
				{
					if (ms.getName().startsWith("P"))
					{
						if (ms.getType().equals(Wec3.class))
						{
							Wec3 wp = (Wec3) ms.get(null);
							e.worldObj.setBlock((int) wp.x, (int) wp.y, (int) wp.z, ModBlocks.BlockTeleporter);
						}
					}
				}
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		return true;
	}

}
