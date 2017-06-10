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
package net.xalcon.energyconverters.common.tiles;

import ic2.api.energy.EnergyNet;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.info.Info;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Optional;
import net.xalcon.energyconverters.common.Constants;

@Optional.Interface(iface = "ic2.api.energy.tile.IEnergySource", modid = "IC2", striprefs = true)
public class TileEntityProducerEu extends TileEntityEnergyConvertersProducer implements ITickable, IEnergySource {
    private double maxEnergyUnits;
    private boolean addedToNet;
    private int tier;

    public TileEntityProducerEu(int tier) {
        this.tier = tier;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.tier = compound.getInteger("tier");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("tier", this.tier);
        return super.writeToNBT(compound);
    }

    private void onLoaded() {
        System.out.println("onLoad (isRemote: " + this.worldObj.isRemote + ")");
        super.onLoad();
        if (this.addedToNet || FMLCommonHandler.instance().getEffectiveSide().isClient() || !Info.isIc2Available())
            return;
        MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
        this.addedToNet = true;
        this.maxEnergyUnits = EnergyNet.instance.getPowerFromTier(this.getSourceTier());
    }

    @Override
    public void invalidate() {
        System.out.println("invalidate (isRemote: " + this.worldObj.isRemote + ")");
        super.invalidate();
        onChunkUnload();
    }

    @Override
    public void onChunkUnload() {
        System.out.println("OnChunkUnload (isRemote: " + this.worldObj.isRemote + ")");
        super.onChunkUnload();
        if (this.addedToNet && Info.isIc2Available()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            this.addedToNet = false;
        }
    }

    @Override
    public void update() {
        if (this.worldObj.isRemote)
            return;
        if (!addedToNet)
            onLoaded();
    }

    @Optional.Method(modid = "IC2")
    @Override
    public double getOfferedEnergy() {
        return Math.min(getBridgeEnergyStored() / Constants.EU_TO_EC_CONVERSION_FACTOR, this.maxEnergyUnits);
    }

    @Optional.Method(modid = "IC2")
    @Override
    public void drawEnergy(double v) {
        this.retrieveEnergyFromBridge(v * Constants.EU_TO_EC_CONVERSION_FACTOR, false);
    }

    @Optional.Method(modid = "IC2")
    @Override
    public int getSourceTier() {
        return this.tier;
    }

    @Optional.Method(modid = "IC2")
    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor iEnergyAcceptor, EnumFacing enumFacing) {
        return true;
    }
}
