package AAM.Network;

import AAM.Common.Container.ContainerArmoury;
import AAM.Common.Container.SoulAltarContainer;
import AAM.Common.Container.SpellTableContainer;
import AAM.Common.Tiles.TileArmoury;
import AAM.Common.Tiles.TileSoulAltar;
import AAM.Common.Tiles.TileSpellTable;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer p, World w, int x, int y, int z)
	{
		switch (ID)
		{
		case (0):
			return new SpellTableContainer(p.inventory, (TileSpellTable) w.getTileEntity(x, y, z));
		case (1):
			return new SoulAltarContainer(p.inventory, (TileSoulAltar) w.getTileEntity(x, y, z));
		case (2):
			return new ContainerArmoury(p.inventory, (TileArmoury) w.getTileEntity(x, y, z));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	public World getClientWorld()
	{
		return null;
	}

	public void addMember()
	{

	}

	@Deprecated
	public void EnergyFX(double... ds)
	{

	}

	public void spawnParticle(EntityFX e)
	{

	}

	public void getSkill()
	{

	}
}
