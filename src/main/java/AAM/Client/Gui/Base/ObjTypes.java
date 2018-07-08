package AAM.Client.Gui.Base;

import java.util.ArrayList;
import java.util.List;

public class ObjTypes
{
	public static List<SlotType> slots = new ArrayList<SlotType>();
	public static List<BarType> bars = new ArrayList<BarType>();

	public static void load()
	{
		addSlotType(0, 0, 36, 32);
		addSlotType(1, 0, 72, 32);
		addSlotType(2, 0, 108, 32);
		addSlotType(3, 0, 144, 32);
		addSlotType(4, 0, 180, 32);
		addSlotType(5, 0, 216, 32);
		addSlotType(6, 36, 216, 32);
		addSlotType(7, 36, 180, 32);
		addSlotType(8, 36, 144, 32);

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
