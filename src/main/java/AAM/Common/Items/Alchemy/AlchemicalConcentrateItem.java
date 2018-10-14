package aam.common.items.alchemy;

import java.util.List;

import aam.common.items.ModItems;
import aam.common.potions.Concentrate;
import aam.common.potions.ModPotions;
import aam.utils.InventoryUtils;
import aam.utils.MiscUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class AlchemicalConcentrateItem extends ItemFood
{
	public int size = 1;

	public AlchemicalConcentrateItem(int size)
	{
		super(0, 0, false);
		this.setAlwaysEdible();
		this.setHasSubtypes(true);
		this.size = size;
		if (size > 0)
		{
			this.setMaxStackSize(1);
		}
		icon = new IIcon[Volumes[size] + 1];
	}

	public static final int[] Volumes = new int[] { 0, 2, 8 };

	@Override
	public ItemStack onEaten(ItemStack i, World w, EntityPlayer p)
	{
		if (!w.isRemote)
		{
			if (!i.hasTagCompound())
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("Fluid", Volumes[size]);
				i.setTagCompound(tag);
			}
			else
			{
				i.getTagCompound().setInteger("Fluid", i.getTagCompound().getInteger("Fluid") - 1);
			}
			if (i.hasTagCompound())
			{
				int level = i.getTagCompound().getInteger("potionLevel");
				ModPotions.concentrates.get(i.getItemDamage()).action.act(w, p, level, size);
				if (!p.capabilities.isCreativeMode)
				{
					if (i.getTagCompound().getInteger("Fluid") <= 0)
					{
						InventoryUtils.addItemStack(p, new ItemStack(ModItems.ConcentratePhial, 1, size));
						InventoryUtils.decrPlayerStack(p, 1);
						if (size == 0)
						{
							if (i.stackSize > 0)
							{
								i.getTagCompound().setInteger("Fluid", 1);
							}
						}
					}
				}
			}
		}
		return i;
	}

	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean h)
	{
		if (i.hasTagCompound())
		{
			l.add("Level: " + (i.getTagCompound().getInteger("potionLevel") + 1));
			l.add(i.getTagCompound().getInteger("Fluid") + " uses left");
		}
		else
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("Fluid", Volumes[size] + 1);
			i.setTagCompound(tag);
		}
	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
	{
		return EnumAction.drink;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p)
	{
		p.setItemInUse(is, this.getMaxItemUseDuration(is));
		return is;
	}

	public static final String[] sizes = new String[] { "small", "medium", "big" };
	public IIcon bottle;
	public IIcon[] icon;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		String wp = "aam:potions/";
		bottle = ir.registerIcon(wp + sizes[size] + "_concentrate");
		for (int i = 0; i < Volumes[size] + 1; i++)
		{
			String volume = sizes[size];
			icon[i] = ir.registerIcon(wp + volume + "_concentrate_offset_" + i);

		}
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack i)
	{
		if (i.hasTagCompound())
		{
			String name = ModPotions.concentrates.get(i.getItemDamage()).name;
			return "aam.alchconcentrate." + name + size;
		}
		else
		{
			return "aam.alchconcentrate.null" + size;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	/**
	 * Gets an icon index based on an item's damage value and the given render
	 * pass
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		if (pass == 0)
		{
			return bottle;
		}
		else
		{
			return icon[0];
		}
	}

	@Override
	public IIcon getIcon(ItemStack i, int pass)
	{
		if (pass == 0)
		{
			return bottle;
		}
		else
		{
			int volume = Volumes[size];
			if (i.hasTagCompound())
			{
				volume = i.getTagCompound().getInteger("Fluid") - 1;
			}
			if (volume < 0)
			{
				return bottle;
			}
			else
			{
				return icon[volume];
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int pass)
	{
		if (pass != 0)
		{
			if (is.hasTagCompound())
			{
				if (is.getItemDamage() < ModPotions.concentrates.size())
				{
					Concentrate conc = ModPotions.concentrates.get(is.getItemDamage());
					return MiscUtils.rgbToHex(conc.color.red, conc.color.green, conc.color.blue);
				}
			}
		}
		return 16777215;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack is)
	{
		return 4 + size * 8;
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item is, CreativeTabs tab, List l)
	{
		for (int i = 0; i < ModPotions.concentrates.size(); i++)
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("potionLevel", 0);
			tag.setInteger("Fluid", Volumes[size] + 1);
			ItemStack item = new ItemStack(is, 1, i);
			item.setTagCompound(tag);
			l.add(item);
		}
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps
	 * to check if is on a player hand and update it's contents.
	 */
	@Override
	public void onUpdate(ItemStack i, World w, Entity e, int p_77663_4_, boolean inHand)
	{

	}

}
