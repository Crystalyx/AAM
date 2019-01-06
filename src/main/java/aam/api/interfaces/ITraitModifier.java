package aam.api.interfaces;

import aam.api.TraitModifier;
import aam.utils.PlayerDataHandler;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public interface ITraitModifier
{
	public void getModification(EntityPlayer p, PlayerDataHandler ph, List<TraitModifier> traitList);
}
