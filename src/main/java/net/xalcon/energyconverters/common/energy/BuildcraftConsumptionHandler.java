package net.xalcon.energyconverters.common.energy;

import buildcraft.api.mj.IMjConnector;
import buildcraft.api.mj.IMjReceiver;
import buildcraft.api.mj.MjAPI;
import buildcraft.api.mj.MjBattery;
import net.minecraftforge.fml.common.Optional;
import net.xalcon.energyconverters.common.EnergyConvertersConfig;

import javax.annotation.Nonnull;

@Optional.Interface(iface = "buildcraft.api.mj.IMjReceiver", modid = "buildcraftenergy")
public class BuildcraftConsumptionHandler implements IMjReceiver
{
	private final static long requestPower = 1_000_000 * MjAPI.MJ;
	private IEnergyBridgeInputAccessProvider energyBridge;

	public BuildcraftConsumptionHandler(IEnergyBridgeInputAccessProvider energyBridge)
	{
		this.energyBridge = energyBridge;
	}

	/**
	 * @return The number of microjoules that this receiver currently wants, and can accept.
	 */
	@Override
	public long getPowerRequested()
	{
		return requestPower;
	}

	/**
	 * Receives power. You are encouraged to either:
	 * <ul>
	 * <li>Use up all power immediately, or when you next tick.
	 * <li>Store all power in something like an {@link MjBattery} for later usage.
	 * <li>Refuse all power (if you have no more work to do or your {@link MjBattery} is full).
	 * </ul>
	 * <p>
	 * Note that callers are NOT expected to call {@link #canReceive()} before calling this - implementors should check
	 * all of the conditions in {@link #canReceive()} before accepting power.
	 *
	 * @param microJoules The number of micro joules to add.
	 * @param simulate    If true then just pretend you received power- don't actually change any of your internal state.
	 * @return The excess power.
	 */
	@Override
	public long receivePower(long microJoules, boolean simulate)
	{
		return (int) this.energyBridge.addEnergyToBridge(microJoules / (double)MjAPI.MJ * EnergyConvertersConfig.mjConversion, simulate);
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
