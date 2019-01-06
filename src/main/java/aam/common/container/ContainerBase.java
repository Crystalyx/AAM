package aam.common.container;

import aam.client.gui.base.*;
import aam.utils.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ContainerBase extends Container
{
	public List<GuiOBJ> objs = new ArrayList<>();
	public int xSize;
	public int ySize;

	public IInventory tile;
	public InventoryPlayer p;

	public ContainerBase(InventoryPlayer p, IInventory tile)
	{
		this.tile = tile;
		this.p = p;
		tile.openInventory();
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}

	@Override
	public void onContainerClosed(EntityPlayer p)
	{
		tile.closeInventory();
		super.onContainerClosed(p);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
	{
		ItemStack newItemStack = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemStack = slot.getStack();
			newItemStack = itemStack.copy();

			if (slotIndex < p.getSizeInventory())
			{
				if (!this.mergeItemStack(itemStack, p.getSizeInventory(), inventorySlots.size(), false))
				{
					return null;
				}
			}
			else
				if (!this.mergeItemStack(itemStack, 0, p.getSizeInventory(), false))
				{
					return null;
				}

			if (itemStack.stackSize == 0)
			{
				slot.putStack(null);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return newItemStack;
	}

	// Credits to Pahimar for his awesome ItemStack merging code!
	@Override
	public boolean mergeItemStack(ItemStack itemStack, int slotMin, int slotMax, boolean ascending)
	{
		boolean slotFound = false;
		int currentSlotIndex = ascending ? slotMax - 1 : slotMin;
		Slot slot;
		ItemStack stackInSlot;
		if (itemStack.isStackable())
		{
			while (itemStack.stackSize > 0 && (!ascending && currentSlotIndex < slotMax || ascending && currentSlotIndex >= slotMin))
			{
				slot = (Slot) inventorySlots.get(currentSlotIndex);
				stackInSlot = slot.getStack();
				if (slot.isItemValid(itemStack) && equalsIgnoreStackSize(itemStack, stackInSlot))
				{
					int combinedStackSize = stackInSlot.stackSize + itemStack.stackSize;
					int slotStackSizeLimit = Math.min(stackInSlot.getMaxStackSize(), slot.getSlotStackLimit());
					if (combinedStackSize <= slotStackSizeLimit)
					{
						itemStack.stackSize = 0;
						stackInSlot.stackSize = combinedStackSize;
						slot.onSlotChanged();
						slotFound = true;
					}
					else
						if (stackInSlot.stackSize < slotStackSizeLimit)
						{
							itemStack.stackSize -= slotStackSizeLimit - stackInSlot.stackSize;
							stackInSlot.stackSize = slotStackSizeLimit;
							slot.onSlotChanged();
							slotFound = true;
						}
				}
				currentSlotIndex += ascending ? -1 : 1;
			}
		}

		if (itemStack.stackSize > 0)
		{
			currentSlotIndex = ascending ? slotMax - 1 : slotMin;
			while (!ascending && currentSlotIndex < slotMax || ascending && currentSlotIndex >= slotMin)
			{
				slot = (Slot) inventorySlots.get(currentSlotIndex);
				stackInSlot = slot.getStack();
				if (slot.isItemValid(itemStack) && stackInSlot == null)
				{
					slot.putStack(cloneItemStack(itemStack, Math.min(itemStack.stackSize, slot.getSlotStackLimit())));
					slot.onSlotChanged();
					if (slot.getStack() != null)
					{
						itemStack.stackSize -= slot.getStack().stackSize;
						slotFound = true;
					}
					break;
				}
				currentSlotIndex += ascending ? -1 : 1;
			}
		}
		return slotFound;
	}

	public static ItemStack cloneItemStack(ItemStack itemStack, int stackSize)
	{
		ItemStack clonedItemStack = itemStack.copy();
		clonedItemStack.stackSize = stackSize;
		return clonedItemStack;
	}

	public static boolean equalsIgnoreStackSize(ItemStack itemStack1, ItemStack itemStack2)
	{
		if (itemStack1 != null && itemStack2 != null)
		{
			if (itemStack1.getItem() == itemStack2.getItem())
			{
				if (itemStack1.getItemDamage() == itemStack2.getItemDamage())
				{
					if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound())
					{
						if (ItemStack.areItemStackTagsEqual(itemStack1, itemStack2))
						{
							return true;
						}
					}
					else
						if (!itemStack1.hasTagCompound() && !itemStack2.hasTagCompound())
						{
							return true;
						}
				}
			}
		}

		return false;
	}

	public void add(GuiOBJ obj)
	{
		objs.add(obj);
	}

	// =============PICTURE==============
	public void addPicture(int x, int y, int sizex, int sizey, int type, Color c)
	{
		this.add(new GuiPicture(x, y, sizex, sizey, type, c));
	}

	public void addHiddenPicture(int x, int y, int sizex, int sizey, int type, Color c, boolean hide)
	{
		this.add(new GuiPicture(x, y, sizex, sizey, type, c).setHidden(hide));
	}

	// =============BAR==============
	public void addBar(int x, int y, int sizex, int sizey, int type)
	{
		this.add(new GuiBar(x, y, sizex, sizey, type));
	}

	public void addHiddenBar(int x, int y, int sizex, int sizey, int type, boolean hide)
	{
		this.add(new GuiBar(x, y, sizex, sizey, type).setHidden(hide));
	}

	public void addBar(int x, int y, int sizex, int sizey)
	{
		this.add(new GuiBar(x, y, sizex, sizey, -1));
	}

	public void addHiddenBar(int x, int y, int sizex, int sizey, boolean hide)
	{
		this.add(new GuiBar(x, y, sizex, sizey, -1).setHidden(hide));
	}

	public void addBar(int x, int y, int sizex, int sizey, Color c)
	{
		this.add(new GuiBar(x, y, sizex, sizey, -1, c));
	}

	public void addHiddenBar(int x, int y, int sizex, int sizey, Color c, boolean hide)
	{
		this.add(new GuiBar(x, y, sizex, sizey, -1, c).setHidden(hide));
	}

	public void addBar(int x, int y, int sizex, int sizey, int r, int g, int b)
	{
		this.add(new GuiBar(x, y, sizex, sizey, -1, new Color(r, g, b)));
	}

	public void addHiddenBar(int x, int y, int sizex, int sizey, int r, int g, int b, boolean hide)
	{
		this.add(new GuiBar(x, y, sizex, sizey, -1, new Color(r, g, b)).setHidden(hide));
	}

	// =============BACKGROUND==============
	public void addBackground(int sizex, int sizey)
	{
		xSize = sizex;
		ySize = sizey;
		this.add(new GuiBackground(sizex, sizey));
	}

	// =============SLOT==============
	public void addSlot(int id, IInventory inv, int x, int y, int size)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, -1));
		this.addSlotToContainer(new Slot(inv, id, x, y));
	}

	public void addHiddenSlot(int id, IInventory inv, int x, int y, int size, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, -1).setHidden(hidden));
		this.addSlotToContainer(new Slot(inv, id, x, y));
	}

	public void addSlot(int id, IInventory inv, int x, int y)
	{
		this.add(new GuiSlot(x - 2, y - 2, -1));
		this.addSlotToContainer(new Slot(inv, id, x, y));
	}

	public void addHiddenSlot(int id, IInventory inv, int x, int y, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, -1).setHidden(hidden));
		this.addSlotToContainer(new Slot(inv, id, x, y));
	}

	public void addSlot(int id, IInventory inv, int x, int y, int size, int type)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, type));
		this.addSlotToContainer(new Slot(inv, id, x, y));
	}

	public void addHiddenSlot(int id, IInventory inv, int x, int y, int size, int type, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, type));
		this.addSlotToContainer(new Slot(inv, id, x, y));
	}

	public void addSlot(int id, int type, IInventory inv, int x, int y)
	{
		this.add(new GuiSlot(x - 2, y - 2, 18, type));
		this.addSlotToContainer(new Slot(inv, id, x, y));
	}

	public void addHiddenSlot(int id, int type, IInventory inv, int x, int y, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, 18, type));
		this.addSlotToContainer(new Slot(inv, id, x, y));
	}

	// =============TEXT==============
	public void addText(String text, int x, int y, int sizex, int sizey)
	{
		this.add(new GuiText(text, x, y, sizex, sizey));
	}

	public void addText(String text, int x, int y, int sizex, int sizey, boolean back)
	{
		this.add(new GuiText(text, x, y, sizex, sizey, back));
	}

	public void addHiddenText(String text, int x, int y, int sizex, int sizey, boolean hide)
	{
		this.add(new GuiText(text, x, y, sizex, sizey).setHidden(hide));
	}

	public void addHiddenText(String text, int x, int y, int sizex, int sizey, boolean back, boolean hide)
	{
		this.add(new GuiText(text, x, y, sizex, sizey, back).setHidden(hide));
	}

	// =============TOOLTIP==============
	public void addTooltip(int x, int y, int sizex, int sizey, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, text));
	}

	public void addTooltip(int x, int y, int sizex, int sizey, boolean back, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, back, text));
	}

	public void addHiddenTooltip(int x, int y, int sizex, int sizey, boolean hide, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, text).setHidden(hide));
	}

	public void addHiddenTooltip(int x, int y, int sizex, int sizey, boolean back, boolean hide, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, back, text).setHidden(hide));
	}

	public void addPlayerSlots()
	{
		for (int i = 1; i <= 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				int id = i * 9 + j;
				this.addSlot(id, p, 32 + j * 18, 100 + i * 18);
			}
		}

		for (int j = 0; j < 9; j++)
		{
			int id = j;
			this.addSlot(id, p, 32 + j * 18, 104 + 18 * 4);
		}
	}

	public void addPlayerSlots(int x, int y)
	{
		for (int i = 1; i <= 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				int id = i * 9 + j;
				this.addSlot(id, p, 11 + j * 19 + x, 130 + i * 19 + y);
			}
		}

		for (int j = 0; j < 9; j++)
		{
			int id = j;
			this.addSlot(id, p, 11 + j * 19 + x, 126 + y);
		}
	}

}
