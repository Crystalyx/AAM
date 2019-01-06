package aam.commands;

import aam.common.items.alchemy.AlchemicalPotionItem;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class CommandAAMPotion extends CommandBase
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
		return StatCollector.translateToLocal("aam.command.potion.name");
	}

	@Override
	public String getCommandUsage(ICommandSender p)
	{
		return StatCollector.translateToLocal("aam.command.potion.usage");
	}

	@Override
	public void processCommand(ICommandSender s, String[] args)
	{
		try
		{
			if (s instanceof EntityPlayer)
			{
				EntityPlayer p = (EntityPlayer) s;
				ItemStack is = p.getCurrentEquippedItem();
				if (is != null)
				{
					setHasTagCompound(is);
					if (args[0].toLowerCase().equals("duration"))
					{
						if (is.getItem() instanceof AlchemicalPotionItem)
						{
							if (args[1].toLowerCase().equals("add"))
							{
								if (args.length == 3)
								{
									int l = Integer.parseInt(args[2]);
									is.getTagCompound().setInteger("PotionDur", is.getTagCompound().getInteger("PotionDur") + l);
								}
								is.getTagCompound().setInteger("PotionDur", is.getTagCompound().getInteger("PotionDur") + 1);
							}
							if (args[1].toLowerCase().equals("set"))
							{
								if (args.length == 3)
								{
									int l = Integer.parseInt(args[2]);
									is.getTagCompound().setInteger("PotionDur", l);
								}
							}
						}
					}
					if (args[0].toLowerCase().equals("power") || args[0].toLowerCase().equals("amplifier"))
					{
						if (is.getItem() instanceof AlchemicalPotionItem)
						{
							if (args[1].toLowerCase().equals("add"))
							{
								if (args.length == 3)
								{
									int l = Integer.parseInt(args[2]);
									is.getTagCompound().setInteger("PotionAmpl", is.getTagCompound().getInteger("PotionAmpl") + l);
								}
								is.getTagCompound().setInteger("PotionAmpl", is.getTagCompound().getInteger("PotionAmpl") + 1);
							}
							if (args[1].toLowerCase().equals("set"))
							{
								if (args.length == 3)
								{
									int l = Integer.parseInt(args[2]);
									is.getTagCompound().setInteger("PotionAmpl", l);
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setHasTagCompound(ItemStack is)
	{
		if (!is.hasTagCompound())
		{
			NBTTagCompound tag = new NBTTagCompound();
			is.setTagCompound(tag);
		}
	}

}
