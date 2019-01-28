package aam.client.gui.base;

import java.util.ArrayList;
import java.util.List;

public class ObjTypes
{
	public static List<SlotType> slots = new ArrayList<>();
	public static List<BarType> bars = new ArrayList<>();

	public static void load()
	{
		addSlotType(0, 0, 36, 32);// arrows in
		addSlotType(1, 0, 72, 32);// arrows out
		addSlotType(2, 0, 108, 32);// bucket empty
		addSlotType(3, 0, 144, 32);// bucket water
		addSlotType(4, 0, 180, 32);// swordType
		addSlotType(5, 0, 216, 32);// artifact
		addSlotType(6, 36, 216, 32);// crystal bow
		addSlotType(7, 36, 180, 32);// swordType dye
		addSlotType(8, 36, 144, 32);// upgrade
		addSlotType(9, 36, 108, 32);// hammer
		addSlotType(10, 36, 72, 32);// catalyst

		addBarType(0, 108, 0, 140, 0, 32, 48);
		addBarType(1, 36, 52, 72, 52, 28, 28);

	}

	public static void addSlotType(int id, int px, int py, int size)
	{
		slots.add(new SlotType(id, px, py, size));
	}

	public static void addBarType(int id, int px, int py, int bpx, int bpy, int sizex, int sizey)
	{
		bars.add(new BarType(id, px, py, bpx, bpy, sizex, sizey));
	}
}
