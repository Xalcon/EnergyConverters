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

import org.apache.logging.log4j.Logger;

import lombok.Getter;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.xalcon.energyconverters.common.ModProxy;
import net.xalcon.energyconverters.common.config.EnergyConvertersConfig;
import net.xalcon.energyconverters.common.init.ModBlocks;
import net.xalcon.energyconverters.common.init.ModRecipes;
import net.xalcon.energyconverters.common.init.ModTileEntities;

@Mod(modid = EnergyConverters.MOD_ID, name = EnergyConverters.MOD_NAME, version = EnergyConverters.VERSION, guiFactory = "net.xalcon.energyconverters.client.config.EnergyConvertersGUIFactory")
public class EnergyConverters {
    public static final String MOD_ID = "energyconverters";
    public static final String MOD_NAME = "Energy Converters";
    public static final String VERSION = "@VERSION@";

    @Getter
    private static Logger logger;

    @SidedProxy(clientSide = "net.xalcon.energyconverters.client.ClientProxy", serverSide = "net.xalcon.energyconverters.server.ServerProxy")
    @Getter
    private static ModProxy proxy;

    @Getter
    private static EnergyConvertersConfig config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        config = new EnergyConvertersConfig(event.getSuggestedConfigurationFile());

        ModBlocks.init();
        ModTileEntities.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
        proxy.init();
    }

    @SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent event) {
        if (event.getModID().equals(MOD_ID)) {
            config.syncConfig();
        }
    }
}
