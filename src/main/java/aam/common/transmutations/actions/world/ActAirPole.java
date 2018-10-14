package aam.common.transmutations.actions.world;

import java.util.ArrayList;

import aam.common.blocks.building.ModBlocks;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.EnergyProvider;
import aam.common.transmutations.TransAction;
import aam.utils.InventoryUtils;
import aam.utils.MathUtils;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.VectorWorld;
import aam.utils.vectors.Wec3;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ActAirPole extends TransAction
{

	public ActAirPole(int size)
	{
		super(size);
	}

	@Override
	public void act(World w, Wec3 tile, TETransCircle te, EntityPlayer p, double potency, ForgeDirection dir)
	{

	}

	@Override
	public boolean actTick(World w, Wec3 tile, TETransCircle te, EntityPlayer p, int time, double potency, ForgeDirection dir)
	{
		if (p == null)
		{
			return false;
		}

		PlayerDataHandler ph = PlayerDataHandler.get(p);

		int h = w.getBlockMetadata((int) tile.x, (int) tile.y - 1, (int) tile.z);
		int d = (int) potency / 2 + 2 - size * 2;
		int hg = (int) potency + 3 - size;

		VectorWorld pw = new VectorWorld(w);
		pw.translate(tile.sub(new Wec3(dir)));
		pw.alignYAxisToDirection(dir);

		int maxTime = (hg + 1) * (2 * d + 1) * (2 * d + 1);

		int stepCount = 2 * d + 1;
		tile.centralize();

		int i = Math.floorDiv(te.completeTimer, (2 * d + 1) * (2 * d + 1)) + 1;

		for (int o = 0; o < stepCount; o++)
		{
			int jk = Math.floorMod(te.completeTimer + o, (2 * d + 1) * (2 * d + 1));
			int j = Math.floorDiv(jk, 2 * d + 1) - d;
			int k = Math.floorMod(jk, 2 * d + 1) - d;

			if (!(j == 0 && i == 1 && k == 0))
			{
				Wec3 v = new Wec3(j, i, k);
				if (pw.getBlock(v) != Blocks.air && pw.getBlock(v) != ModBlocks.TransCircle)
				{
					Wec3 wd = tile.add(pw.getRealWec3(v));
					if (!ph.consumeSoul((int) pw.getBlock(v).getBlockHardness(w, wd.ix, wd.iy, wd.iz)))
					{
						return false;
					}
					Block b = pw.getBlock(v);
					ArrayList<ItemStack> drops = b.getDrops(w, te.xCoord, te.yCoord, te.zCoord, pw.getBlockMetadata(v), 0);

					if (te.is == null || !EnergyProvider.hasValue(te.is))
					{
						for (ItemStack is : drops)
						{
							InventoryUtils.tryToPlaceInInventoryOrDrop(pw, tile, is, dir);
						}
					}
					else
					{
						for (ItemStack is : drops)
						{
							if (EnergyProvider.hasValue(is))
							{
								te.energy += EnergyProvider.getValue(is);
							}
							else
							{
								InventoryUtils.tryToPlaceInInventoryOrDrop(pw, tile, is, dir);
							}
						}
						int count = (int) MathUtils.floorDiv(te.energy, EnergyProvider.getValue(te.is));
						te.energy = te.energy - count * EnergyProvider.getValue(te.is);
						int stackCount = Math.floorDiv(count, 64);
						int lessCount = Math.floorMod(count, 64);

						ItemStack dropStack = EnergyProvider.findIS(te.is.getItem(), te.is.getItemDamage()).copy();
						dropStack.stackSize = 64;
						for (int l = 0; l < stackCount; l++)
						{
							InventoryUtils.tryToPlaceInInventoryOrDrop(pw, tile, dropStack.copy(), dir);
						}
						dropStack.stackSize = lessCount;
						InventoryUtils.tryToPlaceInInventoryOrDrop(pw, tile, dropStack, dir);
					}

					pw.setBlock(v, Blocks.air);
				}
			}
		}
		te.completeTimer += stepCount - 1;

		return te.completeTimer < maxTime;
	}

}
