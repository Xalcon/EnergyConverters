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
package net.xalcon.energyconverters.common.blocks;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.xalcon.energyconverters.EnergyConvertersMod;
import reborncore.api.power.EnumPowerTier;

public abstract class BlockConverterTechRebornBase extends BlockBase {
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum PowerTierMap implements IStringSerializable {
        LOW(EnumPowerTier.LOW, "low", 0), MEDIUM(EnumPowerTier.MEDIUM, "medium", 1), HIGH(EnumPowerTier.HIGH, "high", 2), EXTREME(EnumPowerTier.EXTREME, "extreme", 3), INSANE(
                EnumPowerTier.INSANE, "insane", 4), INFINITE(EnumPowerTier.INFINITE, "infinite", 5);
        @Getter
        private final EnumPowerTier tier;
        @Getter
        private final String name;
        @Getter
        private final int meta;
    }

    public static final PropertyEnum<PowerTierMap> TYPE = PropertyEnum.create("tier", PowerTierMap.class);

    public BlockConverterTechRebornBase(Material material, String name) {
        super(material, name);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (PowerTierMap t : PowerTierMap.values())
            list.add(new ItemStack(itemIn, 1, t.getMeta()));
    }

    @Override
    public void registerItemModel(ItemBlock itemBlock) {
        for (PowerTierMap t : PowerTierMap.values())
            EnergyConvertersMod.proxy.registerItemRenderer(itemBlock, t.getMeta(), this.internalName, "tier=" + t.getName());
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(TYPE, PowerTierMap.values()[meta]);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TYPE).getMeta();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TYPE);
    }
}
