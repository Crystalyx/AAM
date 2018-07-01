
package AAM.Network.Packages;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class SkillPackage extends AlchemicalPackage
{
	private NBTTagCompound data;

	public SkillPackage()
	{
	}

	public SkillPackage(EntityPlayer player, int skill)
	{
		data = new NBTTagCompound();
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
	}

}
