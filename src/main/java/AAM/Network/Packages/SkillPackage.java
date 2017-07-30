
package AAM.Network.Packages;

import java.io.IOException;

import AAM.Utils.PlayerDataHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class SkillPackage extends AlchemicalPackage
{
	// Previously, we've been writing each field in our properties one at a
	// time,
	// but that is really annoying, and we've already done it in the save and
	// load
	// NBT methods anyway, so here's a slick way to efficiently send all of your
	// extended data, and no matter how much you add or remove, you'll never
	// have
	// to change the packet / synchronization of your data.

	// this will store our ExtendedPlayer data, allowing us to easily read and
	// write
	private NBTTagCompound data;

	// The basic, no-argument constructor MUST be included to use the new
	// automated handling
	public SkillPackage()
	{
	}

	// We need to initialize our data, so provide a suitable constructor:
	public SkillPackage(EntityPlayer player, int skill)
	{
		// create a new tag compound
		data = new NBTTagCompound();
		// and save our player's data into it
		data.setInteger("Skill", skill);
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException
	{
		data = buffer.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException
	{
		buffer.writeNBTTagCompoundToBuffer(data);
	}

	@Override
	public void process(EntityPlayer player, Side side)
	{
		// now we can just load the NBTTagCompound data directly; one and done,
		// folks
//		int skill = data.getInteger("Skill");
//		PlayerDataHandler ph = PlayerDataHandler.get(player);
//		ph.skills.get(skill).act.act(player.worldObj, player);
//		ph.soulTag.setInteger("Skill", skill);
//		for (int i = 0; i < player.inventory.getSizeInventory(); i++)
//		{
//			if (player.inventory.getStackInSlot(i) != null)
//			{
//				ItemStack is = player.inventory.getStackInSlot(i);
//				if (is.hasTagCompound() && is.getTagCompound().getString("Owner") == player.getGameProfile().getName())
//				{
//					is.getTagCompound().setInteger("Skill", skill);
//				}
//			}
//		}
//		PlayerSyncMessage psm = new PlayerSyncMessage(player);
//		AlchemicalDispatcher.sendTo(psm, player);
	}

}
