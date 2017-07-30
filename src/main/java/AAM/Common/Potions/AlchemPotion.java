package AAM.Common.Potions;

import java.util.List;

import AAM.Utils.Color;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.PotionEffect;

public class AlchemPotion
{
	public AlchemPotion(int pot, int duration, int id, List<Ingridient> ingrs, String name, Color col)
	{
		this.potion = pot;
		this.duration = duration;
		this.id = id;
		this.ingridients = ingrs;
		this.name = name;
		this.col = col;
	}

	public void affectEffect(EntityLiving e, int effic)
	{
		PotionEffect eff = new PotionEffect(this.potion, this.duration, effic);
		e.addPotionEffect(eff);
	}

	public Color col;
	public String name;
	public int potion;
	public int duration;
	public int id;
	public List<Ingridient> ingridients;
}
