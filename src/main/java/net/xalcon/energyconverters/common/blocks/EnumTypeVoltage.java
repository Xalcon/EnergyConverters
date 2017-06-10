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
package net.xalcon.energyconverters.common.blocks;

import net.minecraft.util.IStringSerializable;

public enum EnumTypeVoltage implements IStringSerializable {
    LowVoltage(0, 1, "lv"), MediumVoltage(1, 2, "mv"), HighVoltage(2, 3, "hv"), ExtremeVoltage(3, 4, "ev"), IndustrialVoltage(4, 5, "iv"); // or is it infinite voltage? No idea D: its just
                                                                                                                                           // IV in the wiki

    private int meta;
    private int tier;
    private String name;

    EnumTypeVoltage(int meta, int tier, String name) {
        this.meta = meta;
        this.tier = tier;
        this.name = name;
    }

    public int getMeta() {
        return meta;
    }

    public int getTier() {
        return tier;
    }

    @Override
    public String getName() {
        return name;
    }
}
