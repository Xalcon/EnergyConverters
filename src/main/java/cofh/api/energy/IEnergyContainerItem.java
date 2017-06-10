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

import net.minecraft.item.ItemStack;

/**
 * Implement this interface on Item classes that support external manipulation of their internal energy storages.
 * <p>
 * A reference implementation is provided {@link ItemEnergyContainer}.
 *
 * @author King Lemming
 */
public interface IEnergyContainerItem {

    /**
     * Adds energy to a container item. Returns the quantity of energy that was accepted. This should always return 0 if the item cannot be externally charged.
     *
     * @param container ItemStack to be charged.
     * @param maxReceive Maximum amount of energy to be sent into the item.
     * @param simulate If TRUE, the charge will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) received by the item.
     */
    int receiveEnergy(ItemStack container, int maxReceive, boolean simulate);

    /**
     * Removes energy from a container item. Returns the quantity of energy that was removed. This should always return 0 if the item cannot be externally discharged.
     *
     * @param container ItemStack to be discharged.
     * @param maxExtract Maximum amount of energy to be extracted from the item.
     * @param simulate If TRUE, the discharge will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) extracted from the item.
     */
    int extractEnergy(ItemStack container, int maxExtract, boolean simulate);

    /**
     * Get the amount of energy currently stored in the container item.
     */
    int getEnergyStored(ItemStack container);

    /**
     * Get the max amount of energy that can be stored in the container item.
     */
    int getMaxEnergyStored(ItemStack container);

}
