package AAM.Network;

import AAM.Common.Container.SoulAltarContainer;
import AAM.Common.Container.SpellTableContainer;
import AAM.Common.Tiles.TESoulAltar;
import AAM.Common.Tiles.TESpellTable;
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
			return new SpellTableContainer(p.inventory, (TESpellTable) w.getTileEntity(x, y, z));
		case (1):
			return new SoulAltarContainer(p.inventory, (TESoulAltar) w.getTileEntity(x, y, z));
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
