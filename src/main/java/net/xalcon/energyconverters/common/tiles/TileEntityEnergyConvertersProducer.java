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

import net.xalcon.energyconverters.common.energy.IEnergyBridgeOutputAccessProvider;

public abstract class TileEntityEnergyConvertersProducer extends TileEntityConverterBase implements IEnergyBridgeOutputAccessProvider {
    public double retrieveEnergyFromBridge(double maxAmount, boolean simulate) {
        TileEntityEnergyBridge energyBridge = getEnergyBridge();
        if (energyBridge == null)
            return 0;
        return energyBridge.getEnergy(maxAmount, simulate);
    }

    public double getBridgeEnergyStored() {
        TileEntityEnergyBridge energyBridge = getEnergyBridge();
        if (energyBridge == null)
            return 0;
        return energyBridge.getStoredEnergy();
    }

    public double getBridgeEnergyStoredMax() {
        TileEntityEnergyBridge energyBridge = getEnergyBridge();
        if (energyBridge == null)
            return 0;
        return energyBridge.getStoredEnergyMax();
    }
}
