package net.xalcon.energyconverters.common.energy;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.minecraftforge.fml.common.Optional;
import net.xalcon.energyconverters.EnergyConverters;
import net.xalcon.energyconverters.common.EnergyConvertersConfig;

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
		double ratio = EnergyConvertersConfig.teslaConversion;
		return (long)(energyBridge.addEnergyToBridge(power * ratio, simulated) / ratio);
	}
}
