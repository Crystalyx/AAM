package aam.common.transmutations;

import aam.utils.Color;

import java.util.ArrayList;
import java.util.List;

public class Transmutation
{
	public Transmutation(String name, String l, TransAction action)
	{
		this.name = name;
		for (int i = 0; i < l.length() / 4; i++)
		{
			parts.add(l.substring(i * 4, (i + 1) * 4));
		}
		this.action = action;
	}

	public Transmutation setPrepTime(int time)
	{
		prepTime = time;
		return this;
	}

	public Transmutation setPrepColor(Color prep)
	{
		prepCol = prep;
		return this;
	}

	public Transmutation setActTime(int time)
	{
		actTime = time;
		return this;
	}

	public Transmutation setActColor(Color act)
	{
		actCol = act;
		return this;
	}

	public String name;
	public int prepTime = 40;
	public int actTime = 10;
	public Color prepCol = new Color(60, 40, 255);
	public Color actCol = new Color(160, 140, 255);
	public List<String> parts = new ArrayList<>();
	public TransAction action;

}
