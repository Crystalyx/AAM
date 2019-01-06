package aam.common.skills;

import aam.common.entity.SoulCharge;
import aam.utils.MathUtils;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.Wec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ModSkills
{
	public static List<SkillBase> skillbases = new ArrayList<>();
	public static List<Skill> skills = new ArrayList<>();

	// TODO
	public static void load()
	{
		SkillAction act = new SkillAction()
		{
			@Override
			public void act(World w, EntityPlayer p)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				if (ph.consumeSoul(60))
				{
					for (int i = 0; i < 60; i++)
					{
						double a = Math.toRadians(i * 6);
						SoulCharge s = new SoulCharge(p.worldObj, p);
						s.setLife(500);
						Wec3 look = MathUtils.getPosBy3DAngle(a, 0, 1);
						look.ptm(s);
						if (!p.worldObj.isRemote)
						{
							p.worldObj.spawnEntityInWorld(s);
						}
					}
				}
			}
		};
		registerSkillBase("Nova", 0, act, new ResourceLocation("aam:textures/misc/skills/nova.png"));
		act = new SkillAction()
		{
			@Override
			public void act(World w, EntityPlayer p)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				if (ph.consumeSoul(25))
				{
					SoulCharge s = new SoulCharge(p.worldObj, p);
					s.setLife(50);
					s.effs += "DF";
					Wec3 look = new Wec3(p.getLookVec());
					look.ptm(s);
					if (!p.worldObj.isRemote)
					{
						p.worldObj.spawnEntityInWorld(s);
					}
				}
			}
		};
		registerSkillBase("Fire", 0, act, new ResourceLocation("aam:textures/misc/skills/fire.png"));
		act = new SkillAction()
		{
			@Override
			public void act(World w, EntityPlayer p)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				if (ph.consumeSoul(20))
				{
					SoulCharge s = new SoulCharge(p.worldObj, p);
					s.setLife(50);
					s.effs += "DP";
					Wec3 look = new Wec3(p.getLookVec());
					look.ptm(s);
					if (!p.worldObj.isRemote)
					{
						p.worldObj.spawnEntityInWorld(s);
					}
				}
			}
		};
		registerSkillBase("Poison", 0, act, new ResourceLocation("aam:textures/misc/skills/poison.png"));
		act = new SkillAction()
		{
			@Override
			public void act(World w, EntityPlayer p)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				if (ph.consumeSoul(30))
				{
					SoulCharge s = new SoulCharge(p.worldObj, p);
					s.setLife(50);
					s.effs += "DK";
					Wec3 look = new Wec3(p.getLookVec());
					look.ptm(s);
					if (!p.worldObj.isRemote)
					{
						p.worldObj.spawnEntityInWorld(s);
					}
				}
			}
		};
		registerSkillBase("Force", 0, act, new ResourceLocation("aam:textures/misc/skills/force.png"));

		act = new SkillAction()
		{
			@Override
			public void act(World w, EntityPlayer p)
			{
				PlayerDataHandler ph = PlayerDataHandler.get(p);
				if (ph.consumeSoul(15))
				{
					Wec3 pp = new Wec3(p);
					Wec3 look = new Wec3(p.getLookVec()).mult(10);

					pp.add(look).ptp(p);
				}
			}
		};
		registerSkillBase("Blink", 0, act, new ResourceLocation("aam:textures/misc/skills/blink.png"), true);
	}

	public static void registerSkillBase(String name, int gainLevel, SkillAction act, ResourceLocation text)
	{
		skillbases.add(new SkillBase(name, gainLevel, act, text));
		skills.add(new SkillBase(name, gainLevel, act, text).toSkill(1));
	}

	public static void registerSkillBase(String name, int gainLevel, SkillAction act, ResourceLocation text, boolean clientn)
	{
		skillbases.add(new SkillBase(name, gainLevel, act, text, clientn));
		skills.add(new SkillBase(name, gainLevel, act, text).toSkill(1));
	}

	public static SkillBase getSkillByName(String name)
	{
		for (SkillBase sb : skillbases)
		{
			if (sb.name.equals(name))
			{
				return sb;
			}
		}
		return null;
	}
}
