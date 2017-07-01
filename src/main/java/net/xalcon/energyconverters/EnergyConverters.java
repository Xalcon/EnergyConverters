package net.xalcon.energyconverters;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xalcon.energyconverters.common.EnergyConvertersConfig;
import net.xalcon.energyconverters.common.init.ModTileEntities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = EnergyConverters.MOD_ID, version = EnergyConverters.VERSION, guiFactory = EnergyConverters.GUI_FACTORY)
public class EnergyConverters
{
    public static final String MOD_NAME = "EnergyConverters";
    public static final String MOD_ID = "energyconverters";
    public static final String VERSION = "@VERSION@";
    public static final String GROUP = "net.xalcon.energyconverters";
    public static final String GUI_FACTORY = GROUP + ".client.EnergyConvertersGuiFactory";

    public static Logger Log = LogManager.getLogger(MOD_ID);

    private static EnergyConvertersConfig configHandler;

    public static EnergyConvertersConfig getConfig()
    {
        return configHandler;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModTileEntities.init();
        configHandler = new EnergyConvertersConfig(event.getSuggestedConfigurationFile());
    }
}
