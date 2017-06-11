package net.xalcon.energyconverters.common.tiles;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.xalcon.energyconverters.common.energy.TeslaEnergyProductionHandler;

import javax.annotation.Nonnull;

public class TileEntityTeslaProducer extends TileEntityEnergyConvertersProducer implements ITickable
{
	private TeslaEnergyProductionHandler teslaProducer = new TeslaEnergyProductionHandler(this);

	@Override
	@Nonnull
	@SuppressWarnings("unchecked")
	public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing)
	{
		if (capability == TeslaCapabilities.CAPABILITY_PRODUCER)
			return (T) this.teslaProducer;

		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing)
	{
		return capability == TeslaCapabilities.CAPABILITY_PRODUCER || super.hasCapability(capability, facing);
	}

	@Override
	public void update()
	{
		if(this.getWorld().isRemote) return;
		for(EnumFacing f : EnumFacing.values())
		{
			long available = (long) this.getBridgeEnergyStored();
			if(available < 1) break;

			TileEntity te = this.getWorld().getTileEntity(pos.offset(f));
			if(te == null) continue;
			if(te.hasCapability(TeslaCapabilities.CAPABILITY_CONSUMER, f.getOpposite()))
			{
				ITeslaConsumer consumer = te.getCapability(TeslaCapabilities.CAPABILITY_CONSUMER, f.getOpposite());
				long accepted = consumer.givePower(available, true);
				long reallyAccepted = consumer.givePower(accepted, false);
				this.retrieveEnergyFromBridge(reallyAccepted, false);
			}
		}
	}
}
