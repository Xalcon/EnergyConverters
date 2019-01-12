package net.xalcon.energyconverters;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xalcon.energyconverters.common.EnergyConvertersConfig;
import net.xalcon.energyconverters.common.init.ModTileEntities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = EnergyConverters.MOD_ID,
        version = EnergyConverters.VERSION,
        acceptedMinecraftVersions = "*"
)
public class EnergyConverters
{
    public static final String MOD_ID = "energyconverters";
    public static final String VERSION = "@VERSION@";

    public static Logger Log = LogManager.getLogger(MOD_ID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModTileEntities.init();
    }
}
