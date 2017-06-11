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

import java.lang.ref.WeakReference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.xalcon.energyconverters.common.init.ModBlocks;

public abstract class TileEntityConverterBase extends TileEntity {
    private WeakReference<TileEntityEnergyBridge> cachedEnergyBridge;

    public TileEntityConverterBase() {
        this.cachedEnergyBridge = new WeakReference<>(null);
    }

    TileEntityEnergyBridge getEnergyBridge() {
        TileEntityEnergyBridge energyBridge = this.cachedEnergyBridge.get();
        if (energyBridge == null || energyBridge.isInvalid()) {
            energyBridge = null;
            for (EnumFacing direction : EnumFacing.VALUES) {
                BlockPos pos = this.pos.offset(direction);
                IBlockState blockState = this.worldObj.getBlockState(pos);
                if (blockState.getBlock() != ModBlocks.EnergyBridge)
                    continue;
                TileEntity te = this.worldObj.getTileEntity(pos);
                if (te == null || !(te instanceof TileEntityEnergyBridge) || te.isInvalid())
                    continue;
                energyBridge = (TileEntityEnergyBridge) te;
                this.cachedEnergyBridge = new WeakReference<>(energyBridge);
            }
        }
        return energyBridge;
    }
}
