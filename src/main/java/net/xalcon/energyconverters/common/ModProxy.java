package net.xalcon.energyconverters.common;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xalcon.energyconverters.common.init.ModBlocks;

public abstract class ModProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.init();
    }

    public abstract void registerItemRenderer(Item item, int meta, String id);

	public abstract void registerItemRenderer(Item item, int meta, String id, String variant);
}
