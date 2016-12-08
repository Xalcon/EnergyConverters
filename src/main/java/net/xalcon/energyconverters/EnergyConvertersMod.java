package net.xalcon.energyconverters;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xalcon.energyconverters.common.ModProxy;
import net.xalcon.energyconverters.common.init.ModBlocks;
import net.xalcon.energyconverters.common.init.ModTileEntities;

@Mod(modid = EnergyConvertersMod.MOD_ID, version = EnergyConvertersMod.VERSION)
public class EnergyConvertersMod
{
    public static final String MOD_ID = "energyconverters";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "net.xalcon.energyconverters.client.ClientProxy", serverSide = "net.xalcon.energyconverters.server.ServerProxy")
    public static ModProxy Proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.init();
        ModTileEntities.init();
    }
}
