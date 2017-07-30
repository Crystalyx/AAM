package AAM.Common.Blocks.Circles;

import AAM.Common.Items.ModItems;
import AAM.Common.Tiles.MechanicalBaseTE;
import AAM.Common.Tiles.TETransCircle;
import AAM.Common.Transmutations.Circle;
import AAM.Common.Transmutations.CirclePart;
import AAM.Common.Transmutations.ModCircles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MechanicalBase extends BlockContainer
{
	public MechanicalBase(Material mat)
	{
		super(mat);
		this.setBlockTextureName("aam:circles/mechanical_base");
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float fx, float fy, float fz)
	{
		MechanicalBaseTE te = (MechanicalBaseTE) w.getTileEntity(x, y, z);

		if (p.getCurrentEquippedItem() != null)
		{
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
								if (!te.circle.contains(c))
									te.circle.add(c);
							}
						}
					}
				}
			}
		}
		else
		{
			if (!p.isSneaking())
				te.createCircleAbove();
			else
				te.circle.clear();
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new MechanicalBaseTE();
	}
}
