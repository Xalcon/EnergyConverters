package net.xalcon.energyconverters.common.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xalcon.energyconverters.common.tiles.*;

public class ModTileEntities
{
	public static void init()
	{
		GameRegistry.registerTileEntity(TileEntityProducerEu.class, "tileentity_producer_eu");
		GameRegistry.registerTileEntity(TileEntityProducerRf.class, "tileentity_producer_rf");
		GameRegistry.registerTileEntity(TileEntityConsumerEu.class, "tileentity_consumer_eu");
		GameRegistry.registerTileEntity(TileEntityConsumerRf.class, "tileentity_consumer_rf");
		GameRegistry.registerTileEntity(TileEntityEnergyBridge.class, "tileentity_energy_bridge");
	}
}
