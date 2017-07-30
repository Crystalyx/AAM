package AAM.Utils;

public class Color
{
	public Color(int i, int j, int k)
	{
		this.red = i;
		this.green = j;
		this.blue = k;
		this.hex = MiscUtils.rgbToHex(i, j, k);
	}

	public Color(double i, double j, double k)
	{
		this.red = (int) Math.round(i * 255);
		this.green = (int) Math.round(j * 255);
		this.blue = (int) Math.round(k * 255);
		this.hex = MiscUtils.rgbToHex((int) Math.round(i * 255), (int) Math.round(j * 255), (int) Math.round(k * 255));
	}

	public Color(int[] arr)
	{
		this.red = arr[0];
		this.green = arr[1];
		this.blue = arr[2];
		this.hex = MiscUtils.rgbToHex(arr[0], arr[1], arr[2]);
	}

	public Color(int i, int j, int k, int a)
	{
		this.red = i;
		this.green = j;
		this.blue = k;
		this.alpha = a;
		this.hex = MiscUtils.rgbToHex(i, j, k);
	}

	public Color()
	{
		this.red = 255;
		this.green = 255;
		this.blue = 255;
		this.alpha = 255;
		this.hex = MiscUtils.rgbToHex(255, 255, 255);
	}

	public int red;
	public int green;
	public int blue;
	public int alpha = 255;
	public int hex;

	public Color add(Color c)
	{
		red = ((c.red + this.red) / 2);
		green = ((c.green + this.green) / 2);
		blue = ((c.blue + this.blue) / 2);

		if (c.red == 256 && c.green == 256 && c.blue == 256)
			return c;

		if (red > 255)
			red = 255;
		if (green > 255)
			green = 255;
		if (blue > 255)
			blue = 255;

		return new Color(red, green, blue);

	}

	@Override
	public String toString()
	{
		return this.red + "|" + this.green + "|" + this.blue;
	}
}