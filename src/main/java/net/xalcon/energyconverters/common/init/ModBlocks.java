package net.xalcon.energyconverters.common.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.xalcon.energyconverters.EnergyConverters;
import net.xalcon.energyconverters.common.blocks.*;

@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder(EnergyConverters.MOD_ID)
public class ModBlocks
{
    @GameRegistry.ObjectHolder(BlockEnergyBridge.INTERNAL_NAME)
    private static BlockEnergyBridge energyBridge;

    @GameRegistry.ObjectHolder(BlockProducerEu.INTERNAL_NAME)
    private static BlockProducerEu producerEu;

    @GameRegistry.ObjectHolder(BlockConsumerEu.INTERNAL_NAME)
    private static BlockConsumerEu consumerEu;

    @GameRegistry.ObjectHolder(BlockProducerRf.INTERNAL_NAME)
    private static BlockProducerRf producerRf;

    @GameRegistry.ObjectHolder(BlockConsumerRf.INTERNAL_NAME)
    private static BlockConsumerRf consumerRf;

    @GameRegistry.ObjectHolder(BlockProducerTesla.INTERNAL_NAME)
    private static BlockProducerTesla producerTesla;

    @GameRegistry.ObjectHolder(BlockConsumerTesla.INTERNAL_NAME)
    private static BlockConsumerTesla consumerTesla;

    @GameRegistry.ObjectHolder(BlockProducerFe.INTERNAL_NAME)
    private static BlockProducerFe producerFe;

    @GameRegistry.ObjectHolder(BlockConsumerFe.INTERNAL_NAME)
    private static BlockConsumerFe consumerFe;

    /*@GameRegistry.ObjectHolder(BlockProducerMj.INTERNAL_NAME)
    private static BlockProducerMj producerMj;

    @GameRegistry.ObjectHolder(BlockConsumerMj.INTERNAL_NAME)
    private static BlockConsumerMj consumerMj;*/

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().register(new BlockEnergyBridge());
        event.getRegistry().register(new BlockProducerFe());
        event.getRegistry().register(new BlockConsumerFe());

        if(Loader.isModLoaded("ic2"))
        {
            event.getRegistry().register(new BlockProducerEu());
            event.getRegistry().register(new BlockConsumerEu());
        }

        if(Loader.isModLoaded("tesla"))
        {
            event.getRegistry().register(new BlockProducerTesla());
            event.getRegistry().register(new BlockConsumerTesla());
        }

        if(Loader.isModLoaded("redstoneflux"))
        {
            event.getRegistry().register(new BlockProducerRf());
            event.getRegistry().register(new BlockConsumerRf());
        }

        /*if (Loader.isModLoaded("buildcraftenergy"))
        {
            event.getRegistry().register(new BlockProducerMj());
            event.getRegistry().register(new BlockConsumerMj());
        }*/
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(energyBridge.createItemBlock());
        event.getRegistry().register(producerFe.createItemBlock());
        event.getRegistry().register(consumerFe.createItemBlock());

        if(Loader.isModLoaded("ic2"))
        {
            event.getRegistry().register(producerEu.createItemBlock());
            event.getRegistry().register(consumerEu.createItemBlock());
        }

        if(Loader.isModLoaded("tesla"))
        {
            event.getRegistry().register(consumerTesla.createItemBlock());
            event.getRegistry().register(producerTesla.createItemBlock());
        }

        if(Loader.isModLoaded("redstoneflux"))
        {
            event.getRegistry().register(producerRf.createItemBlock());
            event.getRegistry().register(consumerRf.createItemBlock());
        }

        /*if (Loader.isModLoaded("buildcraftenergy"))
        {
            event.getRegistry().register(producerMj.createItemBlock());
            event.getRegistry().register(consumerMj.createItemBlock());
        }*/
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegisterModels(ModelRegistryEvent event)
    {
        energyBridge.registerItemModel(Item.getItemFromBlock(energyBridge));
        producerFe.registerItemModel(Item.getItemFromBlock(producerFe));
        consumerFe.registerItemModel(Item.getItemFromBlock(consumerFe));

        if(Loader.isModLoaded("ic2"))
        {
            producerEu.registerItemModel(Item.getItemFromBlock(producerEu));
            consumerEu.registerItemModel(Item.getItemFromBlock(consumerEu));
        }

        if(Loader.isModLoaded("tesla"))
        {
            consumerTesla.registerItemModel(Item.getItemFromBlock(consumerTesla));
            producerTesla.registerItemModel(Item.getItemFromBlock(producerTesla));
        }

        if(Loader.isModLoaded("redstoneflux"))
        {
            producerRf.registerItemModel(Item.getItemFromBlock(producerRf));
            consumerRf.registerItemModel(Item.getItemFromBlock(consumerRf));
        }

        /*if (Loader.isModLoaded("buildcraftenergy"))
        {
            producerMj.registerItemModel(Item.getItemFromBlock(producerMj));
            consumerMj.registerItemModel(Item.getItemFromBlock(consumerMj));
        }*/
    }

    public static BlockEnergyBridge getEnergyBridge()
    {
        return energyBridge;
    }
}
