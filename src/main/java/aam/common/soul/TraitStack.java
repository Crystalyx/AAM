package aam.common.soul;

import aam.api.interfaces.INBTSave;
import aam.api.TraitModifier;
import aam.utils.NBTHelper;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

public class TraitStack implements INBTSave
{
	public Trait trait;
	public float base;

	public List<TraitModifier> modifiers = null;

	public TraitStack()
	{
		trait = Trait.MentalHealth;
		base = 0;
	}

	public TraitStack(Trait trait)
	{
		this.trait = trait;
		base = trait.base;
	}

	public TraitStack(Trait trait, float base)
	{
		this.trait = trait;
		this.base = base;
	}

	public void applyModifier(TraitModifier tm)
	{
		if (modifiers == null)
		{
			modifiers = new ArrayList<>();
		}

		if (modifiers.contains(tm))
		{
			return;
		}

		modifiers.add(tm);
	}

	public void removeModifier(TraitModifier tm)
	{
		if (modifiers != null)
		{
			if (modifiers.contains(tm))
			{
				modifiers.remove(tm);
			}
		}
	}

	/**
	 * Removes all modifiers with id = given id
	 * 
	 * @param id
	 */
	public void removeModifier(String id)
	{
		if (modifiers != null)
		{
			List<TraitModifier> rmList = new ArrayList<>();
			for (TraitModifier tm : modifiers)
			{
				if (tm.id == id)
				{
					rmList.add(tm);
				}
			}
			modifiers.removeAll(rmList);
		}
	}

	public float countValue()
	{
		float add = 0;
		float perc = 0;
		float mult = 1;
		if (modifiers != null)
		{
			for (TraitModifier tm : modifiers)
			{
				if (tm.operation.equals(TraitModifier.Operation.add))
				{
					add += tm.value;
				}
				if (tm.operation.equals(TraitModifier.Operation.addPercent))
				{
					perc += tm.value;
				}
				if (tm.operation.equals(TraitModifier.Operation.mult))
				{
					mult *= tm.value;
				}
			}
		}
		return (base + add) * (1 + perc) * mult;
	}

	@Override
	public void saveToNBT(NBTTagCompound tag)
	{
		tag.setInteger("Trait", trait.ordinal());
		tag.setFloat("Base", base);
		if (modifiers != null && !modifiers.isEmpty())
		{
			tag.setTag("List", NBTHelper.saveList(modifiers));
		}
	}

	@Override
	public void loadFromNBT(NBTTagCompound tag)
	{
		trait = Trait.values()[tag.getInteger("Trait")];
		base = tag.getFloat("Base");
		if (tag.hasKey("List"))
		{
			modifiers = (List<TraitModifier>) NBTHelper.loadList(tag.getTagList("List", 10));
		}
	}

	public TraitStack copy()
	{
		TraitStack ret = new TraitStack(trait, base);
		ret.modifiers = modifiers;
		return ret;
	}

}
