package AAM.API.Interface;

import java.util.List;

import AAM.API.TraitModifier;
import AAM.Utils.PlayerDataHandler;
import net.minecraft.entity.player.EntityPlayer;

public interface ITraitModifier
{
	public void getModification(EntityPlayer p, PlayerDataHandler ph, List<TraitModifier> traitList);
}
