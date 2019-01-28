package aam.common.items.weapon;

import aam.api.abstraction.GameWeapon;
import aam.api.abstraction.MeleeWeapon;
import aam.common.soul.SoulDamageSource;
import aam.utils.EnumRarity;
import aam.utils.MathUtils;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BlueEyedSword extends MeleeWeapon
{

	public BlueEyedSword()
	{
		super("aam.sword.blue_eyed.broad", 2, 4);
		this.baseDamage = 72;
		this.rarity = EnumRarity.Epic;
		this.texture = "aam:sword/blue_eyed_sword";
		this.durability = 4000;
		this.repairs = -1;
		this.knockback = 10;
	}

	@Override
	public void onAttack(EntityPlayer p, EntityLivingBase e, float damage)
	{
		ItemStack sw = p.getCurrentEquippedItem();
		GameWeapon gw = (GameWeapon) sw.getItem();
		int times = 1 + gw.getUpgradeLevel(sw);

		EntityLivingBase el = e;
		List<EntityLivingBase> done = new ArrayList<EntityLivingBase>();
		done.add(e);
		done.add(p);

		for (int i = 0; i < times; i++)
		{
			List<EntityLivingBase> entitiesAround = p.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, new Wec3(el).extendBoth(5));
			entitiesAround.removeAll(done);
			if (entitiesAround.size() > 0)
			{
				el = entitiesAround.get(MathUtils.r.nextInt(entitiesAround.size()));
				el.attackEntityFrom(new SoulDamageSource(PlayerDataHandler.get(p)), damage);
			} else break;
		}
	}
}
