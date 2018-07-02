package AAM.Common.Potions;

import java.util.ArrayList;
import java.util.List;

import AAM.Core.AAMConfig;
import AAM.Core.AAMCore;
import AAM.Utils.Color;
import DummyCore.Utils.MiscUtils;
import net.minecraft.potion.Potion;

public class ModPotions
{
	public static AlchemicalPotion[] pots = new AlchemicalPotion[0];
	private static int lastId;

	public static Potion heal;
	public static Potion antidote;
	public static Potion flame;
	public static Potion flight;
	public static Potion ice;
	public static Potion invincibility;
	public static Potion lavaResistance;
	public static Potion poison;
	public static Potion soul;

	public static void load()
	{
		MiscUtils.extendPotionArray(32);

		heal = (new PotionEffects(0 + AAMCore.cfg.genericPID, false)).setPotionName("alchamagicr.heal");
		antidote = (new PotionEffects(1 + AAMCore.cfg.genericPID, false)).setPotionName("alchamagicr.antidote");
		flame = (new PotionEffects(2 + AAMCore.cfg.genericPID, false)).setPotionName("alchamagicr.flame");
		flight = (new PotionEffects(3 + AAMCore.cfg.genericPID, false)).setPotionName("alchamagicr.flight");
		ice = (new PotionEffects(4 + AAMCore.cfg.genericPID, false)).setPotionName("alchamagicr.ice");
		invincibility = (new PotionEffects(5 + AAMCore.cfg.genericPID, false)).setPotionName("alchamagicr.invincibility");
		lavaResistance = (new PotionEffects(6 + AAMCore.cfg.genericPID, false)).setPotionName("alchamagicr.lavaResistance");
		poison = (new PotionEffects(7 + AAMCore.cfg.genericPID, true)).setPotionName("alchamagicr.poison");
		soul = (new PotionEffects(8 + AAMCore.cfg.genericPID, true)).setPotionName("alchamagicr.soul");

		addPotion(200, "Heal", new Color(239, 0, 0), Ingridients.raspberry, Ingridients.shroom, Ingridients.raspberry, Ingridients.red_flower);
		addPotion(10, "Antidote", new Color(30, 200, 85), Ingridients.spider_eye, Ingridients.ghast_tear, Ingridients.nether_wart, Ingridients.cactus);
		addPotion(200, "Flame", new Color(215, 130, 15), Ingridients.ender_pearl, Ingridients.mortisberry, Ingridients.wormwood, Ingridients.diamond, Ingridients.magma_cream);
		addPotion(1000, "Flight", new Color(0, 250, 220), Ingridients.feather, Ingridients.feather, Ingridients.blackberry, Ingridients.ender_pearl, Ingridients.diamond, Ingridients.emerald, Ingridients.golden_apple, Ingridients.ghast_tear,
				Ingridients.fermented_spider_eye, Ingridients.magma_cream, Ingridients.snowball, Ingridients.nether_star, Ingridients.shadowveil, Ingridients.nether_wart);
		addPotion(200, "Ice", new Color(10, 215, 170), Ingridients.snowball, Ingridients.snowball, Ingridients.ender_pearl, Ingridients.blueberry, Ingridients.wormwood, Ingridients.diamond);
		addPotion(200, "Invincibility", new Color(250, 195, 0), Ingridients.ender_pearl, Ingridients.blackberry, Ingridients.diamond, Ingridients.diamond, Ingridients.golden_apple, Ingridients.bone, Ingridients.blaze_powder);
		addPotion(600, "LavaResistance", new Color(140, 30, 30), Ingridients.nether_wart, Ingridients.shadowveil, Ingridients.diamond, Ingridients.ender_eye, Ingridients.magma_cream);
		addPotion(200, "Poison", new Color(130, 210, 10), Ingridients.spider_eye, Ingridients.spider_eye, Ingridients.fermented_spider_eye, Ingridients.shadowveil, Ingridients.poisonous_potato);
		addPotion(200, "Soul", new Color(65, 42, 167), Ingridients.apple, Ingridients.blackberry, Ingridients.blackberry, Ingridients.diamond, Ingridients.ender_pearl, Ingridients.shroom, Ingridients.shadowveil);

		// TODO 3 potions
	}

	public static Ingridient get(int id)
	{
		return Ingridients.get(id);
	}

	public static void addPotion(int duration, String name, Color color, int... ings)
	{
		AlchemicalPotion add;
		List<Ingridient> ing = new ArrayList<Ingridient>();
		for (int i = 0; i < ings.length; i++)
		{
			ing.add(get(ings[i]));
		}
		add = new AlchemicalPotion(lastId + AAMConfig.genericPID, duration, lastId, ing, name, color);
		expandArr();
		pots[lastId] = add;
		lastId++;
	}

	public static void addPotion(int duration, String name, Color color, Ingridient... ings)
	{
		AlchemicalPotion add;
		List<Ingridient> ing = new ArrayList<Ingridient>();
		for (int i = 0; i < ings.length; i++)
		{
			ing.add(ings[i]);
		}
		add = new AlchemicalPotion(lastId + AAMConfig.genericPID, duration, lastId, ing, name, color);
		expandArr();
		pots[lastId] = add;
		lastId++;
	}

	public static void expandArr()
	{
		AlchemicalPotion[] pot = new AlchemicalPotion[pots.length + 1];
		for (int i = 0; i < pots.length; i++)
		{
			pot[i] = pots[i];
		}
		pots = pot;
	}
}
