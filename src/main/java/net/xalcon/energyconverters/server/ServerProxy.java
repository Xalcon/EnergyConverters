package net.xalcon.energyconverters.server;

import net.minecraft.item.Item;
import net.xalcon.energyconverters.common.CommonProxy;

public class ServerProxy extends CommonProxy
{
    @Override
    public void registerItemRenderer(Item item, int meta, String id) { /* NOP */ }

    @Override
    public void registerItemRenderer(Item item, int meta, String id, String variant) { /* NOP */ }
}