package net.xalcon.energyconverters.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.xalcon.energyconverters.client.gui.EnergyConvertersGuiConfig;

import java.util.Set;

public class EnergyConvertersGuiFactory implements IModGuiFactory
{
    @Override
    public void initialize(Minecraft mc) { }

    @Override
    public boolean hasConfigGui()
    {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen)
    {
        return new EnergyConvertersGuiConfig(parentScreen);
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass()
    {
        return EnergyConvertersGuiConfig.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
    {
        return null;
    }
}
