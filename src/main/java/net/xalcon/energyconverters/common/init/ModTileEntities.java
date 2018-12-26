package net.xalcon.energyconverters.common.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xalcon.energyconverters.EnergyConverters;
import net.xalcon.energyconverters.common.blocks.BlockConsumerRf;
import net.xalcon.energyconverters.common.blocks.BlockProducerRf;
import net.xalcon.energyconverters.common.tiles.*;

public class ModTileEntities
{
	public static void init()
	{
		GameRegistry.registerTileEntity(TileEntityEnergyBridge.class, new ResourceLocation(EnergyConverters.MOD_ID, "tileentity_energy_bridge"));
		GameRegistry.registerTileEntity(TileEntityProducerFe.class, new ResourceLocation(EnergyConverters.MOD_ID, "tileentity_producer_fe"));
		GameRegistry.registerTileEntity(TileEntityConsumerFe.class, new ResourceLocation(EnergyConverters.MOD_ID, "tileentity_consumer_fe"));

		if (Loader.isModLoaded("ic2"))
		{
			GameRegistry.registerTileEntity(TileEntityProducerEu.class, new ResourceLocation(EnergyConverters.MOD_ID, "tileentity_producer_eu"));
			GameRegistry.registerTileEntity(TileEntityConsumerEu.class, new ResourceLocation(EnergyConverters.MOD_ID, "tileentity_consumer_eu"));
		}

		if (Loader.isModLoaded("tesla"))
		{
			GameRegistry.registerTileEntity(TileEntityTeslaProducer.class, new ResourceLocation(EnergyConverters.MOD_ID, "tileentity_producer_tesla"));
			GameRegistry.registerTileEntity(TileEntityTeslaConsumer.class, new ResourceLocation(EnergyConverters.MOD_ID, "tileentity_consumer_tesla"));
		}

		if(Loader.isModLoaded("redstoneflux"))
		{
			GameRegistry.registerTileEntity(TileEntityProducerRf.class, new ResourceLocation(EnergyConverters.MOD_ID, "tileentity_producer_rf"));
			GameRegistry.registerTileEntity(TileEntityConsumerRf.class, new ResourceLocation(EnergyConverters.MOD_ID, "tileentity_consumer_rf"));
		}
		
		if (Loader.isModLoaded("buildcraftenergy"))
		{
			GameRegistry.registerTileEntity(TileEntityProducerMj.class, new ResourceLocation(EnergyConverters.MOD_ID, "tileentity_producer_mj"));
			GameRegistry.registerTileEntity(TileEntityConsumerMj.class, new ResourceLocation(EnergyConverters.MOD_ID, "tileentity_consumer_mj"));
		}
		
		if (Loader.isModLoaded("gregtech"))
		{
			GameRegistry.registerTileEntity(TileEntityProducerGTCE.class, new ResourceLocation(EnergyConverters.MOD_ID, "producer_gtce"));
			GameRegistry.registerTileEntity(TileEntityConsumerGTCE.class, new ResourceLocation(EnergyConverters.MOD_ID, "consumer_gtce"));
		}
	}
}
