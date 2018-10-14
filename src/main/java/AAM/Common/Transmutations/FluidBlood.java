package aam.common.transmutations;

import net.minecraftforge.fluids.Fluid;

public class FluidBlood extends Fluid
{
	public FluidBlood()
	{
		super("aam_blood");
		this.setDensity(1051);
		viscosity = 1100;
	}

}
