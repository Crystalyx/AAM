package AAM.API;

import AAM.API.Interface.INBTSave;
import AAM.Common.Soul.Trait;
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
			return this.id == tm.id && this.trait.equals(tm.trait) && this.value == tm.value && this.operation.equals(tm.operation);
		}
		return false;
	}

	@Override
	public void saveToNBT(NBTTagCompound tag)
	{
		tag.setString("ID", this.id);
		tag.setInteger("Trait", this.trait.ordinal());
		tag.setFloat("Value", this.value);
		tag.setInteger("Operation", this.operation.ordinal());
	}

	@Override
	public void loadFromNBT(NBTTagCompound tag)
	{
		this.id = tag.getString("ID");
		this.trait = Trait.values()[tag.getInteger("Trait")];
		this.value = tag.getFloat("Value");
		this.operation = TraitModifier.Operation.values()[tag.getInteger("Operation")];
	}
}
