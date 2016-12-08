package net.xalcon.energyconverters.common.tiles;

public abstract class TileEntityEnergyConvertersProducer extends TileEntityConverterBase
{
	protected double retrieveEnergyFromBridge(double maxAmount, boolean simulate)
	{
		TileEntityEnergyBridge energyBridge = getEnergyBridge();
		if(energyBridge == null) return 0;
		return energyBridge.getEnergy(maxAmount, simulate);
	}

	protected double getAvailableEnergyFromBridge()
	{
		TileEntityEnergyBridge energyBridge = getEnergyBridge();
		if(energyBridge == null) return 0;
		return energyBridge.getStoredEnergy();
	}

	protected double getBridgeEnergyStoredMax()
	{
		TileEntityEnergyBridge energyBridge = getEnergyBridge();
		if(energyBridge == null) return 0;
		return energyBridge.getStoredEnergyMax();
	}
}
