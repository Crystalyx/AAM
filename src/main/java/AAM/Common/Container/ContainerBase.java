package AAM.Common.Container;

import java.util.ArrayList;
import java.util.List;

import AAM.Client.Gui.Base.GuiBackground;
import AAM.Client.Gui.Base.GuiBar;
import AAM.Client.Gui.Base.GuiOBJ;
import AAM.Client.Gui.Base.GuiPicture;
import AAM.Client.Gui.Base.GuiSlot;
import AAM.Client.Gui.Base.GuiText;
import AAM.Client.Gui.Base.GuiTooltip;
import AAM.Utils.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBase extends Container
{
	public List<GuiOBJ> objs = new ArrayList<GuiOBJ>();
	public int xSize;
	public int ySize;

	public IInventory tile;
	public InventoryPlayer p;

	public ContainerBase(InventoryPlayer p, IInventory tile)
	{
		this.tile = tile;
		this.p = p;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}

	public void add(GuiOBJ obj)
	{
		this.objs.add(obj);
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
		this.xSize = sizex;
		this.ySize = sizey;
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

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer p, int slotid)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotid);
		//
		// if (slot != null && slot.getHasStack())
		// {
		ItemStack itemstack1 = slot.getStack();
		itemstack = itemstack1.copy();
		//
		// if ((slotid < 0 || slotid > 2) && slotid != 3)
		// {
		// if (!this.getSlot(1).getHasStack() &&
		// this.getSlot(1).isItemValid(itemstack1))
		// {
		// if (!this.mergeItemStack(itemstack1, 3, 4, false))
		// {
		// return null;
		// }
		// }
		// else
		// if (slotid >= 4 && slotid < 31)
		// {
		// if (!this.mergeItemStack(itemstack1, 31, 40, false))
		// {
		// return null;
		// }
		// }
		// else
		// if (slotid >= 31 && slotid < 40)
		// {
		// if (!this.mergeItemStack(itemstack1, 4, 31, false))
		// {
		// return null;
		// }
		// }
		// else
		// if (!this.mergeItemStack(itemstack1, 4, 40, false))
		// {
		// return null;
		// }
		// }
		// else
		// {
		// if (!this.mergeItemStack(itemstack1, 4, 40, true))
		// {
		// return null;
		// }
		//
		// slot.onSlotChange(itemstack1, itemstack);
		// }
		//
		// if (itemstack1.stackSize == 0)
		// {
		// slot.putStack((ItemStack) null);
		// }
		// else
		// {
		// slot.onSlotChanged();
		// }
		//
		// if (itemstack1.stackSize == itemstack.stackSize)
		// {
		// return null;
		// }
		//
		// slot.onPickupFromSlot(p, itemstack1);
		// }

		return itemstack;
	}

	public void addPlayerSlots()
	{
		for (int i = 1; i <= 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				int id = i * 9 + j;
				this.addSlot(id, p, 11 + j * 19, 130 + i * 19);
			}
		}

		for (int j = 0; j < 9; j++)
		{
			int id = j;
			this.addSlot(id, p, 11 + j * 19, 126);
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
