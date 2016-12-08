package net.xalcon.energyconverters.common.tiles;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.info.Info;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;


public class TileEntityConsumerEu extends TileEntityEnergyConvertersConsumer implements ITickable, IEnergySink
{
	private final static double EU_TO_EC_CONVERSION_FACTOR = 4;
	private boolean addedToNet;
	private int tier;

	public TileEntityConsumerEu() { }
	public TileEntityConsumerEu(int tier) { this.tier = tier; }

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

	//region ITickable implementations
	@Override
	public void update()
	{
		if(this.worldObj.isRemote) return;
		if(!addedToNet) onLoaded();
	}
	//endregion


	//region IEnergySink implementation
	@Override
	public double getDemandedEnergy()
	{
		return 128;
	}

	@Override
	public int getSinkTier()
	{
		return this.tier;
	}

	@Override
	public double injectEnergy(EnumFacing enumFacing, double amount, double tier)
	{
		// return the amount of energy we didn't consume
		return amount - (this.addEnergyToBridge(amount * EU_TO_EC_CONVERSION_FACTOR, false) / EU_TO_EC_CONVERSION_FACTOR);
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing)
	{
		return true;
	}
	//endregion
}
