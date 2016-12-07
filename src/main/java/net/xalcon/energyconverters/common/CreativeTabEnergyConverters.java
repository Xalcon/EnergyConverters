package net.xalcon.energyconverters.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.xalcon.energyconverters.common.init.ModBlocks;

public class CreativeTabEnergyConverters extends CreativeTabs
{
    public final static CreativeTabEnergyConverters Instance = new CreativeTabEnergyConverters();

    public CreativeTabEnergyConverters()
    {
        super("energy_converters");
    }

    @Override
    public Item getTabIconItem()
    {
        return Item.getItemFromBlock(ModBlocks.EnergyBridge);
    }
}
