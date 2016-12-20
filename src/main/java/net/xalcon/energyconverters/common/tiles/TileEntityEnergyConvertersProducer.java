package net.xalcon.energyconverters.common.tiles;

import net.xalcon.energyconverters.common.energy.IEnergyBridgeOutputAccessProvider;

public abstract class TileEntityEnergyConvertersProducer extends TileEntityConverterBase implements IEnergyBridgeOutputAccessProvider
{
	public double retrieveEnergyFromBridge(double maxAmount, boolean simulate)
	{
		TileEntityEnergyBridge energyBridge = getEnergyBridge();
		if(energyBridge == null) return 0;
		return energyBridge.getEnergy(maxAmount, simulate);
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
