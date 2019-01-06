package aam.commands;

import aam.api.GameWeapon;
import aam.api.interfaces.IUpgradableItem;
import aam.common.weapon.WeaponManager;
import aam.network.packages.AlchemicalDispatcher;
import aam.network.packages.PlayerSyncMessage;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class CommandItem extends CommandBase
{

	/**
	 * Return the required permission level for this command.
	 */
	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	@Override
	public String getCommandName()
	{
		return StatCollector.translateToLocal("aam.command.item.name");
	}

	@Override
	public String getCommandUsage(ICommandSender p)
	{
		return StatCollector.translateToLocal("aam.command.item.usage");
	}

	@Override
	public void processCommand(ICommandSender s, String[] args)
	{
		if (s instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) s;
			ItemStack is = p.getCurrentEquippedItem();
			if (is != null)
			{
				if (is.getItem() instanceof IUpgradableItem)
				{
					if (args[0].toLowerCase().equals("addlevel"))
					{
						if (args.length == 2)
						{
							int l = Integer.parseInt(args[1]);
							((IUpgradableItem) is.getItem()).addUpgradeLevel(p.worldObj, is, l);
						}
						((IUpgradableItem) is.getItem()).addUpgradeLevel(p.worldObj, is, 1);
					}
					if (args[0].toLowerCase().equals("setlevel"))
					{
						if (args.length == 2)
						{
							int l = Integer.parseInt(args[1]);
							((IUpgradableItem) is.getItem()).setUpgradeLevel(p.worldObj, is, l);
						}
					}

				}
				// TODO
				if (args[0].toLowerCase().equals("weapondamage"))
				{
					if (args.length == 2)
					{
						int l = Integer.parseInt(args[1]);
						if (is.getItem() instanceof GameWeapon)
						{
							WeaponManager.setDamagePoints(is, l);
						}
						// ((IUpgradableItem)
						// is.getItem()).setUpgradeLevel(p.worldObj, is, l);
					}
				}
				if (args[0].toLowerCase().equals("repair"))
				{
					if (is.getItem() instanceof GameWeapon)
					{
						WeaponManager.repair(is);
					}
					// ((IUpgradableItem)
					// is.getItem()).setUpgradeLevel(p.worldObj, is, l);
				}
				AlchemicalDispatcher.sendToClient(new PlayerSyncMessage(p), p);
			}
		}
	}

}
