package net.xalcon.energyconverters.common.energy;

public interface IEnergyBridgeOutputAccessProvider extends IEnergyBridge
{
	double retrieveEnergyFromBridge(double maxAmount, boolean simulate);
}
