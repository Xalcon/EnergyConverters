package net.xalcon.energyconverters.common.tiles;

public abstract class TileEntityEnergyConvertersConsumer extends TileEntityConverterBase
{
	protected double addEnergyToBridge(double amount, boolean simulate)
	{
		TileEntityEnergyBridge energyBridge = getEnergyBridge();
		if(energyBridge == null) return 0;
		return energyBridge.addEnergy(amount, simulate);
	}
}
