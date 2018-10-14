package aam.api.abstraction;

import aam.api.GameWeapon;
import aam.common.weapon.WeaponManager;
import net.minecraft.item.ItemStack;

public class RangedWeapon extends GameWeapon
{
	private int meleeDmg = 3;
	private int rangedDmg = 7;
	private int soulConsumed = 2;
	private int cd = 20;

	public RangedWeapon(String name, int meleeDmg, int rangedDmg, int soulConsumed, int minSlots, int maxSlots)
	{
		super(name, minSlots, maxSlots);
		this.meleeDmg = meleeDmg;
		this.rangedDmg = rangedDmg;
		this.soulConsumed = soulConsumed;
	}

	public RangedWeapon setCooldown(int cd)
	{
		this.cd = cd;
		return this;
	}

	public int getCoolDown(ItemStack is)
	{
		return cd;
	}

	public int getMeleeDamage(ItemStack is)
	{
		return meleeDmg;
	}

	public int getRangedDamage(ItemStack is)
	{
		return rangedDmg;
	}

	public int getSoulConsumed(ItemStack is)
	{
		return soulConsumed;
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
		return WeaponManager.getDamagePoints(stack) > 0;
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
		return 1 - (double) WeaponManager.getDamagePoints(stack) / (double) WeaponManager.getMaxDamagePoints(stack);
	}

}
