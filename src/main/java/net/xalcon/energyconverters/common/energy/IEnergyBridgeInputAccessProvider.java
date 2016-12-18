package net.xalcon.energyconverters.common.energy;

public interface IEnergyBridgeInputAccessProvider
{
	double addEnergyToBridge(double amount, boolean simulate);
}
