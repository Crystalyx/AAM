package aam.utils;

public class Color
{
	public Color(int i, int j, int k)
	{
		red = i;
		green = j;
		blue = k;
		hex = MiscUtils.rgbToHex(i, j, k);
	}

	public Color(double i, double j, double k)
	{
		red = (int) Math.round(i * 255);
		green = (int) Math.round(j * 255);
		blue = (int) Math.round(k * 255);
		hex = MiscUtils.rgbToHex((int) Math.round(i * 255), (int) Math.round(j * 255), (int) Math.round(k * 255));
	}

	public Color(int[] arr)
	{
		red = arr[0];
		green = arr[1];
		blue = arr[2];
		hex = MiscUtils.rgbToHex(arr[0], arr[1], arr[2]);
	}

	public Color(int i, int j, int k, int a)
	{
		red = i;
		green = j;
		blue = k;
		alpha = a;
		hex = MiscUtils.rgbToHex(i, j, k);
	}

	public Color()
	{
		red = 255;
		green = 255;
		blue = 255;
		alpha = 255;
		hex = MiscUtils.rgbToHex(255, 255, 255);
	}

	public int red;
	public int green;
	public int blue;
	public int alpha = 255;
	public int hex;

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Color)
		{
			Color c = (Color) obj;
			return c.red == red && c.green == green && c.blue == blue && c.alpha == alpha;
		}
		return false;
	}

	public Color add(Color c)
	{
		red = (c.red + red) / 2;
		green = (c.green + green) / 2;
		blue = (c.blue + blue) / 2;

		if (c.red == 256 && c.green == 256 && c.blue == 256)
		{
			return c;
		}

		if (red > 255)
		{
			red = 255;
		}
		if (green > 255)
		{
			green = 255;
		}
		if (blue > 255)
		{
			blue = 255;
		}

		return new Color(red, green, blue);

	}

	@Override
	public String toString()
	{
		return red + "|" + green + "|" + blue;
	}

	public static final Color White = new Color(255, 255, 255);
	public static final Color Black = new Color(0, 0, 0);
	public static final Color Cobalt = new Color(40, 60, 255);
	public static final Color Mithril = new Color(95, 207, 215);
	public static final Color Red = new Color(194, 0, 0);
	public static final Color Yellow = new Color(247, 215, 0);
	public static final Color Green = new Color(0, 200, 0);
	public static final Color Cyan = new Color(0, 220, 255);
	public static final Color Blue = new Color(0, 68, 198);
	public static final Color LightBlue = new Color(88, 171, 255);
	public static final Color Purple = new Color(133, 0, 255);
	public static final Color Void = new Color(68, 57, 193);
	public static final Color Gold = new Color(255, 199, 62);
}
