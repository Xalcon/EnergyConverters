package net.xalcon.energyconverters.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEnergyBridge extends TileEntity
{
	private final static double MAX_ENERGY = 1000000;
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

	public double addEnergy(double amount, boolean simulate)
	{
		if(amount + this.energyStored > MAX_ENERGY)
			amount = MAX_ENERGY - this.energyStored;
		if(!simulate)
			this.energyStored += amount;
		return amount;
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
		return MAX_ENERGY;
	}
}
