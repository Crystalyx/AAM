package aam.utils;

import aam.utils.vectors.Angle3D;
import aam.utils.vectors.Wec3;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathUtils
{
	public static Random r = new Random();

	public static Color rainbow(double time)
	{
		float r = (float) (Math.sin(time * Math.PI / 3 + 5 * Math.PI / 6) + 1) / 2f;
		float g = (float) (Math.sin(time * Math.PI / 3 + Math.PI / 6) + 1) / 2f;
		float b = (float) (Math.sin(time * Math.PI / 3 - Math.PI / 2) + 1) / 2f;
		return new Color(r, g, b);
	}

	public static double floorMod(double x, double y)
	{
		return x - floorDiv(x, y);
	}

	public static double floorDiv(double x, double y)
	{
		return Math.floor(x / y);
	}

	public static float floorMod(float x, float y)
	{
		return x - floorDiv(x, y);
	}

	public static float floorDiv(float x, float y)
	{
		return (float) Math.floor(x / y);
	}

	/**
	 * Checks if number lower then max and larger then min
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isInLimit(int a, int min, int max)
	{
		if (a <= max && a >= min)
		{
			return true;
		}

		return false;
	}

	/**
	 * Checks if number lower then max and larger then min
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isInLimit(double a, double min, double max)
	{
		if (a <= max && a >= min)
		{
			return true;
		}

		return false;
	}

	/**
	 * Puts number in the limits
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static int limit(int a, int min, int max)
	{
		if (a > max)
		{
			a = max;
		}
		if (a < min)
		{
			a = min;
		}
		return a;
	}

	/**
	 * Puts number in the limits
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static float limit(float a, float min, float max)
	{
		if (a > max)
		{
			a = max;
		}
		if (a < min)
		{
			a = min;
		}
		return a;
	}

	/**
	 * Puts number in the limits
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static double limit(double a, double min, double max)
	{
		if (a > max)
		{
			a = max;
		}
		if (a < min)
		{
			a = min;
		}
		return a;
	}

	public static int cycle(int i, int min, int max)
	{
		if (i > max)
		{
			return min;
		}
		if (i < min)
		{
			return max;
		}
		return i;
	}

	public static double cycle(double i, double min, double max)
	{
		if (i > max)
		{
			return min;
		}
		if (i < min)
		{
			return max;
		}
		return i;
	}

	/**
	 * @param value
	 * @param max
	 * @return if value is bigger then half of max it will return 1-value else
	 *         return value
	 */
	public static double getSawValue(double value, double max)
	{
		return value > max / 2 ? max - value : value;
	}

	/**
	 * @param speed
	 * @return Value changing in time. Usable in things like bobbing
	 */
	public static double getTimedValue(double speed)
	{
		Minecraft.getMinecraft();
		return Minecraft.getSystemTime() % speed / speed;
	}

	/**
	 * @param speed
	 * @return deg angle changing in time
	 */
	public static double getTimedAngle(double speed)
	{
		Minecraft.getMinecraft();
		return Minecraft.getSystemTime() % (360 * speed) / speed;
	}

	/**
	 * @param l
	 * @return mixed list
	 */
	public static <T> List<T> randomize(List<T> l)
	{
		List<T> ret = new ArrayList<>();
		for (int i = 0; i < l.size(); i++)
		{
			ret.add(l.get(r.nextInt(l.size())));
		}
		return ret;
	}

	/**
	 * @param percent
	 *            - 1% : percent=1
	 * @return are you lucky today?
	 */
	public static boolean randWPercent(double percent)
	{
		double ret = r.nextDouble();
		return ret <= percent / 100d;
	}

	/**
	 * @param percent
	 *            - 1% : percent=1
	 * @return are you lucky today?
	 */
	public static boolean randWPercent(Random r, double percent)
	{
		double ret = r.nextDouble();
		return ret <= percent / 100d;
	}

	/**
	 * Smart way to get random int
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getIntInRange(int a, int b)
	{
		if (b > a)
		{
			int raw = r.nextInt(b - a);
			return a + raw;
		}
		else
			if (a > b)
			{
				int raw = r.nextInt(a - b);
				return b + raw;
			}
			else
			{
				return a;
			}

	}

	/**
	 * 
	 * @param b
	 * @return numeric representation of b
	 */
	public static int boolToNum(boolean b)
	{
		return b ? 1 : 0;

	}

	/**
	 * @return numeric representation of random boolean
	 */
	public static int boolToNum()
	{
		return r.nextBoolean() ? 1 : 0;
	}

	/**
	 * 
	 * @return numeric representation of b b? tr : fl
	 */
	public static int boolToNum(boolean b, int tr, int fl)
	{
		return b ? tr : fl;
	}

	/**
	 * 
	 * @return numeric representation of random boolean b? tr : fl
	 */
	public static int boolToNum(int tr, int fl)
	{
		return r.nextBoolean() ? tr : fl;
	}

	/**
	 * @param value
	 * @param i
	 * @return value cut to i decimal numbers
	 */
	public static double round(double value, int i)
	{
		return (int) (value * Math.pow(10, i)) / Math.pow(10, i);
	}

	/**
	 * @param value
	 * @param i
	 * @return String value cut to i decimal numbers
	 */
	public static String roundStr(double value, int i)
	{
		String s = Math.round(value * Math.pow(10, i)) / Math.pow(10, i) + "";
		while (s.endsWith("0") && s.contains("."))
		{
			s = s.substring(0, s.length() - 1);
		}
		if (s.indexOf(".") == s.length() - 1)
		{
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	/**
	 * @param p
	 * @return WorldPos(Vector) rounded to the axis
	 */
	public static Wec3 getAxialVec(Wec3 p)
	{
		if (Math.abs(p.x) > Math.abs(p.y))
		{
			if (Math.abs(p.x) > Math.abs(p.z))
			{
				return new Wec3(p.x, 0, 0);
			}
			else
			{
				if (Math.abs(p.z) > Math.abs(p.x))
				{
					return new Wec3(p.x, 0, 0);
				}
			}
		}
		else
		{
			if (Math.abs(p.y) > Math.abs(p.x))
			{
				if (Math.abs(p.y) > Math.abs(p.z))
				{
					return new Wec3(p.y, 0, 0);
				}
				else
				{
					if (Math.abs(p.z) > Math.abs(p.y))
					{
						return new Wec3(p.z, 0, 0);
					}
				}
			}
		}
		return p;
	}

	/**
	 * 
	 * @param yaw
	 * @param pitch
	 * @param dist
	 * @return pos in orthogonal coords instead of polar
	 */
	public static Wec3 getPosBy3DAngle(double yaw, double pitch, double dist)
	{
		double rY = Math.sin(pitch) * dist;
		double mod = Math.cos(pitch) * dist;

		double rX = Math.cos(yaw) * mod;
		double rZ = Math.sin(yaw) * mod;

		return new Wec3(rX, rY, rZ);
	}

	/**
	 * @param angle
	 * @param dist
	 * @return pos in orthogonal coords instead of polar
	 */
	public static Wec3 getPosBy3DAngle(Angle3D angle, double dist)
	{
		double rY = Math.sin(angle.pitch) * dist;
		double mod = Math.cos(angle.pitch) * dist;

		double rX = Math.cos(angle.yaw) * mod;
		double rZ = Math.sin(angle.yaw) * mod;

		return new Wec3(rX, rY, rZ);
	}

	/**
	 * @param p
	 * @return 3d Angle in polar coords instead of orthogonal
	 */
	public static Angle3D get3DAngleByPos(Wec3 p)
	{
		double mod = Math.hypot(p.x, p.z);
		double pitch = 0, yaw = 0;

		pitch = Math.atan2(p.y, mod);

		yaw = Math.atan2(p.x, p.z);
		return new Angle3D(yaw, pitch);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @return 3d Angle in polar coords instead of orthogonal
	 */
	public static Angle3D get3DAngleByPos(double x, double y, double z)
	{
		double mod = Math.hypot(x, z);
		double pitch = 0, yaw = 0;

		pitch = Math.atan2(y, mod);
		yaw = Math.atan2(x, z);

		return new Angle3D(yaw, pitch);
	}
}
