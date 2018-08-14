package AAM.Common.Items.Alchemy;

import java.util.List;

import AAM.Common.Potions.AlchemicalPotion;
import AAM.Common.Potions.ModPotions;
import AAM.Core.AAMCore;
import AAM.Utils.MiscUtils;
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
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class AlchemicalPotionItem extends ItemFood
{
	public AlchemicalPotionItem()
	{
		super(0, 0, false);
		this.setAlwaysEdible();
		this.setHasSubtypes(true);
	}

	@Override
	public ItemStack onEaten(ItemStack i, World w, EntityPlayer p)
	{
		if (i.hasTagCompound())
		{
			int id = i.getTagCompound().getInteger("PotionID");
			if (id < ModPotions.pots.length)
			{
				PotionEffect eff = new PotionEffect(AAMCore.cfg.genericPID + id, i.getTagCompound().getInteger("PotionDur"), i.getTagCompound().getInteger("PotionAmpl"));
				p.addPotionEffect(eff);
				ItemStack item = new ItemStack(i.getItem(), i.stackSize - 1);
				item.setTagCompound(i.getTagCompound());
				return item;
			}
			else
			{
				if (id >= ModPotions.pots.length)
				{
					PotionEffect eff = new PotionEffect(id, i.getTagCompound().getInteger("PotionDur"), i.getTagCompound().getInteger("PotionAmpl"));
					p.addPotionEffect(eff);
					ItemStack item = new ItemStack(i.getItem(), i.stackSize - 1);
					item.setTagCompound(i.getTagCompound());
					return item;
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
			int dur = i.getTagCompound().getInteger("PotionDur") / 20;
			if (dur > 0)
				l.add("Duration: " + dur + " sec");
			else
			{
				l.add("Duration: Instant");
			}
			l.add("Power: " + (i.getTagCompound().getInteger("PotionAmpl") + 1));
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

	public static IIcon[] icon = new IIcon[2];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		icon[0] = ir.registerIcon("aam:potions/emptyphial");
		icon[1] = ir.registerIcon("aam:potions/potionoffset");
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
			int id = i.getTagCompound().getInteger("PotionID");
			String name = ModPotions.pots[id].name;
			return "aam.alchpotion." + name;
		}
		else
			return "aam.alchpotion.null";
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
			return icon[0];
		}
		else
			return icon[1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int pass)
	{
		if (pass != 0)
		{
			if (is.hasTagCompound())
			{
				int beta = is.getTagCompound().getInteger("PotionID");
				AlchemicalPotion spell = ModPotions.pots[Math.min(beta, ModPotions.pots.length - 1)];
				return MiscUtils.rgbToHex(spell.col.red, spell.col.green, spell.col.blue);
			}
		}
		return 16777215;
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item is, CreativeTabs tab, List l)
	{
		for (int i = 0; i < ModPotions.pots.length; i++)
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("PotionID", ModPotions.pots[i].id);
			tag.setInteger("PotionDur", ModPotions.pots[i].duration);
			tag.setInteger("PotionAmpl", 0);
			ItemStack item = new ItemStack(is, 1, 0);
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
