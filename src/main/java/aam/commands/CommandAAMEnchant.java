package aam.commands;

import net.minecraft.command.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;

public class CommandAAMEnchant extends CommandBase
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
		return StatCollector.translateToLocal("aam.command.enchant.name");
	}

	@Override
	public String getCommandUsage(ICommandSender p)
	{
		return StatCollector.translateToLocal("aam.command.enchant.usage");
	}

	@Override
	public void processCommand(ICommandSender s, String[] args)
	{
		if (args.length < 2)
		{
			throw new WrongUsageException("commands.enchant.usage", new Object[0]);
		}
		else
		{
			EntityPlayerMP entityplayermp = getPlayer(s, args[0]);
			int i = parseIntBounded(s, args[1], 0, Enchantment.enchantmentsList.length - 1);
			int j = 1;
			ItemStack itemstack = entityplayermp.getCurrentEquippedItem();

			if (itemstack == null)
			{
				throw new CommandException("commands.enchant.noItem", new Object[0]);
			}
			else
			{
				Enchantment enchantment = Enchantment.enchantmentsList[i];

				if (enchantment == null)
				{
					throw new NumberInvalidException("commands.enchant.notFound", new Object[] { Integer.valueOf(i) });
				}
				else
				{
					if (args.length >= 3)
					{
						j = Integer.parseInt(args[2]);
					}

					if (itemstack.hasTagCompound())
					{
						NBTTagList nbttaglist = itemstack.getEnchantmentTagList();

						if (nbttaglist != null)
						{
							for (int k = 0; k < nbttaglist.tagCount(); ++k)
							{
								short short1 = nbttaglist.getCompoundTagAt(k).getShort("id");

								if (Enchantment.enchantmentsList[short1] != null)
								{
									Enchantment enchantment1 = Enchantment.enchantmentsList[short1];
								}
							}
						}
					}

					itemstack.addEnchantment(enchantment, j);
					func_152373_a(s, this, "commands.enchant.success", new Object[0]);
				}
			}
		}
	}

}
