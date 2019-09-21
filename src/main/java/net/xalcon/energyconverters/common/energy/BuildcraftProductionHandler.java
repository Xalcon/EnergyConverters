package net.xalcon.energyconverters.common.energy;

import buildcraft.api.mj.IMjConnector;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nonnull;

@Optional.Interface(iface = "buildcraft.api.mj.IMjConnector", modid = "buildcraftcore")
public class BuildcraftProductionHandler implements IMjConnector
{
	private IEnergyBridgeOutputAccessProvider energyBridge;

	public BuildcraftProductionHandler(IEnergyBridgeOutputAccessProvider energyBridge)
	{
		this.energyBridge = energyBridge;
	}

	/**
	 * Checks to see if this connector can connect to the other connector. By default this should check that the other
	 * connector is the same power system.
	 *
	 * @param other
	 * @return
	 */
	@Override
	public boolean canConnect(@Nonnull IMjConnector other)
	{
		return true;
	}
}
