package net.xalcon.energyconverters.common.init;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xalcon.energyconverters.common.tiles.*;

public class ModTileEntities
{
	public static void init()
	{
		GameRegistry.registerTileEntity(TileEntityEnergyBridge.class, "tileentity_energy_bridge");
		GameRegistry.registerTileEntity(TileEntityProducerRf.class, "tileentity_producer_rf");
		GameRegistry.registerTileEntity(TileEntityConsumerRf.class, "tileentity_consumer_rf");

		if (Loader.isModLoaded("IC2"))
		{
			GameRegistry.registerTileEntity(TileEntityProducerEu.class, "tileentity_producer_eu");
			GameRegistry.registerTileEntity(TileEntityConsumerEu.class, "tileentity_consumer_eu");
		}

		if (Loader.isModLoaded("tesla"))
		{
			GameRegistry.registerTileEntity(TileEntityTeslaProducer.class, "tileentity_producer_tesla");
			GameRegistry.registerTileEntity(TileEntityTeslaConsumer.class, "tileentity_consumer_tesla");
		}

		if (Loader.isModLoaded("techreborn"))
		{
			GameRegistry.registerTileEntity(TileEntityTechRebornProducer.class, "tileentity_producer_tb");
			GameRegistry.registerTileEntity(TileEntityTechRebornConsumer.class, "tileentity_consumer_tb");
		}
	}
}
