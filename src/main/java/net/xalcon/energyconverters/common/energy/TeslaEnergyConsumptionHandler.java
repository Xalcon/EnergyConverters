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
package net.xalcon.energyconverters.common.energy;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "net.darkhax.tesla.api.ITeslaConsumer", modid = "tesla")
public class TeslaEnergyConsumptionHandler implements ITeslaConsumer {
    private IEnergyBridgeInputAccessProvider energyBridge;

    public TeslaEnergyConsumptionHandler(IEnergyBridgeInputAccessProvider energyBridge) {
        this.energyBridge = energyBridge;
    }

    @Override
    @Optional.Method(modid = "tesla")
    public long givePower(long power, boolean simulated) {
        return (long) energyBridge.addEnergyToBridge(power, simulated);
    }
}
