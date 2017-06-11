/*
 * A mod that allows conversion between EU, RF, Forge Energy, and Tesla.
 * Copyright (C) 2017  Xalcon (https://github.com/Xalcon)
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see LICENSE.md at the root of the project.
 */
package net.xalcon.energyconverters.common.init;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xalcon.energyconverters.common.CreativeTabEnergyConverters;
import net.xalcon.energyconverters.common.blocks.BlockBase;
import net.xalcon.energyconverters.common.blocks.BlockConsumerEu;
import net.xalcon.energyconverters.common.blocks.BlockConsumerFe;
import net.xalcon.energyconverters.common.blocks.BlockConsumerRf;
import net.xalcon.energyconverters.common.blocks.BlockConsumerTechReborn;
import net.xalcon.energyconverters.common.blocks.BlockConverterEuBase.EnumTypeVoltage;
import net.xalcon.energyconverters.common.blocks.BlockConverterTechRebornBase;
import net.xalcon.energyconverters.common.blocks.BlockEnergyBridge;
import net.xalcon.energyconverters.common.blocks.BlockProducerEu;
import net.xalcon.energyconverters.common.blocks.BlockProducerFe;
import net.xalcon.energyconverters.common.blocks.BlockProducerRf;
import net.xalcon.energyconverters.common.blocks.BlockProducerTechReborn;
import net.xalcon.energyconverters.common.blocks.BlockTeslaConsumer;
import net.xalcon.energyconverters.common.blocks.BlockTeslaProducer;
import net.xalcon.energyconverters.common.blocks.ItemBlockEnumMeta;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModBlocks {
    public static BlockEnergyBridge EnergyBridge;
    public static BlockProducerEu ProducerEu;
    public static BlockConsumerEu ConsumerEu;
    public static BlockProducerRf ProducerRf;
    public static BlockConsumerRf ConsumerRf;
    public static BlockTeslaProducer ProducerTesla;
    public static BlockTeslaConsumer ConsumerTesla;
    public static BlockProducerTechReborn ProducerTechReborn;
    public static BlockConsumerTechReborn ConsumerTechReborn;
    public static BlockProducerFe ProducerFe;
    public static BlockConsumerFe ConsumerFe;

    public static void init() {
        EnergyBridge = register(new BlockEnergyBridge());
        if (Loader.isModLoaded("IC2")) {
            ProducerEu = new BlockProducerEu();
            register(ProducerEu, new ItemBlockEnumMeta<>(ProducerEu, EnumTypeVoltage.values()));
            ConsumerEu = new BlockConsumerEu();
            register(ConsumerEu, new ItemBlockEnumMeta<>(ConsumerEu, EnumTypeVoltage.values()));
        }

        if (Loader.isModLoaded("tesla")) {
            ProducerTesla = register(new BlockTeslaProducer());
            ConsumerTesla = register(new BlockTeslaConsumer());
        }

        if (Loader.isModLoaded("techreborn")) {
            ProducerTechReborn = new BlockProducerTechReborn();
            register(ProducerTechReborn, new ItemBlockEnumMeta<>(ProducerTechReborn, BlockConverterTechRebornBase.PowerTierMap.values()));
            ConsumerTechReborn = new BlockConsumerTechReborn();
            register(ConsumerTechReborn, new ItemBlockEnumMeta<>(ConsumerTechReborn, BlockConverterTechRebornBase.PowerTierMap.values()));
        }

        ProducerRf = register(new BlockProducerRf());
        ConsumerRf = register(new BlockConsumerRf());

        ProducerFe = register(new BlockProducerFe());
        ConsumerFe = register(new BlockConsumerFe());
    }

    private static <T extends BlockBase> T register(T block, ItemBlock itemBlock) {
        itemBlock.setRegistryName(block.getRegistryName());
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);
        block.setCreativeTab(CreativeTabEnergyConverters.INSTANCE);
        block.registerItemModel(itemBlock);
        return block;
    }

    private static <T extends BlockBase> T register(T block) {
        return register(block, new ItemBlock(block));
    }
}
