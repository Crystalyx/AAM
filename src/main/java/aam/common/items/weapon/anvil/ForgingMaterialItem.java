package aam.common.items.weapon.anvil;

import java.util.HashSet;
import java.util.List;

import aam.common.weapon.anvil.ForgingMaterial;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class ForgingMaterialItem extends ItemTool
{
	public int enchantability = 14;
	public ForgingMaterial material;

	public ForgingMaterialItem(ForgingMaterial mat)
	{
		super(0, ToolMaterial.IRON, new HashSet());
		this.setUnlocalizedName(mat.name);
		this.setMaxStackSize(1);
		this.setMaxDamage(mat.maxDamage);
		this.setEnchantability(mat.enchantability);
		this.setTextureName(mat.icon);
		this.setHasSubtypes(true);
		this.material = mat;
	}

	public ForgingMaterialItem setEnchantability(int enchantability)
	{
		this.enchantability = enchantability;
		return this;
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List l, boolean shift)
	{
		l.add(is.getMaxDamage() - is.getItemDamage() + " material units");
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

	/**
	 * Return the enchantability factor of the item, most of the time is based
	 * on material.
	 */
	@Override
	public int getItemEnchantability()
	{
		return this.enchantability;
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	@Override
	public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_)
	{
		return false;
	}

	/**
	 * Current implementations of this method in child classes do not use the
	 * entry argument beside ev. They just raise the damage on the stack.
	 */
	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
	{
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
	{
		return true;
	}
}
