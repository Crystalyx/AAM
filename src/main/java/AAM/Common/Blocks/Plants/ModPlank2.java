package aam.common.blocks.plants;

import aam.common.blocks.building.Storage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * 
 * @author user Used to create cut type of plank
 */
public class ModPlank2 extends Storage
{
	public String base;

	public ModPlank2(String base, String[] textures)
	{
		super(Material.wood);
		this.textures = textures;
		this.base = base;
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeWood);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		icons = new IIcon[textures.length];
		for (int j = 0; j < icons.length; j++)
		{
			icons[j] = ir.registerIcon("aam:" + base + textures[j]);
		}
	}
}
