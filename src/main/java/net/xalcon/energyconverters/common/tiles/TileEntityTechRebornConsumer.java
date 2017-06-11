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

import lombok.NoArgsConstructor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.Optional;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.power.IEnergyInterfaceTile;

@NoArgsConstructor // Required to be able to restore tile entity state.
@Optional.Interface(iface = "reborncore.api.power.IEnergyInterfaceTile", modid = "reborncore", striprefs = true)
public class TileEntityTechRebornConsumer extends TileEntityConverterBase implements IEnergyInterfaceTile {
    private EnumPowerTier tier;

    public TileEntityTechRebornConsumer(EnumPowerTier tier) {
        this.tier = tier;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.tier = EnumPowerTier.values()[compound.getInteger("tier")];
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("tier", this.tier.ordinal());
        return super.writeToNBT(compound);
    }

    @Override
    public double getEnergy() {
        TileEntityEnergyBridge bridge = this.getEnergyBridge();
        return bridge == null ? 0 : bridge.getStoredEnergy();
    }

    @Override
    public void setEnergy(double v) {}

    @Override
    public double getMaxPower() {
        TileEntityEnergyBridge bridge = this.getEnergyBridge();
        return bridge == null ? 0 : bridge.getStoredEnergyMax();
    }

    @Override
    public boolean canAddEnergy(double v) {
        return true;
    }

    @Override
    public double addEnergy(double v) {
        return this.addEnergy(v, false);
    }

    @Override
    public double addEnergy(double v, boolean simulate) {
        TileEntityEnergyBridge bridge = this.getEnergyBridge();
        return bridge == null ? 0 : bridge.addEnergy(v, simulate);
    }

    @Override
    public boolean canUseEnergy(double v) {
        return false;
    }

    @Override
    public double useEnergy(double v) {
        return 0;
    }

    @Override
    public double useEnergy(double v, boolean b) {
        return 0;
    }

    @Override
    public boolean canAcceptEnergy(EnumFacing enumFacing) {
        return true;
    }

    @Override
    public boolean canProvideEnergy(EnumFacing enumFacing) {
        return false;
    }

    @Override
    public double getMaxOutput() {
        return 0;
    }

    @Override
    public double getMaxInput() {
        return this.tier.getMaxInput();
    }

    @Override
    public EnumPowerTier getTier() {
        return this.tier;
    }
}
