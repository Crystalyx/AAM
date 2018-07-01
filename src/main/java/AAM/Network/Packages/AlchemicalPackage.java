package AAM.Network.Packages;

import java.io.IOException;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

public abstract class AlchemicalPackage<T extends AlchemicalPackage<T>> implements IMessage, IMessageHandler<T, IMessage>
{
	/**
	 * Some PacketBuffer methods throw IOException - default handling propagates
	 * the exception. if an IOException is expected but should not be fatal,
	 * handle it within this method.
	 */
	protected abstract void read(PacketBuffer buffer) throws IOException;

	/**
	 * Some PacketBuffer methods throw IOException - default handling propagates
	 * the exception. if an IOException is expected but should not be fatal,
	 * handle it within this method.
	 */
	protected abstract void write(PacketBuffer buffer) throws IOException;

	/**
	 * Called on whichever side the message is received; for bidirectional
	 * packets, be sure to check side If {@link #requiresMainThread()} returns
	 * true, this method is guaranteed to be called on the main Minecraft thread
	 * for this side.
	 */
	public abstract void process(EntityPlayer player, Side side);

	/**
	 * If message is sent to the wrong side, an exception will be thrown during
	 * handling. Default allows handling on both sides, i.e. a bidirectional
	 * packet
	 * 
	 * @return True if the message is allowed to be handled on the given side
	 */
	protected boolean isValidOnSide(Side side)
	{
		return true;
	}

	/**
	 * Whether this message requires the main thread to be processed (i.e. it
	 * requires that the world, player, and other objects are in a valid state).
	 */
	protected boolean requiresMainThread()
	{
		return true;
	}

	@Override
	public void fromBytes(ByteBuf buffer)
	{
		try
		{
			read(new PacketBuffer(buffer));
		}
		catch (IOException e)
		{
			throw Throwables.propagate(e);
		}
	}

	@Override
	public void toBytes(ByteBuf buffer)
	{
		try
		{
			write(new PacketBuffer(buffer));
		}
		catch (IOException e)
		{
			throw Throwables.propagate(e);
		}
	}

	@Override
	public final IMessage onMessage(T msg, MessageContext ctx)
	{
		if (!msg.isValidOnSide(ctx.side))
		{
			throw new RuntimeException("Invalid side " + ctx.side.name() + " for " + msg.getClass().getSimpleName());
		}
		else
		{
			msg.process(ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : ctx.getServerHandler().playerEntity, ctx.side);
		}
		return null;
	}

	/**
	 * Messages that can only be sent from the server to the client should use
	 * this class
	 */
	public static abstract class AlchemicalClientPackage<T extends AlchemicalPackage<T>> extends AlchemicalPackage<T>
	{
		@Override
		protected final boolean isValidOnSide(Side side)
		{
			return side.isClient();
		}
	}

	/**
	 * Messages that can only be sent from the client to the server should use
	 * this class
	 */
	public static abstract class AlchemicalServerPackage<T extends AlchemicalPackage<T>> extends AlchemicalPackage<T>
	{
		@Override
		protected final boolean isValidOnSide(Side side)
		{
			return side.isServer();
		}
	}
}
