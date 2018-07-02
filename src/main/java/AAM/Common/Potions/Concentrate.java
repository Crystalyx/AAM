package AAM.Common.Potions;

import AAM.Utils.Color;

public class Concentrate
{
	public String name;
	public Color color;
	public ConcentrAction action;
	public AlchemicalPotion potion;

	public Concentrate(String name, Color color, ConcentrAction act, AlchemicalPotion potion)
	{
		this.name = name;
		this.color = color;
		this.action = act;
		this.potion = potion;
	}

}
