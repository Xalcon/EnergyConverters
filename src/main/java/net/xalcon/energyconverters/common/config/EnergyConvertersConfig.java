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
package net.xalcon.energyconverters.common.config;

import java.io.File;
import java.util.List;

import lombok.Getter;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;

public class EnergyConvertersConfig {
    private Property propertyEnergyBridgeMax;
    @Getter
    private double energyBridgeMax;

    private Property propertyEuConversion;
    @Getter
    private double euConversion;

    private Property propertyRfConversion;
    @Getter
    private double rfConversion;

    private Configuration config;

    public EnergyConvertersConfig(File file) {
        config = new Configuration(file);
        config.load();

        propertyEnergyBridgeMax = config.get("settings", "energyBridgeMax", 10000.0, "Maximum energy in an Energy Bridge.");
        propertyEuConversion = config.get("settings", "euConversionFactor", 4.0, "EU to Energy Converters internal energy conversion factor.");
        propertyRfConversion = config.get("settings", "rfConversionFactor", 1.0, "RF to Energy Converters internal energy conversion factor.");

        syncConfig();
    }

    public void syncConfig() {
        energyBridgeMax = propertyEnergyBridgeMax.getDouble();
        euConversion = propertyEuConversion.getDouble();
        rfConversion = propertyRfConversion.getDouble();

        if (config.hasChanged())
            config.save();
    }

    public String getString() {
        return config.toString();
    }

    public List<IConfigElement> getConfigElements() {
        return new ConfigElement(config.getCategory("settings")).getChildElements();
    }
}
