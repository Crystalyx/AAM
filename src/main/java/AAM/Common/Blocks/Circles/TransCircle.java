package AAM.Common.Blocks.Circles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import AAM.API.ICircleExtender;
import AAM.Common.Blocks.Building.ModBlocks;
import AAM.Common.Items.ModItems;
import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Tiles.TETransCircle.State;
import AAM.Common.Transmutations.Circle;
import AAM.Common.Transmutations.CirclePart;
import AAM.Common.Transmutations.EnergyProvider;
import AAM.Common.Transmutations.ModCircles;
import AAM.Utils.Logger;
import AAM.Utils.MiscUtils;
import AAM.Utils.Wec3;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TransCircle extends BlockContainer
{

	public TransCircle()
	{
		super(Material.circuits);
		this.setBlockTextureName("aam:clearblock");
		this.setResistance(100000000F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.08F, 1.0F);
		this.setLightLevel(1F);
	}

	public List<Item> blacklist = new ArrayList<Item>();

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z)
	{
		if (w.getBlock(x, y, z) == ModBlocks.TransCircle)
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
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return null;
	}

	@Override
	public float getExplosionResistance(Entity exp)
	{
		return Float.MAX_VALUE / 4;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TETransCircle();
	}

	public static final int range = 32;

	public static String outputCircle(List<Circle> l)
	{
		String out = "S: " + l.size() + " C:[";
		for (Circle c : l)
		{
			out += ModCircles.getCodeStr(c);
		}
		out += "]";
		return out;
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
		// outputCircle(te.circle);
		if (p.getCurrentEquippedItem() != null)
		{
			if (p.getCurrentEquippedItem().getItem() == ModItems.AlchPaper && p.getCurrentEquippedItem().getItemDamage() == 0)
			{
				if (!te.circle.isEmpty())
				{
					NBTTagCompound tag = new NBTTagCompound();
					int sz = 0;
					for (int i = 0; i < te.circle.size(); i++)
					{
						if (!te.circle.get(i).pt.extended)
						{
							sz += 1;
						}
					}
					if (sz > 0)
					{
						tag.setInteger("Size", sz);
						for (int i = 0; i < te.circle.size(); i++)
						{
							Circle c = te.circle.get(i);
							if (!c.pt.extended)
							{
								tag.setString("Part_" + i, ModCircles.getprts(c.pt));
								tag.setDouble("Scale_" + i, c.scale);
								tag.setBoolean("rev_" + i, c.rev);
							}
						}
						p.getCurrentEquippedItem().setTagCompound(tag);
						p.getCurrentEquippedItem().setItemDamage(1);
					}
				}
				return true;
			}
			if (MiscUtils.contains(p.inventory, ModItems.ItemChalk))
			{
				MiscUtils.getStack(p.inventory, ModItems.ItemChalk).damageItem(1, p);

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
				if (p.getCurrentEquippedItem().getItem() == ModItems.AlchPaper)
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
								Logger.info("TL" + tl);
								Logger.info("LL" + ll);
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
			if (p.getCurrentEquippedItem().getItem() instanceof ICircleExtender)
			{
				((ICircleExtender) p.getCurrentEquippedItem().getItem()).onExtended(p.getCurrentEquippedItem(), p, w, new Wec3(x, y, z));
				te.extend();
			}
			if (p.getCurrentEquippedItem().getItem() == ModItems.LinkObol)
			{
				if (!te.isLink)
				{
					te.isLink = true;
					MiscUtils.decrPlayerStack(p, 1);
				}
				else
				{
					te.isLink = false;
					MiscUtils.addItemStack(p, new ItemStack(ModItems.LinkObol));
				}
				return true;
			}
			if (te.is == null)
			{
				if (p.getCurrentEquippedItem() != null)
				{
					if (EnergyProvider.hasValue(p.getCurrentEquippedItem()) && !this.blacklist.contains(p.getCurrentEquippedItem().getItem()))
					{
						te.is = p.inventory.decrStackSize(p.inventory.currentItem, 1);
					}
				}
			}
		}
		else
		{
			if (!p.isSneaking())
			{
				// TODO activation stone
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
				if (te.state.equals(State.complete))
				{
					te.state = State.idle;
				}
			}
			else
			{
				if (te.is != null)
				{
					EntityItem ei = new EntityItem(w, x, y + 0.5, z, te.is);
					ei.onCollideWithPlayer(p);
					te.is = null;
				}
				else
				{
					te.clearEnergy();
				}
			}
		}
		return true;
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

	@Override
	public boolean isNormalCube()
	{
		return false;
	}

}
