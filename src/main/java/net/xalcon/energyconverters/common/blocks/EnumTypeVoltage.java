package net.xalcon.energyconverters.common.blocks;

import net.minecraft.util.IStringSerializable;

public enum EnumTypeVoltage implements IStringSerializable
{
	LowVoltage(0, 1, "lv"),
	MediumVoltage(1, 2, "mv"),
	HighVoltage(2, 3, "hv"),
	ExtremeVoltage(3, 4, "ev"),
	IndustrialVoltage(4, 5, "iv"); // or is it infinite voltage? No idea D: its just IV in the wiki

	private int meta;
	private int tier;
	private String name;

	EnumTypeVoltage(int meta, int tier, String name)
	{
		this.meta = meta;
		this.tier = tier;
		this.name = name;
	}

	public int getMeta()
	{
		return meta;
	}

	public int getTier()
	{
		return tier;
	}

	@Override
	public String getName()
	{
		return name;
	}
}
