package net.xalcon.energyconverters.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.xalcon.energyconverters.EnergyConvertersMod;
import net.xalcon.energyconverters.common.ModProxy;

public class ClientProxy extends ModProxy
{
    @Override
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(EnergyConvertersMod.MOD_ID + ":" + id, "inventory"));
    }
}
