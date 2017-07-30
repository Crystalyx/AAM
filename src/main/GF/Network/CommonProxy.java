/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Network;

import GF.Client.Render.SOWPRender;
import GF.Common.Tile.SOWPTile;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
	
	public void registerRenderThings()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(SOWPTile.class, new SOWPRender());
	}

}
