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

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.util.EnumFacing;
import net.xalcon.energyconverters.EnergyConvertersMod;
import net.xalcon.energyconverters.common.config.EnergyConvertersConfig;

public class TileEntityConsumerRf extends TileEntityEnergyConvertersConsumer implements IEnergyReceiver {
    private static final EnergyConvertersConfig config = EnergyConvertersMod.getConfig();

    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    @Override
    public int getEnergyStored(EnumFacing from) {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return Integer.MAX_VALUE;
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        return (int) (this.addEnergyToBridge(maxReceive * config.getRfConversion(), simulate) / config.getRfConversion());
    }
}
