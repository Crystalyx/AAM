package aam.common.skills;

import aam.common.soul.Soul;
import net.minecraft.util.ResourceLocation;

public class SkillBase
{
	public SkillBase(String name, int gainLevel, SkillAction act, ResourceLocation text)
	{
		this.name = name;
		this.gainLevel = gainLevel;
		texture = text;
		this.act = act;
	}

	public SkillBase(String name, int gainLevel, SkillAction act, ResourceLocation text, boolean clientn)
	{
		this.name = name;
		this.gainLevel = gainLevel;
		texture = text;
		this.act = act;
		clientN = clientn;
	}

	public String name;
	public int gainLevel;
	public SkillAction act;
	public ResourceLocation texture;
	public boolean clientN = false;

	public Skill toSkill(int level)
	{
		return new Skill(name, level, gainLevel, act, texture, clientN);
	}

	public Skill toSkill(int level, Soul affinity)
	{
		return new Skill(name, level, gainLevel, affinity, act, texture, clientN);
	}
}
