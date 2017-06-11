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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = EnergyConverters.MOD_ID, version = EnergyConverters.VERSION)
public class EnergyConverters
{
    public static final String MOD_ID = "energyconverters";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "net.xalcon.energyconverters.client.ClientProxy", serverSide = "net.xalcon.energyconverters.server.ServerProxy")
    public static ModProxy Proxy;

    public static Logger Log = LogManager.getLogger(MOD_ID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.init();
        ModTileEntities.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ModRecipes.init();
    }
}
