package net.xalcon.energyconverters.common.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.xalcon.energyconverters.common.energy.ForgeEnergyProductionHandler;

import javax.annotation.Nullable;

public class TileEntityProducerFe extends TileEntityEnergyConvertersProducer implements ITickable
{
	private ForgeEnergyProductionHandler productionHandler = new ForgeEnergyProductionHandler(this);

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability == CapabilityEnergy.ENERGY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
	{
		return capability == CapabilityEnergy.ENERGY ? CapabilityEnergy.ENERGY.cast(this.productionHandler) : super.getCapability(capability, facing);
	}

	@Override
	public void update()
	{
		if (this.getWorld().isRemote) return;
		for (EnumFacing facing : EnumFacing.VALUES)
		{
			BlockPos pos = this.pos.offset(facing);
			TileEntity te = this.getWorld().getTileEntity(pos);
			if(te == null) continue;
			if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()))
			{
				IEnergyStorage energyStorage = te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
				if (energyStorage != null && energyStorage.canReceive())
				{
					int o = (int) this.getBridgeEnergyStored();
					int v = energyStorage.receiveEnergy(o, false);
					this.retrieveEnergyFromBridge(v, false);
				}
			}
		}
	}
}
