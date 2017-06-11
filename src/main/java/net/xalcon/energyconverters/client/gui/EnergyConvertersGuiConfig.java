package net.xalcon.energyconverters.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.xalcon.energyconverters.EnergyConverters;

import java.util.List;

public class EnergyConvertersGuiConfig extends GuiConfig
{
    public EnergyConvertersGuiConfig(GuiScreen parentScreen)
    {
        super(parentScreen, getConfigElements(), EnergyConverters.MOD_ID, false, false, EnergyConverters.MOD_NAME);
    }

    private static List<IConfigElement> getConfigElements()
    {
        return EnergyConverters.getConfig().getConfigElements();
    }
}
