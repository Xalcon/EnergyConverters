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
package net.xalcon.energyconverters.common.init;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xalcon.energyconverters.common.tiles.TileEntityConsumerEu;
import net.xalcon.energyconverters.common.tiles.TileEntityConsumerFe;
import net.xalcon.energyconverters.common.tiles.TileEntityConsumerRf;
import net.xalcon.energyconverters.common.tiles.TileEntityEnergyBridge;
import net.xalcon.energyconverters.common.tiles.TileEntityProducerEu;
import net.xalcon.energyconverters.common.tiles.TileEntityProducerFe;
import net.xalcon.energyconverters.common.tiles.TileEntityProducerRf;
import net.xalcon.energyconverters.common.tiles.TileEntityTechRebornConsumer;
import net.xalcon.energyconverters.common.tiles.TileEntityTechRebornProducer;
import net.xalcon.energyconverters.common.tiles.TileEntityTeslaConsumer;
import net.xalcon.energyconverters.common.tiles.TileEntityTeslaProducer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityEnergyBridge.class, "tileentity_energy_bridge");
        GameRegistry.registerTileEntity(TileEntityProducerRf.class, "tileentity_producer_rf");
        GameRegistry.registerTileEntity(TileEntityConsumerRf.class, "tileentity_consumer_rf");
        GameRegistry.registerTileEntity(TileEntityProducerFe.class, "tileentity_producer_fe");
        GameRegistry.registerTileEntity(TileEntityConsumerFe.class, "tileentity_consumer_fe");

        if (Loader.isModLoaded("IC2")) {
            GameRegistry.registerTileEntity(TileEntityProducerEu.class, "tileentity_producer_eu");
            GameRegistry.registerTileEntity(TileEntityConsumerEu.class, "tileentity_consumer_eu");
        }

        if (Loader.isModLoaded("tesla")) {
            GameRegistry.registerTileEntity(TileEntityTeslaProducer.class, "tileentity_producer_tesla");
            GameRegistry.registerTileEntity(TileEntityTeslaConsumer.class, "tileentity_consumer_tesla");
        }

        if (Loader.isModLoaded("techreborn")) {
            GameRegistry.registerTileEntity(TileEntityTechRebornProducer.class, "tileentity_producer_tb");
            GameRegistry.registerTileEntity(TileEntityTechRebornConsumer.class, "tileentity_consumer_tb");
        }
    }
}
