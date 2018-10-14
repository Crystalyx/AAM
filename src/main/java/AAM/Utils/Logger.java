package aam.utils;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

/**
 * @author Lord_Crystalyx
 */
public class Logger
{

	public static void log(Level logLevel, Object object)
	{
		FMLLog.log("AlchemyAndMagic", logLevel, String.valueOf(object));
	}

	public static void all(Object... object)
	{
		String s = MiscUtils.compact(object);
		log(Level.ALL, s);
	}

	public static void debug(Object... object)
	{
		String s = MiscUtils.compact(object);
		log(Level.DEBUG, s);
	}

	public static void error(Object... object)
	{
		String s = MiscUtils.compact(object);
		log(Level.ERROR, s);
	}

	public static void fatal(Object... object)
	{
		String s = MiscUtils.compact(object);
		log(Level.FATAL, s);
	}

	public static void minfo(Object... object)
	{
		String s = MiscUtils.compact(object);
		log(Level.INFO, s);
	}

	public static void info(Object object)
	{
		String s = object + "";
		log(Level.INFO, s);
	}

	public static void off(Object... object)
	{
		String s = MiscUtils.compact(object);
		log(Level.OFF, s);
	}

	public static void trace(Object... object)
	{
		String s = MiscUtils.compact(object);
		log(Level.TRACE, s);
	}

	public static void warn(Object... object)
	{
		String s = MiscUtils.compact(object);
		log(Level.WARN, s);
	}

	public static void chat(EntityPlayer p, Object object)
	{
		if (p.worldObj.isRemote)
		{
			String s = object + "";
			p.addChatMessage(new ChatComponentText(s));
		}
	}

	public static void mchat(EntityPlayer p, Object... object)
	{
		if (p.worldObj.isRemote)
		{
			String s = MiscUtils.compact(object);
			p.addChatMessage(new ChatComponentText(s));
		}
	}

	public static void achat(EntityPlayer p, Object[] object)
	{
		if (p.worldObj.isRemote)
		{
			String s = MiscUtils.compact(object);
			p.addChatMessage(new ChatComponentText(s));
		}
	}
}