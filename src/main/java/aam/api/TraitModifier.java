package aam.api;

import aam.api.interfaces.INBTSave;
import aam.common.soul.Trait;
import net.minecraft.nbt.NBTTagCompound;

public class TraitModifier implements INBTSave
{
	public String id = "";
	public Trait trait = Trait.MentalHealth;
	public float value = 0;
	public Enum<Operation> operation = Operation.add;

	public TraitModifier()
	{

	}

	public TraitModifier(String id, Trait trait, float value, Enum<Operation> operation)
	{
		this.id = id;
		this.trait = trait;
		this.value = value;
		this.operation = operation;
	}

	public enum Operation
	{
		add, addPercent, mult;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof TraitModifier)
		{
			TraitModifier tm = (TraitModifier) obj;
			return id == tm.id && trait.equals(tm.trait) && value == tm.value && operation.equals(tm.operation);
		}
		return false;
	}

	@Override
	public void saveToNBT(NBTTagCompound tag)
	{
		tag.setString("ID", id);
		tag.setInteger("Trait", trait.ordinal());
		tag.setFloat("Value", value);
		tag.setInteger("Operation", operation.ordinal());
	}

	@Override
	public void loadFromNBT(NBTTagCompound tag)
	{
		id = tag.getString("ID");
		trait = Trait.values()[tag.getInteger("Trait")];
		value = tag.getFloat("Value");
		operation = TraitModifier.Operation.values()[tag.getInteger("Operation")];
	}
}
