package aam.common.skills;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class SkillAction
{
	public abstract void act(World w, EntityPlayer p);
}
