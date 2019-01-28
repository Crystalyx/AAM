package aam.utils;

import net.minecraft.util.EnumChatFormatting;

public enum EnumRarity
{
	Common(EnumChatFormatting.WHITE),
	Uncommon(EnumChatFormatting.YELLOW),
	Rare(EnumChatFormatting.DARK_BLUE),
	Epic(EnumChatFormatting.GOLD),
	Legendary(EnumChatFormatting.AQUA),
	Unique(EnumChatFormatting.DARK_PURPLE);

	public EnumChatFormatting formatter;
	public String name;

	private EnumRarity(EnumChatFormatting _formatter)
	{
		this.formatter = _formatter;
		name = name();
	}

	@Override
	public String toString()
	{
		return this.formatter.toString();
	}
}
