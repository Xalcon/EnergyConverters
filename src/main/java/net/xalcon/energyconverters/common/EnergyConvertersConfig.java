package net.xalcon.energyconverters.common;

import net.minecraftforge.common.config.*;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.xalcon.energyconverters.EnergyConverters;

import java.io.File;
import java.util.List;

@Mod.EventBusSubscriber(modid = EnergyConverters.MOD_ID)
@Config(modid = EnergyConverters.MOD_ID)
public class EnergyConvertersConfig
{
    @Config.Comment("Maximum amount of energy the bridge can buffer. This needs to be >0 otherwise, the bridge is not able to convert energy")
    @Config.Name("energyBridgeBuffer")
    @Config.RangeInt(min = 0)
    public static double bridgeEnergyBuffer = 10000;

    @Config.Comment("EU (IndustrialCraft2) to Energy Converters internal energy conversion factor. 1 EU is converted into this amount of Energy Converters Energy")
    @Config.Name("euConversionFactor")
    @Config.RangeDouble(min = 0.001)
    public static double ic2Conversion = 4.0;

    @Config.Comment("RF (RedstoneFlux) to Energy Converters internal energy conversion factor. 1 RF is converted into this amount of Energy Converters Energy")
    @Config.Name("rfConversionFactor")
    @Config.RangeDouble(min = 0.001)
    public static double rfConversion = 1.0;

    @Config.Comment("FE (ForgeEnergy) to Energy Converters internal energy conversion factor. 1 FE is converted into this amount of Energy Converters Energy")
    @Config.Name("feConversionFactor")
    @Config.RangeDouble(min = 0.001)
    public static double feConversion = 1.0;

    @Config.Comment("MJ (Buildcraft) to Energy Converters internal energy conversion factor. 1 MJ is converted into this amount of Energy Converters Energy")
    @Config.Name("mjConversionFactor")
    @Config.RangeDouble(min = 0.001)
    public static double mjConversion = 15.0;

    @Config.Comment("Tesla to Energy Converters internal energy conversion factor. 1 Tesla is converted into this amount of Energy Converters Energy")
    @Config.Name("teslaConversionFactor")
    @Config.RangeDouble(min = 0.001)
    public static double teslaConversion = 1.0;

    @Config.Comment("Percentage of energy lost on conversion.")
    @Config.Name("conversionLoss")
    @Config.RangeDouble(min = 0, max = 100)
    public static double conversionLoss = 0;

    @Config.Comment("Controls if hints should be shown in the tooltip for certain problematic converter blocks. To modpack makers: Do not disable this without providing alternative hints.")
    @Config.Name("showInfoTooltips")
    public static boolean showInfoTooltips = true;

    @Mod.EventBusSubscriber
    private static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if(EnergyConverters.MOD_ID.equals(event.getModID()))
                ConfigManager.sync(EnergyConverters.MOD_ID, Config.Type.INSTANCE);
        }
    }
}
