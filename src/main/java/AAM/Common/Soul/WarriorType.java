package aam.common.soul;

import net.minecraft.util.ResourceLocation;

public enum WarriorType
{
	Tank("aam:textures/hud/tank.png"), Carry("aam:textures/hud/carry.png"), Caster("aam:textures/hud/caster.png"), NotSelected("aam:textures/hud/noone.png");

	public ResourceLocation icon;

	private WarriorType(String path)
	{
		icon = new ResourceLocation(path);
	}

	@Override
	public String toString()
	{
		return this.name() != "NotSelected" ? this.name() : "Not Selected";
	}
}
