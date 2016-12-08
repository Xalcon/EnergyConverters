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

public class TileEntityProducerEu extends TileEntityEnergyConvertersProducer implements ITickable, IEnergySource
{
	private final static double EU_TO_EC_CONVERSION_FACTOR = 4;
	private double maxEnergyUnits;
	private boolean addedToNet;
	private int tier;

	public TileEntityProducerEu()
	{
		this.addedToNet = false;
	}

	public TileEntityProducerEu(int tier) { super(); this.tier = tier; }

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.tier = compound.getInteger("tier");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("tier", this.tier);
		return super.writeToNBT(compound);
	}

	private void onLoaded()
	{
		System.out.println("onLoad (isRemote: "+this.worldObj.isRemote+")");
		super.onLoad();
		if(this.addedToNet || FMLCommonHandler.instance().getEffectiveSide().isClient() || !Info.isIc2Available()) return;
		MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
		this.addedToNet = true;
		this.maxEnergyUnits = EnergyNet.instance.getPowerFromTier(this.getSourceTier());
	}

	@Override
	public void invalidate()
	{
		System.out.println("invalidate (isRemote: "+this.worldObj.isRemote+")");
		super.invalidate();
		onChunkUnload();
	}

	@Override
	public void onChunkUnload()
	{
		System.out.println("OnChunkUnload (isRemote: "+this.worldObj.isRemote+")");
		super.onChunkUnload();
		if (this.addedToNet && Info.isIc2Available())
		{
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.addedToNet = false;
		}
	}

	@Override
	public void update()
	{
		if(this.worldObj.isRemote) return;
		if(!addedToNet) onLoaded();
	}

	@Override
	public double getOfferedEnergy()
	{
		return Math.min(getAvailableEnergyFromBridge() / EU_TO_EC_CONVERSION_FACTOR, this.maxEnergyUnits);
	}

	@Override
	public void drawEnergy(double v)
	{
		this.retrieveEnergyFromBridge(v * EU_TO_EC_CONVERSION_FACTOR, false);
	}

	@Override
	public int getSourceTier()
	{
		return this.tier;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor iEnergyAcceptor, EnumFacing enumFacing)
	{
		return true;
	}
}
