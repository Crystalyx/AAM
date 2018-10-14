package aam.common.items.alchemy;

import aam.common.blocks.building.ModBlocks;
import aam.common.blocks.circles.TransCircle;
import aam.common.items.ModItems;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.Circle;
import aam.common.transmutations.CircleUtils;
import aam.common.transmutations.ModCircles;
import aam.utils.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemChalk extends ItemSpade
{
	public ItemChalk()
	{
		super(EnumHelper.addToolMaterial("aamchalk", 0, 128, 0, 0, 0));
		this.setTextureName("aam:tools/chalk/chalk_blank");
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int px, int py, int pz, int side, float fx, float fy, float fz)
	{
		if (w.getBlock(px, py, pz) instanceof TransCircle)
		{
			return false;
		}
		int x = px + ForgeDirection.getOrientation(side).offsetX;
		int y = py + ForgeDirection.getOrientation(side).offsetY;
		int z = pz + ForgeDirection.getOrientation(side).offsetZ;
		if (y < 256)
		{
			if (InventoryUtils.contains(p.inventory, ModItems.ItemChalk))
			{
				InventoryUtils.getStack(p.inventory, ModItems.ItemChalk).damageItem(1, p);
				if (w.getBlock(px, py, pz) != ModBlocks.TransCircle && w.getBlock(px, py, pz) != ModBlocks.MechanicalBase)
				{
					if (w.getBlock(x, y, z) == Blocks.air && w.getBlock(x, y, z) != ModBlocks.TransCircle && w.getBlock(x, y, z) != ModBlocks.MechanicalBase)
					{
						w.setBlock(x, y, z, ModBlocks.TransCircle, side, 2);
						ModBlocks.TransCircle.onBlockActivated(w, x, y, z, p, side, fx, fy, fz);
						TETransCircle te = (TETransCircle) w.getTileEntity(x, y, z);

						CircleUtils.tryToAddCircle(te, new Circle(ModCircles.circle_big, 1.0, p.isSneaking()));
					}
				}
			}
		}
		return true;
	}
}
