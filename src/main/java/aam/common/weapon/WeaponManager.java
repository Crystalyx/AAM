package aam.common.weapon;

import aam.api.GameWeapon;
import aam.api.Interface.IUpgradableItem;
import aam.api.abstraction.MeleeWeapon;
import aam.api.abstraction.RangedWeapon;
import aam.common.items.ModItems;
import aam.common.items.weapon.anvil.ForgedMeleeWeapon;
import aam.common.items.weapon.anvil.ForgedRangedWeapon;
import aam.utils.MathUtils;
import aam.utils.PlayerDataHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class WeaponManager
{
	public static void setCompounds(ItemStack i)
	{
		if (i != null)
		{
			if (!i.hasTagCompound())
			{
				if (i.getItem() instanceof IUpgradableItem)
				{
					IUpgradableItem weap = (IUpgradableItem) i.getItem();
					int slotCount = MathUtils.getIntInRange(weap.getMinSlotCount(i), weap.getMaxSlotCount(i) + 1);
					setSlotCount(i, slotCount);
				}
			}
		}
	}

	public static void setSlotCount(ItemStack i, int count)
	{
		if (i.hasTagCompound())
		{
			i.getTagCompound().setInteger("SlotCount", count);
		}
		else
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("SlotCount", count);
			i.setTagCompound(tag);
		}
	}

	public static void assertHasNBT(ItemStack i)
	{
		if (!i.hasTagCompound())
		{
			NBTTagCompound tag = new NBTTagCompound();
			i.setTagCompound(tag);
		}
	}

	public static int getSlotCount(ItemStack i)
	{
		if (i != null)
		{
			if (i.getItem() instanceof IUpgradableItem)
			{
				if (i.hasTagCompound())
				{
					return i.getTagCompound().getInteger("SlotCount");
				}
				else
				{
					NBTTagCompound tag = new NBTTagCompound();
					IUpgradableItem weap = (IUpgradableItem) i.getItem();
					int slotCount = MathUtils.getIntInRange(weap.getMinSlotCount(i), weap.getMaxSlotCount(i) + 1);
					tag.setInteger("SlotCount", slotCount);
					i.setTagCompound(tag);
					return slotCount;
				}
			}
		}
		return 0;
	}

	public static void recreateSlots(ItemStack i)
	{
		if (i != null)
		{
			if (i.getItem() instanceof IUpgradableItem)
			{
				NBTTagCompound tag = i.getTagCompound();
				if (tag == null)
					tag = new NBTTagCompound();
				IUpgradableItem weap = (IUpgradableItem) i.getItem();
				int slotCount = MathUtils.getIntInRange(weap.getMinSlotCount(i), weap.getMaxSlotCount(i) + 1);
				tag.setInteger("SlotCount", slotCount);
				i.setTagCompound(tag);
			}
		}
	}

	public static void setSlotContents(ItemStack i, int slot, WeaponUpgrade up)
	{
		if (i != null)
		{
			if (i.getItem() instanceof IUpgradableItem)
			{
				if (i.hasTagCompound())
				{
					int slotCount = i.getTagCompound().getInteger("SlotCount");
					if (slot >= slotCount)
					{
						setSlotCount(i, slot + 1);
					}
					i.getTagCompound().setInteger("UpgSlot_" + slot, WeaponUpgrades.upgrades.indexOf(up) + 1);
				}
				else
				{
					NBTTagCompound tag = new NBTTagCompound();
					IUpgradableItem weap = (IUpgradableItem) i.getItem();
					int slotCount = MathUtils.getIntInRange(weap.getMinSlotCount(i), weap.getMaxSlotCount(i) + 1);
					tag.setInteger("SlotCount", slotCount);
					tag.setInteger("UpgSlot_0", WeaponUpgrades.upgrades.indexOf(up) + 1);
					i.setTagCompound(tag);
				}
			}
		}
	}

	public static void addUpgrade(ItemStack i, WeaponUpgrade up)
	{
		if (i != null)
		{
			if (i.getItem() instanceof IUpgradableItem)
			{
				if (i.hasTagCompound())
				{
					int slotCount = i.getTagCompound().getInteger("SlotCount");
					int slot = 0;
					for (int j = 0; j < slotCount && getUpgradeAt(i, j) != null; j++)
					{
						slot += 1;
					}
					if (slot >= slotCount)
					{
						setSlotCount(i, slotCount + 1);
					}
					i.getTagCompound().setInteger("UpgSlot_" + slot, WeaponUpgrades.upgrades.indexOf(up) + 1);
				}
				else
				{
					NBTTagCompound tag = new NBTTagCompound();
					IUpgradableItem weap = (IUpgradableItem) i.getItem();
					int slotCount = MathUtils.getIntInRange(weap.getMinSlotCount(i), weap.getMaxSlotCount(i) + 1);
					tag.setInteger("SlotCount", slotCount);
					tag.setInteger("UpgSlot_0", WeaponUpgrades.upgrades.indexOf(up) + 1);
					i.setTagCompound(tag);
				}
			}
		}
	}

	public static int getRepairs(ItemStack i)
	{
		setCompounds(i);
		return i.getTagCompound().getInteger("Repairs");
	}

	public static int getMaxRepairs(ItemStack i)
	{
		if (i != null)
		{
			if (i.getItem() instanceof IUpgradableItem)
			{
				return ((IUpgradableItem) i.getItem()).getMaxRepairCount(i);
			}
		}
		return 3;
	}

	public static int getMaxDamagePoints(ItemStack i)
	{
		if (i != null)
		{
			if (i.getItem() instanceof IUpgradableItem)
			{
				return ((IUpgradableItem) i.getItem()).getDurability(i);
			}
		}
		return 3;
	}

	public static void setDamagePoints(ItemStack i, int count)
	{
		if (i.hasTagCompound())
		{
			i.getTagCompound().setInteger("Damage", count);
		}
		else
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("Damage", count);
			i.setTagCompound(tag);
		}
	}

	public static void addDamagePoints(ItemStack i, int count)
	{
		if (i.hasTagCompound())
		{
			i.getTagCompound().setInteger("Damage", count + getDamagePoints(i));
		}
		else
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("Damage", count + getDamagePoints(i));
			i.setTagCompound(tag);
		}
	}

	public static int getDamagePoints(ItemStack i)
	{
		if (i.hasTagCompound())
		{
			return i.getTagCompound().getInteger("Damage");
		}
		else
		{
			return 0;
		}
	}

	public static void repair(ItemStack i)
	{
		setCompounds(i);
		setDamagePoints(i, 0);
		setBroken(i, false);
		i.getTagCompound().setInteger("Repairs", getRepairs(i) + 1);
	}

	public static boolean isBroken(ItemStack i)
	{
		setCompounds(i);
		return i.getTagCompound().getBoolean("Broken");
	}

	public static void setBroken(ItemStack i, boolean broke)
	{
		setCompounds(i);
		i.getTagCompound().setBoolean("Broken", broke);
	}

	public static float getReachValue(ItemStack i, EntityPlayer p)
	{
		if (i != null)
		{
			if (i.getItem() instanceof GameWeapon)
			{
				return ((GameWeapon) i.getItem()).getReachValue(p, i);
			}
		}
		return 0;
	}

	public static int getUpgradeLevel(ItemStack i)
	{
		if (i != null)
		{
			if (i.getItem() instanceof IUpgradableItem)
			{
				return ((IUpgradableItem) i.getItem()).getUpgradeLevel(i);
			}
		}
		return 0;
	}

	public static WeaponUpgrade getUpgradeAt(ItemStack i, int slot)
	{
		if (i.hasTagCompound())
		{
			NBTTagCompound tag = i.getTagCompound();
			int id = tag.getInteger("UpgSlot_" + slot);
			if (id > 0)
			{
				return WeaponUpgrades.upgrades.get(id - 1);
			}
		}
		return null;
	}

	public static boolean isHammer(ItemStack i)
	{
		if (i != null)
		{
			if (i.getItem() instanceof MeleeWeapon)
			{
				return ((MeleeWeapon) i.getItem()).isHammer;
			}
		}
		return false;
	}

	public static boolean isMelee(ItemStack i)
	{
		if (i != null)
		{
			return i.getItem() instanceof MeleeWeapon;
		}
		return false;
	}

	public static boolean isRanged(ItemStack i)
	{
		if (i != null)
		{
			return i.getItem() instanceof RangedWeapon;
		}
		return false;
	}

	public static float getWeaponMeleeDamage(EntityPlayer p, ItemStack i)
	{
		if (i == null)
		{
			return 0;
		}
		Item item = i.getItem();
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		if (item instanceof GameWeapon)
		{
			float meleeBonus = ((GameWeapon) item).getMeleeDamageBonus(ph, getUpgradeLevel(i), ((GameWeapon) item).getBaseDamage(i), false);

			if (item instanceof MeleeWeapon)
			{
				float m = 1;
				if (((MeleeWeapon) item).isHammer)
					m = (float) (1 + 1 / (Math.exp(-(ph.blockDuration - 160) / 10) + 1));
				return ((MeleeWeapon) item).getBaseDamage(i) * m + meleeBonus;
			}
			if (item instanceof RangedWeapon)
			{
				return ((RangedWeapon) item).getMeleeDamage(i) + meleeBonus;
			}
		}
		return 0;
	}

	public static ItemStack createMeleeWeapon(String name, int[] parts)
	{
		ItemStack is = new ItemStack(ModItems.ForgedMeleeWeapon);
		assertHasNBT(is);
		is.getTagCompound().setIntArray("Parts", parts);
		is.getTagCompound().setString("AnvilName", name);
		ForgedMeleeWeapon.recountValues(is);
		recreateSlots(is);
		return is;
	}

	public static ItemStack createRangedWeapon(String name, int[] parts)
	{
		ItemStack is = new ItemStack(ModItems.ForgedRangedWeapon);
		assertHasNBT(is);
		is.getTagCompound().setIntArray("Parts", parts);
		is.getTagCompound().setString("AnvilName", name);
		ForgedRangedWeapon.recountValues(is);
		recreateSlots(is);
		return is;
	}

	public static float getSpecificWeaponMeleeDamage(EntityPlayer p, EntityLivingBase l, ItemStack i)
	{
		if (i == null)
		{
			return 0;
		}
		Item item = i.getItem();
		PlayerDataHandler ph = PlayerDataHandler.get(p);
		if (item instanceof GameWeapon)
		{
			return ((GameWeapon) item).getSpecificMeleeDamageBonus(ph, l, ((GameWeapon) item).getUpgradeLevel(i), ((GameWeapon) item).getBaseDamage(i));
		}
		return 0;
	}

	public static int getEmptySlotCount(ItemStack i)
	{
		if (i != null)
		{
			if (i.getItem() instanceof IUpgradableItem)
			{
				if (i.hasTagCompound())
				{
					int slotCount = i.getTagCompound().getInteger("SlotCount");
					int empty = 0;
					for (int j = 0; j < slotCount; j++)
					{
						if (getUpgradeAt(i, j) == null)
						{
							empty += 1;
						}
					}
					return empty;
				}
				else
				{
					NBTTagCompound tag = new NBTTagCompound();
					IUpgradableItem weap = (IUpgradableItem) i.getItem();
					int slotCount = MathUtils.getIntInRange(weap.getMinSlotCount(i), weap.getMaxSlotCount(i) + 1);
					tag.setInteger("SlotCount", slotCount);
					i.setTagCompound(tag);
					return slotCount;
				}
			}
		}
		return 0;
	}

	public static int getRangedCooldown(ItemStack is)
	{
		if (is != null)
			if (is.getItem() instanceof RangedWeapon)
				return ((RangedWeapon) is.getItem()).getCoolDown(is);
		return 0;
	}

	public static int getRangedSoulConsumed(ItemStack is)
	{
		if (is != null)
			if (is.getItem() instanceof RangedWeapon)
				return ((RangedWeapon) is.getItem()).getSoulConsumed(is);
		return 0;
	}

	public static float getRangedDamage(ItemStack is)
	{
		if (is != null)
			if (is.getItem() instanceof RangedWeapon)
				return ((RangedWeapon) is.getItem()).getRangedDamage(is);
		return 0;
	}
}
