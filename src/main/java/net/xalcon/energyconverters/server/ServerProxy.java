package net.xalcon.energyconverters.server;

import net.minecraft.item.Item;
import net.xalcon.energyconverters.common.ModProxy;

public class ServerProxy extends ModProxy
{
    @Override
    public void registerItemRenderer(Item item, int meta, String id) { /* NOP */ }
}
