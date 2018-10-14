package aam.api.abstraction;

import aam.api.GameWeapon;
import aam.common.weapon.WeaponManager;
import net.minecraft.item.ItemStack;

public class MeleeWeapon extends GameWeapon
{
	public boolean isHammer = false;

	public MeleeWeapon(String name, int minSlots, int maxSlots)
	{
		super(name, minSlots, maxSlots);
	}

	public MeleeWeapon setHammer(boolean isHammer)
	{
		this.isHammer = isHammer;
		return this;
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
