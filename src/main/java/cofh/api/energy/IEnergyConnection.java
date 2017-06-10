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
package cofh.api.energy;

import net.minecraft.util.EnumFacing;

/**
 * Implement this interface on TileEntities which should connect to energy transportation blocks. This is intended for blocks which generate energy but do not accept it; otherwise just use
 * IEnergyHandler.
 * <p>
 * Note that {@link IEnergyHandler} is an extension of this.
 *
 * @author King Lemming
 */
public interface IEnergyConnection {

    /**
     * Returns TRUE if the TileEntity can connect on a given side.
     */
    boolean canConnectEnergy(EnumFacing from);

}
