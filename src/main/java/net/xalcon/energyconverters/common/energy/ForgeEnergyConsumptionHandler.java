package net.xalcon.energyconverters.common.energy;

import net.minecraftforge.energy.IEnergyStorage;
import net.xalcon.energyconverters.EnergyConverters;

public class ForgeEnergyConsumptionHandler implements IEnergyStorage
{
	private IEnergyBridgeInputAccessProvider energyBridge;

	public ForgeEnergyConsumptionHandler(IEnergyBridgeInputAccessProvider energyBridge)
	{
		this.energyBridge = energyBridge;
	}

	/**
	 * Adds energy to the storage. Returns quantity of energy that was accepted.
	 *
	 * @param maxReceive Maximum amount of energy to be inserted.
	 * @param simulate   If TRUE, the insertion will only be simulated.
	 * @return Amount of energy that was (or would have been, if simulated) accepted by the storage.
	 */
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{
		double ratio = EnergyConverters.getConfig().getRfConversion();
		return (int) (this.energyBridge.addEnergyToBridge(maxReceive * ratio, simulate) / ratio);
	}

	/**
	 * Removes energy from the storage. Returns quantity of energy that was removed.
	 *
	 * @param maxExtract Maximum amount of energy to be extracted.
	 * @param simulate   If TRUE, the extraction will only be simulated.
	 * @return Amount of energy that was (or would have been, if simulated) extracted from the storage.
	 */
	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{
		return 0;
	}

	/**
	 * Returns the amount of energy currently stored.
	 */
	@Override
	public int getEnergyStored()
	{
		return (int) (this.energyBridge.getBridgeEnergyStored() / EnergyConverters.getConfig().getRfConversion());
	}

	/**
	 * Returns the maximum amount of energy that can be stored.
	 */
	@Override
	public int getMaxEnergyStored()
	{
		return (int) (this.energyBridge.getBridgeEnergyStoredMax() / EnergyConverters.getConfig().getRfConversion());
	}

	/**
	 * Returns if this storage can have energy extracted.
	 * If this is false, then any calls to extractEnergy will return 0.
	 */
	@Override
	public boolean canExtract()
	{
		return false;
	}

	/**
	 * Used to determine if this storage can receive energy.
	 * If this is false, then any calls to receiveEnergy will return 0.
	 */
	@Override
	public boolean canReceive()
	{
		return true;
	}
}