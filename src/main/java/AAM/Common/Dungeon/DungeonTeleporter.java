package AAM.Common.Dungeon;

import java.lang.reflect.Field;

import AAM.Common.Blocks.ModBlocks;
import AAM.Utils.MiscUtils;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.WorldPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class DungeonTeleporter extends Teleporter
{

	public DungeonTeleporter(WorldServer s)
	{
		super(s);
	}

	/**
	 * Place an entity in a nearby portal which already exists.
	 */
	public boolean placeInExistingPortal(Entity e, double x, double y, double z, float f)
	{
		this.makePortal(e);
		if (e instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e;
			try
			{
				Class clazz = Class.forName("AAM.Common.Dungeon.DungeonRegistry");
				int dung = PlayerDataHandler.get(p).dung;
				Field ms = clazz.getField("PDung" + dung);
				if (ms != null)
				{
					if (ms.getName().startsWith("P"))
					{
						if (ms.getType().equals(WorldPos.class))
						{
							WorldPos wp = (WorldPos) ms.get(null);
							e.setPosition((int) wp.x + MiscUtils.r.nextDouble() * 2, (int) wp.y+2, (int) wp.z + MiscUtils.r.nextDouble() * 2);

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

	public boolean makePortal(Entity e)
	{
		if (e instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e;
			try
			{
				Class clazz = Class.forName("AAM.Common.Dungeon.DungeonRegistry");
				int dung = PlayerDataHandler.get(p).dung;
				Field ms = clazz.getField("PDung" + dung);
				if (ms != null)
				{
					if (ms.getName().startsWith("P"))
					{
						if (ms.getType().equals(WorldPos.class))
						{
							WorldPos wp = (WorldPos) ms.get(null);
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
