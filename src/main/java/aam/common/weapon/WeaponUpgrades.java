package aam.common.weapon;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class WeaponUpgrades
{
	public static List<WeaponUpgrade> upgrades = new ArrayList<>();

	public static WeaponUpgrade soulVampirism = new SoulVampirismUpgrade();
	public static WeaponUpgrade flame = new FlameUpgrade();
	public static WeaponUpgrade enderInversion = new EnderInversionUpgrade();
	public static WeaponUpgrade iceGem = new IceGem();
	public static WeaponUpgrade fireGem = new FireGem();
	public static WeaponUpgrade airGem = new AirGem();
	public static WeaponUpgrade earthGem = new EarthGem();

	public static void load()
	{
		upgrades.add(soulVampirism);
		upgrades.add(flame);
		upgrades.add(enderInversion);
		upgrades.add(iceGem);
		upgrades.add(fireGem);
		upgrades.add(airGem);
		upgrades.add(earthGem);

	}

	public static WeaponUpgrade getUpgrade(Item i, int meta)
	{
		for (int j = 0; j < upgrades.size(); j++)
		{
			WeaponUpgrade wu = upgrades.get(j);
			if (wu.item == i && wu.meta == meta)
			{
				return wu;
			}
		}
		return null;
	}

	public static int getUpgradeId(Item i, int meta)
	{
		for (int j = 0; j < upgrades.size(); j++)
		{
			WeaponUpgrade wu = upgrades.get(j);
			if (wu.item == i && wu.meta == meta)
			{
				return j + 1;
			}
		}
		return 0;// zero value is equal to null upg
	}

}
