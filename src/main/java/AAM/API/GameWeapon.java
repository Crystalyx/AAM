package aam.api;

import java.util.List;

import aam.api.Interface.IExtendedReach;
import aam.api.Interface.ISoulUpgrade;
import aam.api.Interface.IUpgradableItem;
import aam.common.weapon.WeaponManager;
import aam.common.weapon.WeaponUpgrade;
import aam.utils.PlayerDataHandler;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class GameWeapon extends ItemSword implements IUpgradableItem, ISoulUpgrade, IExtendedReach
{
	public EnumRarity rarity = EnumRarity.common;
	public int repairs = 3;
	public int durability = 4000;
	private int minSlots = 0;
	private int maxSlots = 3;
	public Item repairItem = null;
	public int meta = 0;
	public double knockback = 0;
	public float reach = 4;

	private float baseDmg = 5;
	private boolean bypassesArmor = false;
	public String texture = "";
	public String name = "";

	public GameWeapon(String name, int minSlots, int maxSlots)
	{
		super(ToolMaterial.IRON);
		this.setUnlocalizedName(name);
		this.minSlots = minSlots;
		this.maxSlots = maxSlots;
		this.name = name;
		this.setMaxDamage(0);
	}

	public IIcon icon;

	@Override
	public void registerIcons(IIconRegister ir)
	{
		icon = ir.registerIcon(texture);
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return icon;
	}

	public GameWeapon setBaseDmg(float baseDmg)
	{
		this.baseDmg = baseDmg;
		return this;
	}

	public GameWeapon setBaseDmg(ItemStack i, float baseDmg)
	{
		this.baseDmg = baseDmg;
		return this;
	}

	public GameWeapon setBypassesArmor(boolean bypassesArmor)
	{
		this.bypassesArmor = bypassesArmor;
		return this;
	}

	public boolean getBypassesArmor(ItemStack is)
	{
		return this.bypassesArmor;
	}

	public GameWeapon setTexture(String texture)
	{
		this.texture = texture;
		return this;
	}

	public GameWeapon setRarity(EnumRarity rarity)
	{
		this.rarity = rarity;
		return this;
	}

	public GameWeapon setDurability(int durability)
	{
		this.durability = durability;
		return this;
	}

	public GameWeapon setRepairItem(Item repairItem, int meta)
	{
		this.repairItem = repairItem;
		this.meta = meta;
		return this;
	}

	public GameWeapon setRepairs(int repairs)
	{
		this.repairs = repairs;
		return this;
	}

	public GameWeapon setKnockback(double knockback)
	{
		this.knockback = knockback;
		return this;
	}

	public void setReach(float reach)
	{
		this.reach = reach;
	}

	@Override
	public int getUpgradeLevel(ItemStack is)
	{
		return is.hasTagCompound() ? is.getTagCompound().getInteger("UpgradeLevel") : 0;
	}

	@Override
	public void addUpgradeLevel(World w, ItemStack is)
	{
		this.setUpgradeLevel(w, is, this.getUpgradeLevel(is) + 1);
	}

	@Override
	public void addUpgradeLevel(World w, ItemStack is, int level)
	{
		this.setUpgradeLevel(w, is, this.getUpgradeLevel(is) + level);
	}

	@Override
	public void setUpgradeLevel(World w, ItemStack is, int level)
	{
		if (is.hasTagCompound())
		{
			is.getTagCompound().setInteger("UpgradeLevel", level);
		}
		else
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("UpgradeLevel", level);
			is.setTagCompound(tag);
		}
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List l, boolean hand)
	{
		l.set(0, rarity.rarityColor + "" + l.get(0));
		l.add("Rarity: " + rarity.rarityColor + rarity.rarityName);
		l.add(2, "");
		if (is.hasTagCompound())
		{
			l.add("Slots: " + WeaponManager.getSlotCount(is));
			for (int i = 0; i < WeaponManager.getSlotCount(is); i++)
			{
				WeaponUpgrade wu = WeaponManager.getUpgradeAt(is, i);
				if (wu != null)
				{
					l.add("Slot " + (i + 1) + ": " + wu.color + wu.name);
				}
			}
		}
	}

	@Override
	public void onUpdate(ItemStack i, World w, Entity e, int time, boolean hand)
	{
		if (!i.hasTagCompound())
		{
			WeaponManager.setCompounds(i);
		}
	}

	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
	{
		return false;
	}

	@Override
	public int getMaxLevel(ItemStack is)
	{
		return 15;
	}

	@Override
	public boolean enableLayers(ItemStack is)
	{
		return true;
	}

	@Override
	public int getMinSlotCount(ItemStack is)
	{
		return minSlots;
	}

	@Override
	public int getMaxSlotCount(ItemStack is)
	{
		return maxSlots;
	}

	@Override
	public int getDurability(ItemStack is)
	{
		return durability;
	}

	@Override
	public int getMaxRepairCount(ItemStack is)
	{
		return repairs;
	}

	public float getBaseDamage(ItemStack is)
	{
		return this.baseDmg;
	}

	@Override
	public float getMeleeDamageBonus(PlayerDataHandler ph, int level, float baseDamage, boolean inAttack)
	{
		return baseDamage * (level * 0.05f);
	}

	@Override
	public float getSpecificMeleeDamageBonus(PlayerDataHandler ph, EntityLivingBase l, int level, float baseDamage)
	{
		return baseDamage * (level * 0.05f);
	}

	@Override
	public void applySpecificPotionEffects(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{

	}

	@Override
	public float getRangedDamageBonus(PlayerDataHandler ph, int level, float baseDamage, boolean inAttack)
	{
		return 0;
	}

	@Override
	public float getSpecificRangedDamageBonus(PlayerDataHandler ph, EntityLivingBase l, int soulLevel, float baseDamage)
	{
		return 0;
	}

	@Override
	public void onAttack(EntityPlayer p, EntityLivingBase e, float damage)
	{

	}

	@Override
	public boolean onEnderTeleport(EntityPlayer p, EntityLivingBase ender)
	{
		return false;
	}

	@Override
	public float getReachValue(EntityPlayer p, ItemStack is)
	{
		return this.reach;
	}

	/**
	 * Determines if the durability bar should be rendered for this item.
	 * Defaults to vanilla stack.isDamaged behavior. But modders can use this
	 * for any data they wish.
	 *
	 * @param stack
	 *            The current Item Stack
	 * @return True if it should render the 'durability' bar.
	 */
	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return stack.isItemDamaged();
	}

	/**
	 * Queries the percentage of the 'Durability' bar that should be drawn.
	 *
	 * @param stack
	 *            The current ItemStack
	 * @return 1.0 for 100% 0 for 0%
	 */
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		return (double) stack.getItemDamage() / (double) stack.getMaxDamage();
	}

}
