package AAM.Common.Potions;

import java.util.ArrayList;
import java.util.List;

import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Core.AAMConfig;
import AAM.Core.AAMCore;
import AAM.Utils.Color;
import AAM.Utils.PlayerDataHandler;
import AAM.Utils.VectorWorld;
import AAM.Utils.Wec3;
import DummyCore.Utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class ModPotions
{
	public static AlchemicalPotion[] pots = new AlchemicalPotion[0];
	public static List<Concentrate> concentrates = new ArrayList<Concentrate>();

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
		addPotion(200, "Flame", new Color(215, 130, 15), Ingridients.ender_pearl, Ingridients.mortisberry, Ingridients.wormwood, Ingridients.diamond, Ingridients.blaze_powder);
		addPotion(1000, "Flight", new Color(0, 250, 220), Ingridients.feather, Ingridients.feather, Ingridients.blackberry, Ingridients.ender_pearl, Ingridients.diamond, Ingridients.emerald, Ingridients.golden_apple, Ingridients.ghast_tear,
				Ingridients.spider_eye, Ingridients.blaze_powder, Ingridients.snowball, Ingridients.nether_star, Ingridients.shadowveil, Ingridients.nether_wart);
		addPotion(200, "Ice", new Color(10, 215, 170), Ingridients.snowball, Ingridients.snowball, Ingridients.ender_pearl, Ingridients.blueberry, Ingridients.wormwood, Ingridients.diamond);
		addPotion(200, "Invincibility", new Color(250, 195, 0), Ingridients.ender_pearl, Ingridients.blackberry, Ingridients.diamond, Ingridients.diamond, Ingridients.golden_apple, Ingridients.bone, Ingridients.blaze_powder);
		addPotion(600, "LavaResistance", new Color(140, 30, 30), Ingridients.nether_wart, Ingridients.shadowveil, Ingridients.diamond, Ingridients.ender_pearl, Ingridients.blaze_powder);
		addPotion(200, "Poison", new Color(130, 210, 10), Ingridients.spider_eye, Ingridients.spider_eye, Ingridients.spider_eye, Ingridients.shadowveil, Ingridients.poisonous_potato);
		addPotion(200, "Soul", new Color(65, 42, 167), Ingridients.apple, Ingridients.blackberry, Ingridients.blackberry, Ingridients.diamond, Ingridients.ender_pearl, Ingridients.shroom, Ingridients.shadowveil);

		ConcentrAction act = new ConcentrAction()
		{
			@Override
			public void act(World w, EntityPlayer p, int level, int size)
			{
				p.heal(5F * (size * 2 + level));
			}
		};
		addConcentrate("Heal", new Color(239, 0, 0), act, pots[0]);

		act = new ConcentrAction()
		{
			@Override
			public void act(World w, EntityPlayer p, int level, int size)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				ph.addSoul((size * 5 + level + 1) * 40);
			}
		};
		addConcentrate("Soul", new Color(65, 42, 167), act, pots[8]);

		act = new ConcentrAction()
		{
			@Override
			public void act(World w, EntityPlayer p, int level, int size)
			{
				Wec3 pp = new Wec3(p);
				pp = pp.sub(new Wec3(0, 0, 0));
				Wec3 down = pp.sub(new Wec3(0, 1, 0));
				VectorWorld vw = new VectorWorld(w);
				if (vw.getBlock(down).isBlockSolid(w, down.ix, down.iy, down.iz, 1) && vw.isAirBlock(down))
				{
					vw.setBlock(pp, Blocks.snow_layer);
				}
				int r = (size * 2 + level) + 2;

				for (int i = -r; i <= r; i++)
				{
					for (int j = -r; j <= r; j++)
					{
						for (int k = -r; k <= r; k++)
						{
							Wec3 ijk = pp.add(new Wec3(i, j, k));
							if (vw.getBlock(ijk) == Blocks.water || vw.getBlock(ijk) == Blocks.flowing_water)
							{
								vw.setBlock(ijk, Blocks.ice, 0, 2);
							}
							if (vw.getBlock(ijk) == Blocks.lava || vw.getBlock(ijk) == Blocks.flowing_lava)
							{
								if (vw.getBlockMetadata(ijk) == 0)
									vw.setBlock(ijk, Blocks.obsidian, 0, 2);
								else
									vw.setBlock(ijk, Blocks.cobblestone, 0, 2);
							}
							if (vw.getBlock(ijk) == ModBlocks.BloodBlock)
							{
								if (((BlockFluidClassic) vw.getBlock(ijk)).isSourceBlock(w, pp.ix + i, pp.iy + j, pp.iz + k))
									vw.setBlock(ijk, ModBlocks.miniumBlock, 0, 2);
								else
									vw.setBlock(ijk, Blocks.ice, 0, 2);
							}
						}
					}
				}
			}
		};
		addConcentrate("Ice", new Color(10, 215, 170), act, pots[4]);

		// TODO 3 potions
	}

	public static Ingridient get(int id)
	{
		return Ingridients.get(id);
	}

	public static void addConcentrate(String name, Color color, ConcentrAction act, AlchemicalPotion potion)
	{
		concentrates.add(new Concentrate(name, color, act, potion));
	}

	public static int getConcentrateID(int potionID)
	{
		for (int i = 0; i < concentrates.size(); i++)
		{
			Concentrate conc = concentrates.get(i);
			if (conc.potion.id == potionID)
			{
				return i;
			}
		}
		return -1;
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
		add = new AlchemicalPotion(lastId, duration, lastId, ing, name, color);
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
