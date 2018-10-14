package aam.commands;

import aam.api.TraitModifier;
import aam.common.soul.Trait;
import aam.network.packages.AlchemicalDispatcher;
import aam.network.packages.PlayerSyncMessage;
import aam.utils.PlayerDataHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

public class CommandSoulDamage extends CommandBase
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
		return StatCollector.translateToLocal("aam.command.souldamage.name");
	}

	@Override
	public String getCommandUsage(ICommandSender p)
	{
		return StatCollector.translateToLocal("aam.command.souldamage.usage");
	}

	@Override
	public void processCommand(ICommandSender s, String[] args)
	{
		if (s instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) s;
			PlayerDataHandler ph = PlayerDataHandler.get(p);
			if (args[0].toLowerCase().equals("add"))
			{
				if (args.length == 2)
				{
					TraitModifier old = ph.findModifier(Trait.MeleeDamage, "Command");
					float oldD = 0;
					if (old != null)
					{
						oldD = old.value;
					}
					float l = Float.parseFloat(args[1]) + oldD;
					TraitModifier tmM = new TraitModifier("Command", Trait.MeleeDamage, l, TraitModifier.Operation.add);
					ph.removeModifier(Trait.MeleeDamage, "Command");
					TraitModifier tmR = new TraitModifier("Command", Trait.RangedDamage, l, TraitModifier.Operation.add);
					ph.removeModifier(Trait.RangedDamage, "Command");
					ph.applyModifier(tmM);
					ph.applyModifier(tmR);
				}
				else
				{
					TraitModifier tmM = new TraitModifier("Command", Trait.MeleeDamage, 1, TraitModifier.Operation.add);
					ph.removeModifier(Trait.MeleeDamage, "Command");
					TraitModifier tmR = new TraitModifier("Command", Trait.RangedDamage, 1, TraitModifier.Operation.add);
					ph.removeModifier(Trait.RangedDamage, "Command");
					ph.applyModifier(tmM);
					ph.applyModifier(tmR);
				}
			}
			if (args[0].toLowerCase().equals("set"))
			{
				if (args.length == 2)
				{
					float l = Float.parseFloat(args[1]);
					TraitModifier tmM = new TraitModifier("Command", Trait.MeleeDamage, l, TraitModifier.Operation.add);
					ph.removeModifier(Trait.MeleeDamage, "Command");
					TraitModifier tmR = new TraitModifier("Command", Trait.RangedDamage, l, TraitModifier.Operation.add);
					ph.removeModifier(Trait.RangedDamage, "Command");
					ph.applyModifier(tmM);
					ph.applyModifier(tmR);
				}
			}
			if (args[0].toLowerCase().equals("get"))
			{
				ChatComponentText cctM = new ChatComponentText("Your Additional Melee Soul damage: " + ph.getTrait(Trait.MeleeDamage));
				ChatComponentText cctR = new ChatComponentText("Your Additional Ranged Soul damage: " + ph.getTrait(Trait.RangedDamage));
				p.addChatMessage(cctM);
				p.addChatMessage(cctR);
			}
			AlchemicalDispatcher.sendToClient(new PlayerSyncMessage(p), p);
		}
	}

}
