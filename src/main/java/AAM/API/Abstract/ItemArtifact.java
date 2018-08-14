package AAM.API.Abstract;

import java.util.List;

import AAM.Utils.PlayerDataHandler;
import DummyCore.Utils.EnumRarityColor;
import DummyCore.Utils.MiscUtils;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * Base class for any art that will be added
 * 
 * @author user
 *
 */
public abstract class ItemArtifact extends Item
{

	/**
	 * @param name
	 *            - Item Unlocalized name
	 * @param descr
	 *            - Item unlocalized description (like "aam.art.descr")
	 * @param descrSize
	 *            - Amount of lines used to write description
	 */
	public ItemArtifact(String name, String descr, int descrSize)
	{
		this.Descr = descr;
		this.descrSize = descrSize;
		this.setUnlocalizedName(name);
	}

	/**
	 * - Amount of soul consumed each 40 ticks when wearing artifact
	 */
	public int soul = 1;
	/**
	 * - Item unlocalized description (like "aam.art.descr")
	 */
	String Descr = "";
	/**
	 * - Amount of lines used to write description
	 */
	int descrSize = 3;
	// TODO Rarity
	// String rarity = EnumRarityColor.COMMON.getRarityColor();

	/**
	 * Array that contains format colors for rainbow format
	 */
	EnumChatFormatting[] rainbow = new EnumChatFormatting[] { EnumChatFormatting.RED, EnumChatFormatting.GOLD, EnumChatFormatting.YELLOW, EnumChatFormatting.GREEN, EnumChatFormatting.AQUA, EnumChatFormatting.BLUE,
			EnumChatFormatting.DARK_BLUE, EnumChatFormatting.DARK_PURPLE, EnumChatFormatting.RED };

	/**
	 * Method that adds lines to the tooltip
	 */
	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean shift)
	{
		l.clear();

		long time = p.worldObj.getWorldTime();
		l.add(rainbow[(int) ((time / 1) % rainbow.length)] + StatCollector.translateToLocal(this.getUnlocalizedName()));

		for (int j = 0; j < this.descrSize; j++)
		{
			l.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal(this.Descr + j));
		}

	}

	/**
	 * Method called each tick when item is worn
	 */
	public void onWornTick(ItemStack i, EntityLivingBase e)
	{
		if (e instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) e;
			onUpdate(i, p);
		}
	}

	/**
	 * @param i
	 * @param e
	 * @return does you have enough soul to use this artifact
	 */
	public boolean isAble(ItemStack i, EntityLivingBase e)
	{
		if (e.worldObj.getWorldTime() % 40 == 1)
		{
			if (e instanceof EntityPlayer)
			{
				EntityPlayer p = (EntityPlayer) e;
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				return ph.consumeSoul(this.soul);
			}
		}
		return false;
	}

	public String getSpeedModifierName(ItemStack i)
	{
		return "00000";

	}

	public String getKnockbackModifierName(ItemStack i)
	{
		return "00000";

	}

	public String getHealthModifierName(ItemStack i)
	{
		return "00000";

	}

	public String getAttackModifierName(ItemStack i)
	{
		return "00000";

	}

	public float getDamage(ItemStack i, EntityPlayer p, float am)
	{
		return am;
	}

	public float getFallDistance(ItemStack i, EntityPlayer p, float am)
	{
		return am;
	}

	public void setJump(ItemStack i, EntityPlayer p)
	{
	}

	public void onUpdate(ItemStack i, EntityPlayer p)
	{
		MiscUtils.applyPlayerModifier(p, SharedMonsterAttributes.movementSpeed, this.getSpeedModifierName(i), this.getSpeedModifierValue(i), false, 2, "inventory");
		MiscUtils.applyPlayerModifier(p, SharedMonsterAttributes.knockbackResistance, this.getKnockbackModifierName(i), this.getKnockbackModifierValue(i), false, 0, "inventory");
		MiscUtils.applyPlayerModifier(p, SharedMonsterAttributes.maxHealth, this.getHealthModifierName(i), this.getHealthModifierValue(i), false, 0, "inventory");
		MiscUtils.applyPlayerModifier(p, SharedMonsterAttributes.attackDamage, this.getAttackModifierName(i), this.getAttackModifierValue(i), false, 2, "inventory");
	}

	public float getSpeedModifierValue(ItemStack i)
	{
		return 1f;

	}

	public double getAttackModifierValue(ItemStack i)
	{
		return 1;
	}

	public double getHealthModifierValue(ItemStack i)
	{
		return 0;
	}

	public float getKnockbackModifierValue(ItemStack par1ItemStack)
	{
		return 0;
	}

	public float setDamageOnAttack(ItemStack par1ItemStack, EntityPlayer p, EntityLivingBase base, float am)
	{
		return am;
	}
}
