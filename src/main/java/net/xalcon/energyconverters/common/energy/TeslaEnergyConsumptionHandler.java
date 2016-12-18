package net.xalcon.energyconverters.common.energy;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "net.darkhax.tesla.api.ITeslaConsumer", modid = "tesla")
public class TeslaEnergyConsumptionHandler implements ITeslaConsumer
{
	private IEnergyBridgeInputAccessProvider energyBridge;

	public TeslaEnergyConsumptionHandler(IEnergyBridgeInputAccessProvider energyBridge)
	{
		this.energyBridge = energyBridge;
	}

	@Override
	@Optional.Method(modid = "tesla")
	public long givePower(long power, boolean simulated)
	{
		return (long)energyBridge.addEnergyToBridge(power, simulated);
	}
}
