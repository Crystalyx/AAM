package AAM.Network.Packages;

import AAM.Core.AAMCore;
import AAM.Network.Packages.AlchemicalPackage.AlchemicalClientPackage;
import AAM.Network.Packages.AlchemicalPackage.AlchemicalServerPackage;
import AAM.Utils.Logger;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AlchemicalDispatcher
{
	/**
	 * The SimpleNetworkWrapper instance is used both to register and send
	 * packets. Since I will be adding wrapper methods, this field is private,
	 * but you should make it public if you plan on using it directly.
	 */
	public static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(AAMCore.modid);
	public static int packetId = 0;

	/**
	 * Call this during pre-init or loading and register all of your packets
	 * (messages) here
	 */
	public static final void registerPackets()
	{
		registerMessage(PlayerSyncMessage.class);
		registerMessage(MultiInvSyncMessage.class);
		registerMessage(MessageExtendedReachAttack.class);

	}

	/**
	 * Registers an {@link AbstractMessage} to the appropriate side(s)
	 */
	public static final <T extends AlchemicalPackage<T> & IMessageHandler<T, IMessage>> void registerMessage(Class<T> clazz)
	{
		if (AlchemicalClientPackage.class.isAssignableFrom(clazz))
		{
			AlchemicalDispatcher.dispatcher.registerMessage(clazz, clazz, packetId++, Side.CLIENT);
		}
		else
			if (AlchemicalServerPackage.class.isAssignableFrom(clazz))
			{
				AlchemicalDispatcher.dispatcher.registerMessage(clazz, clazz, packetId++, Side.SERVER);
			}
			else
			{
				AlchemicalDispatcher.dispatcher.registerMessage(clazz, clazz, packetId, Side.CLIENT);
				AlchemicalDispatcher.dispatcher.registerMessage(clazz, clazz, packetId++, Side.SERVER);
			}
	}

	// ========================================================//
	// The following methods are the 'wrapper' methods; again,
	// this just makes sending a message slightly more compact
	// and is purely a matter of stylistic preference
	// ========================================================//

	/**
	 * Send this message to the specified player's client-side counterpart. See
	 * {@link SimpleNetworkWrapper#sendTo(IMessage, EntityPlayerMP)}
	 */
	public static final void sendToClient(IMessage message, EntityPlayer player)
	{
		Logger.info("S -> C");
		AlchemicalDispatcher.dispatcher.sendTo(message, (EntityPlayerMP) player);
	}

	/**
	 * Send this message to the specified player's client-side counterpart. See
	 * {@link SimpleNetworkWrapper#sendTo(IMessage, EntityPlayerMP)}
	 */
	public static final void sendToServer(IMessage message)
	{
		// Logger.info("C -> S");
		AlchemicalDispatcher.dispatcher.sendToServer(message);
	}
}
