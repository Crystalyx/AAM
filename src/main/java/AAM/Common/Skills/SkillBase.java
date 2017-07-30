package AAM.Common.Skills;

import AAM.Utils.Soul;
import net.minecraft.util.ResourceLocation;

public class SkillBase
{
	public SkillBase(String name, int gainLevel, SkillAction act, ResourceLocation text)
	{
		this.name = name;
		this.gainLevel = gainLevel;
		this.texture = text;
		this.act = act;
	}

	public SkillBase(String name, int gainLevel, SkillAction act, ResourceLocation text, boolean clientn)
	{
		this.name = name;
		this.gainLevel = gainLevel;
		this.texture = text;
		this.act = act;
		this.clientN = clientn;
	}

	public String name;
	public int gainLevel;
	public SkillAction act;
	public ResourceLocation texture;
	public boolean clientN = false;

	public Skill toSkill(int level)
	{
		return new Skill(this.name, level, this.gainLevel, this.act, this.texture, this.clientN);
	}

	public Skill toSkill(int level, Soul affinity)
	{
		return new Skill(this.name, level, this.gainLevel, affinity, this.act, this.texture, this.clientN);
	}
}
