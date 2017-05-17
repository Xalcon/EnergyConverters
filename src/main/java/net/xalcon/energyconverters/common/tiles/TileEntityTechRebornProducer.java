package net.xalcon.energyconverters.common.tiles;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Optional;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.power.IEnergyInterfaceTile;
import reborncore.common.powerSystem.tesla.TeslaManager;

@Optional.Interface(iface = "reborncore.api.power.IEnergyInterfaceTile", modid = "reborncore", striprefs = true)
public class TileEntityTechRebornProducer extends TileEntityEnergyConvertersProducer implements ITickable, IEnergyInterfaceTile
{
	private EnumPowerTier tier;

	public TileEntityTechRebornProducer()
	{
	}

	public TileEntityTechRebornProducer(EnumPowerTier tier)
	{
		super();
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

	/**
	 * Like the old updateEntity(), except more generic.
	 */
	@Override
	public void update()
	{
		if (getEnergy() > 0)
		{ //Tesla or IC2 should handle this if enabled, so only do this without tesla
			for (EnumFacing side : EnumFacing.values())
			{
				if (this.canProvideEnergy(side))
				{
					TileEntity tile = this.getWorld().getTileEntity(pos.offset(side));
					if (tile instanceof IEnergyInterfaceTile)
					{
						IEnergyInterfaceTile eFace = (IEnergyInterfaceTile) tile;
						if (eFace.getTier().ordinal() < getTier().ordinal())
						{
							for (int j = 0; j < 2; ++j)
							{
								double d3 = (double) pos.getX() + this.getWorld().rand.nextDouble() + (side.getFrontOffsetX() / 2);
								double d8 = (double) pos.getY() + this.getWorld().rand.nextDouble() + 1;
								double d13 = (double) pos.getZ() + this.getWorld().rand.nextDouble() + (side.getFrontOffsetZ() / 2);
								this.getWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, d3, d8, d13, 0.0D, 0.0D, 0.0D);
							}
						}
						else
						{
							if (eFace.canAcceptEnergy(side.getOpposite()) && eFace.canAddEnergy(Math.min(getEnergy(), getMaxOutput())))
							{
								eFace.addEnergy(this.useEnergy(Math.min(getEnergy(), getMaxOutput())));
							}
						}
					}
				}
			}
		}
	}

	@Override
	public double getEnergy()
	{
		TileEntityEnergyBridge bridge = this.getEnergyBridge();
		return bridge == null ? 0 : bridge.getStoredEnergy();
	}

	@Override
	public void setEnergy(double v)
	{
	}


	@Override
	public double getMaxPower()
	{
		TileEntityEnergyBridge bridge = this.getEnergyBridge();
		return bridge == null ? 0 : bridge.getStoredEnergyMax();
	}

	@Override
	public boolean canAddEnergy(double v)
	{
		return false;
	}

	@Override
	public double addEnergy(double v)
	{
		return 0;
	}

	@Override
	public double addEnergy(double v, boolean b)
	{
		return 0;
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
		return false;
	}

	@Override
	public boolean canProvideEnergy(EnumFacing enumFacing)
	{
		return true;
	}

	@Override
	public double getMaxOutput()
	{
		return this.tier.getMaxOutput();
	}

	@Override
	public double getMaxInput()
	{
		return 0;
	}

	@Override
	public EnumPowerTier getTier()
	{
		return this.tier;
	}
}
