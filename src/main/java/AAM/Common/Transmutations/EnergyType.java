package aam.common.transmutations;

public enum EnergyType
{
	Matter, Fluid, Fuel, Philo, Blood, Unknown;

	public boolean isFriendly(EnergyType e)
	{
		switch (this)
		{
		case Fluid:
			return e.equals(Matter) || e.equals(Fluid);
		case Fuel:
			return e.equals(Fuel);
		case Matter:
			return e.equals(Matter) || e.equals(Fluid);
		case Philo:
			return e.equals(Fluid) || e.equals(Fuel) || e.equals(Matter);
		case Unknown:
			return true;
		case Blood:
			return e.equals(Philo);
		default:
			return false;
		}
	}
}
