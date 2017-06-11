package net.xalcon.energyconverters.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.Optional;
import net.xalcon.energyconverters.EnergyConverters;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.power.IEnergyInterfaceTile;

@Optional.Interface(iface = "reborncore.api.power.IEnergyInterfaceTile", modid = "reborncore", striprefs = true)
public class TileEntityTechRebornConsumer extends TileEntityConverterBase implements IEnergyInterfaceTile
{
	private EnumPowerTier tier;

	public TileEntityTechRebornConsumer(EnumPowerTier tier)
	{
		this.tier = tier;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.tier = EnumPowerTier.values()[compound.getInteger("tier")];
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("tier", this.tier.ordinal());
		return super.writeToNBT(compound);
	}

	@Override
	public double getEnergy()
	{
		TileEntityEnergyBridge bridge = this.getEnergyBridge();
		return bridge == null ? 0 : bridge.getStoredEnergy() / EnergyConverters.getConfig().getRfConversion();
	}

	@Override
	public void setEnergy(double v) {}

	@Override
	public double getMaxPower()
	{
		TileEntityEnergyBridge bridge = this.getEnergyBridge();
		return bridge == null ? 0 : bridge.getStoredEnergyMax() / EnergyConverters.getConfig().getRfConversion();
	}

	@Override
	public boolean canAddEnergy(double v)
	{
		return true;
	}

	@Override
	public double addEnergy(double v)
	{
		return this.addEnergy(v, false);
	}

	@Override
	public double addEnergy(double v, boolean simulate)
	{
	    double ratio = EnergyConverters.getConfig().getRfConversion();
		TileEntityEnergyBridge bridge = this.getEnergyBridge();
		return bridge == null ? 0 : bridge.addEnergy(v * ratio, simulate) / ratio;
	}

	@Override
	public boolean canUseEnergy(double v)
	{
		return false;
	}

	@Override
	public double useEnergy(double v)
	{
		return 0;
	}

	@Override
	public double useEnergy(double v, boolean b)
	{
		return 0;
	}

	@Override
	public boolean canAcceptEnergy(EnumFacing enumFacing)
	{
		return true;
	}

	@Override
	public boolean canProvideEnergy(EnumFacing enumFacing)
	{
		return false;
	}

	@Override
	public double getMaxOutput()
	{
		return 0;
	}

	@Override
	public double getMaxInput()
	{
		return this.tier.getMaxInput();
	}

	@Override
	public EnumPowerTier getTier()
	{
		return this.tier;
	}
}
