package AAM.Common.Blocks.Circles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import AAM.Common.Items.ModItems;
import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Tiles.TETransCircle.State;
import AAM.Common.Transmutations.Circle;
import AAM.Common.Transmutations.CirclePart;
import AAM.Common.Transmutations.ModCircles;
import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
import AAM.Utils.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TransCircle extends BlockContainer
{

	public TransCircle()
	{
		super(Material.circuits);
		this.setBlockTextureName("aam:clearblock");
		this.setBlockName("aamtranscircle");
		this.setResistance(100000000F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.08F, 1.0F);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z)
	{
		int meta = w.getBlockMetadata(x, y, z);
		if (meta == 1)
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.08F, 1.0F);
		if (meta == 0)
			this.setBlockBounds(0.0F, 1.0F - 0.08F, 0.0F, 1.0F, 1, 1.0F);
		if (meta == 3)
			this.setBlockBounds(0.0F, 0, 0.0F, 1.0F, 1, 0.08F);
		if (meta == 2)
			this.setBlockBounds(0.0F, 0, 1.0F - 0.08F, 1.0F, 1, 1.0F);
		if (meta == 5)
			this.setBlockBounds(0.0F, 0, 0.0F, 0.08F, 1, 1.0F);
		if (meta == 4)
			this.setBlockBounds(1.0F - 0.08F, 0, 0.0f, 1.0F, 1, 1.0F);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return null;
	}

	@Override
	public float getExplosionResistance(Entity p_149638_1_)
	{
		return Float.MAX_VALUE / 4;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TETransCircle();
	}

	public static final int range = 32;

	public void outputCircle(List<Circle> l)
	{
		Logger.info(l.size());
		String out = "[";
		for (Circle c : l)
		{
			out += ModCircles.getCodeStr(c);
		}
		Logger.info(out + "]");
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz)
	{
		TETransCircle te = (TETransCircle) w.getTileEntity(x, y, z);
		te.check();
		te.alchemist = p;
		outputCircle(te.circle);
		if (p.getCurrentEquippedItem() != null && MiscUtils.contains(p.inventory, ModItems.ItemChalk))
		{
			MiscUtils.getStack(p.inventory, ModItems.ItemChalk).damageItem(1, p);
			;

			if (p.getCurrentEquippedItem().getItem() == ModItems.ChalkPattern)
			{
				boolean cont = false;
				double size = 1.0;
				CirclePart pt = ModCircles.parts.get(p.getCurrentEquippedItem().getItemDamage());
				Circle pr = new Circle(pt, 1.0, p.isSneaking());
				for (Circle par : te.circle)
				{
					cont = (par.pt.equals(pr.pt)) && (par.rev == pr.rev);
					if (cont)
					{
						pr = par;
						break;
					}
				}
				if (!cont)
				{
					te.circle.add(pr);
				}
				else
				{
					pr.scale += 1.0;
				}
				return true;
			}
			if (p.getCurrentEquippedItem().getItem() == ModItems.TeleportationCrystal)
			{
				if (!te.isLink)
				{
					te.isLink = true;
					MiscUtils.decrPlayerStack(p, 1);
				}
				else
				{
					te.isLink = false;
					MiscUtils.addItemStack(p, new ItemStack(ModItems.TeleportationCrystal));
				}
				return true;
			}
			if (p.getCurrentEquippedItem().getItem() == ModItems.AlchPaper)
			{
				if (p.getCurrentEquippedItem().getItemDamage() == 0)
				{
					if (!te.circle.isEmpty())
					{
						NBTTagCompound tag = new NBTTagCompound();
						tag.setInteger("Size", te.circle.size());
						for (int i = 0; i < te.circle.size(); i++)
						{
							Circle c = te.circle.get(i);
							tag.setString("Part_" + i, ModCircles.getprts(c.pt));
							tag.setDouble("Scale_" + i, c.scale);
							tag.setBoolean("rev_" + i, c.rev);
						}
						p.getCurrentEquippedItem().setTagCompound(tag);
						p.getCurrentEquippedItem().setItemDamage(1);
					}
				}
				else
				{
					if (p.getCurrentEquippedItem().getItemDamage() == 1)
					{
						NBTTagCompound tag = p.getCurrentEquippedItem().getTagCompound();

						int count = tag.getInteger("Size");
						if (count > 0)
						{
							List<Circle> l = new ArrayList<Circle>();
							for (int i = 0; i < count; i++)
							{
								String code = tag.getString("Part_" + i);
								boolean rev = tag.getBoolean("rev_" + i);
								if (p.isSneaking())
								{
									rev = !rev;
								}
								double scale = tag.getDouble("Scale_" + i);
								Circle c = new Circle(ModCircles.getprtsr(code), scale, rev);
								if (!l.contains(c))
									l.add(c);
							}
							if (te.circle.size() == l.size())
							{
								List<String> tl = new ArrayList<String>();
								List<String> ll = new ArrayList<String>();

								for (int i = 0; i < count; i++)
								{
									String sst = ModCircles.getCodeStr(te.circle.get(i));
									tl.add(sst.substring(0, 2) + sst.substring(sst.length() - 1, sst.length()));

									String ssl = ModCircles.getCodeStr(l.get(i));
									ll.add(sst.substring(0, 2) + sst.substring(sst.length() - 1, sst.length()));
								}
								Logger.info("TL"+tl);
								Logger.info("LL"+ll);
								if (MiscUtils.containsOnly(tl, ll))
								{
									for (int i = 0; i < count; i++)
									{
										te.circle.get(i).scale += 1;
									}
								}
							}
							else
							{
								te.circle.clear();
								te.circle.addAll(l);
							}
						}
					}
				}
			}
		}
		else
		{
			if (te.state.equals(State.idle))
			{
				te.state = State.active;
				if (te.isLink)
				{
					for (int i = 1; i < range; i++)
					{
						if (w.getTileEntity(x + i, y, z) instanceof TETransCircle)
						{
							if (((TETransCircle) w.getTileEntity(x + i, y, z)).state.equals(State.idle))
								((TransCircle) w.getBlock(x + i, y, z)).onBlockActivated(w, x + i, y, z, p, side, px, py, pz);
							break;
						}
					}
					for (int i = 1; i < range; i++)
					{
						if (w.getTileEntity(x - i, y, z) instanceof TETransCircle)
						{
							if (((TETransCircle) w.getTileEntity(x - i, y, z)).state.equals(State.idle))
								((TransCircle) w.getBlock(x - i, y, z)).onBlockActivated(w, x - i, y, z, p, side, px, py, pz);
							break;
						}
					}

					for (int i = 1; i < range; i++)
					{
						if (w.getTileEntity(x, y + i, z) instanceof TETransCircle)
						{
							if (((TETransCircle) w.getTileEntity(x, y + i, z)).state.equals(State.idle))
								((TransCircle) w.getBlock(x, y + i, z)).onBlockActivated(w, x, y + i, z, p, side, px, py, pz);
							break;
						}
					}
					for (int i = 1; i < range; i++)
					{
						if (w.getTileEntity(x, y - i, z) instanceof TETransCircle)
						{
							if (((TETransCircle) w.getTileEntity(x, y - i, z)).state.equals(State.idle))
								((TransCircle) w.getBlock(x, y - i, z)).onBlockActivated(w, x, y - i, z, p, side, px, py, pz);
							break;
						}
					}

					for (int i = 1; i < range; i++)
					{
						if (w.getTileEntity(x, y, z + i) instanceof TETransCircle)
						{
							if (((TETransCircle) w.getTileEntity(x, y, z + i)).state.equals(State.idle))
								((TransCircle) w.getBlock(x, y, z + i)).onBlockActivated(w, x, y, z + i, p, side, px, py, pz);
							break;
						}
					}
					for (int i = 1; i < range; i++)
					{
						if (w.getTileEntity(x, y, z - i) instanceof TETransCircle)
						{
							if (((TETransCircle) w.getTileEntity(x, y, z - i)).state.equals(State.idle))
								((TransCircle) w.getBlock(x, y, z - i)).onBlockActivated(w, x, y, z - i, p, side, px, py, pz);
							break;
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public void onNeighborBlockChange(World w, int x, int y, int z, Block b)
	{
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean isNormalCube()
	{
		return false;
	}

}
