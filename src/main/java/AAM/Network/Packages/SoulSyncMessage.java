package AAM.Network.Packages;

import java.io.IOException;

import AAM.Utils.Logger;
import AAM.Utils.PlayerDataHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class SoulSyncMessage extends AlchemicalPackage
{
	private NBTTagCompound data;

	public SoulSyncMessage()
	{
	}

	public SoulSyncMessage(EntityPlayer player)
	{
		data = new NBTTagCompound();
		PlayerDataHandler.get(player).saveSoulNBTData(data);
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
		Logger.info("Synchronizing Player Data data on CLIENT");
		PlayerDataHandler.get(player).loadSoulNBTData(data);
	}

}
