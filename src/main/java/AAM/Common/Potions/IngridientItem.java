package AAM.Common.Potions;

public class IngridientItem
{
	public Ingridient ing;
	public int type = 0;

	public IngridientItem(Ingridient ing)
	{
		this.ing = ing;
	}

	public IngridientItem(Ingridient ing, int type)
	{
		this.ing = ing;
		this.type = type;
	}

}
