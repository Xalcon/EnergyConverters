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

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.Optional;
import reborncore.api.IListInfoProvider;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.power.IEnergyInterfaceTile;

@Optional.Interface(iface = "reborncore.api.power.IEnergyInterfaceTile", modid = "reborncore", striprefs = true)
public class TileEntityTechRebornProducer extends TileEntityEnergyConvertersProducer implements ITickable, IEnergyInterfaceTile, IListInfoProvider {
    private EnumPowerTier tier;

    public TileEntityTechRebornProducer() {}

    public TileEntityTechRebornProducer(EnumPowerTier tier) {
        super();
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

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public void update() {
        if (getEnergy() > 0) {
            for (EnumFacing side : EnumFacing.values()) {
                if (this.canProvideEnergy(side)) {
                    TileEntity tile = this.getWorld().getTileEntity(pos.offset(side));
                    if (tile instanceof IEnergyInterfaceTile) {
                        IEnergyInterfaceTile eFace = (IEnergyInterfaceTile) tile;
                        if (eFace.getTier().ordinal() < getTier().ordinal()) {
                            for (int j = 0; j < 2; ++j) {
                                double d3 = (double) pos.getX() + this.getWorld().rand.nextDouble() + (side.getFrontOffsetX() / 2);
                                double d8 = (double) pos.getY() + this.getWorld().rand.nextDouble() + 1;
                                double d13 = (double) pos.getZ() + this.getWorld().rand.nextDouble() + (side.getFrontOffsetZ() / 2);
                                this.getWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, d3, d8, d13, 0.0D, 0.0D, 0.0D);
                            }
                        } else {
                            if (eFace.canAcceptEnergy(side.getOpposite()) && eFace.canAddEnergy(Math.min(getEnergy(), getMaxOutput()))) {
                                eFace.addEnergy(this.useEnergy(Math.min(getEnergy(), getMaxOutput())));
                            }
                        }
                    }
                }
            }
        }
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
        return false;
    }

    @Override
    public double addEnergy(double v) {
        return 0;
    }

    @Override
    public double addEnergy(double v, boolean b) {
        return 0;
    }

    @Override
    public boolean canUseEnergy(double v) {
        return true;
    }

    @Override
    public double useEnergy(double v) {
        return this.useEnergy(v, false);
    }

    @Override
    public double useEnergy(double v, boolean b) {
        TileEntityEnergyBridge bridge = this.getEnergyBridge();
        return bridge == null ? 0 : bridge.getEnergy(v, b);
    }

    @Override
    public boolean canAcceptEnergy(EnumFacing enumFacing) {
        return false;
    }

    @Override
    public boolean canProvideEnergy(EnumFacing enumFacing) {
        return true;
    }

    @Override
    public double getMaxOutput() {
        return this.tier.getMaxOutput();
    }

    @Override
    public double getMaxInput() {
        return 0;
    }

    @Override
    public EnumPowerTier getTier() {
        return this.tier;
    }

    @Override
    public void addInfo(List<String> list, boolean b) {}
}
