package net.xalcon.energyconverters.common.init;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xalcon.energyconverters.common.CreativeTabEnergyConverters;
import net.xalcon.energyconverters.common.blocks.BlockBase;
import net.xalcon.energyconverters.common.blocks.BlockEnergyBridge;

public class ModBlocks
{

    public static BlockEnergyBridge EnergyBridge;

    public static void init()
    {
        EnergyBridge = register(new BlockEnergyBridge());
    }


    private static <T extends BlockBase> T register(T block, ItemBlock itemBlock)
    {
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);
        block.setCreativeTab(CreativeTabEnergyConverters.Instance);
        block.registerItemModel(itemBlock);
        return block;
    }

    private static <T extends BlockBase> T register(T block)
    {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return register(block, itemBlock);
    }
}
