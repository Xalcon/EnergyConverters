package net.xalcon.energyconverters.common.tiles;

import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Optional;
import net.xalcon.energyconverters.EnergyConverters;
import net.xalcon.energyconverters.common.EnergyConvertersConfig;

@Optional.Interface(iface = "cofh.redstoneflux.api.IEnergyProvider", modid = "redstoneflux", striprefs = true)
public class TileEntityProducerRf extends TileEntityEnergyConvertersProducer implements IEnergyProvider, ITickable
{
	@Override
	public boolean canConnectEnergy(EnumFacing from)
	{
		return true;
	}

	@Override
	public int getEnergyStored(EnumFacing from)
	{
		return (int) this.getBridgeEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from)
	{
		return (int) this.getBridgeEnergyStoredMax();
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate)
	{
		return (int)(this.retrieveEnergyFromBridge(maxExtract, simulate) / EnergyConvertersConfig.rfConversion);
	}

	@Override
	public void update()
	{
		if (this.getWorld().isRemote) return;
		double ratio = EnergyConvertersConfig.rfConversion;
		for (EnumFacing facing : EnumFacing.VALUES)
		{
			BlockPos pos = this.pos.offset(facing);
			TileEntity te = this.getWorld().getTileEntity(pos);
			if(te == null) continue;
			if (te instanceof IEnergyReceiver)
			{
				IEnergyReceiver rcv = (IEnergyReceiver) te;
				if (rcv.canConnectEnergy(facing.getOpposite()))
				{
					int o = (int) this.getBridgeEnergyStored();
					double v = rcv.receiveEnergy(facing.getOpposite(), (int) (o / ratio), false) * ratio;
					this.retrieveEnergyFromBridge(v, false);
				}
			}
		}
	}
}
