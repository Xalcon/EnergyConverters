package net.xalcon.energyconverters.common.tiles;

import buildcraft.api.mj.MjAPI;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.xalcon.energyconverters.common.energy.BuildcraftConsumptionHandler;

import javax.annotation.Nullable;

public class TileEntityConsumerMj extends TileEntityEnergyConvertersConsumer
{
	private BuildcraftConsumptionHandler consumptionHandler = new BuildcraftConsumptionHandler(this);

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability == MjAPI.CAP_CONNECTOR || capability == MjAPI.CAP_RECEIVER || super.hasCapability(capability, facing);
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
	{
		if(capability == MjAPI.CAP_CONNECTOR)
			return MjAPI.CAP_CONNECTOR.cast(this.consumptionHandler);
		if(capability == MjAPI.CAP_RECEIVER)
			return MjAPI.CAP_RECEIVER.cast(this.consumptionHandler);
		return super.getCapability(capability, facing);
	}
}
