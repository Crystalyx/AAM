package AAM.API;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class StackList
{
	List<Object> stacks = new ArrayList<Object>();
	List<String> tips = new ArrayList<String>();

	public StackList()
	{
	}

	public StackList(List<Object> stacks, List<String> tips)
	{
		this.stacks = stacks;
		this.tips = tips;
	}

	public void add(ItemStack is, String tip)
	{
		this.stacks.add(is);
		this.tips.add(tip);
	}

	public void add(ItemStack is)
	{
		this.stacks.add(is);
		this.tips.add("");
	}

	public Object getStack(int i)
	{
		return this.stacks.get(i);
	}

	public String getTip(int i)
	{
		return this.tips.get(i);
	}

	public int size()
	{
		return this.stacks.size();
	}

	public boolean isItemsEqual(StackList stack)
	{
		return this.stacks.equals(stack.stacks) && this.tips.equals(stack.tips);
	}

	public void add(ItemStack[] itemStacks, String tip)
	{
		this.stacks.add(itemStacks);
		this.tips.add(tip);
	}

}
