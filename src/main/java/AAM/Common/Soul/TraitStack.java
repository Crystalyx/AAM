package AAM.Common.Soul;

import java.util.ArrayList;
import java.util.List;

import AAM.API.TraitModifier;
import AAM.API.Interface.INBTSave;
import AAM.Utils.NBTHelper;
import net.minecraft.nbt.NBTTagCompound;

public class TraitStack implements INBTSave
{
	public Trait trait;
	public float base;

	public List<TraitModifier> modifiers = null;

	public TraitStack()
	{
		this.trait = Trait.MentalHealth;
		this.base = 0;
	}

	public TraitStack(Trait trait)
	{
		this.trait = trait;
		this.base = trait.base;
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
			modifiers = new ArrayList<TraitModifier>();
		}

		if (this.modifiers.contains(tm))
			return;

		modifiers.add(tm);
	}

	public void removeModifier(TraitModifier tm)
	{
		if (modifiers != null)
		{
			if (this.modifiers.contains(tm))
				this.modifiers.remove(tm);
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
			List<TraitModifier> rmList = new ArrayList<TraitModifier>();
			for (TraitModifier tm : modifiers)
			{
				if (tm.id == id)
				{
					rmList.add(tm);
				}
			}
			this.modifiers.removeAll(rmList);
		}
	}

	public float countValue()
	{
		float add = 0;
		float perc = 0;
		float mult = 1;
		if (this.modifiers != null)
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
		return (this.base + add) * (1 + perc) * mult;
	}

	@Override
	public void saveToNBT(NBTTagCompound tag)
	{
		tag.setInteger("Trait", this.trait.ordinal());
		tag.setFloat("Base", this.base);
		if (this.modifiers != null && !this.modifiers.isEmpty())
			tag.setTag("List", NBTHelper.saveList(this.modifiers));
	}

	@Override
	public void loadFromNBT(NBTTagCompound tag)
	{
		this.trait = Trait.values()[tag.getInteger("Trait")];
		this.base = tag.getFloat("Base");
		if (tag.hasKey("List"))
		{
			this.modifiers = (List<TraitModifier>) NBTHelper.loadList(tag.getTagList("List", 10));
		}
	}

	public TraitStack copy()
	{
		TraitStack ret = new TraitStack(this.trait, this.base);
		ret.modifiers = this.modifiers;
		return ret;
	}

}
