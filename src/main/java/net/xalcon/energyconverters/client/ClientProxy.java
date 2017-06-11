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
package net.xalcon.energyconverters.client;

import java.text.NumberFormat;
import java.util.Locale;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.xalcon.energyconverters.EnergyConvertersMod;
import net.xalcon.energyconverters.common.ModProxy;

public class ClientProxy extends ModProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(EnergyConvertersMod.MOD_ID + ":" + id, "inventory"));
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id, String variant) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(EnergyConvertersMod.MOD_ID + ":" + id, variant));
    }

    @Getter
    private NumberFormat formatter;

    @Override
    public void init() {
        formatter = NumberFormat.getInstance(Locale.forLanguageTag(Minecraft.getMinecraft()
                .getLanguageManager().getCurrentLanguage().getLanguageCode()));
    }
}
