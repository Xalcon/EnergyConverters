package net.xalcon.energyconverters.common;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.xalcon.energyconverters.EnergyConverters;

import java.io.File;
import java.util.List;

public class EnergyConvertersConfig
{
    private final Configuration config;
    private final Property propertyEnergyBridgeMax;
    private final Property propertyEuConversion;
    private final Property propertyRfConversion;
    private final Property propertyConversionLoss;
    private final Property propertyMjConversion;

    private double bridgeEnergyBuffer;
    private double ic2Conversion;
    private double rfConversion;
    private double mjConversion;
    private double conversionLoss;

    public double getBridgeEnergyBuffer()
    {
        return this.bridgeEnergyBuffer;
    }

    public double getIc2Conversion()
    {
        return this.ic2Conversion;
    }

    public double getRfConversion()
    {
        return this.rfConversion;
    }

    public double getMjConversion()
    {
        return this.mjConversion;
    }

    public double getConversionLoss()
    {
        return this.conversionLoss;
    }

    public EnergyConvertersConfig(File file)
    {
        this.config = new Configuration(file);
        this.config.load();

        this.propertyEnergyBridgeMax = config.get("settings", "energyBridgeBuffer", 10000.0, "Maximum amount of energy the bridge can buffer. This needs to be >0 otherwise, the bridge is not able to convert energy");
        this.propertyEuConversion = config.get("settings", "euConversionFactor", 4.0, "EU (IndustrialCraft2) to Energy Converters internal energy conversion factor.");
        this.propertyRfConversion = config.get("settings", "rfConversionFactor", 1.0, "RF (RedstoneFlux and ForgeEnergy) to Energy Converters internal energy conversion factor.");
        this.propertyMjConversion = config.get("settings", "mjConversionFactor", 10.0, "MJ (Buildcraft MJ) to Energy Converters internal energy conversion factor.");
        this.propertyConversionLoss = config.get("settings", "conversionLoss", 0.0, "Percentage of energy lost on conversion.", 0, 100);

        this.syncConfig();
    }

    private void syncConfig()
    {
        this.bridgeEnergyBuffer = propertyEnergyBridgeMax.getDouble();
        this.ic2Conversion = propertyEuConversion.getDouble();
        this.rfConversion = propertyRfConversion.getDouble();
        this.mjConversion = propertyMjConversion.getDouble();
        this.conversionLoss = propertyConversionLoss.getDouble() / 100.0;

        if (this.config.hasChanged())
            this.config.save();
    }

    public String getString()
    {
        return this.config.toString();
    }

    public List<IConfigElement> getConfigElements()
    {
        return new ConfigElement(this.config.getCategory("settings")).getChildElements();
    }

    @Mod.EventBusSubscriber
    private static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if(EnergyConverters.MOD_ID.equals(event.getModID()))
                EnergyConverters.getConfig().syncConfig();
        }
    }
}
