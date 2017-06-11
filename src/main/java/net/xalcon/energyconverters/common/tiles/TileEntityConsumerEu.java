package net.xalcon.energyconverters.common.tiles;

import ic2.api.energy.EnergyNet;
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
import net.minecraftforge.fml.common.Optional;
import net.xalcon.energyconverters.EnergyConverters;

@Optional.Interface(iface = "ic2.api.energy.tile.IEnergySink", modid = "IC2", striprefs = true)
public class TileEntityConsumerEu extends TileEntityEnergyConvertersConsumer implements ITickable, IEnergySink
{
	private boolean addedToNet;
	private int tier;
	private double tierEnergyMax;

	public TileEntityConsumerEu()
	{
	}

	public TileEntityConsumerEu(int tier)
	{
		this.tier = tier;
		this.tierEnergyMax = EnergyNet.instance.getPowerFromTier(this.tier);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.tier = compound.getInteger("tier");
		this.tierEnergyMax = EnergyNet.instance.getPowerFromTier(this.tier);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("tier", this.tier);
		return super.writeToNBT(compound);
	}

	private void onLoaded()
	{
		super.onLoad();
		if (this.addedToNet || FMLCommonHandler.instance().getEffectiveSide().isClient() || !Info.isIc2Available()) return;
		MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
		this.addedToNet = true;
	}

	@Override
	public void invalidate()
	{
		super.invalidate();
		onChunkUnload();
	}

	@Override
	public void onChunkUnload()
	{
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
		if (this.getWorld().isRemote) return;
		if (!addedToNet) onLoaded();
	}
	//endregion


	//region IEnergySink implementation
	@Optional.Method(modid = "IC2")
	@Override
	public double getDemandedEnergy()
	{
		double demand = this.getBridgeEnergyStoredMax() - this.getBridgeEnergyStored();
		if (demand > 0)
		{
			return Math.min(demand, this.tierEnergyMax);
		}
		return 0;
	}

	@Optional.Method(modid = "IC2")
	@Override
	public int getSinkTier()
	{
		return this.tier;
	}

	@Optional.Method(modid = "IC2")
	@Override
	public double injectEnergy(EnumFacing enumFacing, double amount, double tier)
	{
		double ratio = EnergyConverters.getConfig().getIc2Conversion();
		// return the amount of energy we didn't consume
		return amount - (this.addEnergyToBridge(amount * ratio, false) / ratio);
	}

	@Optional.Method(modid = "IC2")
	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing)
	{
		return true;
	}
	//endregion
}
