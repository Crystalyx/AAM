package aam.common.items.weapon.anvil;

import aam.api.abstraction.RangedWeapon;
import aam.common.weapon.WeaponManager;
import aam.common.weapon.anvil.AnvilRegistry;
import aam.common.weapon.anvil.WeaponPart;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ForgedRangedWeapon extends RangedWeapon
{

	public ForgedRangedWeapon()
	{
		super("forgedRangedWeapon", 0, 0, 1, 0, 0);
	}

	@Override
	public int getMaxSlotCount(ItemStack is)
	{
		return getSlots(is);
	}

	@Override
	public int getMinSlotCount(ItemStack is)
	{
		return getSlots(is);
	}

	@Override
	public void onUpdate(ItemStack i, World w, Entity e, int time, boolean hand)
	{
		if (time % 20 == 1)
		{
			recountValues(i);
		}
	}

	public int getSlots(ItemStack is)
	{
		if (is.hasTagCompound())
		{
			return is.getTagCompound().getInteger("AnvilSlots");
		}
		return 0;
	}

	@Override
	public int getMaxRepairCount(ItemStack is)
	{
		if (is.hasTagCompound())
		{
			return is.getTagCompound().getInteger("AnvilRepairs");
		}
		return 0;
	}

	@Override
	public int getDurability(ItemStack is)
	{
		if (is.hasTagCompound())
		{
			return is.getTagCompound().getInteger("AnvilDurability");
		}
		return 0;
	}

	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		if (is.hasTagCompound())
		{
			return is.getTagCompound().getString("AnvilName");
		}
		return super.getItemStackDisplayName(is);
	}

	@Override
	public float getBaseDamage(ItemStack is)
	{
		if (is.hasTagCompound())
		{
			return is.getTagCompound().getInteger("AnvilDamage");
		}
		return 0;
	}

	@Override
	public int getRangedDamage(ItemStack is)
	{
		return (int) this.getBaseDamage(is);
	}

	@Override
	public int getMeleeDamage(ItemStack is)
	{
		return getRangedDamage(is) / 10;
	}

	@Override
	public boolean getBypassesArmor(ItemStack is)
	{
		if (is.hasTagCompound())
		{
			return is.getTagCompound().getBoolean("AnvilBypass");
		}
		return false;
	}

	@Override
	public EnumRarity getRarity(ItemStack is)
	{
		if (is.hasTagCompound())
		{
			return EnumRarity.values()[is.getTagCompound().getInteger("AnvilRarity")];
		}
		return EnumRarity.common;
	}

	@Override
	public int getSoulConsumed(ItemStack is)
	{
		if (is.hasTagCompound())
		{
			return is.getTagCompound().getInteger("AnvilSoulConsumed");
		}
		return 0;
	}

	public static void recountValues(ItemStack is)
	{
		WeaponManager.assertHasNBT(is);

		int slots = 0;
		int repairs = 0;
		int durability = 0;
		float damage = 0;
		boolean bypass = false;
		int rarity = 0;
		int soulConsumed = 0;

		int[] parts = is.getTagCompound().getIntArray("Parts");

		for (int i = 0; i < parts.length; i++)
		{
			WeaponPart pt = AnvilRegistry.parts.get(parts[i]);
			slots += pt.slots;
			repairs += pt.repairs;
			durability += pt.durability;
			damage += pt.damage;
			bypass |= pt.bypassesArmor;
			soulConsumed += pt.soulConsumed;
			if (pt.rarity.ordinal() > rarity)
			{
				rarity = pt.rarity.ordinal();
			}
		}

		NBTTagCompound tg = is.getTagCompound();
		tg.setInteger("AnvilSlots", slots);
		tg.setInteger("AnvilRepairs", repairs);
		tg.setInteger("AnvilDurability", durability);
		tg.setFloat("AnvilDamage", damage);
		tg.setBoolean("AnvilBypass", bypass);
		tg.setInteger("AnvilRarity", rarity);
		tg.setInteger("AnvilSoulConsumed", rarity);
	}

	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	public int getRenderPasses(int metadata)
	{
		return 32;
	}

	public static IIcon[] icons;

	@Override
	public void registerIcons(IIconRegister ir)
	{
		icons = new IIcon[AnvilRegistry.parts.size()];
		for (int i = 0; i < AnvilRegistry.parts.size(); i++)
		{
			icons[i] = ir.registerIcon(AnvilRegistry.parts.get(i).pass);
		}
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		WeaponManager.assertHasNBT(stack);
		int[] parts = stack.getTagCompound().getIntArray("Parts");
		if (pass < parts.length)
			return icons[parts[pass]];
		return icons[parts[0]];
	}
}
