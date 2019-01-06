package aam.common.items.weapon;

import aam.api.GameWeapon;
import aam.api.abstraction.MeleeWeapon;
import aam.common.soul.SoulDamageSource;
import aam.utils.MathUtils;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BlueEyedSword extends MeleeWeapon
{

	public BlueEyedSword()
	{
		super("aam.item.sword.blue_eyed.broad", 2, 4);
		this.setBaseDmg(72);
		this.setBypassesArmor(false);
		this.setRarity(EnumRarity.epic);
		this.setTexture("aam:sword/blue_eyed_sword");
		this.setDurability(24000);
		this.setRepairs(-1);
		this.setRepairItem(null, 0);
		this.setKnockback(10);
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
			el = entitiesAround.get(MathUtils.r.nextInt(entitiesAround.size()));
			el.attackEntityFrom(new SoulDamageSource(PlayerDataHandler.get(p)), damage);
		}
	}
}
