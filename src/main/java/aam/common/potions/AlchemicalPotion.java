package aam.common.potions;

import aam.utils.Color;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.PotionEffect;

import java.util.List;

public class AlchemicalPotion
{
	public AlchemicalPotion(int pot, int duration, int id, List<Ingridient> ingrs, String name, Color col)
	{
		potion = pot;
		this.duration = duration;
		this.id = id;
		ingridients = ingrs;
		this.name = name;
		this.col = col;
	}

	public void affectEffect(EntityLiving e, int effic)
	{
		PotionEffect eff = new PotionEffect(potion, duration, effic);
		e.addPotionEffect(eff);
	}

	public Color col;
	public String name;
	public int potion;
	public int duration;
	public int id;
	public List<Ingridient> ingridients;
}
