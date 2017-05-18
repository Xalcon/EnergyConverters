package net.xalcon.energyconverters.common.tiles;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.xalcon.energyconverters.common.energy.ForgeEnergyConsumptionHandler;

import javax.annotation.Nullable;

public class TileEntityConsumerFe extends TileEntityEnergyConvertersConsumer
{
	private ForgeEnergyConsumptionHandler consumptionHandler = new ForgeEnergyConsumptionHandler(this);

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability == CapabilityEnergy.ENERGY || super.hasCapability(capability, facing);
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
	{
		return capability == CapabilityEnergy.ENERGY ? CapabilityEnergy.ENERGY.cast(this.consumptionHandler) : super.getCapability(capability, facing);
	}
}
