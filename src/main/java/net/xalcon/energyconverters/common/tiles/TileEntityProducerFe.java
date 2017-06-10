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

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.xalcon.energyconverters.common.energy.ForgeEnergyProductionHandler;

public class TileEntityProducerFe extends TileEntityEnergyConvertersProducer implements ITickable {
    private ForgeEnergyProductionHandler productionHandler = new ForgeEnergyProductionHandler(this);

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY ? CapabilityEnergy.ENERGY.cast(this.productionHandler) : super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        for (EnumFacing facing : EnumFacing.VALUES) {
            BlockPos pos = this.pos.offset(facing);
            TileEntity te = this.worldObj.getTileEntity(pos);
            if (te == null)
                continue;
            if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
                IEnergyStorage energyStorage = te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
                if (energyStorage != null && energyStorage.canReceive()) {
                    int o = (int) this.getBridgeEnergyStored();
                    int v = energyStorage.receiveEnergy(o, false);
                    this.retrieveEnergyFromBridge(v, false);
                }
            }
        }
    }
}
