package net.xalcon.energyconverters.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.xalcon.energyconverters.EnergyConverters;
import net.xalcon.energyconverters.common.EnergyConvertersConfig;

public class TileEntityEnergyBridge extends TileEntity
{
	private double energyStored;

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.energyStored = compound.getDouble("energy");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setDouble("energy", this.energyStored);
		return super.writeToNBT(compound);
	}

	public double addEnergy(double amountIn, boolean simulate)
	{
		double lossRate = 1.0 - (EnergyConvertersConfig.conversionLoss / 100d);
		double amount = amountIn * lossRate;
		double maxBuffer = EnergyConvertersConfig.bridgeEnergyBuffer;
		if(amount + this.energyStored > maxBuffer)
			amount = maxBuffer - this.energyStored;
		if(!simulate)
			this.energyStored += amount;
		return amount / lossRate;
	}

	public double getEnergy(double maxAmount, boolean simulate)
	{
		double amount = maxAmount;
		if(this.energyStored - amount < 0)
			amount = this.energyStored;
		if(!simulate)
			this.energyStored -= amount;
		return amount;
	}

	public double getStoredEnergy()
	{
		return this.energyStored;
	}

	public double getStoredEnergyMax()
	{
		return EnergyConvertersConfig.bridgeEnergyBuffer;
	}
}
