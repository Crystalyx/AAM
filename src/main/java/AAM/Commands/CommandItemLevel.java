package AAM.Commands;

import AAM.API.Interface.IUpgradableItem;
import AAM.Network.Packages.AlchemicalDispatcher;
import AAM.Network.Packages.PlayerSyncMessage;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class CommandItemLevel extends CommandBase
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
		return StatCollector.translateToLocal("aam.command.level.name");
	}

	@Override
	public String getCommandUsage(ICommandSender p)
	{
		return StatCollector.translateToLocal("aam.command.level.usage");
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
					if (args[0].toLowerCase().equals("add"))
					{
						if (args.length == 2)
						{
							int l = Integer.parseInt(args[1]);
							((IUpgradableItem) is.getItem()).addUpgradeLevel(p.worldObj, is, l);
						}
						((IUpgradableItem) is.getItem()).addUpgradeLevel(p.worldObj, is, 1);
					}
					if (args[0].toLowerCase().equals("set"))
					{
						if (args.length == 2)
						{
							int l = Integer.parseInt(args[1]);
							((IUpgradableItem) is.getItem()).setUpgradeLevel(p.worldObj, is, l);
						}
					}
				}
				AlchemicalDispatcher.sendToClient(new PlayerSyncMessage(p), p);
			}
		}
	}

}
