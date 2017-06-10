/*
 * A mod that allows conversion between EU, RF, Forge Energy, and Tesla.
 * Copyright (C) 2017  Xalcon (https://github.com/Xalcon)
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see LICENSE.md at the root of the project.
 */
package net.xalcon.energyconverters.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.xalcon.energyconverters.common.Constants;

public class TileEntityEnergyBridge extends TileEntity {
    private double energyStored;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.energyStored = compound.getDouble("energy");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setDouble("energy", this.energyStored);
        return super.writeToNBT(compound);
    }

    public double addEnergy(double amount, boolean simulate) {
        if (amount + this.energyStored > Constants.MAX_ENERGY)
            amount = Constants.MAX_ENERGY - this.energyStored;
        if (!simulate)
            this.energyStored += amount;
        return amount;
    }

    public double getEnergy(double maxAmount, boolean simulate) {
        double amount = maxAmount;
        if (this.energyStored - amount < 0)
            amount = this.energyStored;
        if (!simulate)
            this.energyStored -= amount;
        return amount;
    }

    public double getStoredEnergy() {
        return this.energyStored;
    }

    public double getStoredEnergyMax() {
        return Constants.MAX_ENERGY;
    }
}
