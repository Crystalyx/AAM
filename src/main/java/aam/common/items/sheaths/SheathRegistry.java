package aam.common.items.sheaths;

import aam.api.abstraction.Sheath;

import java.util.ArrayList;
import java.util.List;

public class SheathRegistry
{
	public static ArrayList<Sheath> sheaths = new ArrayList<Sheath>();

	public static void registerSheath(Sheath _sheath)
	{
		sheaths.add(_sheath);
	}
}
