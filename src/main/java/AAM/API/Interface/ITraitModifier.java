package aam.api.Interface;

import java.util.List;

import aam.api.TraitModifier;
import aam.utils.PlayerDataHandler;
import net.minecraft.entity.player.EntityPlayer;

public interface ITraitModifier
{
	public void getModification(EntityPlayer p, PlayerDataHandler ph, List<TraitModifier> traitList);
}
