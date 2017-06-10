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

import javax.annotation.Nonnull;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.xalcon.energyconverters.common.energy.TeslaEnergyProductionHandler;

public class TileEntityTeslaProducer extends TileEntityEnergyConvertersProducer implements ITickable {
    private TeslaEnergyProductionHandler teslaProducer = new TeslaEnergyProductionHandler(this);

    @Override
    @Nonnull
    @SuppressWarnings("unchecked")
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER)
            return (T) this.teslaProducer;

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
        return capability == TeslaCapabilities.CAPABILITY_PRODUCER || super.hasCapability(capability, facing);
    }

    @Override
    public void update() {
        if (worldObj.isRemote)
            return;
        for (EnumFacing f : EnumFacing.values()) {
            long available = (long) this.getBridgeEnergyStored();
            if (available < 1)
                break;

            TileEntity te = worldObj.getTileEntity(pos.offset(f));
            if (te == null)
                continue;
            if (te.hasCapability(TeslaCapabilities.CAPABILITY_CONSUMER, f.getOpposite())) {
                ITeslaConsumer consumer = te.getCapability(TeslaCapabilities.CAPABILITY_CONSUMER, f.getOpposite());
                long accepted = consumer.givePower(available, true);
                long reallyAccepted = consumer.givePower(accepted, false);
                this.retrieveEnergyFromBridge(reallyAccepted, false);
            }
        }
    }
}
