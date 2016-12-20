package net.xalcon.energyconverters.common.energy;

public interface IEnergyBridgeOutputAccessProvider
{
	double retrieveEnergyFromBridge(double maxAmount, boolean simulate);
	double getBridgeEnergyStored();
	double getBridgeEnergyStoredMax();
}
