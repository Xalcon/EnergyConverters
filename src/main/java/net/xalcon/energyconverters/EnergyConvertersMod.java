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
package net.xalcon.energyconverters;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xalcon.energyconverters.common.ModProxy;
import net.xalcon.energyconverters.common.init.ModBlocks;
import net.xalcon.energyconverters.common.init.ModRecipes;
import net.xalcon.energyconverters.common.init.ModTileEntities;

@Mod(modid = EnergyConvertersMod.MOD_ID, version = EnergyConvertersMod.VERSION)
public class EnergyConvertersMod {
    public static final String MOD_ID = "energyconverters";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(clientSide = "net.xalcon.energyconverters.client.ClientProxy", serverSide = "net.xalcon.energyconverters.server.ServerProxy")
    public static ModProxy Proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.init();
        ModTileEntities.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
    }
}
