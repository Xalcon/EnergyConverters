package net.xalcon.energyconverters.common.tiles;

import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Optional;
import net.xalcon.energyconverters.common.energy.TeslaEnergyConsumptionHandler;

import javax.annotation.Nonnull;

public class TileEntityTeslaConsumer extends TileEntityEnergyConvertersConsumer
{
	private TeslaEnergyConsumptionHandler teslaConsumer = new TeslaEnergyConsumptionHandler(this);

	@Override
	@Nonnull
	@SuppressWarnings("unchecked")
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nonnull EnumFacing facing)
	{
		if (capability == TeslaCapabilities.CAPABILITY_CONSUMER)
			return (T) this.teslaConsumer;

		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nonnull EnumFacing facing)
	{
		return capability == TeslaCapabilities.CAPABILITY_CONSUMER || super.hasCapability(capability, facing);
	}
}
