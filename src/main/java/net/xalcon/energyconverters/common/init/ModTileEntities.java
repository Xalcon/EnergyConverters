package net.xalcon.energyconverters.common.init;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xalcon.energyconverters.common.blocks.BlockConsumerRf;
import net.xalcon.energyconverters.common.blocks.BlockProducerRf;
import net.xalcon.energyconverters.common.tiles.*;

public class ModTileEntities
{
	public static void init()
	{
		GameRegistry.registerTileEntity(TileEntityEnergyBridge.class, "tileentity_energy_bridge");
		GameRegistry.registerTileEntity(TileEntityProducerFe.class, "tileentity_producer_fe");
		GameRegistry.registerTileEntity(TileEntityConsumerFe.class, "tileentity_consumer_fe");

		if (Loader.isModLoaded("ic2"))
		{
			GameRegistry.registerTileEntity(TileEntityProducerEu.class, "tileentity_producer_eu");
			GameRegistry.registerTileEntity(TileEntityConsumerEu.class, "tileentity_consumer_eu");
		}

		if (Loader.isModLoaded("tesla"))
		{
			GameRegistry.registerTileEntity(TileEntityTeslaProducer.class, "tileentity_producer_tesla");
			GameRegistry.registerTileEntity(TileEntityTeslaConsumer.class, "tileentity_consumer_tesla");
		}

		if(Loader.isModLoaded("redstoneflux"))
		{
			GameRegistry.registerTileEntity(TileEntityProducerRf.class, "tileentity_producer_rf");
			GameRegistry.registerTileEntity(TileEntityConsumerRf.class, "tileentity_consumer_rf");
		}

		/*if (Loader.isModLoaded("buildcraftenergy"))
		{
			GameRegistry.registerTileEntity(TileEntityProducerMj.class, "tileentity_producer_mj");
			GameRegistry.registerTileEntity(TileEntityConsumerMj.class, "tileentity_consumer_mj");
		}*/
	}
}
