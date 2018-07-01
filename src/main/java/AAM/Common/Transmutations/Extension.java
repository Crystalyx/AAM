package AAM.Common.Transmutations;

import java.util.List;

import AAM.Common.Tiles.TETransCircle;
import AAM.Utils.VectorWorld;
import AAM.Utils.Wec3;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Extension
{
	public String name;
	public CirclePart part;
	public String[][] circles;
	public ExtAction action = new ExtAction();
	public double size = 5;
	public ItemList items;

	public Extension(String name, String[][] circles, CirclePart part, ItemList items)
	{
		this.name = name;
		this.circles = circles;
		this.part = part;
		this.items = items;
	}

	public Extension(String name, String[][] circles, CirclePart part, ItemList items, double size)
	{
		this.name = name;
		this.circles = circles;
		this.part = part;
		this.size = size;
		this.items = items;
	}

	public void work(World w, Wec3 pos)
	{
		VectorWorld pw = new VectorWorld(w);
		pw.translate(pos);
		if (this.action.check(pw, this.circles, this.items))
		{
			this.action.print(pw, this.part, this.items);
			TETransCircle te = (TETransCircle) pw.getTile(new Wec3());
			te.esize = this.size;
			te.extended = true;
		}
	}
}
