package aam.network;

import aam.api.PouchInventory;
import aam.common.container.*;
import aam.common.tiles.TEMechanistsTable;
import aam.common.tiles.TEModificationAnvil;
import aam.common.tiles.TESoulAltar;
import aam.common.tiles.TESpellTable;
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
		case 0:
			return new SpellTableContainer(p.inventory, (TESpellTable) w.getTileEntity(x, y, z));
		case 1:
			return new SoulAltarContainer(p.inventory, (TESoulAltar) w.getTileEntity(x, y, z));
		case 2:
			return new ModificationAnvilContainer(p.inventory, (TEModificationAnvil) w.getTileEntity(x, y, z));
		case 3:
			return new PouchContainer(p.inventory, new PouchInventory(p.getCurrentEquippedItem()));
		case 4:
			return new MechanistsTableContainer(p.inventory, (TEMechanistsTable) w.getTileEntity(x, y, z));
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

	public void openCircleGui(int x, int y, int z)
	{

	}

	public void init()
	{
	}
}
