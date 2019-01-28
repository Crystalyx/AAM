package aam.api.abstraction;

import aam.client.renderer.item.HammerRenderer;
import aam.client.renderer.item.SheathedSwordRenderer;
import aam.common.items.sheaths.SheathRegistry;
import aam.common.weapon.WeaponManager;
import aam.utils.InventoryUtils;
import aam.utils.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class MeleeWeapon extends GameWeapon
{
	public boolean isHammer = false;

	public MeleeWeapon(String name, int minSlots, int maxSlots)
	{
		super(name, minSlots, maxSlots);
		MinecraftForgeClient.registerItemRenderer(this, new SheathedSwordRenderer());
	}

	public MeleeWeapon setHammer(boolean isHammer)
	{
		this.isHammer = isHammer;

		return this;
	}

	public Sheath getCommonSheath() {return null;}
	public boolean canBePutTo(Sheath sheath) {return false;}
	public WearType getWearType() {return WearType.Side;}

	@Override
	public void onUpdate(ItemStack i, World w, Entity e, int time, boolean hand)
	{
		super.onUpdate(i, w, e, time, hand);
		WeaponManager.assertHasNBT(i);
		NBTTagCompound tag = i.getTagCompound();
		if(tag.getInteger("EquipCD") <=0)
		{
			tag.removeTag("EquipCD");
		}
		else
			tag.setInteger("EquipCD",tag.getInteger("EquipCD")-1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack _item, World _world, EntityPlayer _player)
	{
		WeaponManager.assertHasNBT(_item);
		return super.onItemRightClick(_item, _world, _player);
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
		return (double) WeaponManager.getDamagePoints(stack) / (double) WeaponManager.getMaxDamagePoints(stack);
	}
}
