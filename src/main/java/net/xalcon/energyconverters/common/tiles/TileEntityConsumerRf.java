package net.xalcon.energyconverters.common.tiles;

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.util.EnumFacing;
import net.xalcon.energyconverters.EnergyConverters;

public class TileEntityConsumerRf extends TileEntityEnergyConvertersConsumer implements IEnergyReceiver
{
	@Override
	public boolean canConnectEnergy(EnumFacing from)
	{
		return true;
	}

	@Override
	public int getEnergyStored(EnumFacing from)
	{
		return Integer.MAX_VALUE;
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from)
	{
		return Integer.MAX_VALUE;
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate)
	{
		double ratio = EnergyConverters.getConfig().getRfConversion();
		return (int) (this.addEnergyToBridge(maxReceive * ratio, simulate) / ratio);
	}
}
