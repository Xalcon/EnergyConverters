package net.xalcon.energyconverters.common.tiles;

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.xalcon.energyconverters.common.energy.ForgeEnergyConsumptionHandler;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.power.IEnergyInterfaceTile;

import javax.annotation.Nullable;

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
		return (int) this.addEnergyToBridge(maxReceive, simulate);
	}
}
