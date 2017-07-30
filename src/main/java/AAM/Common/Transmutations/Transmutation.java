package AAM.Common.Transmutations;

import java.util.ArrayList;
import java.util.List;

import AAM.Utils.Color;

public class Transmutation
{
	public Transmutation(String name, String l, long price, TransAction action)
	{
		this.name = name;
		for (int i = 0; i < l.length() / 4; i++)
		{
			this.parts.add(l.substring(i * 4, (i + 1) * 4));
		}
		this.action = action;
		this.price = price;
	}

	public Transmutation setTick(boolean tick)
	{
		this.needTick = tick;
		return this;
	}

	public Transmutation setPrepTime(int time)
	{
		this.prepTime = time;
		return this;
	}

	public Transmutation setPrepColor(Color prep)
	{
		this.prepCol = prep;
		return this;
	}

	public Transmutation setActTime(int time)
	{
		this.actTime = time;
		return this;
	}

	public Transmutation setActColor(Color act)
	{
		this.actCol = act;
		return this;
	}

	public String name;
	public int prepTime = 40;
	public int actTime = 10;
	public Color prepCol = new Color(60, 40, 255);
	public Color actCol = new Color(160, 140, 255);
	public List<String> parts = new ArrayList<String>();
	public TransAction action;
	public boolean needTick = false;
	public long price;

}
