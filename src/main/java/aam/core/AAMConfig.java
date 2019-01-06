package aam.core;

import DummyCore.Utils.IDummyConfig;
import net.minecraftforge.common.config.Configuration;

public class AAMConfig implements IDummyConfig
{
	public Configuration cfg;

	public AAMConfig(Configuration cfg)
	{
		this.cfg = cfg;
	}

	public static int genericPID = 23;
	public static boolean enableExpander = true;
	public static boolean enableRemovingRSword = false;
	public static int dungDimId = 23;
	public static int dungBiomeID;

	@Override
	public void load(Configuration config)
	{
		genericPID = cfg.getInt("potions", "genericPID", genericPID, 25, 64, null);
		enableExpander = cfg.getBoolean("EasterEggs", "enableExpander", enableExpander, null);
		enableRemovingRSword = cfg.getBoolean("EasterEggs", "enableRemovingRSword", enableRemovingRSword, null);
		dungDimId = cfg.getInt("dungeon", "dungeonID", 23, 1, 100, "Id of dungeon Dimension");
		dungDimId = cfg.getInt("dungeon", "dungBiomeID", 23, 1, 100, "Id of dungeon Biome");

	}

}
