package net.xalcon.energyconverters.common.tiles;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

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
		return (int) this.retrieveEnergyFromBridge(maxExtract, simulate);
	}

	@Override
	public void update()
	{
		for (EnumFacing facing : EnumFacing.VALUES)
		{
			BlockPos pos = this.pos.offset(facing);
			TileEntity te = this.worldObj.getTileEntity(pos);
			if (te instanceof IEnergyReceiver)
			{
				IEnergyReceiver rcv = (IEnergyReceiver) te;
				if (rcv.canConnectEnergy(facing.getOpposite()))
				{
					int o = (int) this.getBridgeEnergyStored();
					int v = rcv.receiveEnergy(facing.getOpposite(), o, false);
					this.retrieveEnergyFromBridge(v, false);
				}
			}
		}
	}
}
