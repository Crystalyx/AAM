package aam.api;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class StackList
{
	List<Object> stacks = new ArrayList<>();
	List<String> tips = new ArrayList<>();

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
		stacks.add(is);
		tips.add(tip);
	}

	public void add(ItemStack is)
	{
		stacks.add(is);
		tips.add("");
	}

	public Object getStack(int i)
	{
		return stacks.get(i);
	}

	public String getTip(int i)
	{
		return tips.get(i);
	}

	public int size()
	{
		return stacks.size();
	}

	public boolean isItemsEqual(StackList stack)
	{
		return stacks.equals(stack.stacks) && tips.equals(stack.tips);
	}

	public void add(ItemStack[] itemStacks, String tip)
	{
		stacks.add(itemStacks);
		tips.add(tip);
	}

}
