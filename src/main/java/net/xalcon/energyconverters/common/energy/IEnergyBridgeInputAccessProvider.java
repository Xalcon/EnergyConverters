package net.xalcon.energyconverters.common.energy;

public interface IEnergyBridgeInputAccessProvider extends IEnergyBridge
{
	double addEnergyToBridge(double amount, boolean simulate);
}
