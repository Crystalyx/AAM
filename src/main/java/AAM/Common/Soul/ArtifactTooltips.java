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
			l.add(EnumRarityColor.EPIC.getRarityColor() + "+" + (20) + "% Attack Damage vs Hostile");
			l.add(EnumRarityColor.EPIC.getRarityColor() + "+" + (15) + "% Attack Damage vs Undead");
		}
		if (type.equals(Soul.Normal))
		{
			l.add(EnumRarityColor.RARE.getRarityColor() + "+" + (10) + "% Attack Damage");
			l.add(EnumRarityColor.RARE.getRarityColor() + "+" + (25) + "% Attack Damage vs Non-Undead and Non-Arthopods");
		}
		if (type.equals(Soul.Blood))
		{
			l.add(EnumRarityColor.LEGENDARY.getRarityColor() + "+" + (15) + "% Attack Damage");
			l.add(EnumRarityColor.LEGENDARY.getRarityColor() + "+" + (15) + "% Attack Damage vs Players");
			l.add(EnumRarityColor.TURQUOISE.getRarityColor() + "+4 Soul per Attack");
		}
		if (type.equals(Soul.Lunar))
		{
			l.add(EnumRarityColor.EXCEPTIONAL.getRarityColor() + "+" + ((p.worldObj.getCurrentMoonPhaseFactor()) * 50) + "% Attack Damage at current night");
		}
		if (type.equals(Soul.Plant))
		{
			l.add(EnumChatFormatting.DARK_AQUA + "+" + (15) + "% Attack Damage");
			l.add(EnumChatFormatting.GREEN + "+" + (Math.min(15 + 2 * (level - 1), 100)) + "% to Poison enemy");
		}
		if (type.equals(Soul.Watery))
		{
			l.add(EnumChatFormatting.AQUA + "+" + (15) + "% Attack Damage when underwater");
			l.add(EnumChatFormatting.BLUE + "+" + (15) + "% movement speed under water");
		}
	}
}
