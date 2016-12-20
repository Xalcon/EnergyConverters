package net.xalcon.energyconverters.common.tiles;

import net.xalcon.energyconverters.common.energy.IEnergyBridgeInputAccessProvider;

public abstract class TileEntityEnergyConvertersConsumer extends TileEntityConverterBase implements IEnergyBridgeInputAccessProvider
{
	public double addEnergyToBridge(double amount, boolean simulate)
	{
		TileEntityEnergyBridge energyBridge = getEnergyBridge();
		if(energyBridge == null) return 0;
		return energyBridge.addEnergy(amount, simulate);
	}

	public double getBridgeEnergyStored()
	{
		TileEntityEnergyBridge energyBridge = getEnergyBridge();
		if(energyBridge == null) return 0;
		return energyBridge.getStoredEnergy();
	}

	public double getBridgeEnergyStoredMax()
	{
		TileEntityEnergyBridge energyBridge = getEnergyBridge();
		if(energyBridge == null) return 0;
		return energyBridge.getStoredEnergyMax();
	}
}
