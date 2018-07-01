package AAM.Common.Soul;

import java.util.List;

import DummyCore.Utils.EnumRarityColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class ArtifactTooltips
{
	public static void addToTooltip(Soul type, EntityPlayer p, int level, List l)
	{
		if (type.equals(Soul.Light))
		{
			l.add(EnumRarityColor.EPIC.getRarityColor() + "+" + (20 + 2 * (level - 1)) + "% Attack Damage vs Hostile");
			l.add(EnumRarityColor.EPIC.getRarityColor() + "+" + (15 + 2 * (level - 1)) + "% Attack Damage vs Undead");
		}
		if (type.equals(Soul.Normal))
		{
			l.add(EnumRarityColor.RARE.getRarityColor() + "+" + (35 + 2 * (level - 1)) + "% Attack Damage vs Non-Undead and Non-Arthopods");
		}
		if (type.equals(Soul.Blood))
		{
			l.add(EnumRarityColor.LEGENDARY.getRarityColor() + "+" + (25 + 2 * (level - 1)) + "% Attack Damage");
			l.add(EnumRarityColor.LEGENDARY.getRarityColor() + "+" + (25 + 2 * (level - 1)) + "% Attack Damage vs Players");
			l.add(EnumRarityColor.TURQUOISE.getRarityColor() + "+1 Soul per Attack");
		}
		if (type.equals(Soul.Lunar))
		{
			l.add(EnumChatFormatting.DARK_AQUA + "+" + (15 + 2 * (level - 1)) + "% Attack Damage");
			l.add(EnumRarityColor.EXCEPTIONAL.getRarityColor() + "+" + (25 + 4 * (level - 1) * p.worldObj.getCurrentMoonPhaseFactor()) + "% Attack Damage at current night");
		}
		if (type.equals(Soul.Plant))
		{
			l.add(EnumChatFormatting.DARK_AQUA + "+" + (15 + 2 * (level - 1)) + "% Attack Damage");
			l.add(EnumChatFormatting.GREEN + "+" + (15 + 2 * (level - 1)) + "% to Poison enemy");
		}
	}
}
