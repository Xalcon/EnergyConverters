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

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.xalcon.energyconverters.EnergyConvertersMod;
import net.xalcon.energyconverters.common.config.EnergyConvertersConfig;

public class TileEntityProducerRf extends TileEntityEnergyConvertersProducer implements IEnergyProvider, ITickable {
    private static final EnergyConvertersConfig config = EnergyConvertersMod.getConfig();

    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    @Override
    public int getEnergyStored(EnumFacing from) {
        return (int) (this.getBridgeEnergyStored() / config.getRfConversion());
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return (int) (this.getBridgeEnergyStoredMax() / config.getRfConversion());
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        return (int) (this.retrieveEnergyFromBridge(maxExtract * config.getRfConversion(), simulate) / config.getRfConversion());
    }

    @Override
    public void update() {
        for (EnumFacing facing : EnumFacing.VALUES) {
            BlockPos pos = this.pos.offset(facing);
            TileEntity te = this.worldObj.getTileEntity(pos);
            if (te != null && !te.isInvalid() && te instanceof IEnergyReceiver) {
                IEnergyReceiver rcv = (IEnergyReceiver) te;
                if (rcv.canConnectEnergy(facing.getOpposite())) {
                    int o = (int) (this.getBridgeEnergyStored() / config.getRfConversion());
                    int v = rcv.receiveEnergy(facing.getOpposite(), o, false);
                    this.retrieveEnergyFromBridge(v * config.getRfConversion(), false);
                }
            }
        }
    }
}
