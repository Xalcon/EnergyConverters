package net.xalcon.energyconverters.common.init;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xalcon.energyconverters.common.CreativeTabEnergyConverters;
import net.xalcon.energyconverters.common.blocks.*;

public class ModBlocks
{
    public static BlockEnergyBridge EnergyBridge;
    public static BlockProducerEu ProducerEu;
    public static BlockConsumerEu ConsumerEu;
    public static BlockProducerRf ProducerRf;
    public static BlockConsumerRf ConsumerRf;

    public static void init()
    {
        EnergyBridge = register(new BlockEnergyBridge());
        ProducerEu = register(new BlockProducerEu());
        BlockConsumerEu block = new BlockConsumerEu();
        ConsumerEu = register(block, new ItemBlockTieredVoltage(block));

        ProducerRf = register(new BlockProducerRf());
        ConsumerRf = register(new BlockConsumerRf());
    }


    private static <T extends BlockBase> T register(T block, ItemBlock itemBlock)
    {
        itemBlock.setRegistryName(block.getRegistryName());
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);
        block.setCreativeTab(CreativeTabEnergyConverters.Instance);
        block.registerItemModel(itemBlock);
        return block;
    }

    private static <T extends BlockBase> T register(T block)
    {
        return register(block, new ItemBlock(block));
    }
}
